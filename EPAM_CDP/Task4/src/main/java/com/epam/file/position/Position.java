package com.epam.file.position;

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
        final int prime = 31;
        int result = 1;

        result = prime * result + (int) (offset ^ (offset >>> 32));
        result = prime * result + size;

        return result;
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

        if (offset != other.offset)
            return false;

        if (size != other.size)
            return false;

        return true;
    }
}
