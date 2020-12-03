package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

import java.util.Iterator;

public class Queue {

    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList linkedList) {
        queue = linkedList;
    }

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getLast();
    }

    public Object dequeue() {
        Object firstElem = queue.getLast();
        queue = queue.removeLast();
        return firstElem;
    }

    public void enqueue(Object e) {
        queue = queue.addFirst(e);
    }

}
