package com.example.demo.servicios;

import com.example.demo.entidades.Direccion;
import com.example.demo.entidades.Usuario;
import com.example.demo.excepciones.ExcepcionPropia;
import com.example.demo.repositorios.DireccionRepositorio;
import com.example.demo.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private DireccionRepositorio direccionRepositorio;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Usuario guardar(String email, String nombre, String apellido, Date fechaNacimiento, Direccion direccion) throws Exception {

        // VALIDACIONES
        validar(email, nombre, apellido, fechaNacimiento, direccion);
        
        Usuario usuario = new Usuario();
        
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setDireccion(direccion);
        usuario.setEstaActivo(Boolean.TRUE);
        
        return usuarioRepositorio.save(usuario);
    }
    
    public void validar(String email, String nombre, String apellido, Date fechaNacimiento, Direccion direccion) throws Exception {
        
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
    }
    
    @Transactional //nuevo
    public void modificar(String id, String email, String nombre, String apellido, Date fechaNacimiento, String idDireccion) throws Exception {

        // con el idDireccion busco en la base de datos el objeto direccion para persistir el Usuario
        Direccion direccion = direccionRepositorio.buscarPorId(idDireccion);
        
        //validamos los nuevos valores que paso el usuario a traves de la vista
        validar(email, nombre, apellido, fechaNacimiento, direccion);
        
        // con el id del Usuario, buscamos si existe ese objeto Usuario en nuestra DB.
        //  alojamos el resultado de esta query en un Optional porque puede no existir.
        //  recordar que Optional es una clase que puede ser nula
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Usuario usuario = respuesta.get();
            usuario.setEmail(email);
            usuario.setNombre(nombre);                     
            usuario.setApellido(apellido);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setDireccion(direccion);
            
            usuarioRepositorio.save(usuario);
        } else {
            throw new Exception("No se encontró el usuario solicitado");
        }
        
    }
    //nuevo
    @Transactional(readOnly = true)
    public List<Usuario> mostrarTodos(){
        return usuarioRepositorio.findAll();
    }
}
