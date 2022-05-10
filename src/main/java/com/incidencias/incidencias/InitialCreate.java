package com.incidencias.incidencias;

import java.util.ArrayList;

import com.incidencias.incidencias.entity.Permiso;
import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.entity.Rol;
import com.incidencias.incidencias.repository.PermisoRepository;
import com.incidencias.incidencias.repository.ProfesorRepository;
import com.incidencias.incidencias.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitialCreate
    implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private PermisoRepository perRep;
  @Autowired
  private RolRepository rolRep;
  @Autowired
  private ProfesorRepository proRep;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

    Permiso perm = new Permiso();

    perm.setCodigo("codigo");
    perm.setDescripcion("descripcion");

    Rol rol = new Rol();
    rol.setNombre("nombre");
    ArrayList<Permiso> permisos = new ArrayList<Permiso>();
    permisos.add(perm);
    rol.setPermisos(permisos);

    rolRep.save(rol);

    Profesor pro = new Profesor();

    pro.setEmail("root");
    pro.setContrasena("0000");
    pro.setRol(rol);

    proRep.save(pro);
  }

}