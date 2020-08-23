package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class PlainTextTest {
    @Test
    public void properlyEncodesAnUTF8String() {
        assertArrayEquals(
            new byte[] {97, 98, 99},
            new PlainText("abc").asArray(),
            "UTF-8 string argument was not properly encoded!"
        );
    }

    @Test
    public void properlyEncodesAnASCIIString() {
        assertArrayEquals(
            new byte[] {97, 98, 99},
            new PlainText("abc", StandardCharsets.US_ASCII).asArray(),
            "ASCII string argument was not properly encoded!"
        );
    }
}
