package com.clemble.casino.game.cell;

import com.clemble.casino.game.unit.GameUnit;

public interface CellBoard extends GameUnit {

    public CellState[][] getBoard();

    public boolean owned(int row, int column);

}
