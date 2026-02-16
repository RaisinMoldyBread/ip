package raisinchat.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import raisinchat.exceptions.RaisinChatException;

/***
 * An abstraction of the DateTime custom formatter which is used often for datetime parsing
 */
public final class DateTimeParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a", Locale.ENGLISH);

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param input date-time string to parse
     * @return parsed LocalDateTime object
     * @throws RaisinChatException if the input format is invalid
     */
    public static LocalDateTime parse(String input) throws RaisinChatException {
        assert input != null;

        try {
            return LocalDateTime.parse(input.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RaisinChatException(
                    "Invalid date format. Use yyyy-MM-dd hh:mm AM/PM");
        }
    }
}
