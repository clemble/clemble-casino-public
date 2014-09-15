package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.GoalJudgeDuty;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.service.GoalConfigurationService;
import com.clemble.casino.goal.service.GoalJudgeDutyService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalConfigurationService extends AbstractClembleCasinoOperations implements GoalConfigurationService {

    final private RestTemplate restTemplate;

    public AndroidGoalConfigurationService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GoalConfiguration> getConfigurations() {
        // Step 1. Generating my goal configuration URI
        URI myConfigurationsUrl = buildUriWith(toGoalConfigurationUrl(MY_CONFIGURATIONS));
        // Step 2. GET available Goal configurations
        return CollectionUtils.<GoalConfiguration>immutableList(restTemplate.getForObject(myConfigurationsUrl, GoalConfiguration[].class));
    }

}
