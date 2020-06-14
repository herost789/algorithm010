package com.mypractice;

public class MyCircularDeque {
    private int capacity;
    private int[] elements;
    private int front;
    private int rear;

    MyCircularDeque(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Queue size can't below 0");
        }
        //根据元素个数，初始化一个数组
        capacity = k + 1;
        elements = new int[capacity];
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Deque is full");
        }
        front = (front) % capacity;
        elements[front] = value;
        return true;

    }

    public boolean insertLast(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Deque is full");
        }
        elements[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Deque is empty");
        }
        front = (front + 1) % capacity;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Deque is empty");
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Deque is empty");
        }
        return elements[front];
    }

    public int getRear() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Deque is empty");
        }
        return elements[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return front == rear;
    }


    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
