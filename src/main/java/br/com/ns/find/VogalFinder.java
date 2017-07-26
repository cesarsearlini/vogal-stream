/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ns.find;

import br.com.ns.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cesar Searlini
 */
public class VogalFinder {

    private static final String VOGAL_REGEX = "(?i)[aáàãâÁÀÃÂeéêÉÊiíÍoóõôÓÕÔuúÚ]";

    private VogalFinder() {
    }

    public static char firstChar(Stream stream) {
        Map<Character, Boolean> vowelsByOccurrence = new LinkedHashMap<>();
        Map<Character, List<Character>> caracteresAnteriores = new HashMap<>();

        char proximoChar = ' ';
        while (stream.hasNext()) {
            char charAtual = stream.getNext();
            if (isVogal(charAtual)) {
                vowelsByOccurrence.put(charAtual, vowelsByOccurrence.get(charAtual) == null);
            }
            List<Character> listCharacter = caracteresAnteriores.get(charAtual);
            if (listCharacter == null) {
                listCharacter = new ArrayList<>();
                caracteresAnteriores.put(charAtual, listCharacter);
            }
            if (proximoChar != ' ') {
                listCharacter.add(proximoChar);
            }
            proximoChar = charAtual;
        }

        return pesquisaVogal(vowelsByOccurrence, caracteresAnteriores);
    }

    private static Character pesquisaVogal(Map<Character, Boolean> vowelsByOccurrence,
            Map<Character, List<Character>> predecessors) {
        for (Map.Entry<Character, Boolean> vowelOccurrence : vowelsByOccurrence.entrySet()) {
            if (!vowelOccurrence.getValue()) {
                continue;
            }

            for (Character vowelPredecessor : predecessors.get(vowelOccurrence.getKey())) {
                if (isVogal(vowelPredecessor)) {
                    continue;
                }

                for (Character consonantPredecessor : predecessors.get(vowelPredecessor)) {
                    if (isVogal(consonantPredecessor)) {
                        return vowelOccurrence.getKey();
                    }
                }
            }
        }
        return ' ';
    }

    private static void controlaAnteriores(Map<Character, List<Character>> listCaracteresAnteriores,
            char caracaterAtual,
            char ultimoCaracter) {
        List<Character> listCharacter = listCaracteresAnteriores.get(caracaterAtual);
        if (listCharacter == null) {
            listCharacter = new ArrayList<>();
            listCaracteresAnteriores.put(caracaterAtual, listCharacter);
        }
        if (ultimoCaracter != ' ') {
            listCharacter.add(ultimoCaracter);
        }
    }

    private static boolean isVogal(char c) {
        return String.valueOf(c).matches(VOGAL_REGEX);
    }
}
