package com.github.glusk.caesar;

/**
 * The Caesar library type that represents an immutable sequence of
 * bytes.
 */
public interface Bytes {
    /**
     * Returns {@code this} byte sequence as an array.
     *
     * @return a fresh byte array copy of {@code this} byte sequence
     */
    byte[] asArray();
}
