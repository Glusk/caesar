package com.github.glusk.caesar;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/** A test class for {@code Bytes.Identity}. */
public final class IdentityTest {
    /**
     * Tests if <em>argument</em> and <em>value</em> of an Identity equal.
     */
    @Test
    public void identityHolds() {
        final byte[] argument = new byte[] {1, 2, 3};
        assertArrayEquals(
            "Identity doesn't hold true!",
            argument,
            new Identity(argument).asArray()
        );
    }
}
