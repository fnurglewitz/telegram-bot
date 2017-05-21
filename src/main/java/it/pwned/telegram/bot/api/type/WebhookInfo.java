package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Contains information about the current status of a webhook.
 */
public class WebhookInfo {

    private final static String JSON_FIELD_URL = "url";
    private final static String JSON_FIELD_HAS_CUSTOM_CERTIFICATE = "has_custom_certificate";
    private final static String JSON_FIELD_PENDING_UPDATE_COUNT = "pending_update_count";
    private final static String JSON_FIELD_LAST_ERROR_DATE = "last_error_date";
    private final static String JSON_FIELD_LAST_ERROR_MESSAGE = "last_error_message";
    private final static String JSON_FIELD_MAX_CONNECTIONS = "max_connections";
    private final static String JSON_FIELD_ALLOWED_UPDATES = "allowed_updates";

    /**
     * Webhook URL, may be empty if webhook is not set up
     */
    @JsonProperty(JSON_FIELD_URL)
    public final String url;

    /**
     * True, if a custom certificate was provided for webhook certificate checks
     */
    @JsonProperty(JSON_FIELD_HAS_CUSTOM_CERTIFICATE)
    public final Boolean hasCustomCertificate;

    /**
     * Number of updates awaiting delivery
     */
    @JsonProperty(JSON_FIELD_PENDING_UPDATE_COUNT)
    public final Integer pendingUpdateCount;

    /**
     * <em>Optional.</em> Unix time for the most recent error that happened when trying to deliver an update via webhook
     */
    @JsonProperty(JSON_FIELD_LAST_ERROR_DATE)
    public final Integer lastErrorDate;

    /**
     * <em>Optional.</em> Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook
     */
    @JsonProperty(JSON_FIELD_LAST_ERROR_MESSAGE)
    public final String lastErrorMessage;

    /**
     * <em>Optional.</em> Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery
     */
    @JsonProperty(JSON_FIELD_MAX_CONNECTIONS)
    public final Integer maxConnections;

    /**
     * <em>Optional.</em> A list of update types the bot is subscribed to. Defaults to all update types
     */
    @JsonProperty(JSON_FIELD_ALLOWED_UPDATES)
    public final List<String> allowedUpdates;

    /**
     * @param url                  Webhook URL, may be empty if webhook is not set up
     * @param hasCustomCertificate True, if a custom certificate was provided for webhook certificate checks
     * @param pendingUpdateCount   Number of updates awaiting delivery
     * @param lastErrorDate        <em>Optional.</em> Unix time for the most recent error that happened when trying to deliver an update via webhook
     * @param lastErrorMessage     <em>Optional.</em> Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook
     * @param maxConnections       <em>Optional.</em> Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery
     * @param allowedUpdates       <em>Optional.</em> A list of update types the bot is subscribed to. Defaults to all update types
     */
    public WebhookInfo(@JsonProperty(JSON_FIELD_URL) String url,
                       @JsonProperty(JSON_FIELD_HAS_CUSTOM_CERTIFICATE) Boolean hasCustomCertificate,
                       @JsonProperty(JSON_FIELD_PENDING_UPDATE_COUNT) Integer pendingUpdateCount,
                       @JsonProperty(JSON_FIELD_LAST_ERROR_DATE) Integer lastErrorDate,
                       @JsonProperty(JSON_FIELD_LAST_ERROR_MESSAGE) String lastErrorMessage,
                       @JsonProperty(JSON_FIELD_MAX_CONNECTIONS) Integer maxConnections,
                       @JsonProperty(JSON_FIELD_ALLOWED_UPDATES) List<String> allowedUpdates) {
        this.url = url;
        this.hasCustomCertificate = hasCustomCertificate;
        this.pendingUpdateCount = pendingUpdateCount;
        this.lastErrorDate = lastErrorDate;
        this.lastErrorMessage = lastErrorMessage;
        this.maxConnections = maxConnections;
        this.allowedUpdates = allowedUpdates == null ? null : Collections.unmodifiableList(allowedUpdates);
    }

}
