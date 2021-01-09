package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class XorTest {
    @Test
    public void conformsToXorTruthTable() {
        assertArrayEquals(
            new byte[] {1, 0, 0, 1},
            new Xor(
                () -> new byte[] {1, 0, 1, 0},
                () -> new byte[] {0, 0, 1, 1}
            ).asArray()
        );
    }
    @Test
    public void xorOfTwoEmptyByteSequencesIsAnEmptyByteSequence() {
        assertArrayEquals(
            new byte[0],
            new Xor(
                () -> new byte[0],
                () -> new byte[0]
            ).asArray()
        );
    }
    @Test
    public void throwsIfArgumentsAreNotOfEqualLength() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Xor(
                () -> new byte[0],
                () -> new byte[1]
            )
        );
    }
    @Test
    public void identityOperationCheck() {
        assertArrayEquals(
            new byte[] {1, 0, 1, 0},
            new Xor(
                () -> new byte[] {1, 0, 1, 0}
            ).asArray()
        );
    }
    @Test
    public void voidArgsProduceAnEmptyByteSequence() {
        assertArrayEquals(
            new byte[0],
            new Xor(
                new Bytes[0]
            ).asArray()
        );
    }
}
