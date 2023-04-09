package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;

public final class RC4 implements ImmutableCipher {
    /** The size of the state array (S). */
    private static final int STATE_ARRAY_SIZE = 256;

    private final byte[] S;

    public RC4() {
        this(new byte[STATE_ARRAY_SIZE]);
    }
    private RC4(final byte[] S) {
        this.S = S;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method implements the "Key-scheduling algorithm (KSA)" and returns
     * the updated internal state (S) as a new Immutable Cipher.
     * 
     * @param key
     * @return
     */
    @Override
    public ImmutableCipher init(final Bytes key) {
        byte[] S = this.S.clone();
        byte[] k = key.asArray();

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
        return new RC4(S);
    }
    @Override
    public ImmutableCipher update(final Bytes message) {
        throw new UnsupportedOperationException("Not Impl");
    }
    @Override
    public Bytes doFinal() {
        throw new UnsupportedOperationException("Not Impl");
    }
}
