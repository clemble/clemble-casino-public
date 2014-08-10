package com.clemble.casino.bet.service;

import com.clemble.casino.bet.Bet;

import java.util.List;

/**
 * Created by mavarazy on 8/9/14.
 */
public interface BetService {

    public List<Bet> myBets();

    public Bet getBet(String betKey);

}
