package com.incidencias.incidencias;

import java.util.ArrayList;

import com.incidencias.incidencias.entity.Estado;
import com.incidencias.incidencias.entity.Permiso;
import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.entity.Rol;
import com.incidencias.incidencias.entity.TipoHardware;
import com.incidencias.incidencias.repository.EstadoRepository;
import com.incidencias.incidencias.repository.PermisoRepository;
import com.incidencias.incidencias.repository.ProfesorRepository;
import com.incidencias.incidencias.repository.RolRepository;
import com.incidencias.incidencias.repository.TipoHardwareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private TipoHardwareRepository tipoHardwareRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {
        try {
            // AÑADIR TIPOS DE HARDWARE:
            if (tipoHardwareRepository.count() == 0) {
                tipoHardwareRepository.save(new TipoHardware("Otros"));
                tipoHardwareRepository.save(new TipoHardware("Servidor"));
                tipoHardwareRepository.save(new TipoHardware("Ordenador"));
                tipoHardwareRepository.save(new TipoHardware("Monitor"));
                tipoHardwareRepository.save(new TipoHardware("Impresora"));
                tipoHardwareRepository.save(new TipoHardware("Router"));
                tipoHardwareRepository.save(new TipoHardware("Switch"));
                tipoHardwareRepository.save(new TipoHardware("Proyector"));
                tipoHardwareRepository.save(new TipoHardware("Ratón"));
                tipoHardwareRepository.save(new TipoHardware("Teclado"));
                tipoHardwareRepository.save(new TipoHardware("Altavoces"));
            }

            // AÑADIR ESTADOS:
            if (estadoRepository.count() == 0) {
                estadoRepository.save(new Estado("Reportada",
                        "Se ha reportado la incidencia, a la espera de la contestación de Mantenimiento TIC."));
                estadoRepository.save(new Estado("Comunicada",
                        "Se ha asignado su resolución a un profesor o al Servicio de Asistencia Informática SAI."));
                estadoRepository.save(new Estado("En proceso", "Se esta tabajando en la solución de la incidencia."));
                estadoRepository.save(new Estado("Solución inviable", "No es posible resolver la incidencia."));
                estadoRepository.save(new Estado("Solucionada", "Se ha resuelto la incidencia."));
            }

            // AÑADIR PERMISOS:
            if (permisoRepository.count() == 0) {
                Permiso addIncidencias = new Permiso("ADIN", "Permiso para añadir incidencias.");
                Permiso modDelIncidencias = new Permiso("MOIN", "Permiso para modificar y eliminar incidencias.");
                Permiso allTipoHardware = new Permiso("ALTH",
                        "Permiso para añadir, modificar y eliminar tipos de hardware.");
                Permiso allRol = new Permiso("ALRO", "Permiso para añadir, modificar y eliminar roles.");
                Permiso impExp = new Permiso("IEDA", "Permiso para la importación y exportación de datos.");
                Permiso informes = new Permiso("INFO", "Permiso para generación y visualización de informes.");

                permisoRepository.save(addIncidencias);
                permisoRepository.save(modDelIncidencias);
                permisoRepository.save(allTipoHardware);
                permisoRepository.save(allRol);
                permisoRepository.save(impExp);
                permisoRepository.save(informes);

                ArrayList<Permiso> permisos = new ArrayList<Permiso>();

                permisos.add(addIncidencias);
                permisos.add(informes);

                rolRepository.save(new Rol("Profesor", permisos));

                permisos.add(modDelIncidencias);
                permisos.add(allTipoHardware);
                permisos.add(allRol);

                rolRepository.save(new Rol("Directivo", permisos));
                rolRepository.save(new Rol("Mantenimiento TIC", permisos));

                permisos.add(impExp);

                Rol admin = new Rol("Administrador", permisos);
                rolRepository.save(admin);

                // AÑADIR USUARIO ROOT
                profesorRepository.save(new Profesor(1, "root", "root", admin));
            }
        } catch (Exception ex) {
            System.out.print(ex);
            ex.printStackTrace();
        }
    }

}
