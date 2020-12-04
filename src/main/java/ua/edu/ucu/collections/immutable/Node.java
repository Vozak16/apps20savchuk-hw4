package ua.edu.ucu.collections.immutable;

public class Node {
    protected Node next;
    protected Object value;

    public Node() {
        this.value = null;
        this.next = null;
    }

    public Node(Object inputData) {
        this.value = inputData;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public Object getValue() {
        return value;
    }
}
