package com.clemble.casino.search.service;

import com.clemble.casino.search.PlayerSearch;

import java.util.List;

/**
 * @author Anton Oparin (antono@clemble.com)
 */
public interface PlayerSearchService {

    List<PlayerSearch> search(String fullName);

}
