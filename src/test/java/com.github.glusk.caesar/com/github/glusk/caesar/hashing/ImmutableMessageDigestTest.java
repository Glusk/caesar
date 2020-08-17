package com.github.glusk.caesar.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public final class ImmutableMessageDigestTest {
    class UncloneableMessageDigest extends MessageDigest {
        public UncloneableMessageDigest(String algorithm) {
            super(algorithm);
        }
        @Override
        protected void engineReset() {
            throw new UnsupportedOperationException();
        }
        @Override
        protected byte[] engineDigest() {
            throw new UnsupportedOperationException();
        }
        @Override
        protected void engineUpdate​(byte input) {
            throw new UnsupportedOperationException();
        }
        @Override
        protected void engineUpdate​(byte[] input, int offset, int len) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }
    
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

    @Test
    public void digestFailsWithUncloneableMessageDigest() {
        assertThrows(
            RuntimeException.class,
            () -> {
                new ImmutableMessageDigest(
                    new UncloneableMessageDigest("")
                ).digest();
            }
        );
    }
}
