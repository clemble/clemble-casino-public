package com.clemble.casino.registration;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.validation.*;
import com.clemble.casino.player.*;
import com.clemble.casino.security.ClembleConsumerDetails;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlayerRegistrationRequest
    implements RegistrationRequest, EmailAware, PlayerNickNameAware, PlayerAware {

    private static final long serialVersionUID = -7419091879616342442L;

    final private String player;

    @JsonProperty("nickName")
    @NoWhiteSpaces(message = ClembleCasinoError.Code.NICK_NO_WHITE_SPACES_ALLOWED_CODE)
    @NickNameConstraint(message = ClembleCasinoError.Code.NICK_INVALID_CODE)
    @MaxSize(max = 64, message = ClembleCasinoError.Code.NICK_TOO_LONG_CODE)
    @MinSize(min = 5, message = ClembleCasinoError.Code.NICK_TOO_SHORT_CODE)
    final private String nickName;

    @Email(message = ClembleCasinoError.Code.EMAIL_INVALID_CODE)
    final private String email;

    @PasswordConstraint
    @MinSize(min = 6, message = ClembleCasinoError.Code.PASSWORD_TOO_SHORT_CODE)
    @MaxSize(max = 64, message = ClembleCasinoError.Code.PASSWORD_TOO_LONG_CODE)
    @NotNull(message = ClembleCasinoError.Code.PASSWORD_MISSING_CODE)
    final private String password;


    @JsonProperty("firstName")
    @MaxSize(max = 64, message = ClembleCasinoError.Code.FIRST_NAME_TOO_LONG_CODE)
    final private String firstName;
    @JsonProperty("lastName")
    @MaxSize(max = 64, message = ClembleCasinoError.Code.LAST_NAME_TOO_LONG_CODE)
    final private String lastName;
    @JsonProperty("gender")
    final private PlayerGender gender;
    @JsonProperty("birthDate")
    final private DateTime birthDate;

    @JsonCreator
    public PlayerRegistrationRequest(
        @JsonProperty(value = "player",defaultValue = "") String player,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("nickName") String nickName,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("gender") PlayerGender gender,
        @JsonProperty("birthDate") DateTime birthDate
    ) {
        this.player = player;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public static PlayerRegistrationRequest create(PlayerCredential playerCredential, PlayerProfile playerProfile) {
        return new PlayerRegistrationRequest(
            null,
            playerCredential.getEmail(),
            playerCredential.getPassword(),
            playerProfile.getNickName(),
            playerProfile.getFirstName(),
            playerProfile.getLastName(),
            playerProfile.getGender(),
            playerProfile.getBirthDate()
        );
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerGender getGender() {
        return gender;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public PlayerRegistrationRequest copyWithPlayer(String player) {
        return new PlayerRegistrationRequest(
            player,
            email,
            password,
            nickName,
            firstName,
            lastName,
            gender,
            birthDate
        );
    }

    public PlayerCredential toCredentials() {
        return new PlayerCredential(email, password);
    }

    public PlayerProfile toProfileWithPlayer(String player) {
        return new PlayerProfile().
            setBirthDate(birthDate).
            setFirstName(firstName).
            setLastName(lastName).
            setGender(gender).
            setPlayer(player).
            setNickName(nickName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerRegistrationRequest)) return false;

        PlayerRegistrationRequest that = (PlayerRegistrationRequest) o;

        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (gender != that.gender) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

}
