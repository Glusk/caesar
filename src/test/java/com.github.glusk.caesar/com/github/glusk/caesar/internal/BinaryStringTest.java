package com.github.glusk.caesar.internal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class BinaryStringTest {
    @Test
    public void properlyEncodesAHexStringArgument() {
        assertArrayEquals(
            new byte[] {10, 11, 12},
            new BinaryString("0a0b0c", 16).asArray(),
            "Hex string was not properly parsed!"
        );
    }

    @Test
    public void properlyEncodesABase64StringArgument() {
        assertArrayEquals(
            new byte[] {10, 11, 12},
            new BinaryString("CgsM", 64).asArray(),
            "Base64 string was not properly parsed!"
        );
    }  
}