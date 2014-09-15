package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.GoalJudgeInvitation;
import com.clemble.casino.goal.service.GoalJudgeInvitationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

import static com.clemble.casino.goal.GoalJudgeInvitationWebMapping.*;
import static com.clemble.casino.goal.GoalJudgeInvitationWebMapping.toGoalJudgeInvitationUrl;

/**
 * Created by mavarazy on 8/18/14.
 */
public class AndroidGoalJudgeInvitationService extends AbstractClembleCasinoOperations implements GoalJudgeInvitationService {

    final private RestTemplate restTemplate;

    public AndroidGoalJudgeInvitationService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<GoalJudgeInvitation> myPending() {
        // Step 1. Generating goal duties URI
        URI myInvitationsUrl = buildUriWith(toGoalJudgeInvitationUrl(MY_INVITATIONS_PENDING));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myInvitationsUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public Collection<GoalJudgeInvitation> myAccepted() {
        // Step 1. Generating goal duties URI
        URI myInvitationsUrl = buildUriWith(toGoalJudgeInvitationUrl(MY_INVITATIONS_ACCEPTED));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myInvitationsUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public Collection<GoalJudgeInvitation> myDeclined() {
        // Step 1. Generating goal duties URI
        URI myInvitationsUrl = buildUriWith(toGoalJudgeInvitationUrl(MY_INVITATIONS_DECLINED));
        // Step 2. Post to Player URI
        return CollectionUtils.<GoalJudgeInvitation>immutableList(restTemplate.getForObject(myInvitationsUrl, GoalJudgeInvitation[].class));
    }

    @Override
    public GoalJudgeInvitation reply(String goalKey, GoalJudgeInvitation response) {
        // Step 1. Generating goal duties URI
        URI replyUrl = buildUriWith(toGoalJudgeInvitationUrl(INVITATION_REPLY), goalKey);
        // Step 2. Post to Player URI
        return restTemplate.exchange(replyUrl, HttpMethod.PUT, new HttpEntity<GoalJudgeInvitation>(response), GoalJudgeInvitation.class).getBody();
    }
}
