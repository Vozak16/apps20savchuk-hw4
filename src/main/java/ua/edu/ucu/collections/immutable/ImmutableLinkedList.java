package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {

    static class Node {
        private Node next;
        private Object value;

        public Node() {
            this.value = null;
            this.next = null;
        }

        public Node(Object inputData) {
            this.value = inputData;
            this.next = null;
        }
    }

    private final Node head;
    private final int listLength;

    public ImmutableLinkedList() {
        head = new Node();
        listLength = 0;
    }

    //private void PutNodeAhead()

    public ImmutableLinkedList(Object[] inputArray) {
        head = new Node(inputArray[0]);
        Node current = new Node();
        head.next = current;
        listLength = inputArray.length;
        for (int j = 1; j < listLength; j++) {
            current.value = inputArray[j];
            Node nextNode = new Node();
            current.next = nextNode;
            current = nextNode;
        }
    }

    public void linkedListIndexOutOfBound(int index) {
        if (index < 0 || index >= listLength) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(listLength, e);
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index != listLength) {
            linkedListIndexOutOfBound(index);
        }
        Object[] addedArray = new Object[]{e};
        return addAll(index, addedArray);

    }
    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(listLength, c);

    }
    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index != listLength) {
            linkedListIndexOutOfBound(index);
        }
        Object[] newArray = new Object[listLength + c.length];
        Node current = head;
        for (int i = 0; i < index; i++) {
            newArray[i] = current.value;
            current = current.next;
            
        }
        System.arraycopy(c, 0, newArray, index, c.length);

        for (int i = c.length + index; i < newArray.length; i++) {
            newArray[i] = current.value;
            current = current.next;

        }
        return new ImmutableLinkedList(newArray);

    }
    @Override
    public Object get(int index) {
        linkedListIndexOutOfBound(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        linkedListIndexOutOfBound(index);
        Object[] newArray = new Object[listLength - 1];
        Node current = head;
        for (int i = 0; i < index; i++) {
            newArray[i] = current.value;
            current = current.next;

        }

        current = current.next;

        for (int i = index; i < newArray.length; i++) {
            newArray[i] = current.value;
            current = current.next;
        }

        return new ImmutableLinkedList(newArray);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        linkedListIndexOutOfBound(index);
        Object[] newArray = this.toArray();
        newArray[index] = e;
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node current = head;
        while (i < listLength) {
            if (current.value == e) {
                return i;
            } else {
                i++;
                current = current.next;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return listLength;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }
    @Override
    public boolean isEmpty() {
        return listLength == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[listLength];
        Node current = head;
        for (int i = 0; i < listLength; i++) {
            newArray[i] = current.value;
            current = current.next;

        }
        return newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(listLength - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);

    }
    public ImmutableLinkedList removeLast() {
        return remove(listLength - 1);
    }



}
