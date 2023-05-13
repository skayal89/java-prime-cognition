package javastreams;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PriorityQueueDemoTest {

    @Spy
    PriorityQueueDemo toTest;

    @BeforeEach
    void setUp() {
//        can't initialize like this because verify() expects Mock/Spy object
//        toTest = new PriorityQueueDemo();
    }

    @Test
    void getKMin(){
        List<Integer> input = List.of(4,3,6,5,1,9);
        int k = 3;
        int res = toTest.getKMin(input, k);
        verify(toTest).buildMinHeap(anyList());
        verify(toTest).validate(any(), eq(k));

        assertEquals(4, res);
    }

    @Test
    void getKMinWithOneElement(){
        List<Integer> input = List.of(2);
        int k = 1;
        int res = toTest.getKMin(input, k);
        verify(toTest).buildMinHeap(anyList());
        verify(toTest).validate(any(), eq(k));

        assertEquals(2, res);
    }

    @Test
    void getKMinWhenKIsGreater(){
        List<Integer> input = List.of(4);
        int k = 3;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            toTest.getKMin(input, k);
            verify(toTest).buildMinHeap(anyList());
            verify(toTest).validate(any(), eq(k));
            verify(toTest, never()).pollKMin(any(), anyInt());
        });

        String expected = "k is larger than input";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void getKMinWhenValidateIsMocked() {
        List<Integer> input = List.of(4);
        int k = 3;

        doReturn(null).when(toTest).buildMinHeap(anyList());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            toTest.getKMin(input, k);
            verify(toTest, times(1)).buildMinHeap(anyList());
            verify(toTest, times(1)).validate(any(), anyInt());
            verify(toTest, never()).pollKMin(any(), anyInt());
        });
        String expected = "k is larger than input";
        assertEquals(expected, exception.getMessage());
    }
}