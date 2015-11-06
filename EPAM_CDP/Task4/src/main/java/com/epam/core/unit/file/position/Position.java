package com.epam.core.unit.file.position;

import com.google.common.base.Objects;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class Position {

    private long offset;
    private int size;

    public Position() {
    }

    public Position(long offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(offset, size);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Position other = (Position) obj;

        return Objects.equal(offset, other.offset) && Objects.equal(size, other.size);
    }
}
