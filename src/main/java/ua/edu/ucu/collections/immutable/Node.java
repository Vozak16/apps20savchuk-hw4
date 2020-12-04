package ua.edu.ucu.collections.immutable;

public class Node {
    public Node next;
    public Object value;

    public Node() {
        this.value = null;
        this.next = null;
    }

    public Node(Object inputData) {
        this.value = inputData;
        this.next = null;
    }
}
