package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue {

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

    public <T> Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                if (queue.isEmpty()) {
                    throw new NoSuchElementException();
                }

                return (T) dequeue();
            }
        };
    }



}
