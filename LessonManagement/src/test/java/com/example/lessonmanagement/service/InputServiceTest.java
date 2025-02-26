package com.example.lessonmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class InputServiceTest {

    private InputService inputService;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        inputService = new InputService(new Scanner(System.in));
    }

    @Test
    void testReadString() {
        String simulatedInput = "Hello World\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        String result = inputService.readString("Enter a string: ");
        assertEquals("Hello World", result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveDouble_ValidInput() {
        String simulatedInput = "42.5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readPositiveDouble("Enter positive double: ");
        assertEquals(42.5, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveDouble_InvalidThenValidInput() {
        String simulatedInput = "-10\nabc\n50\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readPositiveDouble("Enter positive double: ");
        assertEquals(50, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadDoubleInRange_ValidInput() {
        String simulatedInput = "1.5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readDoubleInRange("Enter value between 1 and 2: ", 1, 2);
        assertEquals(1.5, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadDoubleInRange_InvalidThenValidInput() {
        String simulatedInput = "3\n0.5\n1.8\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readDoubleInRange("Enter value between 1 and 2: ", 1, 2);
        assertEquals(1.8, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadInt_ValidInput() {
        String simulatedInput = "42\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        int result = inputService.readInt("Enter an integer: ");
        assertEquals(42, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadInt_InvalidThenValidInput() {
        String simulatedInput = "abc\nxyz\n15\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        int result = inputService.readInt("Enter an integer: ");
        assertEquals(15, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveDouble_EmptyInputThenValid() {
        String simulatedInput = "\n0\n10\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readPositiveDouble("Enter a positive double: ");
        assertEquals(10.0, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveDouble_SpacesThenValid() {
        String simulatedInput = "   \n-5\n20\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readPositiveDouble("Enter a positive double: ");
        assertEquals(20, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveDouble_SpecialCharsThenValid() {
        String simulatedInput = "@#\nabc\n15.2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readPositiveDouble("Enter positive double: ");
        assertEquals(15.2, result, 0.001);
        System.setIn(originalIn);
    }

    @Test
    void testReadDoubleInRange_ExactMinValue() {
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readDoubleInRange("Enter value between 1 and 2: ", 1, 2);
        assertEquals(1, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadDoubleInRange_ExactMaxValue() {
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        double result = inputService.readDoubleInRange("Enter value between 1 and 2: ", 1, 2);
        assertEquals(2, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadInt_NegativeThenValid() {
        String simulatedInput = "-10\nhello\n25\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        int firstValue = inputService.readInt("Enter an integer: ");
        assertEquals(-10, firstValue);
        int secondValue = inputService.readInt("Enter an integer: ");
        assertEquals(25, secondValue);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveInt_InvalidThenValid() {
        String simulatedInput = "-5\n-1\n10\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        int result = inputService.readPositiveInt("Enter a positive integer: ");
        assertEquals(10, result);
        System.setIn(originalIn);
    }

    @Test
    void testReadPositiveInt_TextThenValid() {
        String simulatedInput = "abc\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        inputService = new InputService(new Scanner(System.in));
        int result = inputService.readPositiveInt("Enter a positive integer: ");
        assertEquals(5, result);
        System.setIn(originalIn);
    }
}
