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

    /**
     * Returns a new byte sequence which contains elements of {@code this}
     * sequence in reverse order.
     *
     * @return a new byte sequence with elements in reversed order
     */
    default Bytes reversed() {
        byte[] reversed = this.asArray();

        for (int i = 0; i < reversed.length / 2; i++) {
            byte tmp = reversed[i];
            reversed[i] = reversed[reversed.length - 1 - i];
            reversed[reversed.length - 1 - i] = tmp;
        }

        return Bytes.wrapped(reversed);
    }

    /**
     * Encodes this byte sequence as a hex string and returns it.
     * <p>
     * The string returned conforms to the specification described in
     * {@link Hex}.
     * <p>The returned hex string letters will be upper-cased.
     *
     * @return this byte sequence encoded as a hex string.
     */
    default String asHexString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : asArray()) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * Wraps an arbitrary number of bytes into a new byte sequence and
     * returns it.
     * <p>
     * If an array is passed as an argument to this method, is must not be
     * modified afterward. Doing so breaks the identity of the byte sequence
     * returned by this method.
     *
     * @param bytes the bytes to wrap into a byte sequence
     * @return wrapped bytes as a new byte sequence
     */
    static Bytes wrapped(final byte... bytes) {
        return
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return bytes.clone();
                }
            };
    }

    /**
     * Returns {@code true} if and only if {@code this} and {@code obj} are
     * both byte sequence objects and {@code this.asArray()} element-wise
     * equals {@code ((Bytes) obj).asArray()}.
     *
     * @param obj the object to be compared for equality with this byte
     *            sequence
     * @return {@code true} if the specified object is equal to this byte
     *         sequence
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this byte sequence.
     * <p>
     * The hash code of a byte sequence has to obey the general contract of
     * {@link Object#hashCode}. That is, if two byte sequences are equal, then
     * they must have the same hash code.
     *
     * @return the hash code value for this byte sequence
     */
    @Override
    int hashCode();
}
