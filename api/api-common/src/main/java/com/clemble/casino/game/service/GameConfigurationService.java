package com.clemble.casino.game.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.specification.GameConfigurations;
import com.clemble.casino.web.game.GameWebMapping;
import com.clemble.casino.web.mapping.WebMapping;

public interface GameConfigurationService extends ClembleService {

    public GameConfigurations getConfigurations();

}
