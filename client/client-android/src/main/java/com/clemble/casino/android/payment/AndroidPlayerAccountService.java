package com.clemble.casino.android.payment;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.payment.PendingTransaction;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.money.Currency;
import com.clemble.casino.payment.service.PlayerAccountService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static com.clemble.casino.payment.PaymentWebMapping.*;
import static com.clemble.casino.utils.Preconditions.checkNotNull;

/**
 * Created by mavarazy on 8/5/14.
 */
public class AndroidPlayerAccountService extends AbstractClembleCasinoOperations implements PlayerAccountService {

    final private RestTemplate restTemplate;

    public AndroidPlayerAccountService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = checkNotNull(restTemplate);
    }

    @Override
    public PlayerAccount myAccount() {
        return restTemplate.getForObject(buildUriWith(toPaymentUrl(MY_ACCOUNT)), PlayerAccount.class);
    }

    @Override
    public PlayerAccount getAccount(String player) {
        return restTemplate.getForObject(buildUriWith(toPaymentUrl(ACCOUNT), player), PlayerAccount.class);
    }

    @Override
    public List<String> canAfford(Collection<String> players, Currency currency, Long amount) {
        // Step 1. Generating URL
        String url = buildUriWith(toPaymentUrl(ACCOUNTS)) + "?currency=" + currency + "&amount=" + amount;
        for(String player: players)
            url += "&player=" + player;
        // Step 2. Sending and receiving response
        return CollectionUtils.immutableList(restTemplate.getForObject(url, String[].class));
    }

}
