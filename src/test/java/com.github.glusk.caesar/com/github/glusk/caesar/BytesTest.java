package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.glusk.caesar.Bytes;

import org.junit.jupiter.api.Test;

public class BytesTest {
    @Test
    public void properlyEncodesAHexString() {
        assertEquals(
            "0A0B0C",
            new Bytes() {
                @Override
                public byte[] asArray() {
                    return new byte[] {10, 11, 12};
                }
            }.asHexString(),
            "Bytes were not properly encoded to a hex string!"
        );
    }
}