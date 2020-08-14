package com.github.glusk.caesar.hashing;

import static org.junit.Assert.assertArrayEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.glusk.caesar.BinaryString;

import org.junit.Test;

public class HashTest {
    /**
     * Tests that an embedded digest is properly computed.
     * <p>
     * The test case is taken from SRP6 test vectors (RFC5054, Appendix B):
     * <pre>
     * x = SHA1(s | SHA1(I | ":" | P))
     * </pre>
     */
    @Test
    public void computesEmbeddedHash() {
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
            "Embedded hash was not properly computed!",
            new BinaryString("94B7555AABE9127CC58CCF4993DB6CF84D16C124", 16).asArray(),
            new Hash(
                imd,
                new DigestArgument("BEB25379D1A8581EB5A727673A2441EE", 16),
                new Hash(
                    imd,
                    new DigestArgument("alice:password123")
                )
            ).asArray()
        );
    }
}