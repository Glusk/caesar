package com.github.glusk.caesar;

/**
 * The Caesar library type that represents an immutable sequence of
 * bytes.
 */
@FunctionalInterface
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

        return () -> reversed;
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
}
