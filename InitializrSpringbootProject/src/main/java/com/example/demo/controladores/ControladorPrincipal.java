package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

//    @GetMapping("") // solo lo muestro
//    public String index() {
//        return "index";
//    }    
     
    @GetMapping("")
    public String index(ModelMap modelo) { // lo muestro y le inyecto información dinámica con thymeleaf: "♥PAGINA COMISION 9♥"
        modelo.put("nombreControlador", "♥ PAGINA COMISION 9 ♥");
        return "index";
    }
    
    @GetMapping("registroUsuario")
    public String registroUsuario(){
        return "usuario_registro";
    }
    
    @GetMapping("registroDireccion")
    public String registroDireccion(){
        return "direccion_registro.html";
    }

}

   
