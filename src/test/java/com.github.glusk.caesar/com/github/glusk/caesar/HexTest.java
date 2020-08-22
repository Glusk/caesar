package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class HexTest {
    @Test
    public void parsesAValidHexString() {
        assertArrayEquals(
            new byte[] {15},
            new Hex("F").asArray()
        );
    }
    @Test
    public void assertsAnEmptyStringIsNotAValidHexString() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Hex("")
        );
    }
}