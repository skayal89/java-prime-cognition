package com.util;

public interface MyStack<T> {
    void push(T item);
    T pop();
    T peek();
    int size();
    boolean isEmpty();
    boolean isFull();

}
