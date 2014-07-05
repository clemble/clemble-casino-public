package com.clemble.casino.payment.service;

import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.web.payment.PaymentWebMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clemble.casino.ClembleService;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.web.mapping.WebMapping;

import java.util.Collection;
import java.util.List;

import static com.clemble.casino.web.payment.PaymentWebMapping.*;

public interface PlayerAccountService extends ClembleService {

    @RequestMapping(method = RequestMethod.GET, value = ACCOUNTS_PLAYER, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PlayerAccount get(@PathVariable("player") String playerWalletId);

    @RequestMapping(method = RequestMethod.GET, value = PaymentWebMapping.PAYMENT_ACCOUNTS, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<String> canAfford(@RequestParam("player") Collection<String> players, @RequestParam("currency") Currency currency, @RequestParam("amount") Long amount);

}
