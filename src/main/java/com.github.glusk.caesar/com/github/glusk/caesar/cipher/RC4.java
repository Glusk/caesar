package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Xor;

public final class RC4 implements ImmutableCipher {
    /** The size of the state array (S). */
    private static final int STATE_ARRAY_SIZE = 256;

    private final byte[] S;
    private final int i;
    private final int j;
    private final Bytes output;

    public RC4() {
        this(new byte[STATE_ARRAY_SIZE], 0, 0, Bytes.wrapped(new byte[0]));
    }
    private RC4(final byte[] S, final int i, final int j, final Bytes output) {
        this.S = S;
        this.i = i;
        this.j = j;
        this.output = output;
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
        return new RC4(S, 0, 0, Bytes.wrapped(new byte[0]));
    }
    @Override
    public ImmutableCipher update(final Bytes input) {
        int inputLength = input.asArray().length;
        byte[] K = new byte[inputLength];
        byte[] S = this.S.clone();
        for (int i = this.i, j = this.j, n = 0; n < inputLength; n++) {
            // i := (i + 1) mod 256
            // j := (j + S[i]) mod 256
            i = (i + 1) % S.length;
            j = (j + (S[i] & 0xff)) % S.length;
            // swap values of S[i] and S[j]
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            // K := S[(S[i] + S[j]) mod 256]
            K[n] = (byte) S[((S[i] & 0xff) + (S[j] & 0xff)) % S.length];
        }
        return new RC4(
            S, i, j,
            new Xor(Bytes.wrapped(K), input)
        );
    }

    @Override
    public Bytes doFinal() {
        return this.output;
    }
}
