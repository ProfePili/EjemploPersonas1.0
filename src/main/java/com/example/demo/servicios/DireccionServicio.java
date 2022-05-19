package com.example.demo.servicios;

import com.example.demo.excepciones.ExcepcionPropia;
import com.example.demo.entidades.Direccion;
import com.example.demo.enumeraciones.Provincia;
import com.example.demo.repositorios.DireccionRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service // PROCESAR INFORMACIÓN - VALIDARLA Y PASARLA AL REPOSITORIO
public class DireccionServicio {

    @Autowired // INSTANCIAR OBJETOS
    private DireccionRepositorio direccionRepositorio;

    @Transactional(propagation = Propagation.NESTED)
    public void guardar(Provincia provincia, String calle, Integer numeracion, Boolean esDepartamento) throws Exception {

        // VALIDACIONES
        validar(provincia, calle, numeracion, esDepartamento);
        
        // SETEO DE ATRIBUTOS
        Direccion direccion = new Direccion();

        direccion.setProvincia(provincia);
        direccion.setCalle(calle);
        direccion.setNumeracion(numeracion);
        direccion.setEsDepartamento(esDepartamento);
        direccion.setEstaActivo(true);

        // ---------- OTRA FORMA DE SETEAR LOS ATRIBUTOS -------- //
        // Direccion direccion2 = new Direccion(provincia, calle, numeracion, esDepartamento, estaActivo);
        
        // PERSISTENCIA DEL OBJETO
        direccionRepositorio.save(direccion);
    }

    public void validar(Provincia provincia, String calle, Integer numeracion, Boolean esDepartamento) throws Exception {
        //VALIDACIONES   
        if (provincia == null || provincia.toString().trim().isEmpty()) {
            throw new ExcepcionPropia("LA PROVINCIA NO PUEDE SER NULA");
        }
        
        if (calle == null || calle.trim().isEmpty()) {
            throw new ExcepcionPropia("CALLE NO VÁLIDA");
        }

        if (numeracion == null || numeracion.toString().trim().isEmpty()) {
            throw new ExcepcionPropia("DEBE INGRESAR UNA NUMERACIÓN");
        }

        if (esDepartamento == null || esDepartamento.toString().isEmpty()) {
            throw new ExcepcionPropia("DEBE DECIR SI ES O NO UN DPTO");
        }
    }

    @Transactional(readOnly = true)
    public List<Direccion> mostrarTodos() {
        return direccionRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Direccion buscarPorId(String id) {
        return direccionRepositorio.buscarPorId(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}) //(LO MISMO QUE NESTED)
    public void borrarPorId(String id) {
        Optional<Direccion> optional = direccionRepositorio.findById(id);

        if (optional.isPresent()) {
            direccionRepositorio.delete(optional.get());
        }
    }
}
