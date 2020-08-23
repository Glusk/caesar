package com.github.glusk.caesar.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class DigestArgumentTest {
    @Test
    public void properlyEncodesAnUTF8StringArgument() {
        assertArrayEquals(
            new byte[] {97, 98, 99},
            new DigestArgument("abc").asArray(),
            "UTF-8 string argument was not properly encoded!"
        );
    }

    @Test
    public void properlyEncodesAnASCIIStringArgument() {
        assertArrayEquals(
            new byte[] {97, 98, 99},
            new DigestArgument("abc", StandardCharsets.US_ASCII).asArray(),
            "ASCII string argument was not properly encoded!"
        );
    }

    @Test
    public void properlyEncodesAByteArrayArgument() {
        assertArrayEquals(
            new byte[] {10, 11, 12},
            new DigestArgument(new byte[] {10, 11, 12}).asArray(),
            "Byte array argument was not properly encoded!"
        );
    }
}
