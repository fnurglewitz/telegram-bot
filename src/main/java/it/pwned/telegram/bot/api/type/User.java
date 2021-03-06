package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Telegram user or bot.
 */
public final class User {

    private final static String JSON_FIELD_ID = "id";
    private final static String JSON_FIELD_IS_BOT = "is_bot";
    private final static String JSON_FIELD_FIRST_NAME = "first_name";
    private final static String JSON_FIELD_LAST_NAME = "last_name";
    private final static String JSON_FIELD_USERNAME = "username";
    private final static String JSON_FIELD_LANGUAGE_CODE = "language_code";

    /**
     * Unique identifier for this user or bot
     */
    @JsonProperty(JSON_FIELD_ID)
    public final Integer id;

    /**
     * True, if this user is a bot
     */
    @JsonProperty(JSON_FIELD_IS_BOT)
    public final Boolean isBot;

    /**
     * User's or bot's first name
     */
    @JsonProperty(JSON_FIELD_FIRST_NAME)
    public final String firstName;

    /**
     * <em>Optional.</em> User's or bot's last name
     */
    @JsonProperty(JSON_FIELD_LAST_NAME)
    public final String lastName;

    /**
     * <em>Optional.</em> IETF language tag of the user's language
     */
    @JsonProperty(JSON_FIELD_LANGUAGE_CODE)
    public final String languageCode;

    /**
     * <em>Optional.</em> User's or bot's username
     */
    @JsonProperty(JSON_FIELD_USERNAME)
    public final String username;

    /**
     * @param id           Unique identifier for this user or bot
     * @param isBot        True, if this user is a bot
     * @param firstName    User's or bot's first name
     * @param lastName     <em>Optional.</em> User's or bot's last name
     * @param username     <em>Optional.</em> User's or bot's username
     * @param languageCode <em>Optional.</em> IETF language tag of the user's language
     */
    public User(@JsonProperty(JSON_FIELD_ID) Integer id,
                @JsonProperty(JSON_FIELD_IS_BOT) Boolean isBot,
                @JsonProperty(JSON_FIELD_FIRST_NAME) String firstName,
                @JsonProperty(JSON_FIELD_LAST_NAME) String lastName,
                @JsonProperty(JSON_FIELD_USERNAME) String username,
                @JsonProperty(JSON_FIELD_LANGUAGE_CODE) String languageCode) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }

    public static class Util {

        public enum UserNameFormat {
            PLAIN, LINK
        }

        /**
         * Always returns a non-empty string representing the user. Priority is
         * given to the username, if null or empty it falls back on the real name
         *
         * @param user   User
         * @param format if UserNameFormat.LINK an @ is prepended to the username
         * @return A string representing the user
         */
        public static String usernameOrName(User user, UserNameFormat format) {

            String username = user.username;

            if (username == null || "".equals(username)) {
                username = user.firstName;

                if (user.lastName != null && !"".equals(user.lastName))
                    username = String.format("%s %s", username, user.lastName);

            } else {
                if (format == UserNameFormat.LINK)
                    username = String.format("@%s", username);
            }

            return username;

        }

        public static String getUserMention(User user, String mentionText) {
            return String.format("[%s](tg://user?id=%d)", mentionText, user.id);
        }

    }

}
