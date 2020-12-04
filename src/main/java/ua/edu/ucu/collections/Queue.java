package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.iterator.QueueIterator;

import java.util.Iterator;

public class Queue implements Iterable  {

    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList linkedList) {
        queue = linkedList;
    }

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Object peek() {
        if (queue.size() == 0) {
            return null;
        }
        return queue.getLast();
    }

    public int size() {
        return queue.size();
    }

    public Object dequeue() {
        Object firstElem = queue.getLast();
        queue = queue.removeLast();
        return firstElem;
    }

    public void enqueue(Object e) {
        queue = queue.addFirst(e);
    }


    @Override
    public Iterator iterator() {
        return new QueueIterator(this) {
        };
    }
}
