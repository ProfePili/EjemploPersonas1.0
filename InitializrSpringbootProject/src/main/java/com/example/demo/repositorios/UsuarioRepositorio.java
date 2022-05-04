package com.example.demo.repositorios;

import com.example.demo.entidades.Usuario;
import com.example.demo.enumeraciones.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario encontrarPorEmail(@Param("email") String ARGUMENTO);
    
    @Query("SELECT u FROM Usuario u WHERE u.direccion.provincia = :provincia ")
    public List<Usuario> buscarPorProvincia(@Param("provincia") Provincia provincia);
       
}
