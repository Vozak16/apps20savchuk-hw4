package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;

public class RWayTrie implements Trie {

    private static final int R = 26;
    private Node root = new Node();
    private int wordNumber = 0;
    private static final char firstSymbol = 'a';

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t.term, 0);
        wordNumber += 1;
    }

    public Node add(Node x, String key, int d) {
        if (x == null) x = new Node();

        if (d == key.length()) {
            x.value = wordNumber;
            return x;
        }

        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c - firstSymbol] = add(x.next[c - firstSymbol], key, d+1);

        return x;
    }

    @Override
    public boolean contains(String word) {
        Node x = search(root, word, 0);
        return x != null;
    }

    public Node search(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        x.next[c - firstSymbol] = search(x.next[c - firstSymbol], key, d+1);

        return x;
    }

    @Override
    public boolean delete(String word) {
        Node tempNode = delete(root, word, 0);
        if (tempNode != null) {
            root = tempNode;
            return true;
        }
        return false;
    }

    private Node delete(Node node, String word, int d) {
        if (node == null) return null;
        if (d == word.length()) node.value = null;
        else {
            char c = word.charAt(d);
            node.next[c] = delete(node.next[c], word, d+1);
        }
        if (node.value != null) {
            return node;
        }
        for (char i = 0; i < R; i++) {
            if (node.next[i] != null) return node;
        }
        return null;
    }

    public void collect(Node node, String prefix, Queue queue) {
        if (node == null) return;
        if (node.value != null) {
            queue.enqueue(prefix);
        }
        for (char i = 0; i < R; i++) {
            collect(node.next[i], prefix + i, queue);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return wordNumber;
    }

}
