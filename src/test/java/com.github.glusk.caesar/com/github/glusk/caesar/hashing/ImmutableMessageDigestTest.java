package com.github.glusk.caesar.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public final class ImmutableMessageDigestTest {
    @Test
    public void updateDoesNotAlterTheEngineState() {
        ImmutableMessageDigest imd = null;
        try {
          imd =
              new ImmutableMessageDigest(
                  MessageDigest.getInstance("SHA-1")
              );
        } catch (NoSuchAlgorithmException nsae) {
            // Should not happen; every JVM implementation must provide an
            // SHA-1 implementation
        }

        byte[] before = imd.digest();
        imd.update(new byte[1]);
        byte[] after = imd.digest();
        assertArrayEquals(
            before,
            after,
            "The engine's state has been changed!"
        );
    }

    @Test
    public void digestDoesNotResetTheEngineState() {
        ImmutableMessageDigest imd = null;
        try {
          imd =
              new ImmutableMessageDigest(
                  MessageDigest.getInstance("SHA-1")
              );
        } catch (NoSuchAlgorithmException nsae) {
            // Should not happen; every JVM implementation must provide an
            // SHA-1 implementation
        }

        assertArrayEquals(
            imd.digest(),
            imd.digest(),
            "The engine's state has been changed!"
        );
    }
}
