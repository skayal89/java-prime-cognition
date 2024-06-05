package com.util;

public class LinkedListStack<T> implements MyStack<T> {
    Node<T> head;

    int capacity, size;

    LinkedListStack(int capacity){
        this.capacity = capacity;
        size = 0;
    }

    @Override
    public void push(T item) {
        if(!isFull()){
            Node<T> node = new Node<>(item);

            if(head == null){
                head = node;
            }
            else {
                node.next = head;
                head = node;
            }

            size++;
        }
    }

    @Override
    public T pop() {
        if(!isEmpty()){
            Node<T> item = head;
            head = head.next;
            size--;
            return item.data;
        }
        return null;
    }

    @Override
    public T peek() {
        if(!isEmpty()) {
            return head.data;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    static class Node<T>{
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            next = null;
        }
    }
}
