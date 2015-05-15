package com.clemble.casino.error;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.clemble.casino.error.ClembleCasinoErrorFormat.ClembleCasinoObjectErrorDeserializer;
import com.clemble.casino.error.ClembleCasinoErrorFormat.ClembleCasinoObjectErrorSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ClembleCasinoObjectErrorSerializer.class)
@JsonDeserialize(using = ClembleCasinoObjectErrorDeserializer.class)
public enum ClembleCasinoError {

    ServerError(Code.SERVER_ERROR_CODE, "Server error"),
    ServerCriticalError(Code.SERVER_CRITICAL_ERROR_CODE, "Server critical error"),
    ServerSessionProcessingError(Code.SERVER_SESSION_PROCESSING_CRITICAL_CODE, "Server session processing error"),
    ServerCacheError(Code.SERVER_CACHE_ERROR_CODE, "Server error cache problem"),
    ServerLatchError(Code.SERVER_LATCH_ERROR_CODE, "Latch error"),

    BadRequestPlayerIdHeaderMissing(Code.BAD_REQUEST_PLAYER_ID_HEADER_MISSING, "Player ID missing from the Header"),
    BadRequestSessionIdHeaderMissing(Code.BAD_REQUEST_SESSION_ID_HEADER_MISSING, "Session ID missing from the Header"),
    BadRequestTableIdHeaderMissing(Code.BAD_REQUEST_TABLE_ID_HEADER_MISSING, "Table ID missing from the Header"),

    EmailInvalid(Code.EMAIL_INVALID_CODE, "Email invalid"),
    EmailNotConfirmed(Code.EMAIL_NOT_CONFIRMED_CODE, "Email not confirmed"),
    EmailNotRegistered(Code.EMAIL_NOT_REGISTERED_CODE, "Email not registered"),
    EmailAlreadyRegistered(Code.EMAIL_ALREADY_REGISTERED_CODE, "Email already registered"),

    PasswordMissingCode(Code.PASSWORD_MISSING_CODE, "Password is missing"),
    PasswordTooShort(Code.PASSWORD_TOO_SHORT_CODE, "Password too short"),
    PasswordTooWeak(Code.PASSWORD_TOO_WEAK_CODE, "Password too weak"),
    PasswordTooLong(Code.PASSWORD_TOO_LONG_CODE, "Password too long"),
    EmailOrPasswordIncorrect(Code.EMAIL_OR_PASSWORD_INCORRECT_CODE, "Email or password is incorrect"),
    PasswordIncorrect(Code.PASSWORD_INCORRECT, "Password incorrect"),

    ProfileSocialCantBeEdited(Code.PROFILE_SOCIAL_CANT_BE_EDITED, "Social connections can't be edited by the user"),

    IdentityInvalid(Code.IDENTITY_INVALID_CODE, "Identity invalid"),

    NickInvalid(Code.NICK_INVALID_CODE, "Nick invalid"),
    NickTooLong(Code.NICK_TOO_LONG_CODE, "Nick too long"),
    NickTooShort(Code.NICK_TOO_SHORT_CODE, "Nick too short"),
    NickOccupied(Code.NICK_OCCUPIED_CODE, "Nick already occupied"),
    NickMustNotBeNull(Code.NICK_MUST_NOT_BE_NULL_CODE, "Nick must not be null"),
    NickNoWhiteSpacesAllowed(Code.NICK_NO_WHITE_SPACES_ALLOWED_CODE, "Nick can't have white spaces"),
    FirstNameTooLong(Code.FIRST_NAME_TOO_LONG_CODE, "First name too long"),
    LastNameTooLong(Code.LAST_NAME_TOO_LONG_CODE, "Last name too long"),
    BirthDateInvalid(Code.BIRTH_DATE_INVALID_CODE, "Birth date invalid"),

    SocialConnectionProviderUserNull(Code.SOCIAL_CONNECTION_PROVIDER_USER_NULL_CODE, "Social connection provider User can't be NULL"),
    SocialConnectionProviderIdNull(Code.SOCIAL_CONNECTION_PROVIDER_ID_NULL_CODE, "Social connection provider ID can't be NULL"),
    SocialConnectionInvalid(Code.SOCIAL_CONNECTION_INVALID_CODE, "Social connection is invalid"),
    SocialConnectionAlreadyRegistered(Code.SOCIAL_CONNECTION_ALREADY_REGISTERED, "Social connection already registered"),
    SocialConnectionProviderNotSupported(Code.SOCIAL_CONNECTION_PROVIDER_NOT_SUPPORTED, "Social connection provider not supported"),

    ClientJsonInvalidError(Code.CLIENT_JSON_INVALID_ERROR_CODE, "Invalid Json"),
    ClientJsonFormatError(Code.CLIENT_JSON_FORMAT_ERROR_CODE, "Incorrect Json"),

    CellOwned(Code.CELL_OWNED_CODE, "Cell already Owned"),

    GameStateReCreationFailure(Code.GAME_STATE_RECREATION_FAILURE_CODE, "Can't create state from provided session"),

    PaymentTransactionInvalid(Code.PAYMENT_TRANSACTION_INVALID_CODE, "Payment transaction invalid"),
    PaymentTransactionEmpty(Code.PAYMENT_TRANSACTION_EMPTY_CODE, "Payment transaction empty"),
    // TODO PaymentTransactionUnknownPlayers is no longer controlled, by the system
    PaymentTransactionUnknownPlayers(Code.PAYMENT_TRANSACTION_UNKNOWN_PLAYERS_ERROR_CODE, "Players are not registered in the system"),
    PaymentTransactionAccessDenied(Code.PAYMENT_TRANSACTION_ACCESS_DENIED, "Player payment transaction access denied"),
    PaymentTransactionNotExists(Code.PAYMENT_TRANSACTION_DOES_NOT_EXISTS, "Player payment transaction does not exists"),
    PaymentTransactionDebitAndCreditNotMatched(Code.PAYMENT_TRANSACTION_DEBIT_AND_CREDIT_NOT_MATCHED, "Payment credit and debit must match"),
    PaymentTransactionTransactionDateMissing(Code.PAYMENT_TRANSACTION_TRANSACTION_DATE_MISSING, "Payment transaction date missing"),
    PaymentTransactionProcessingDateMissing(Code.PAYMENT_TRANSACTION_PROCESSING_DATE_MISSING, "Payment transaction processing date missing"),
    PaymentTransactionInsufficientMoney(Code.PAYMENT_TRANSACTION_INSUFFICIENT_MONEY_CODE, "Not enough money for this game"),

    TimeoutProcessingFailure(Code.TIMEOUT_PROCESSING_FAILURE_CODE, "Failed to invoke timeout events in Scheduler"),

    PlayerLockAcquireFailure(Code.PLAYER_LOCK_ACQUIRE_EXCEPTION_FAILURE_CODE, "Failed to acquire player lock"),
    PlayerSessionTimeout(Code.PLAYER_SESSION_TIMEOUT_ERROR_CODE, "Player session timeout"),
    PlayerProfileDoesNotExists(Code.PLAYER_PROFILE_DOES_NOT_EXISTS, "Player profile does not exists"),
    PlayerNotProfileOwner(Code.PLAYER_NOT_PROFILE_OWNER_ERROR_CODE, "Profile can be changed only by the owner"),
    PlayerProfileInvalid(Code.PLAYER_PROFILE_INVALID_ERROR_CODE, "Player Profile invalid"),
    PlayerNotSessionOwner(Code.PLAYER_NOT_SESSION_OWNER_ERROR_CODE, "Player can't change session, he does not own"),
    PlayerSessionClosed(Code.PLAYER_SESSION_CLOSED_ERROR_CODE, "Player session already closed"),
    PlayerAccountAccessDenied(Code.PLAYER_ACCOUNT_ACCESS_DENIED, "Player account access denied"),
    PlayerNoInvitation(Code.PLAYER_NO_INVITATION, "Player no invitation"),

    GoalStateIncorrect(Code.GOAL_STATE_INCORRECT, "Goal State incorrect, supposed to be in Pending state"),
    GoalDueDateInPast(Code.GOAL_DUE_DATE_IN_PAST, "Goal Due Date in past"),
    // TODO consider removing, this is no longer valid
    GoalPlayerIncorrect(Code.GOAL_PLAYER_INCORRECT, "Goal Player is incorrect"),
    GoalBidInvalid(Code.GOAL_BID_INVALID, "Goal Bid is invalid"),
    GoalIsEmpty(Code.GOAL_IS_EMPTY, "Goal must be specified"),

    GoalJudgeOnlyJudgeCanReplay(Code.GOAL_JUDGE_ONLY_JUDGE_CAN_REPLY, "Only judge can reply to invitation"),
    GoalNotExists(Code.GOAL_DOES_NOT_EXIST, "Goal does not exist"),
    GoalNotOwnedByPlayer(Code.GOAL_NOT_OWNED_BY_PLAYER, "Goal is not owned by this player"),
    GoalActionInvalid(Code.GOAL_ACTION_INVALID, "Goal action invalid"),

    AccountInsufficientAmount(Code.ACCOUNT_INSUFFICIENT_CODE, "Insufficient amount on your account")
    ;


    final private static Map<String, ClembleCasinoError> REGISTERED_ERRORS = new HashMap<String, ClembleCasinoError>();

    static {
        Set<String> existingCodes = new HashSet<String>();
        try {
            for (Field field : ClembleCasinoError.Code.class.getFields())
                if ((field.getModifiers() & Modifier.STATIC) > 0 && field.getName().endsWith("_CODE")) {
                    String fieldValue = String.valueOf(field.get(ClembleCasinoError.Code.class));
                    if (existingCodes.contains(fieldValue))
                        throw new IllegalArgumentException();
                    existingCodes.add(fieldValue);
                }
        } catch (Exception e) {
            throw new RuntimeException("Message processing failed");
        }

        try {
            for (Field field : ClembleCasinoError.class.getFields())
                if ((field.getModifiers() & Modifier.STATIC) > 0 && field.getType().equals(ClembleCasinoError.class)) {
                    ClembleCasinoError gogomayaError = (ClembleCasinoError) (field.get(ClembleCasinoError.class));
                    REGISTERED_ERRORS.put(gogomayaError.getCode(), gogomayaError);
                }
        } catch (Exception e) {
            throw new RuntimeException("Message processing failed");
        }
    }

    @JsonProperty("code")
    final private String code;
    @JsonProperty("description")
    final private String description;

    private ClembleCasinoError(final String newCode, final String newDescription) {
        this.code = newCode;
        this.description = newDescription;
    }

    public static boolean isValid(String code) {
        return REGISTERED_ERRORS.containsKey(code);
    }

    public static ClembleCasinoError forCode(String code) {
        return REGISTERED_ERRORS.get(code);
    }

    public static Set<ClembleCasinoError> forCodes(Collection<String> errorCodes) {
        errorCodes = errorCodes == null || errorCodes.size() == 0 ? Collections.singleton(Code.SERVER_ERROR_CODE) : errorCodes;

        Set<ClembleCasinoError> convertedErrors = new HashSet<ClembleCasinoError>(errorCodes.size());
        for (String errorCode : errorCodes) {
            ClembleCasinoError gogomayaError = ClembleCasinoError.forCode(errorCode);
            convertedErrors.add(gogomayaError != null ? gogomayaError : ClembleCasinoError.ServerError);
        }

        return convertedErrors;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static class Code {
        final public static String SERVER_ERROR_CODE = "0C0";
        final public static String SERVER_CRITICAL_ERROR_CODE = "0C1";
        final public static String SERVER_SESSION_PROCESSING_CRITICAL_CODE = "0C2";
        final public static String SERVER_CACHE_ERROR_CODE = "0C3";
        final public static String SERVER_LATCH_ERROR_CODE = "0C4";
        // Credentials errors
        // Creation problems
        // Email
        final public static String EMAIL_INVALID_CODE = "001";
        final public static String EMAIL_NOT_CONFIRMED_CODE = "002";
        final public static String EMAIL_NOT_REGISTERED_CODE = "unknown";
        final public static String EMAIL_ALREADY_REGISTERED_CODE = "004";
        // Password
        final public static String PASSWORD_MISSING_CODE = "required";
        final public static String PASSWORD_TOO_SHORT_CODE = "minlength";
        final public static String PASSWORD_TOO_WEAK_CODE = "s-weak";
        final public static String PASSWORD_TOO_LONG_CODE = "maxlength";
        final public static String PASSWORD_INCORRECT = "incorrect";
        // Credentials authentication codes
        final public static String EMAIL_OR_PASSWORD_INCORRECT_CODE = "020";
        // Identity errors
        // Processing problems
        final public static String IDENTITY_INVALID_CODE = "030";
        final public static String PROFILE_SOCIAL_CANT_BE_EDITED = "033";
        // Gamer Profile management errors
        // Nickname
        final public static String NICK_INVALID_CODE = "040";
        final public static String NICK_TOO_LONG_CODE = "041";
        final public static String NICK_TOO_SHORT_CODE = "042";
        final public static String NICK_OCCUPIED_CODE = "043";
        final public static String NICK_MUST_NOT_BE_NULL_CODE = "044";
        final public static String NICK_NO_WHITE_SPACES_ALLOWED_CODE = "045";
        // First name
        final public static String FIRST_NAME_TOO_LONG_CODE = "050";
        // Last name
        final public static String LAST_NAME_TOO_LONG_CODE = "060";
        // Birth date
        final public static String BIRTH_DATE_INVALID_CODE = "070";
        // SocialConnectionData
        final public static String SOCIAL_CONNECTION_PROVIDER_ID_NULL_CODE = "090";
        final public static String SOCIAL_CONNECTION_INVALID_CODE = "091";
        final public static String SOCIAL_CONNECTION_PROVIDER_USER_NULL_CODE = "092";
        final public static String SOCIAL_CONNECTION_ALREADY_REGISTERED = "093";
        final public static String SOCIAL_CONNECTION_PROVIDER_NOT_SUPPORTED = "094";
        // Generic Client errors
        final public static String CLIENT_JSON_INVALID_ERROR_CODE = "0D0";
        final public static String CLIENT_JSON_FORMAT_ERROR_CODE = "0D1";
        // Errors specific for TicTacToe
        final public static String CELL_OWNED_CODE = "0G0";
        // Session recreation failure
        final public static String GAME_STATE_RECREATION_FAILURE_CODE = "0H0";
        // Wallet transaction related errors
        final public static String PAYMENT_TRANSACTION_EMPTY_CODE = "0I0";
        final public static String PAYMENT_TRANSACTION_INVALID_CODE = "0I1";
        final public static String PAYMENT_TRANSACTION_UNKNOWN_PLAYERS_ERROR_CODE = "0I2";
        final public static String PAYMENT_TRANSACTION_ACCESS_DENIED = "0I3";
        final public static String PAYMENT_TRANSACTION_DOES_NOT_EXISTS = "0I4";
        final public static String PAYMENT_TRANSACTION_DEBIT_AND_CREDIT_NOT_MATCHED = "0I5";
        final public static String PAYMENT_TRANSACTION_TRANSACTION_DATE_MISSING = "0I6";
        final public static String PAYMENT_TRANSACTION_PROCESSING_DATE_MISSING = "0I7";
        final public static String PAYMENT_TRANSACTION_INSUFFICIENT_MONEY_CODE = "0I8";
        // Player id description
        final public static String BAD_REQUEST_PLAYER_ID_HEADER_MISSING = "0J0";
        final public static String BAD_REQUEST_SESSION_ID_HEADER_MISSING = "0J1";
        final public static String BAD_REQUEST_TABLE_ID_HEADER_MISSING = "0J2";
        // Timeout errors
        final public static String TIMEOUT_PROCESSING_FAILURE_CODE = "0L0";
        // Player errors
        final public static String PLAYER_LOCK_ACQUIRE_EXCEPTION_FAILURE_CODE = "0M0";
        final public static String PLAYER_SESSION_TIMEOUT_ERROR_CODE = "0M1";
        final public static String PLAYER_PROFILE_DOES_NOT_EXISTS = "0M2";
        final public static String PLAYER_NOT_PROFILE_OWNER_ERROR_CODE = "0M3";
        final public static String PLAYER_PROFILE_INVALID_ERROR_CODE = "0M4";
        final public static String PLAYER_NOT_SESSION_OWNER_ERROR_CODE = "0M5";
        final public static String PLAYER_SESSION_CLOSED_ERROR_CODE = "0M6";
        final public static String PLAYER_ACCOUNT_ACCESS_DENIED = "0M7";
        final public static String PLAYER_NO_INVITATION = "0M8";

        // Goal errors
        final public static String GOAL_STATE_INCORRECT = "0N0";
        final public static String GOAL_DUE_DATE_IN_PAST = "0N1";
        final public static String GOAL_PLAYER_INCORRECT = "0N2";
        final public static String GOAL_BID_INVALID = "0N3";
        final public static String GOAL_IS_EMPTY = "0N4";
        final public static String GOAL_DOES_NOT_EXIST = "0N5";
        final public static String GOAL_NOT_OWNED_BY_PLAYER = "0N6";
        final public static String GOAL_ACTION_INVALID = "0N7";

        // Goal judge errors
        final public static String GOAL_JUDGE_ONLY_JUDGE_CAN_REPLY = "0P0";

        // Payment errors
        final public static String ACCOUNT_INSUFFICIENT_CODE = "0Q0";
    }
}
