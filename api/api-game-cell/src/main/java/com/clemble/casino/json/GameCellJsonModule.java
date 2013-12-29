package com.clemble.casino.json;

import com.clemble.casino.game.cell.Cell;
import com.clemble.casino.game.cell.CellState;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

class GameCellJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("GameCell");
        module.registerSubtypes(new NamedType(Cell.class, Cell.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(CellState.class, CellState.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
