package com.clemble.casino.game.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.game.construct.AutomaticGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import static com.clemble.casino.web.game.GameWebMapping.*;

public interface AutoGameConstructionService extends GameConstructionService<AutomaticGameRequest> {

    public GameConstruction construct(final AutomaticGameRequest request);

}
