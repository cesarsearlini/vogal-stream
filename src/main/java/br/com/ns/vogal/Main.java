/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.vogal;

import br.com.ns.find.VogalFinder;
import br.com.ns.stream.CharStream;
import br.com.ns.stream.Stream;
import java.util.Scanner;

/**
 *
 * @author Cesar Searlini
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                String tecla = sc.next();
                if (!tecla.isEmpty()) {
                    Stream s = new CharStream(tecla);
                    Character foundC = VogalFinder.firstChar(s);
                    System.out.println(foundC);
                }
            }
        }
    }

}
