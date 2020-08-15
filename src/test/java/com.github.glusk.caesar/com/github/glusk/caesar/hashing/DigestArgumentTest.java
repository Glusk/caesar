package com.github.glusk.caesar.hashing;

import static org.junit.Assert.assertArrayEquals;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class DigestArgumentTest {
    @Test
    public void properlyEncodesAnUTF8StringArgument() {
        assertArrayEquals(
            "UTF-8 string argument was not properly encoded!",
            new byte[] {97, 98, 99},
            new DigestArgument("abc").asArray()
        );
    }

    @Test
    public void properlyEncodesAnASCIIStringArgument() {
        assertArrayEquals(
            "ASCII string argument was not properly encoded!",
            new byte[] {97, 98, 99},
            new DigestArgument("abc", StandardCharsets.US_ASCII).asArray()
        );
    }

    @Test
    public void properlyEncodesAHexStringArgument() {
        assertArrayEquals(
            "Hex string argument was not properly encoded!",
            new byte[] {10, 11, 12},
            new DigestArgument("0a0b0c", 16).asArray()
        );
    }

    @Test
    public void properlyEncodesABase64StringArgument() {
        assertArrayEquals(
            "Base64 string argument was not properly encoded!",
            new byte[] {10, 11, 12},
            new DigestArgument("CgsM", 64).asArray()
        );
    }
}
