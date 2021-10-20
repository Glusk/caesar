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

        return
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return reversed;
                }
            };
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
}
