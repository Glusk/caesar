package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public final class WrappedBytesTest {
    @Test
    public void properlyWrapsAByteArrayAndReturnsIt() {
        final byte[] bytesToWrap = new byte[] {1, 2, 3};
        assertArrayEquals(
            bytesToWrap,
            new WrappedBytes(bytesToWrap).asArray()
        );
    }
}
