package com.example.demo.controladores;

import com.example.demo.entidades.Direccion;
import com.example.demo.servicios.DireccionServicio;
import com.example.demo.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio personaServicio;

    @Autowired
    private DireccionServicio direccionService;
    
    @GetMapping("/registro")
    public String formulario(ModelMap modelo){
        List<Direccion> direcc = direccionService.mostrarTodos();
        modelo.addAttribute("direccion", direcc);
        return "usuario_registro";
    }

    // Con este método vamos a recolectar información de nuestros HTML
    // utilizando el RequestParam los vamos a extraer.
    // si no se puede realizar la instruccion (persistir un regustro)
    // debemos manejar esa excepción para que no se caiga la página
    @PostMapping("/registro") 
    public String guardar(ModelMap modelo, @RequestParam String email, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Date fechaNacimiento, @RequestParam(required = false) Direccion direccion) {
        try {
            personaServicio.guardar(email, nombre, apellido, fechaNacimiento, direccion, Boolean.TRUE);
            modelo.put("exito", "Persona guardada con éxito");
        } catch (Exception e) {
            modelo.put("error", "Error al registrarse");
        }
        return "usuario_registro";
    }
    
}




















//    @GetMapping("/list")
//    public String listar(Model model, @RequestParam(required = false) String q) {
//        if (q != null) {
//            model.addAttribute("usuario", usuarioServicio.listAllById(q));
//        } else {
//            model.addAttribute("usuario", usuarioServicio.listAll());
//        }
//
//        return "usuario-list";
//    }
//
//    @GetMapping("/form")
//    public String crear(Model model, @RequestParam(required = false) String id) {
//        if (id != null) {
//            Optional<Usuario> optional = usuarioServicio.findById(id);
//            if (optional.isPresent()) {
//                model.addAttribute("usuario", optional.get());
//            } else {
//                return "redirect:/usuario/list";
//            }
//        } else {
//            model.addAttribute("usuario", new Usuario());
//        }
//        model.addAttribute("ciudades", ciudadService.listAll());
//        return "usuario-form";
//    }
//
//    @GetMapping("/delete")
//    public String eliminarPersona(@RequestParam(required = true) String id) {
//        personaServicio.deleteById(id);
//        return "redirect:/persona/list";
//    }