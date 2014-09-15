package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.GoalJudgeDuty;
import com.clemble.casino.goal.service.GoalJudgeDutyService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalJudgeDutyWebMapping.MY_DUTIES;
import static com.clemble.casino.goal.GoalJudgeDutyWebMapping.toGoalJudgeDutyUrl;

/**
 * Created by mavarazy on 8/23/14.
 */
public class AndroidGoalJudgeDutyService extends AbstractClembleCasinoOperations implements GoalJudgeDutyService {

    final private RestTemplate restTemplate;

    public AndroidGoalJudgeDutyService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GoalJudgeDuty> myDuties() {
        // Step 1. Generating goal duties URI
        URI myDutiesUrl = buildUriWith(toGoalJudgeDutyUrl(MY_DUTIES));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeDuty>immutableList(restTemplate.getForObject(myDutiesUrl, GoalJudgeDuty[].class));

    }
}
