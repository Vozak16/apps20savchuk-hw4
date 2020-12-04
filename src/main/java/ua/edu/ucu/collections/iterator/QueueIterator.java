package ua.edu.ucu.collections.iterator;

import ua.edu.ucu.collections.Queue;
import ua.edu.ucu.collections.immutable.Node;

import java.util.Iterator;

public class QueueIterator implements Iterator {

    Node current;

    public QueueIterator(Queue queue) {

        current = (Node) queue.peek();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Object next() {
        Object data = current.value;
        current = current.next;
        return data;
    }
}
