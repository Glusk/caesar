package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;

/**
 * An Immutable Cipher.
 * <p>
 * This interface has been modeled after the {@code javax.crypto.Cipher}.
 * The naming convention for the three key methods of that class:
 * <ul>
 *   <li>{@code init()},</li>
 *   <li>{@code update()}, and</li>
 *   <li>{@code doFinal()}</li>
 * </ul>
 * has been followed closely with some crucial changes.
 * <p>
 * Method {@code init()} is not part of ImmutableCipher. Implementations
 * of this interface are expected to perform all the necessary initialization
 * in their corresponding constructors.
 * <p>
 * A call to {@code ImmutableCipher#update()} advances the engine state and
 * <em>returns a new ImmutableCipher object</em>. This is different to what
 * the Java Standard Library class {@code Cipher} does, where the state of the
 * <em>existing</em> {@code Cipher} object is updated.
 */
interface ImmutableCipher {
    ImmutableCipher update(Bytes message);
    Bytes doFinal();
}
