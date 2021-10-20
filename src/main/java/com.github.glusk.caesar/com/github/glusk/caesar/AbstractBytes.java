package com.github.glusk.caesar;

import java.util.Arrays;

/**
 * This class provides a skeletal implementation of the {@link Bytes}
 * interface to minimize the effort required to implement this interface.
 * <p>
 * Method `equals()` is already implemented as per the specification in
 * {@link Bytes}.
 */
public abstract class AbstractBytes implements Bytes {
    /**
     * {@inheritDoc}
     * <p>
     * <strong>Implementation Notes:</strong><br>
     * This implementation first checks if the specified object is this
     * byte sequence. If so, it returns {@code true}; if not, it checks if the
     * specified object is a byte sequence. If not, it returns {@code false};
     * if so, it compares the corresponding byte arrays
     * ({@link Bytes#asArray()}) via {@code Arrays.equals()}.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Bytes)) {
            return false;
        }
        Bytes that = (Bytes) obj;
        return Arrays.equals(this.asArray(), that.asArray());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Implementation Note:</strong><br>
     * The hash code of a byte sequence is defined as:
     * <pre>
     * Arrays.hashCode(this.asArray());
     * </pre>
     */
    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.asArray());
    }
}
