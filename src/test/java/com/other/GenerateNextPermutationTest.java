package com.other;

import com.other.GenerateNextPermutation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GenerateNextPermutationTest {

    @Spy
    GenerateNextPermutation toTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getNextNumber(){
        Assertions.assertArrayEquals(new int[]{1, 2, 4, 3, 5, 6}, toTest.getNextNumber(new int[]{1, 2, 3, 6, 5, 4}));
    }
}