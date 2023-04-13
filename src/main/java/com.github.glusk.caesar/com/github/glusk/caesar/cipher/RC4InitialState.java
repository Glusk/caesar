package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.AbstractBytes;
import com.github.glusk.caesar.Bytes;

/**
 * An RC4 Initial State.
 * <p>
 * This class implements the "Key-scheduling algorithm (KSA)" and "produces"
 * the initial state - "S" - for the specified RC4 {@code key}.
 */
public final class RC4InitialState extends AbstractBytes {
    /** The size of the state array (S). */
    private static final int STATE_ARRAY_SIZE = 256;
    
    private final Bytes key;
    public RC4InitialState(final Bytes key) {
        this.key = key;
    }
    @Override
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
            j = (j + (S[i] & 0xff) + (k[i % k.length] & 0xff)) % S.length;
            // swap values of S[i] and S[j]
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
        return S;
    }
}
