package com.example.demo;

import com.example.demo.codigo.ArvoreController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Scanner sc = new Scanner(System.in);
		int chave;

		while(true){
			System.out.println("Digite o valor para a chave:  ");
			chave = sc.nextInt();
			ArvoreController.arvore.inserirNo(chave,"rs");
			System.out.println("---");
		}
	}

}
