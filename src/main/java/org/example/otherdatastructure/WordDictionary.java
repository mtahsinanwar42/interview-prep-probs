package org.example.otherdatastructure;

import java.util.*;

public class WordDictionary {

    Node root;

    static class Node {
        Map<Character, Node> children;
        boolean endOfWord = false;

        public Node() {
            children = new HashMap<>();
        }
    }

    public WordDictionary() {
        root = new Node();
    }

    // same as trie
    public void addWord(String word) {
        Node current = root;

        for (char c: word.toCharArray()) {
            current.children.putIfAbsent(c, new Node());
            current = current.children.get(c);
        }

        current.endOfWord = true;
    }

    public boolean search(String word) {
        return searchInNode(word, root, 0);
    }

    private boolean searchInNode(String word, Node node, int index) {
        if (node == null) return false;
        if (index == word.length()) return node.endOfWord;

        char c = word.charAt(index);
        if (c == '.') {
            // try every possible child
            for (Node child : node.children.values()) {
                if (searchInNode(word, child, index + 1)) return true;
            }
            return false;
        } else {
            return searchInNode(word, node.children.get(c), index + 1);
        }
    }
}

