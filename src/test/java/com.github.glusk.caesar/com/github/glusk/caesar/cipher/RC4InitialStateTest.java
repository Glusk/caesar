package com.github.glusk.caesar.cipher;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.Bytes;

public class RC4InitialStateTest {
    @Test
    public void throwsIfKeyLengthIsZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new RC4InitialState(
                    Bytes.wrapped(new byte[0])
                ).asArray();
            }
        );
    }
    @Test
    public void throwsIfKeyLengthIsGreaterThan256() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new RC4InitialState(
                    Bytes.wrapped(new byte[257])
                ).asArray();
            }
        );
    }
}
