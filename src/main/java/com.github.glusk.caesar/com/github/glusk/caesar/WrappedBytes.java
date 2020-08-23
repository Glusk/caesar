package com.github.glusk.caesar;

/**
 * This class simply clones a byte array passed through the constructor
 * and wraps it.
 */
public final class WrappedBytes implements Bytes {
    /** The cloned <em>argument</em> of {@code this} Identity. */
    private final byte[] clonedArray;

    /**
     * Constructs a new byte sequence from {@code bytesToWrap}.
     * <p>
     * {@code this} object stores a <em>copy</em> of {@code bytesToWrap}!
     *
     * @param bytesToWrap the bytes to wrap by this class
     */
    public WrappedBytes(final byte... bytesToWrap) {
        this.clonedArray = bytesToWrap.clone();
    }

    @Override
    public byte[] asArray() {
        return clonedArray.clone();
    }
}
