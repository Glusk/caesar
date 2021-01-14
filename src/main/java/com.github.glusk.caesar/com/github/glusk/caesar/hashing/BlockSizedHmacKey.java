package com.github.glusk.caesar.hashing;

import com.github.glusk.caesar.Bytes;

import java.util.Arrays;

/**
 * A block-sized HMAC Key ({@code K'}).
 * <p>
 * From <a href="https://en.wikipedia.org/wiki/HMAC#Definition">Wikipedia</a>:<br>
 * <em><q>
 * {@code K'} is a block-sized key derived from the secret key, K; either
 * by padding to the right with 0s up to the block size, or by hashing down
 * to less than or equal to the block size first and then padding to the right
 * with zeros.
 * </q></em>
 */
public final class BlockSizedHmacKey implements Bytes {
    /** Hash function. */
    private final ImmutableMessageDigest hashFunction;
    /** Secret key - {@code K}. */
    private final Bytes key;
    /** The block-size of the hash function. */
    private final int blockSize;

    /**
     * Creates a new block-sized HMAC Key ({@code K'}).
     *
     * @param hashFunction hash function
     * @param key secret key - {@code K}
     * @param blockSize the block-size of the hash function
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public BlockSizedHmacKey(
        final ImmutableMessageDigest hashFunction,
        final Bytes key,
        final int blockSize
    ) {
        this.hashFunction = hashFunction;
        this.key = key;
        this.blockSize = blockSize;
    }

    @Override
    public byte[] asArray() {
        byte[] keyBytes = key.asArray();
        if (keyBytes.length > blockSize) {
            keyBytes = hashFunction.update(key).digest();
        }
        return Arrays.copyOf(keyBytes, blockSize);
    }
}
