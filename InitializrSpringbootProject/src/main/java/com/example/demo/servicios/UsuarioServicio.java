package com.example.demo.servicios;

import com.example.demo.entidades.Direccion;
import com.example.demo.entidades.Usuario;
import com.example.demo.excepciones.ExcepcionPropia;
import com.example.demo.repositorios.UsuarioRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Usuario guardar(String email, String nombre, String apellido, Date fechaNacimiento, Direccion direccion, Boolean estaActivo) throws Exception {

        // VALIDACIONES
        validar(email, nombre, apellido, fechaNacimiento, direccion, estaActivo);

        Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setDireccion(direccion);
        usuario.setEstaActivo(estaActivo);

        return usuarioRepositorio.save(usuario);
    }

    public void validar(String email, String nombre, String apellido, Date fechaNacimiento, Direccion direccion, Boolean estaActivo) throws Exception {

        if (email == null || email.trim().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }

        if (fechaNacimiento == null || fechaNacimiento.toString().trim().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }

        if (direccion == null || direccion.toString().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }

        if (estaActivo == null || estaActivo.toString().isEmpty()) {
            throw new ExcepcionPropia("NO PUEDE SER NULO ESTE VALOR");
        }
    }
}
