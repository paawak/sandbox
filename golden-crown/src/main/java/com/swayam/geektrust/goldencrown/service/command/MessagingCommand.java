package com.swayam.geektrust.goldencrown.service.command;

import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class MessagingCommand implements Command {

    static final String START_SENDING_MESSAGES_REGEX = "^messages\\s+to\\s+\\d\\s+kingdoms\\s+from\\s+king\\s+shan\\s*$";

    private static final String INPUT_MESSAGE_REGEX = "^\\w+\\,\\s+\".+\"$";

    private final KingdomService kingdomService;
    private final Kingdom from;
    private final int repeatTime;

    public MessagingCommand(KingdomService kingdomService, Kingdom from, int repeatTime) {
	this.kingdomService = kingdomService;
	this.from = from;
	this.repeatTime = repeatTime;
    }

    @Override
    public String execute(String rawCommand) {

	String rawCommandInLowerCase = rawCommand.toLowerCase();
	if (Pattern.matches(START_SENDING_MESSAGES_REGEX, rawCommandInLowerCase)) {
	    return "Enter the " + repeatTime + " messages to send";
	} else if (!Pattern.matches(INPUT_MESSAGE_REGEX, rawCommandInLowerCase)) {
	    throw new IllegalArgumentException("Invalid message format");
	}

	Kingdom to = getKingdomToSendMessage(rawCommand);
	String message = getMessageToSend(rawCommand);

	boolean accepted = kingdomService.sendMessage(from, to, message);

	return "Message sent from " + from + " to " + to + " was" + (accepted ? " " : " NOT ") + "successful";
    }

    @Override
    public int getRepeatTime() {
	return repeatTime;
    }

    /* added for testing */
    Kingdom getFrom() {
	return from;
    }

    /* added for testing */
    Kingdom getKingdomToSendMessage(String rawCommand) {
	String kingdom = rawCommand.split(",")[0].trim().toUpperCase();
	return Kingdom.valueOf(kingdom);
    }

    /* added for testing */
    String getMessageToSend(String rawCommand) {
	String messageWithQuote = rawCommand.split(",")[1].trim();
	return messageWithQuote.substring(1, messageWithQuote.length() - 1);
    }

}
