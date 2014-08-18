package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.Goal;
import com.clemble.casino.goal.GoalJudgeInvitation;
import com.clemble.casino.goal.GoalKey;
import com.clemble.casino.goal.service.GoalJudgeInvitationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

import static com.clemble.casino.goal.GoalJudgeWebMapping.*;
import static com.clemble.casino.goal.GoalJudgeWebMapping.toGoalJudgeUrl;

/**
 * Created by mavarazy on 8/18/14.
 */
public class AndroidGoalJudgeInvitationService extends AbstractClembleCasinoOperations implements GoalJudgeInvitationService {

    final private RestTemplate restTemplate;

    public AndroidGoalJudgeInvitationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<GoalJudgeInvitation> myDuties() {
        // Step 1. Generating goal duties URI
        URI myDutiesUrl = buildUriWith(toGoalJudgeUrl(MY_DUTIES));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myDutiesUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public Collection<GoalJudgeInvitation> myInvitations() {
        // Step 1. Generating goal duties URI
        URI myInvitationsUrl = buildUriWith(toGoalJudgeUrl(MY_INVITATIONS));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myInvitationsUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public Collection<GoalJudgeInvitation> myDutiesAndInvitations() {
        // Step 1. Generating goal duties URI
        URI myInvitationsAndDutiesUrl = buildUriWith(toGoalJudgeUrl(MY_DUTIES_AND_INVITATIONS));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myInvitationsAndDutiesUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public GoalJudgeInvitation reply(GoalKey goalKey, GoalJudgeInvitation response) {
        // Step 1. Generating goal duties URI
        URI replyUrl = buildUriWith(toGoalJudgeUrl(INVITATION_REPLY));
        // Step 2. Post to Player URI
        return restTemplate.postForObject(replyUrl, response, GoalJudgeInvitation.class);
    }
}
