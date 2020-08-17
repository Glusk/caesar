package com.github.glusk.caesar.internal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/** A test class for {@code Bytes.Identity}. */
public final class IdentityTest {
    /**
     * Tests if <em>argument</em> and <em>value</em> of an Identity equal.
     */
    @Test
    public void identityHolds() {
        final byte[] argument = new byte[] {1, 2, 3};
        assertArrayEquals(
            argument,
            new Identity(argument).asArray(),
            "Identity doesn't hold true!"
        );
    }
}
