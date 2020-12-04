package ua.edu.ucu.collections.iterator;

import ua.edu.ucu.collections.Queue;
import ua.edu.ucu.collections.immutable.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueIterator implements Iterator {

    private Node current;

    public QueueIterator(Queue queue) {

        current = (Node) queue.peek();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Object next() {
        Object data = current.getValue();
        current = current.getNext();
        if (current == null) {
            throw new NoSuchElementException();
        }
        return data;
    }
}
