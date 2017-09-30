package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains information about one member of the chat.
 */
public class ChatMember {

    private final static String JSON_FIELD_USER = "user";
    private final static String JSON_FIELD_STATUS = "status";
    private final static String JSON_FIELD_UNTIL_DATE = "until_date";
    private final static String JSON_FIELD_CAN_BE_EDITED = "can_be_edited";
    private final static String JSON_FIELD_CAN_CHANGE_INFO = "can_change_info";
    private final static String JSON_FIELD_CAN_POST_MESSAGES = "can_post_messages";
    private final static String JSON_FIELD_CAN_EDIT_MESSAGES = "can_edit_messages";
    private final static String JSON_FIELD_CAN_DELETE_MESSAGES = "can_delete_messages";
    private final static String JSON_FIELD_CAN_INVITE_USERS = "can_invite_users";
    private final static String JSON_FIELD_CAN_RESTRICT_MEMBERS = "can_restrict_members";
    private final static String JSON_FIELD_CAN_PIN_MESSAGES = "can_pin_messages";
    private final static String JSON_FIELD_CAN_PROMOTE_MEMBERS = "can_promote_members";
    private final static String JSON_FIELD_CAN_SEND_MESSAGES = "can_send_messages";
    private final static String JSON_FIELD_CAN_SEND_MEDIA_MESSAGES = "can_send_media_messages";
    private final static String JSON_FIELD_CAN_SEND_OTHER_MESSAGES = "can_send_other_messages";
    private final static String JSON_FIELD_CAN_ADD_WEB_PAGE_PREVIEWS = "can_add_web_page_previews";

    /**
     * Information about the user
     */
    @JsonProperty(JSON_FIELD_USER)
    public final User user;

    /**
     * The member's status in the chat.
     */
    @JsonProperty(JSON_FIELD_STATUS)
    public final ChatMemberStatus status;

    /**
     * <em>Optional.</em> Restictred and kicked only. Date when restrictions will be lifted for this user, unix time
     */
    @JsonProperty(JSON_FIELD_UNTIL_DATE)
    public final Integer untilDate;

    /**
     * <em>Optional.</em> Administrators only. True, if the bot is allowed to edit administrator privileges of that user
     */
    @JsonProperty(JSON_FIELD_CAN_BE_EDITED)
    public final Boolean canBeEdited;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can change the chat title, photo and other settings
     */
    @JsonProperty(JSON_FIELD_CAN_CHANGE_INFO)
    public final Boolean canChangeInfo;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can post in the channel, channels only
     */
    @JsonProperty(JSON_FIELD_CAN_POST_MESSAGES)
    public final Boolean canPostMessages;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can edit messages of other users, channels only
     */
    @JsonProperty(JSON_FIELD_CAN_EDIT_MESSAGES)
    public final Boolean canEditMessages;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can delete messages of other users
     */
    @JsonProperty(JSON_FIELD_CAN_DELETE_MESSAGES)
    public final Boolean canDeleteMessages;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can invite new users to the chat
     */
    @JsonProperty(JSON_FIELD_CAN_INVITE_USERS)
    public final Boolean canInviteUsers;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can restrict, ban or unban chat members
     */
    @JsonProperty(JSON_FIELD_CAN_RESTRICT_MEMBERS)
    public final Boolean canRestrictMembers;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can pin messages, supergroups only
     */
    @JsonProperty(JSON_FIELD_CAN_PIN_MESSAGES)
    public final Boolean canPinMessages;

    /**
     * <em>Optional.</em> Administrators only. True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
     */
    @JsonProperty(JSON_FIELD_CAN_PROMOTE_MEMBERS)
    public final Boolean canPromoteMembers;

    /**
     * <em>Optional.</em> Restricted only. True, if the user can send text messages, contacts, locations and venues
     */
    @JsonProperty(JSON_FIELD_CAN_SEND_MESSAGES)
    public final Boolean canSendMessages;

    /**
     * <em>Optional.</em> Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     */
    @JsonProperty(JSON_FIELD_CAN_SEND_MEDIA_MESSAGES)
    public final Boolean canSendMediaMessages;

    /**
     * <em>Optional.</em> Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies can_send_media_messages
     */
    @JsonProperty(JSON_FIELD_CAN_SEND_OTHER_MESSAGES)
    public final Boolean canSendOtherMessages;

    /**
     * <em>Optional.</em> Restricted only. True, if user may add web page previews to his messages, implies can_send_media_messages
     */
    @JsonProperty(JSON_FIELD_CAN_ADD_WEB_PAGE_PREVIEWS)
    public final Boolean canAddWebPagePreviews;

    /**
     * @param user                  Information about the user
     * @param status                The member's status in the chat. Can be "creator",
     *                              "administrator", "member", "left" or "kicked"
     * @param untilDate             <em>Optional.</em> Restictred and kicked only. Date when restrictions will be lifted for this user, unix time
     * @param canBeEdited           <em>Optional.</em> Administrators only. True, if the bot is allowed to edit administrator privileges of that user
     * @param canChangeInfo         <em>Optional.</em> Administrators only. True, if the administrator can change the chat title, photo and other settings
     * @param canPostMessages       <em>Optional.</em> Administrators only. True, if the administrator can post in the channel, channels only
     * @param canEditMessages       <em>Optional.</em> Administrators only. True, if the administrator can edit messages of other users, channels only
     * @param canDeleteMessages     <em>Optional.</em> Administrators only. True, if the administrator can delete messages of other users
     * @param canInviteUsers        <em>Optional.</em> Administrators only. True, if the administrator can invite new users to the chat
     * @param canRestrictMembers    <em>Optional.</em> Administrators only. True, if the administrator can restrict, ban or unban chat members
     * @param canPinMessages        <em>Optional.</em> Administrators only. True, if the administrator can pin messages, supergroups only
     * @param canPromoteMembers     <em>Optional.</em> Administrators only. True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
     * @param canSendMessages       <em>Optional.</em> Restricted only. True, if the user can send text messages, contacts, locations and venues
     * @param canSendMediaMessages  <em>Optional.</em> Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     * @param canSendOtherMessages  <em>Optional.</em> Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies can_send_media_messages
     * @param canAddWebPagePreviews <em>Optional.</em> Restricted only. True, if user may add web page previews to his messages, implies can_send_media_messages
     */
    public ChatMember(@JsonProperty(JSON_FIELD_USER) User user,
                      @JsonProperty(JSON_FIELD_STATUS) ChatMemberStatus status,
                      @JsonProperty(JSON_FIELD_UNTIL_DATE) Integer untilDate,
                      @JsonProperty(JSON_FIELD_CAN_BE_EDITED) Boolean canBeEdited,
                      @JsonProperty(JSON_FIELD_CAN_CHANGE_INFO) Boolean canChangeInfo,
                      @JsonProperty(JSON_FIELD_CAN_POST_MESSAGES) Boolean canPostMessages,
                      @JsonProperty(JSON_FIELD_CAN_EDIT_MESSAGES) Boolean canEditMessages,
                      @JsonProperty(JSON_FIELD_CAN_DELETE_MESSAGES) Boolean canDeleteMessages,
                      @JsonProperty(JSON_FIELD_CAN_INVITE_USERS) Boolean canInviteUsers,
                      @JsonProperty(JSON_FIELD_CAN_RESTRICT_MEMBERS) Boolean canRestrictMembers,
                      @JsonProperty(JSON_FIELD_CAN_PIN_MESSAGES) Boolean canPinMessages,
                      @JsonProperty(JSON_FIELD_CAN_PROMOTE_MEMBERS) Boolean canPromoteMembers,
                      @JsonProperty(JSON_FIELD_CAN_SEND_MESSAGES) Boolean canSendMessages,
                      @JsonProperty(JSON_FIELD_CAN_SEND_MEDIA_MESSAGES) Boolean canSendMediaMessages,
                      @JsonProperty(JSON_FIELD_CAN_SEND_OTHER_MESSAGES) Boolean canSendOtherMessages,
                      @JsonProperty(JSON_FIELD_CAN_ADD_WEB_PAGE_PREVIEWS) Boolean canAddWebPagePreviews
    ) {
        this.user = user;
        this.status = status;
        this.untilDate = untilDate;
        this.canBeEdited = canBeEdited;
        this.canChangeInfo = canChangeInfo;
        this.canPostMessages = canPostMessages;
        this.canEditMessages = canEditMessages;
        this.canDeleteMessages = canDeleteMessages;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessages = canPinMessages;
        this.canPromoteMembers = canPromoteMembers;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    @Override
    public boolean equals(Object m) {

        if (m instanceof ChatMember)
            return ((ChatMember) m).user.id == this.user.id;
        else
            return false;

    }

}
