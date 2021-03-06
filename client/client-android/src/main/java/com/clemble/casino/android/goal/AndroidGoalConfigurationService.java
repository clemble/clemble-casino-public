package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationChoices;
import com.clemble.casino.goal.lifecycle.configuration.IntervalGoalConfigurationBuilder;
import com.clemble.casino.goal.lifecycle.configuration.service.GoalConfigurationService;
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
    public GoalConfigurationChoices getChoices() {
        // Step 1. Generating choices URI
        URI myConfigurationsUrl = buildUri(toGoalConfigurationUrl(MY_CONFIGURATIONS_CHOICES));
        // Step 2. GET available Goal configurations
        return restTemplate.getForObject(myConfigurationsUrl, GoalConfigurationChoices.class);
    }

    @Override
    public List<GoalConfiguration> getConfigurations() {
        // Step 1. Generating my goal configuration URI
        URI myConfigurationsUrl = buildUri(toGoalConfigurationUrl(MY_CONFIGURATIONS));
        // Step 2. GET available Goal configurations
        return CollectionUtils.immutableList(restTemplate.getForObject(myConfigurationsUrl, GoalConfiguration[].class));
    }

    @Override
    public IntervalGoalConfigurationBuilder getIntervalBuilder() {
        // Step 1. Generating choices URI
        URI myConfigurationsUrl = buildUri(toGoalConfigurationUrl(MY_CONFIGURATIONS_INTERVAL));
        // Step 2. GET available Goal configurations
        return restTemplate.getForObject(myConfigurationsUrl, IntervalGoalConfigurationBuilder.class);
    }

}
