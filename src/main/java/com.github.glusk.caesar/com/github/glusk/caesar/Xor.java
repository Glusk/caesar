package com.github.glusk.caesar;

/**
 * A byte sequence that is a result of an xor operation.
 * <p>
 * This implementation performs a per-byte xor operation on byte sequences of
 * equal length.
 * <p>
 * Xor of am empty byte sequences is an empty byte sequence.
 */
public final class Xor implements Bytes {
    /** The arguments to {@code this} xor operation. */
    private final Bytes[] args;

    /**
     * Creates a new xor operation from a variable arguments list.
     *
     * @param args the arguments to {@code this} xor operation
     * @throws IllegalArgumentException if {@code args} differ in length
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public Xor(final Bytes... args) {
        if (args.length >= 1) {
            int len = args[0].asArray().length;
            for (int i = 1; i < args.length; i++) {
                if (len != args[i].asArray().length) {
                    throw new IllegalArgumentException(
                        "Xor arguments differ in length!"
                    );
                }
            }
        }
        this.args = args;
    }

    @Override
    public byte[] asArray() {
        if (args.length >= 1) {
            byte[] result = args[0].asArray();
            for (int i = 1; i < args.length; i++) {
                byte[] nextArg = args[i].asArray();
                for (int j = 0; j < result.length; j++) {
                    result[j] ^= nextArg[j];
                }
            }
            return result;
        } else {
            return new byte[0];
        }
    }
}
