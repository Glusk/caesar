package com.github.glusk.caesar.hashing;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Hex;
import com.github.glusk.caesar.Xor;

/**
 * HMAC - Hash-based message authentication code.
 * <p>
 * <a href="https://en.wikipedia.org/wiki/HMAC#Definition">Definition</a>:
 * <pre>
 * HMAC(K, m) = H( (K' ^ opad) | H( (K' ^ ipad) | m ) )
 *
 * H() ... one-way hash function
 * | ... concatenation operator
 * ^ ... xor operator
 * K ... secret key
 * K' ... block-sized secret key (refer to BlockSizedHmacKey)
 * m ... message to be authenticated
 * opad ... block-sized outer padding, consisting of repeated bytes valued 0x5c
 * ipad ... block-sized inner padding, consisting of repeated bytes valued 0x36
 * </pre>
 * The default block-size is 64 bytes. Refer to <a href="https://en.wikipedia.org/wiki/SHA-3#Comparison_of_SHA_functions">this table</a>
 * when determining the correct block-size for specific hash functions.
 * <p>
 * This implementation does not support truncation as described in <a href="https://tools.ietf.org/html/rfc2104#section-5">Section 5</a>
 * of RFC 2104.
 *
 * @see BlockSizedHmacKey
 */
public final class Hmac implements Bytes {
    /** The default block-size. */
    private static final int DEFAULT_BLOCK_SIZE = 64;

    /** The HMAC that represents {@code this} object. */
    private final Bytes hmac;

    /**
     * Creates a new HMAC with default block size.
     *
     * @param hashFunction the hash function to use {@code H()}
     * @param key HMAC argument: key - ({@code  K})
     * @param message HMAC argument: message - {@code m}
     */
    public Hmac(
        final ImmutableMessageDigest hashFunction,
        final Bytes key,
        final Bytes message
    ) {
        this(hashFunction, DEFAULT_BLOCK_SIZE, key, message);
    }

    /**
     * Creates a new HMAC with specified block size.
     *
     * @param hashFunction the hash function to use {@code H()}
     * @param blockSize the block-size of the hash function
     * @param key HMAC argument: key - ({@code  K})
     * @param message HMAC argument: message - {@code m}
     */
    public Hmac(
        final ImmutableMessageDigest hashFunction,
        final int blockSize,
        final Bytes key,
        final Bytes message
    ) {
        this(
            new Hash(
                hashFunction,
                new Xor(
                    new BlockSizedHmacKey(hashFunction, key, blockSize),
                    new Hex("5C".repeat(blockSize))
                ),
                new Hash(
                    hashFunction,
                    new Xor(
                        new BlockSizedHmacKey(hashFunction, key, blockSize),
                        new Hex("36".repeat(blockSize))
                    ),
                    message
                )
            )
        );
    }

    /**
     * Hidden constructor.
     * <p>
     * Not for public use; an implementation aid.
     *
     * @param hmac the HMAC that represents {@code this} object
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    private Hmac(final Bytes hmac) {
        this.hmac = hmac;
    }

    @Override
    public byte[] asArray() {
        return hmac.asArray();
    }
}
