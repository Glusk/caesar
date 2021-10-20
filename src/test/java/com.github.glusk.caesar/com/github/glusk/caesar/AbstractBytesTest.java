package com.github.glusk.caesar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AbstractBytesTest {
    @Test
    public void sameObjectsEqual() {
        Bytes b =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1};
                }
            };
        assertTrue(b.equals(b));
    }
    @Test
    public void comparisonToNullYieldsFalse() {
        Bytes b =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1};
                }
            };
        assertFalse(b.equals(null));
    }
    @Test
    public void differentObjectsWithSameArrayEqual() {
        Bytes b0 =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1, 2, 3};
                }
            };
        Bytes b1 =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1, 2, 3};
                }
            };
        assertTrue(b0.equals(b1));
    }
    @Test
    public void objectsThatEqualHaveSameHashCode() {
        Bytes b0 =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1, 2, 3};
                }
            };
        Bytes b1 =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return new byte[]{1, 2, 3};
                }
            };
        assertTrue(b0.equals(b1) && b0.hashCode() == b1.hashCode());
    }
}
