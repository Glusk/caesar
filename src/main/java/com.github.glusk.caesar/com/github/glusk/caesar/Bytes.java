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
     * An <em>identity</em> that guarantees:
     * {@code Arrays.equals(argument, value)}, where {@code argument} is
     * the array passed through the constructor and {@code value} is the result
     * of {@code this.asArray()}.
     */
    final class Identity implements Bytes {
        /** The cloned <em>argument</em> of {@code this} Identity. */
        private final byte[] clonedArgument;

        /**
         * Constructs a {@code new} Identity by passing in the {@code argument}.
         * <p>
         * {@code this} object stores a <em>copy</em> of the {@code argument}!
         *
         * @param argument the <em>argument</em> of {@code this} Identity
         */
        public Identity(final byte... argument) {
            this.clonedArgument = argument.clone();
        }

        /**
         * {@inheritDoc}
         * <p>
         * This is the <em>value</em> of {@code this} Identity.
         */
        @Override
        public byte[] asArray() {
            return clonedArgument.clone();
        }
    }
}
