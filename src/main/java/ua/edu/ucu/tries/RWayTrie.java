package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;

import java.util.ArrayList;

public class RWayTrie implements Trie {

    private static final int R = 26;
    private static final char FIRST_SYMBOL = 'a';
    private Node root = new Node();


    private static class Node {
        private Object value;
        private final Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t.term, t.weight, 0);
    }

    public Node add(Node node, String key, int value, int d) {
        Node x = node;

        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            x.value = value;
            return x;
        }

        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c - FIRST_SYMBOL] =
                add(x.next[c - FIRST_SYMBOL], key, value, d+1);
        return x;
    }

    @Override
    public boolean contains(String word) {

        Node x = search(root, word, 0);
        return x != null;
    }

    public Node search(Node x, String key, int d) {

        if (x == null) {
            return null;
        }

        if (d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return search(x.next[c - FIRST_SYMBOL], key, d+1);
    }

    @Override
    public boolean delete(String word) {
        int previousSize = size();
        root = delete(root, word, 0);
        return size() < previousSize;
    }

    private Node delete(Node node, String word, int d) {

        if (node == null) {
            return null;
        }

        if (d == word.length()) {
            node.value = null;
        }
        else {
            char c = word.charAt(d);
            node.next[c - FIRST_SYMBOL] =
                    delete(node.next[c - FIRST_SYMBOL], word, d+1);
        }

        if (node.value != null) {
            return node;
        }

        for (char i = FIRST_SYMBOL; i < R + FIRST_SYMBOL; i++) {

            if (node.next[i - FIRST_SYMBOL] != null) {
                return node;
            }
        }

        return null;
    }

    public void collect(Node node, String prefix, Queue queue) {

        if (node == null) {
            return;
        }

        if (node.value != null) {
            queue.enqueue(prefix);
        }

        for (char i = FIRST_SYMBOL; i < R + FIRST_SYMBOL; i++) {
            collect(node.next[i - FIRST_SYMBOL], prefix + i, queue);
        }
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {

        Queue queue = new Queue();
        collect(search(root, s, 0), s, queue);

        return queue::iterator;
    }

    @Override
    public int size() {

        Queue queue = new Queue();
        collect(search(root, "", 0), "", queue);

        return queue.size();
    }

}
