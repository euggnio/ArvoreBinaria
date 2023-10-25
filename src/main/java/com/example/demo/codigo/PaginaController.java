package com.example.demo.codigo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @GetMapping("/pagArvore")
    public String paginaArvore(){
    return "pagArvore";
    }
}
