package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;

/**
 * An RC4 Initial State.
 * <p>
 * This class implements the "Key-scheduling algorithm (KSA)" and "produces"
 * the initial state - "S" - for the specified RC4 {@code key}.
 * <p>
 * More info: https://en.wikipedia.org/wiki/RC4#Key-scheduling_algorithm_(KSA)
 */
public final class RC4InitialState extends AbstractBytes {
    /** An integer bitmask with only the least significant 8 bits set. */
    private static final int UNSIGNED_BYTE_BITMASK = 0xff;
    /** The size of the state array (S). */
    private static final int STATE_ARRAY_SIZE = 256;

    /** The key used to produce {@code this} RC4 Initital State - S. */
    private final Bytes key;

    /**
     * Creates a new RC4 Initial State (S) with the specified {@code key}.
     *
     * @param key the key used to produce {@code this} RC4 Initital State - S
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public RC4InitialState(final Bytes key) {
        this.key = key;
    }

    /**
     * {@inheritDoc}
     * <p>
     * @throws IllegalArgumentException If length of the key, passed through
     *                                  constructor, is outside the valid
     *                                  range. Valid range:
     *                                  1 ≤ key length ≤ 256.
     */
    @Override
    @SuppressWarnings("checkstyle:localvariablename")
    public byte[] asArray() {
        byte[] S = new byte[STATE_ARRAY_SIZE];
        byte[] k = key.asArray();
        if (k.length < 1 || k.length > STATE_ARRAY_SIZE) {
            throw new IllegalArgumentException(
                String.format(
                    "Invalid key length: %d. The valid range is: "
                  + "1 ≤ key length ≤ 256.",
                    k.length
                )
            );
        }
        for (int i = 0; i < S.length; i++) {
            S[i] = (byte) i;
        }
        for (int i = 0, j = 0; i < S.length; i++) {
            // j := (j + S[i] + key[i mod keylength]) mod 256
            j = (
                j
             + (S[i] & UNSIGNED_BYTE_BITMASK)
             + (k[i % k.length] & UNSIGNED_BYTE_BITMASK)
            ) % S.length;
            // swap values of S[i] and S[j]
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
        return S;
    }
}
