package com.example.demo.entidades;

import com.example.demo.enumeraciones.Provincia;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Direccion {

    // ATRIBUTOS
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    // utilizamos un Enum, ya que las provincias no pueden ser otras que las 23 que existen en Argentina.
    @Enumerated
    private Provincia provincia;
    
    private String calle;
    
    private Integer numeracion;
    
    private Boolean esDepartamento;
    
    private Boolean estaActivo;

    // CONSTRUCTORES
    public Direccion() {
    }

    public Direccion(Provincia provincia, String calle, Integer numeracion, Boolean esDepartamento, Boolean estaActivo) {
        this.provincia = provincia;
        this.calle = calle;
        this.numeracion = numeracion;
        this.esDepartamento = esDepartamento;
        this.estaActivo = estaActivo;
    }

    

    // GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(Integer numeracion) {
        this.numeracion = numeracion;
    }

    public Boolean getEsDepartamento() {
        return esDepartamento;
    }

    public void setEsDepartamento(Boolean esDepartamento) {
        this.esDepartamento = esDepartamento;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    
    // TO STRING
    @Override
    public String toString() {
        return "Direccion{" + "id=" + id + ", provincia=" + provincia + ", calle=" + calle + ", numeracion=" + numeracion + ", esDepartamento=" + esDepartamento + '}';
    }
    
    
}
