package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Test
    public void parsesANegativeByteValue()  {
        assertArrayEquals(
            new byte[] {(byte) 0b10000000},
            new Hex("80").asArray()
        );
    }
    @Test
    public void discardsArgumentWhiteSpaces() {
        assertEquals(
            "0A0B0C0D",
            new Hex("0A 0B\t0C\r\n0D").asHexString()
        );
    }
    @Test
    public void discardsArgumentColons() {
        assertEquals(
            "0A0B0C0D",
            new Hex("0A:0B:0C:0D").asHexString()
        );
    }
}
