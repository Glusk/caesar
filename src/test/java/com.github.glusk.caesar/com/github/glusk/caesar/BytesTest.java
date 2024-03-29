package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class BytesTest {
    @Test
    public void properlyEncodesAHexString() {
        assertEquals(
            "0A0B0C",
            Bytes.wrapped(new byte[] {10, 11, 12}).asHexString(),
            "Bytes were not properly encoded to a hex string!"
        );
    }
    @Test
    public void reversesAnEmptySequence() {
        assertArrayEquals(
            new byte[0],
            Bytes.wrapped(new byte[0]).reversed().asArray()
        );
    }
    @Test
    public void reversesASequenceWithOneElement() {
        assertArrayEquals(
            new byte[1],
            Bytes.wrapped(new byte[1]).reversed().asArray()
        );
    }
    @Test
    public void reversesASequence() {
        assertArrayEquals(
            new byte[] {3, 2, 1},
            Bytes.wrapped(new byte[] {1, 2, 3}).reversed().asArray()
        );
    }
}
