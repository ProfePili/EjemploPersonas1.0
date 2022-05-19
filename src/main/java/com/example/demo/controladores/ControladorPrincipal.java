package com.example.demo.controladores;

import com.example.demo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ControladorPrincipal { // tengamos controladores que nos devuelven nuestras vistas principales

    // inyecto un título que sea dinámico
    @GetMapping("") 
    public String index(ModelMap vista) {
        vista.addAttribute("titulo", "Aca va un título");
        return "index";
    }
}
