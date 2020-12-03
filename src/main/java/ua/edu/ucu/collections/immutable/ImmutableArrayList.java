package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {

    private final Object[] array;
    private final int arrayLength;

    public ImmutableArrayList() {
        this.array = new Object[0];
        this.arrayLength = 0;
    }

    public ImmutableArrayList(Object[] inputArray) {
        this.array = inputArray.clone();
        this.arrayLength = inputArray.length;
    }

    public void arrayListIndexOutOfBound(int index) {
        if (index < 0 || index >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public ImmutableArrayList add(Object e) {
        return add(arrayLength, e);
    }
    @Override
    public ImmutableArrayList add(int index, Object e) {
        if (index != arrayLength) {
            arrayListIndexOutOfBound(index);
        }
        Object[] addedArray = new Object[]{e};
        return addAll(index, addedArray);

    }
    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(arrayLength, c);

    }
    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index != arrayLength) {
            arrayListIndexOutOfBound(index);
        }
        Object[] newArray = new Object[arrayLength + c.length];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(c, 0, newArray, index, c.length);
        System.arraycopy(array, index, newArray, index + c.length,
                arrayLength - index);
        return new ImmutableArrayList(newArray);

    }
    @Override
    public Object get(int index) {
        arrayListIndexOutOfBound(index);
        return array[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        arrayListIndexOutOfBound(index);
        Object[] newArray = new Object[arrayLength - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1,
                newArray, index, arrayLength - index - 1);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        arrayListIndexOutOfBound(index);
        Object[] newArray = array.clone();
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        while (i < arrayLength) {
            if (array[i] == e) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return arrayLength;
    }

    @Override
    public ImmutableArrayList clear() {
            return new ImmutableArrayList();
    }
    @Override
    public boolean isEmpty() {
        return arrayLength == 0;
    }

    @Override
    public Object[] toArray() {
        return array.clone();
    }
    
    @Override
    public String toString() {
        return Arrays.toString(array);
        }
}