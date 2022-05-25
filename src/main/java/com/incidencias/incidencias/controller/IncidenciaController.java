package com.incidencias.incidencias.controller;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.incidencias.incidencias.dto.IncidenciaDTO;
import com.incidencias.incidencias.entity.Incidencia;
import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.repository.EstadoRepository;
import com.incidencias.incidencias.repository.IncidenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidencia")
public class IncidenciaController {

    @Autowired
    @Qualifier("IncidenciaRepository")
    private IncidenciaRepository repository;

    @Autowired
    @Qualifier("EstadoRepository")
    private EstadoRepository estadoRepository;

    String host = "mail.javatpoint.com";
    final String emailSender = "iscaincidencias@gmail.com";
    final String passwordSender = "Isca2022.";

    public boolean sendEmail(String receptor, String asunto, String mensaje) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, passwordSender);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));

            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Incidencia incidencia) {
        try {
            repository.save(incidencia);
            return new ResponseEntity<Incidencia>(repository.findById(incidencia.getId()).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            if (incidencia.getTipo_incidencia() != null)
                inc.setTipo_incidencia(incidencia.getTipo_incidencia());
            if (incidencia.getFecha_incidencia() != null)
                inc.setFecha_incidencia(incidencia.getFecha_incidencia());
            if (incidencia.getFecha_introduccion() != null)
                inc.setFecha_introduccion(incidencia.getFecha_introduccion());
            if (incidencia.getAula() != null)
                inc.setAula(incidencia.getAula());
            if (incidencia.getDescripcion() != null)
                inc.setDescripcion(incidencia.getDescripcion());
            if (incidencia.getObservaciones() != null)
                inc.setObservaciones(incidencia.getObservaciones());
            if (incidencia.getFecha_finalizacion() != null)
                inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            if (incidencia.getTiempo_invertido() != null)
                inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setHistorial(incidencia.getHistorial());

            // RELACIONES CONDICIONALES
            if (incidencia.getResponsable() != null)
                inc.setResponsable(incidencia.getResponsable());
            if (incidencia.getReportador() != null)
                inc.setReportador(incidencia.getReportador());

            inc.setIncidencia_sw(incidencia.getIncidencia_sw());
            inc.setIncidencia_hw(incidencia.getIncidencia_hw());

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comunicar/{id}")
    public ResponseEntity<?> updateResponsable(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            String asunto = "";
            String mensaje = "";

            if (incidencia.getResponsable() != null) {

                inc.setHistorial(incidencia.getHistorial());

                inc.setEstado(estadoRepository.findByCodigo("Comunicada").get(0));

                // EMAIL RESPONSABLE
                asunto = "IscaIncidencias - Se te ha asignado una incidencia:";
                mensaje = "Hola, " + incidencia.getResponsable().getNombre() + " "
                        + incidencia.getResponsable().getApellido1()
                        + "\nSe te ha asignado como responsable de la incidencia con código "
                        + incidencia.getCodigoIncidencia()
                        + ".\nVisita IscaIncidencias para mas información.\nSaludos.";

                sendEmail(incidencia.getResponsable().getEmail(), asunto, mensaje);

                // EMAIL REPORTADOR
                asunto = "IscaIncidencias - Se ha asignado un responsable a tu incidencia:";
                mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                        + incidencia.getReportador().getApellido1() + "\nSe ha asignado a "
                        + incidencia.getResponsable().getNombre() + " "
                        + incidencia.getResponsable().getApellido1() + " como responsable de la incidencia con código "
                        + incidencia.getCodigoIncidencia()
                        + ".\nVisita IscaIncidencias para mas información.\nSaludos.";

                sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);

                inc.setResponsable(incidencia.getResponsable());

            } else {
                inc.setHistorial(incidencia.getHistorial());
                inc.setResponsable(null);
                inc.setEstado(estadoRepository.findByCodigo("En proceso").get(0));

                // EMAIL REPORTADOR
                asunto = "IscaIncidencias - Se ha asignado :";
                mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                        + incidencia.getReportador().getApellido1()
                        + "\nSe ha asignado al SAI como responsable de la incidencia con código "
                        + incidencia.getCodigoIncidencia()
                        + ".\nVisita IscaIncidencias para mas información.\nSaludos.";

                sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);
            }
            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> updateEstado(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            if (incidencia.getEstado().getCodigo().equals("Reportada")) {
                inc.setResponsable(null);
                inc.setFecha_finalizacion(null);
                inc.setTiempo_invertido(null);
            } else if (incidencia.getEstado().getCodigo().equals("Comunicada")
                    || incidencia.getEstado().getCodigo().equals("En proceso")) {
                inc.setFecha_finalizacion(null);
            }

            inc.setHistorial(incidencia.getHistorial());
            inc.setEstado(incidencia.getEstado());

            String asunto = "IscaIncidencias - Se ha cambiado el estado de tu incidencia:";
            String mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                    + incidencia.getReportador().getApellido1()
                    + "\nSe ha cambiado el estado de la incidencia con código "
                    + incidencia.getCodigoIncidencia() + " a \"" + incidencia.getEstado().getCodigo()
                    + "\".\nVisita IscaIncidencias para mas información.\nSaludos.";

            sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/trabajar/{id}")
    public ResponseEntity<?> workingOn(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());

            inc.setEstado(estadoRepository.findByCodigo("En proceso").get(0));

            // ENVIAR CORREO
            String asunto = "IscaIncidencias - Se ha cambiado el estado de tu incidencia:";
            String mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                    + incidencia.getReportador().getApellido1()
                    + "\n" + incidencia.getResponsable().getNombre() + " " + incidencia.getResponsable().getApellido1()
                    + " ha empezado a trabajar en la incidencia con código "
                    + incidencia.getCodigoIncidencia() + ".\nVisita IscaIncidencias para mas información.\nSaludos.";

            sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/viable/{id}")
    public ResponseEntity<?> solved(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());
            inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            inc.setObservaciones(incidencia.getObservaciones());

            inc.setEstado(estadoRepository.findByCodigo("Solucionada").get(0));

            // ENVIAR CORREO
            String asunto = "IscaIncidencias - Tu incidencia ha finalizado";
            String mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                    + incidencia.getReportador().getApellido1()
                    + "\nLa incidencia con código " + incidencia.getCodigoIncidencia()
                    + " se ha solventado correctamente.\nVisita IscaIncidencias para mas información.\nSaludos.";

            sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/inviable/{id}")
    public ResponseEntity<?> unsolved(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());
            inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            inc.setObservaciones(incidencia.getObservaciones());

            inc.setEstado(estadoRepository.findByCodigo("Solución inviable").get(0));

            // ENVIAR CORREO
            String asunto = "IscaIncidencias - Tu incidencia ha finalizado";
            String mensaje = "Hola, " + incidencia.getReportador().getNombre() + " "
                    + incidencia.getReportador().getApellido1()
                    + "\nLa incidencia con código " + incidencia.getCodigoIncidencia()
                    + " no ha podido solucionarse, sentimos mucho no haber podido ayudarle.\nVisita IscaIncidencias para mas información.\nSaludos.";

            sendEmail(incidencia.getReportador().getEmail(), asunto, mensaje);

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            ArrayList<IncidenciaDTO> incidencias = new ArrayList<IncidenciaDTO>();

            for (Incidencia inc : repository.findAll())
                incidencias.add(new IncidenciaDTO(inc));

            return new ResponseEntity<Iterable<IncidenciaDTO>>(incidencias, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all/responsable/{id}")
    public ResponseEntity<?> getAllResponsable(@PathVariable Integer id) {
        try {
            ArrayList<IncidenciaDTO> incidencias = new ArrayList<IncidenciaDTO>();

            Profesor responsable = new Profesor();

            responsable.setId(id);

            for (Incidencia inc : repository.findByResponsable(responsable))
                incidencias.add(new IncidenciaDTO(inc));

            return new ResponseEntity<Iterable<IncidenciaDTO>>(incidencias, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all/reportada/{id}")
    public ResponseEntity<?> getAllReportada(@PathVariable Integer id) {
        try {
            ArrayList<IncidenciaDTO> incidencias = new ArrayList<IncidenciaDTO>();

            Profesor reportador = new Profesor();

            reportador.setId(id);

            for (Incidencia inc : repository.findByReportador(reportador))
                incidencias.add(new IncidenciaDTO(inc));

            return new ResponseEntity<Iterable<IncidenciaDTO>>(incidencias, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Incidencia>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/tipo_incidencia/{tipo_incidencia}")
    public ResponseEntity<?> getByTipo(@PathVariable Integer tipo_incidencia) {
        try {
            ArrayList<IncidenciaDTO> incidencias = new ArrayList<IncidenciaDTO>();

            for (Incidencia inc : tipo_incidencia != 0 ? repository.findByTipo(tipo_incidencia) : repository.findAll())
                incidencias.add(new IncidenciaDTO(inc));

            return new ResponseEntity<Iterable<IncidenciaDTO>>(incidencias, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Incidencia incidencia = repository.findById(id).get();
            repository.delete(incidencia);
            return new ResponseEntity<Incidencia>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
