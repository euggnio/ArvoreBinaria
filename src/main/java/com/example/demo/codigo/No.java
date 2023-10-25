package com.example.demo.codigo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class No {

    @JsonIgnore
    public String valor;
    public int chave;
    public No pai;
    @JsonIgnore
    public No filhoDireito;
    @JsonIgnore
    public No filhoEsquerdo;

    //Para json funcionar é necessário uma lista :/
    public ArrayList<No> filhos;

    @JsonIgnore
    public int balanceamento;

    public No(String valor, int chave) {
        this.valor = valor;
        this.chave = chave;


    }

    public void organizarFilhoLista(){
        this.filhos = new ArrayList<>();
        if(filhoEsquerdo != null) {
            filhos.add(filhoDireito);
        }
        if(filhoEsquerdo != null){
            filhos.add(filhoEsquerdo);
        }
    }

    public boolean possuiFilhos(){
        return filhoEsquerdo != null || filhoDireito != null ;
    }

    @JsonIgnore
    public No getPai() {
        return pai;
    }

    public boolean possuiDoisFilhos(){
        return filhoEsquerdo != null && filhoDireito != null ;
    }

    public boolean noIgualsquerdo(No no){
        return filhoEsquerdo == no;
    }

    @JsonIgnore
    public boolean isFolha(){
        return filhoEsquerdo == null && filhoDireito == null;
    }

    public boolean noIgualDireito(No no){
        return filhoDireito == no;
    }

    public No retorneUnicoFilho(){
        if(filhoEsquerdo != null && filhoDireito == null){
            return  filhoEsquerdo;
        }
        if(filhoDireito != null && filhoEsquerdo == null){
            return  filhoDireito;
        }
        return null;
    }


}
