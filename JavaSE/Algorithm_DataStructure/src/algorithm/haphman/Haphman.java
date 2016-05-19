package algorithm.haphman;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Algorithm Haphman - https://habrahabr.ru/post/144200/
 *
 * @author Andrii_Tkachuk
 * @since 04/14/2015
 */
public class Haphman {

    private final static String BINARY_FORMAT = "00000000";

    private String sourceText;
    private Map<Character, String> haphmanTable;

    public Haphman(String sourceText) {
        this.sourceText = sourceText;
        this.haphmanTable = new LinkedHashMap<Character, String>();
    }

    /**
     * Compress text by Algorithm Haphman
     */
    public void compress() {
        buildCode(buildTree(), "");
    }

    /**
     * Get binary representation of string
     *
     * @return binary string
     */
    public String getBinaryString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sourceText.length(); i++) {
            char symbol = sourceText.charAt(i);
            String code = haphmanTable.get(symbol);
            result.append((BINARY_FORMAT + code).substring(code.length()) + " ");
        }
        return result.toString();
    }

    /**
     * Get Haphman table
     *
     * @return
     */
    public Map<Character, String> getHaphmanTable() {
        return haphmanTable;
    }

    private void buildCode(CharacterFrequencyNode current, String code) {
        if (!current.isLeaf()) {
            buildCode(current.getLeft(), code + "0");
            buildCode(current.getRight(), code + "1");
        } else {
            haphmanTable.put(current.getSymbol(), code);
        }
    }

    private CharacterFrequencyNode buildTree() {
        PriorityQueue<CharacterFrequency> trees = createPriorityQueue();
        while (trees.size() > 1) {

            CharacterFrequencyNode nodeFirst = new CharacterFrequencyNode(trees.poll());
            CharacterFrequencyNode nodeSecond =  new CharacterFrequencyNode(trees.poll());

            trees.offer(new CharacterFrequencyNode(nodeFirst, nodeSecond));
        }
        return (CharacterFrequencyNode) trees.peek();
    }

    private PriorityQueue<CharacterFrequency> createPriorityQueue() {
        PriorityQueue<CharacterFrequency> result = new PriorityQueue<CharacterFrequency>();
        Map<Character, Integer> frequencySymbols = calculateFrequencySymbols();
        for (Map.Entry<Character, Integer> entry : frequencySymbols.entrySet()) {
            result.offer(new CharacterFrequency(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    private Map<Character, Integer> calculateFrequencySymbols() {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (int i = 0; i < sourceText.length(); i++) {
            char symbol = sourceText.charAt(i);
            if (result.containsKey(symbol)) {
                result.put(symbol, result.get(symbol) + 1);
            } else {
                result.put(symbol, 1);
            }
        }
        return result;
    }
}
