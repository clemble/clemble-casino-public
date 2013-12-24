package com.clemble.casino.game.cell;

import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cell")
public class Cell implements GameUnit {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = 3691061790615545958L;

    final public static Cell DEFAULT = new Cell(Byte.MIN_VALUE, Byte.MIN_VALUE);

    final private int row;
    final private int column;

    private Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @JsonCreator
    public static Cell create(@JsonProperty("row") byte row, @JsonProperty("column") byte column) {
        return new Cell(row, column);
    }

    public static Cell create(int row, int column) {
        return create((byte) row, (byte) column);
    }

    @Override
    public String toString() {
        return "{" + row + ", " + column + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
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
        Cell other = (Cell) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }
}
