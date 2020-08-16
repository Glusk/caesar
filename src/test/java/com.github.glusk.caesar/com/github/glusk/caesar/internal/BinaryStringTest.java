package com.github.glusk.caesar.internal;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class BinaryStringTest {
    @Test
    public void properlyEncodesAHexStringArgument() {
        assertArrayEquals(
            "Hex string was not properly parsed!",
            new byte[] {10, 11, 12},
            new BinaryString("0a0b0c", 16).asArray()
        );
    }

    @Test
    public void properlyEncodesABase64StringArgument() {
        assertArrayEquals(
            "Base64 string was not properly parsed!",
            new byte[] {10, 11, 12},
            new BinaryString("CgsM", 64).asArray()
        );
    }  
}