package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList stack;

    public Stack() {
        stack = new ImmutableLinkedList();
    }

    public Stack(ImmutableLinkedList linkedList) {
        stack = linkedList;
    }

    public Object peek() {
        return stack.getFirst();
    }

    public Object pop() {
        Object firstElem = stack.getFirst();
        stack = stack.removeFirst();
        return firstElem;
    }

    public void push(Object e) {
        stack = stack.addFirst(e);
    }
    
}
