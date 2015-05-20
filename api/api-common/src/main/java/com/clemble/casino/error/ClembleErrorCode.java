package com.clemble.casino.error;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;

@JsonSerialize(using = ClembleErrorCodeFormat.Serializer.class)
@JsonDeserialize(using = ClembleErrorCodeFormat.Deserializer.class)
public enum ClembleErrorCode {

    GeneralError,
    CriticalError,
    SessionProcessingError,
    CacheError,
    LatchError,

    BadRequestPlayerIdHeaderMissing,
    BadRequestSessionIdHeaderMissing,
    BadRequestTableIdHeaderMissing,

    EmailMissing,
    EmailInvalid,
    EmailNotConfirmed,
    EmailNotRegistered,
    EmailAlreadyRegistered,

    PasswordMissing,
    PasswordTooShort,
    PasswordTooWeak,
    PasswordTooLong,
    EmailOrPasswordIncorrect,
    PasswordIncorrect,

    ProfileSocialCantBeEdited,

    IdentityInvalid,

    NickInvalid,
    NickTooLong,
    NickTooShort,
    NickOccupied,
    NickMustNotBeNull,
    NickNoWhiteSpacesAllowed,
    FirstNameTooLong,
    LastNameTooLong,
    BirthDateInvalid,

    SocialConnectionProviderUserNull,
    SocialConnectionProviderIdNull,
    SocialConnectionInvalid,
    SocialConnectionAlreadyRegistered,
    SocialConnectionProviderNotSupported,

    ClientJsonInvalidError,
    ClientJsonFormatError,

    CellOwned,

    GameStateReCreationFailure,

    PaymentTransactionInvalid,
    PaymentTransactionEmpty,
    // TODO PaymentTransactionUnknownPlayers is no longer controlled, by the system
    PaymentTransactionUnknownPlayers,
    PaymentTransactionAccessDenied,
    PaymentTransactionNotExists,
    PaymentTransactionDebitAndCreditNotMatched,
    PaymentTransactionTransactionDateMissing,
    PaymentTransactionProcessingDateMissing,
    PaymentTransactionInsufficientMoney,

    TimeoutProcessingFailure,

    PlayerLockAcquireFailure,
    PlayerSessionTimeout,
    PlayerProfileDoesNotExists,
    PlayerNotProfileOwner,
    PlayerProfileInvalid,
    PlayerNotSessionOwner,
    PlayerSessionClosed,
    PlayerAccountAccessDenied,
    PlayerNoInvitation,

    GoalStateIncorrect,
    GoalDueDateInPast,
    // TODO consider removing, this is no longer valid
    GoalPlayerIncorrect,
    GoalBidInvalid,
    GoalIsEmpty,

    GoalJudgeOnlyJudgeCanReplay,
    GoalNotExists,
    GoalNotOwnedByPlayer,
    GoalActionInvalid,

    AccountInsufficientAmount;

    public static Collection<ClembleErrorCode> forCodes(Collection<String> codes) {
        Collection<ClembleErrorCode> errors = new ArrayList<ClembleErrorCode>();
        for (String code: codes)
            errors.add(ClembleErrorCode.valueOf(code));
        return errors;
    }

    /**
     * Name duplicates of existing parameters for check that all
     */
    public static class Code {

        final public static String EMAIL_MISSING = "EmailMissing";
        final public static String EMAIL_INVALID = "EmailInvalid";
        final public static String PASSWORD_TOO_SHORT = "PasswordTooShort";
        final public static String PASSWORD_TOO_LONG = "PasswordTooLong";
        final public static String PASSWORD_MISSING = "PasswordMissing";
        final public static String PASSWORD_TOO_WEAK = "PasswordTooWeak";

        final public static String NICK_NO_WHITE_SPACES_ALLOWED = "NickNoWhiteSpacesAllowed";
        final public static String NICK_INVALID = "NickInvalid";
        final public static String NICK_TOO_LONG = "NickTooLong";
        final public static String NICK_TOO_SHORT = "NickTooShort";

        final public static String FIRST_NAME_TOO_LONG = "FirstNameTooLong";
        final public static String LAST_NAME_TOO_LONG = "LastNameTooLong";

        final public static String SOCIAL_CONNECTION_PROVIDER_ID_NULL = "SocialConnectionProviderIdNull";
        final public static String SOCIAL_CONNECTION_PROVIDER_USER_NULL = "SocialConnectionProviderUserNull";

        final public static String PAYMENT_TRANSACTION_DEBIT_AND_CREDIT_NOT_MATCHED = "PaymentTransactionDebitAndCreditNotMatched";
    }
}
