/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.stream;

/**
 *
 * @author Cesar Searlini
 */
public class CharStream implements Stream {

    private final String stream;
    private int posicao;

    public CharStream(String frase) {
        if (frase == null || frase.isEmpty()) {
            throw new RuntimeException("Frase Não pode ser numa ou vazia.");
        }
        this.stream = frase;
    }

    @Override
    public char getNext() {
        if (!hasNext()) {
            throw new RuntimeException("Termino da execução.");
        }
        return stream.charAt(posicao++);
    }

    @Override
    public boolean hasNext() {
        return posicao < stream.length();
    }

    @Override
    public String toString() {
        return stream;
    }
}
