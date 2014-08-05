package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.clemble.casino.web.player.PlayerWebMapping.*;

/**
 * Created by mavarazy on 7/26/14.
 */
public interface PlayerImageServiceContract extends ClembleService {

    public byte[] getImage(String player);

}
