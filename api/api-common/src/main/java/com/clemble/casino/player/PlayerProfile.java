package com.clemble.casino.player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.clemble.casino.TimeZoneAware;
import com.clemble.casino.error.validation.MinSize;
import com.clemble.casino.error.validation.NoWhiteSpaces;
import com.clemble.casino.social.SocialProvider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;
import org.springframework.social.connect.ConnectionKey;

import com.clemble.casino.error.ClembleErrorCode.Code;
import com.clemble.casino.error.validation.MaxSize;
import com.clemble.casino.error.validation.NickNameConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerProfile implements PlayerAware, PlayerNickNameAware, TimeZoneAware {

    /**
     * Generated 03/11/13
     */
    private static final long serialVersionUID = -3831720055626662655L;

    @Id
    @JsonProperty(PLAYER)
    private String player;

    @JsonProperty("nickName")
    @NoWhiteSpaces(message = Code.NICK_NO_WHITE_SPACES_ALLOWED)
    @NickNameConstraint(message = Code.NICK_INVALID)
    @MaxSize(max = 64, message = Code.NICK_TOO_LONG)
    @MinSize(min = 5, message = Code.NICK_TOO_SHORT)
    private String nickName;

    @JsonProperty("firstName")
    @MaxSize(max = 64, message = Code.FIRST_NAME_TOO_LONG)
    private String firstName;

    @JsonProperty("lastName")
    @MaxSize(max = 64, message = Code.LAST_NAME_TOO_LONG)
    private String lastName;

    @JsonProperty("gender")
    private PlayerGender gender;

    @JsonProperty("birthDate")
    private DateTime birthDate;

    private Set<ConnectionKey> socialConnections = new HashSet<ConnectionKey>();

    private DateTimeZone timezone;

    public PlayerProfile() {
    }

    public String getPlayer() {
        return player;
    }

    public PlayerProfile setPlayer(String player) {
        this.player = player;
        return this;
    }

    public Collection<ConnectionKey> getSocialConnections() {
        return socialConnections;
    }
    
    public ConnectionKey getSocialConnection(SocialProvider provider) {
        // Step 1. Sanity check
        if(provider == null)
            return null;
        // Step 2. Filtering social connections
        for(ConnectionKey key: socialConnections)
            if(key.getProviderId().equals(provider.name()))
                return key;
        // Step 3. No connection found returning null
        return null;
    }

    public PlayerProfile setSocialConnections(Set<ConnectionKey> socialConnections) {
        this.socialConnections = socialConnections;
        return this;
    }

    public PlayerProfile addSocialConnection(ConnectionKey newConnectionKey) {
        socialConnections.add(newConnectionKey);
        return this;
    }

    @Override
    public DateTimeZone getTimezone() {
        return timezone;
    }

    public PlayerProfile setTimezone(DateTimeZone timezone) {
        this.timezone = timezone;
        return this;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public PlayerProfile setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PlayerProfile setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerProfile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerGender getGender() {
        return gender;
    }

    public PlayerProfile setGender(PlayerGender gender) {
        this.gender = gender;
        return this;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public PlayerProfile setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((socialConnections == null) ? 0 : socialConnections.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerProfile other = (PlayerProfile) obj;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (gender != other.gender)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (socialConnections == null) {
            if (other.socialConnections != null)
                return false;
        } else if (!socialConnections.equals(other.socialConnections))
            return false;
        return true;
    }

}
