package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;

/**
 * An Immutable Cipher.
 * <p>
 * Methods {@code init()}, {@code update()}, and {@code doFinal()} follow
 * the naming convention of the Java Standard Library class
 * {@code javax.crypto.Cipher}.
 */
interface ImmutableCipher {
    ImmutableCipher init(Bytes key);
    ImmutableCipher update(Bytes message);
    Bytes doFinal();
}
