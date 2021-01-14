package com.github.glusk.caesar.hashing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.glusk.caesar.Hex;
import com.github.glusk.caesar.PlainText;

import org.junit.jupiter.api.Test;

public class HmacTest {
    @Test
    public void testOneRFC2104() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex("9294727a3638bb1c13f48ef8158bfc9d").asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("MD5")
                ),
                new Hex("0b".repeat(16)),
                new PlainText("Hi There")
            ).asArray()
        );
    }
    /* Test with a key shorter than the length of the HMAC output. */
    @Test
    public void testTwoRFC2104() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex("750c783e6ab0b503eaa86e310a5db738").asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("MD5")
                ),
                new PlainText("Jefe"),
                new PlainText("what do ya want for nothing?")
            ).asArray()
        );
    }
    /* Test with a combined length of key and data that is larger than 64
       bytes (= block-size of MD5). */
    @Test
    public void testThreeRFC2104() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex("56be34521d144c88dbb8c733f0e8b3f6").asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("MD5")
                ),
                new Hex("aa".repeat(16)),
                new Hex("dd".repeat(50))
            ).asArray()
        );
    }
    /* Test with a combined length of key and data that is larger than 64
       bytes (= block-size of SHA-256). */
    @Test
    public void testFourRFC4231SHA256() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex(
                "82558a389a443c0ea4cc819899f2083a"
              + "85f0faa3e578f8077a2e3ff46729665b"
            ).asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-256")
                ),
                new Hex(
                    "0102030405060708090a0b0c0d0e0f"
                  + "10111213141516171819"
                ),
                new Hex("cd".repeat(50))
            ).asArray()
        );
    }
    /* Test with a key larger than 128 bytes (= block-size
       of SHA-512). */
    @Test
    public void testSixRFC4231SHA512() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex(
                "80b24263c7c1a3ebb71493c1dd7be8b4"
              + "9b46d1f41b4aeec1121b013783f8f352"
              + "6b56d037e05f2598bd0fd2215d6a1e52"
              + "95e64f73f63f0aec8b915a985d786598"
            ).asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-512")
                ),
                128,
                new Hex("aa".repeat(131)),
                new PlainText(
                    "Test Using Larger Than Block-Size Key - Hash Key First"
                )
            ).asArray()
        );
    }
    /* Test with a key and data that is larger than 128 bytes (= block-size
       of SHA-512). */
    @Test
    public void testSevenRFC4231SHA512() throws NoSuchAlgorithmException {
        assertArrayEquals(
            new Hex(
                "e37b6a775dc87dbaa4dfa9f96e5e3ffd"
              + "debd71f8867289865df5a32d20cdc944"
              + "b6022cac3c4982b10d5eeb55c3e4de15"
              + "134676fb6de0446065c97440fa8c6a58"
            ).asArray(),
            new Hmac(
                new ImmutableMessageDigest(
                    MessageDigest.getInstance("SHA-512")
                ),
                128,
                new Hex("aa".repeat(131)),
                new PlainText(
                    "This is a test using a larger than block-size key and a "
                  + "larger than block-size data. The key needs to be hashed "
                  + "before being used by the HMAC algorithm."
                )
            ).asArray()
        );
    }
}
