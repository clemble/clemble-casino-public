package com.clemble.casino.goal.lifecycle.construction.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.construction.GoalSuggestion;
import com.clemble.casino.goal.lifecycle.construction.GoalSuggestionRequest;
import com.clemble.casino.goal.lifecycle.construction.GoalSuggestionResponse;

import java.util.List;

/**
 * Created by mavarazy on 1/3/15.
 */
public interface GoalSuggestionService extends ClembleService {

    List<GoalSuggestion> listMy();

    List<GoalSuggestion> list(String player);

    GoalSuggestion getSuggestion(String goalKey);

    GoalSuggestion addSuggestion(String player, GoalSuggestionRequest suggestionRequest);

    GoalSuggestion reply(String goalKey, GoalSuggestionResponse response);

}
