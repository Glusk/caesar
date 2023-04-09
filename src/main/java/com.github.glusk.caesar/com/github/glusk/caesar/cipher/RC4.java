package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;

public final class RC4 implements ImmutableCipher {
    public RC4() {
    }
    private RC4(final byte[] S, final int i, final int j) {
    }
    @Override
    public ImmutableCipher init(final Bytes key) {
        throw new UnsupportedOperationException("Not Impl");
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
