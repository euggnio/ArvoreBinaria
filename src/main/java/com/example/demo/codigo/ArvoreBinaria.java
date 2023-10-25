package com.example.demo.codigo;

public class ArvoreBinaria {

    No raiz;
    int quantidadeDeNos;




    public void inserirNo(int chaveNova, String valor){
        No novoNo = new No(valor,chaveNova);
        if(raiz == null){
            raiz = novoNo;
            quantidadeDeNos++;
        }

        else{
            No arvore = raiz;
            Boolean inserido = false;
            while (!inserido){
                //esquerda
                if(arvore.chave >= chaveNova){
                    if(arvore.filhoEsquerdo == null){
                        arvore.filhoEsquerdo = novoNo;
                        novoNo.pai = arvore;
                        quantidadeDeNos++;
                        inserido = true;
                    }
                    else{
                        arvore = arvore.filhoEsquerdo;
                    }
                }
                //direita
                else{
                    if(arvore.filhoDireito == null){
                        arvore.filhoDireito = novoNo;
                        novoNo.pai = arvore;
                        quantidadeDeNos++;
                        inserido = true;
                    }
                    else{
                        arvore = arvore.filhoDireito;
                    }
                }
            }//fimWhile

        }
        balanceamento(raiz);
        balancerarArvore(raiz);
    }//fimInserir

    public String listarArvore(No no){
      String resultado = "";
      if(no != null){
          listarArvore(no.filhoEsquerdo);
            no.organizarFilhoLista();
          System.out.printf(" "+ String.valueOf(no.chave)  + " b:" + no.balanceamento);
          resultado += ", " + no.chave + " b:" + no.balanceamento + ", ";
          listarArvore(no.filhoDireito);
      }
      return  resultado;
    }

    public void deletarNo(int chave){
        No noAtual = buscarNoEmOrdem(raiz, chave);
        if(noAtual == null){
            System.out.println("Nó não existe na arvore para deletar!");
        }
        else{
            if(noAtual.possuiFilhos()){
                if(noAtual.possuiDoisFilhos()){
                    System.out.println("Nó atual tem dois filhos, pegar o nó mais a esquerda do nó da direita.");
                    No noTroca = noAtual.filhoDireito;
                    while (noTroca.filhoEsquerdo != null){
                        noTroca = noTroca.filhoEsquerdo;



                    }
                    System.out.println("Nó Achado para troca: " + noTroca.chave);

                    String valorTroca = noTroca.valor;
                    int chaveTroca = noTroca.chave;


                    deletarNo(noTroca.chave);

                    noAtual.valor = valorTroca;
                    noAtual.chave = chaveTroca;

                    System.out.println(listarArvore(this.raiz));

                }
                else{
                    System.out.println("No tem apenas um filho, substituir pai pelo filho.");
                    if(noAtual.pai.noIgualsquerdo(noAtual)){
                        noAtual.retorneUnicoFilho().pai = noAtual.pai;
                        noAtual.pai.filhoEsquerdo = noAtual.retorneUnicoFilho();

                    }
                    else{
                        noAtual.retorneUnicoFilho().pai = noAtual.pai;
                        noAtual.pai.filhoDireito = noAtual.retorneUnicoFilho();
                    }
                }
            }else{
                System.out.println("Nó para delete não possui filhos, cortar relação com o pai.");
                if(noAtual.pai.filhoDireito == noAtual){
                    noAtual.pai.filhoDireito = null;
                }
                else{
                    noAtual.pai.filhoEsquerdo = null;
                }
            }
        }
    }

    public No buscarNoEmOrdem(No no, int chave) {
        if (no == null) {
            return null; // Não encontrou o nó com a chave
        }
        if (no.chave == chave) {
            return no; // Encontrou o nó com a chave
        } else if (chave < no.chave) {
            return buscarNoEmOrdem(no.filhoEsquerdo, chave);
        } else {
            return buscarNoEmOrdem(no.filhoDireito, chave);
        }
    }

    public int calcularBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        int alturaEsquerda = calcularAltura(no.filhoEsquerdo);
        int alturaDireita = calcularAltura(no.filhoDireito);
        return alturaEsquerda - alturaDireita;
    }

    public int calcularAltura(No no) {
        if (no == null) {
            return 0;
        }

        return Math.max(calcularAltura(no.filhoEsquerdo), calcularAltura(no.filhoDireito)) +1;
    }

    public void balanceamento(No no){
        if(no == null){
            return;
        }
        no.balanceamento = calcularBalanceamento(no);
        balanceamento(no.filhoDireito);
        balanceamento(no.filhoEsquerdo);
    }

    public void balancerarArvore(No no){
        if(no == null){
            return;
        }
        balancerarArvore(no.filhoDireito);
        balancerarArvore(no.filhoEsquerdo);

        if(no.balanceamento <= -2){
            System.out.println("Balancemento -2 " + no.chave);
            if(no.filhoDireito.balanceamento >= 1){
                System.out.println("Balancemento -2 duplo esquerda" + no.chave);
                rotDuplaEsquerda(no);
            }
            else{
                rotSimplesEsquerda(no);
            }
        }
        if(no.balanceamento >= 2){
            System.out.println("Balancemento 2 " + no.chave);
            if(no.filhoEsquerdo.balanceamento <=-1){
                System.out.println("Balancemento +2 duplo direota " + no.chave);
                rotDuplaDireita(no);
            }else{
                rotSimplesDireita(no);
            }
        }



    }


    public void rotSimplesEsquerda(No no) {
        No direito = no.filhoDireito;
        if(no == raiz) {
            no.pai = direito;
            if(direito.filhoEsquerdo != null){
                no.filhoDireito = direito.filhoEsquerdo;
                //no.pai.filhoDireito = direito;
            }else {
                no.filhoDireito = null;
            }
            direito.filhoEsquerdo = no;
            this.raiz = direito;
        }
        else{
            if (no == no.pai.filhoEsquerdo) {
                no.pai.filhoEsquerdo = direito;
            }else{
                no.pai.filhoDireito = direito;
            }
            direito.pai = no.pai;
            no.pai = direito;
            if(direito.filhoEsquerdo != null){
                no.filhoDireito = direito.filhoEsquerdo;
                direito.filhoEsquerdo.pai = no;
            }else {
                no.filhoDireito = null;
            }
            direito.filhoEsquerdo = no;
        }
        balanceamento(raiz);
    }

    public void rotSimplesDireita(No no) {
        No esquerdo = no.filhoEsquerdo;
        if(no == raiz) {
            no.pai = esquerdo;
            if(esquerdo.filhoDireito != null){
                no.filhoEsquerdo = esquerdo.filhoDireito;
            }else {
                no.filhoEsquerdo = null;
            }
            esquerdo.filhoDireito = no;
            this.raiz = esquerdo;
        }
        else{
            if(no.pai.filhoEsquerdo == no){
                no.pai.filhoEsquerdo = esquerdo;
            }else{
                no.pai.filhoDireito = esquerdo;
            }
            esquerdo.pai = no.pai;
            no.pai = esquerdo;

            //tratamento do filho do no a ser trocado
            no.filhoEsquerdo = esquerdo.filhoDireito;
            if(esquerdo.filhoDireito != null){
                esquerdo.filhoDireito.pai = no;
            }

            esquerdo.filhoDireito = no;


        }
        balanceamento(raiz);
    }


    public void rotDuplaDireita(No no){
        rotSimplesEsquerda(no.filhoEsquerdo);
        rotSimplesDireita(no);
    }

    public void rotDuplaEsquerda(No no){
        rotSimplesDireita(no.filhoDireito);
        rotSimplesEsquerda(no);
    }










}
