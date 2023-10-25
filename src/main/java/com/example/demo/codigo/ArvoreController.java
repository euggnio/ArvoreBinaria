package com.example.demo.codigo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArvoreController {
   public static ArvoreBinaria arvore = new ArvoreBinaria();
    public No getArvore(){
        arvore.balancerarArvore(arvore.raiz);
        arvore.listarArvore(arvore.raiz);
        return arvore.raiz;

    }
}
