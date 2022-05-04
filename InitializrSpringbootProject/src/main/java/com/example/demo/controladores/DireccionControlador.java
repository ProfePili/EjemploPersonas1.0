package com.example.demo.controladores;

import com.example.demo.enumeraciones.Provincia;
import com.example.demo.servicios.DireccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/direccion")
public class DireccionControlador {

    @Autowired
    DireccionServicio direccionServicio;

    @GetMapping("/registro")
    public String formulario(ModelMap vista) {
        vista.addAttribute("provincias", Provincia.values());
        return "direccion_registro";
    }

    @PostMapping("/registro")
    public String registro(ModelMap modelo, @RequestParam Provincia provincia, 
            @RequestParam String calle, @RequestParam Integer numeracion, 
            @RequestParam Boolean esDepartamento) throws Exception {
        try {
            direccionServicio.guardar(provincia, calle, numeracion, esDepartamento);
            modelo.addAttribute("exito", "Registro exitoso: ¡USTED SE HA REGISTRADOOOOO!");
            return "direccion_registro";
        } catch (Exception e) {
            e.printStackTrace();            
            modelo.put("error", "Falló el registro");
            return "direccion_registro";
        }
    }
}
