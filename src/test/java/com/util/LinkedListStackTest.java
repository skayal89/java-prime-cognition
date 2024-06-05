package com.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LinkedListStackTest {

    MyStack<Integer> toTest;

    @BeforeEach
    void setUp() {
        toTest = new LinkedListStack<>(3);
    }

    @Test
    void push() {
        toTest.push(10);
        assertEquals(10, toTest.peek());
        toTest.push(20);
        assertEquals(20, toTest.peek());
        assertFalse(toTest.isEmpty());
    }

    @Test
    void pop() {
        toTest.push(10);
        toTest.push(20);
        assertEquals(20, toTest.pop());
        assertEquals(10, toTest.peek());
        assertFalse(toTest.isEmpty());

        assertEquals(10, toTest.pop());
        assertNull(toTest.peek());
        assertTrue(toTest.isEmpty());
    }

    @Test
    void peek() {
        toTest.push(10);
        assertEquals(10, toTest.peek());
    }

    @Test
    void size() {
        toTest.push(10);
        toTest.push(20);
        assertEquals(2, toTest.size());
    }

    @Test
    void isEmpty() {
        assertTrue(toTest.isEmpty());
        toTest.push(10);
        assertFalse(toTest.isEmpty());
    }

    @Test
    void isFull() {
        toTest.push(10);
        toTest.push(20);
        assertFalse(toTest.isFull());
        toTest.push(30);
        assertTrue(toTest.isFull());
    }
}