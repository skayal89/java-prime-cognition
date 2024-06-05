package com.javastreams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringOperationTest {

    @Spy
    StringOperation toTest;

    @Test
    public void firstNonRepeatingCharacters(){
        String input = "geeksforgeeks";
        String res = toTest.firstNonRepeatingCharactersNullSafe(input);
        assertEquals("f", res);
    }

    @Test
    public void firstNonRepeatingCharactersWithOneChar(){
        String input = "g";
        String res = toTest.firstNonRepeatingCharactersNullSafe(input);
        assertEquals("g", res);
    }

    @Test
    public void firstNonRepeatingCharactersWithAllRepeatedChar(){
        String input = "geeksforgeeksfor";
        Exception noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            toTest.firstNonRepeatingCharactersNullSafe(input);
        });
        assertEquals("Non Repeated Char is not present", noSuchElementException.getMessage());
    }

    @Test
    public void firstNonRepeatingCharactersWithNoChar(){
        String input = "";
        Exception noSuchElementException = assertThrows(NoSuchElementException.class, () -> {
            toTest.firstNonRepeatingCharactersNullSafe(input);
        });
        assertEquals("Non Repeated Char is not present", noSuchElementException.getMessage());
    }

    @Test
    public void firstNonRepeatingCharactersWithNullInput(){
        String input = null;
        Exception noSuchElementException = assertThrows(NullPointerException.class, () -> {
            toTest.firstNonRepeatingCharactersNullSafe(input);
        });
//        assertEquals("Non Repeated Char is not present", noSuchElementException.getMessage());
    }
}