package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Xor;

public final class RC4 implements ImmutableCipher {
    private final Bytes S;
    private final int i;
    private final int j;
    private final Bytes output;

    public RC4(final Bytes key) {
        this(new RC4InitialState(key), 0, 0, Bytes.wrapped(new byte[0]));
    }
    private RC4(final Bytes S, final int i, final int j, final Bytes output) {
        this.S = S;
        this.i = i;
        this.j = j;
        this.output = output;
    }

    @Override
    public ImmutableCipher update(final Bytes input) {
        int inputLength = input.asArray().length;
        byte[] K = new byte[inputLength];
        byte[] S = this.S.asArray();
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
            Bytes.wrapped(S), i, j,
            new Xor(Bytes.wrapped(K), input)
        );
    }

    @Override
    public Bytes doFinal() {
        return this.output;
    }
}
