package com.clemble.casino.payment.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.web.mapping.WebMapping;
import static com.clemble.casino.web.payment.PaymentWebMapping.*;

public interface PlayerAccountService extends ClembleService {

    @RequestMapping(method = RequestMethod.GET, value = ACCOUNTS_PLAYER, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PlayerAccount get(@PathVariable("player") String playerWalletId);

}
