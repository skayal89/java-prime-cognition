package com.util;

public class ArrayStack<T> implements MyStack<T>{
    private int capacity;
    private int top;
    private T[] stack;

    public ArrayStack(int capacity){
        this.capacity = capacity;
        stack = (T[]) new Object[capacity];
        top = 0;
    }
    @Override
    public void push(T item) {
        if(!isFull()){
            stack[top++] = item;
        }
    }

    @Override
    public T pop() {
        T item = null;
        if(!isEmpty()){
            item = stack[top-1];
            top--;
        }
        return item;
    }

    @Override
    public T peek() {
        if(!isEmpty()) {
            return stack[top - 1];
        }
        return null;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == capacity;
    }
}
