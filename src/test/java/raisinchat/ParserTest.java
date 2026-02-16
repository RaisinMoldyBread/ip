package raisinchat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import raisinchat.command.Command;
import raisinchat.exceptions.RaisinChatException;

public class ParserTest {

    @Test
    public void parse_emptyInput_throwsException() {
        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> Parser.parse("   ")
        );

        assertTrue(e.getMessage().contains("Empty command received"));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        RaisinChatException e = assertThrows(
                RaisinChatException.class, () -> Parser.parse("unknownCommand")
        );

        assertTrue(e.getMessage().contains("unknowncommand"));
    }

    @Test
    public void parse_exitCommand_isExit() throws RaisinChatException {
        Command c = Parser.parse("exit");

        assertTrue(c.isExit());
    }
}
