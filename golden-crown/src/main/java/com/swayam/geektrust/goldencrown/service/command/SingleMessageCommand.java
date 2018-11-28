package com.swayam.geektrust.goldencrown.service.command;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class SingleMessageCommand extends AbstractPatternMatchingCommand {

    private static final String INPUT_MESSAGE_REGEX = "^(input:)\\s+\\w+\\,\\s+\".+\"$";

    private final KingdomService kingdomService;

    public SingleMessageCommand(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @Override
    public String execute(Map<String, Object> context, String rawCommand) {

        if (!context.containsKey(InputMessagingStartCommand.INPUT_MESSAGE_FROM_KINDOM_KEY)) {
            throw new IllegalArgumentException("The key `" + InputMessagingStartCommand.INPUT_MESSAGE_FROM_KINDOM_KEY + "` is not set in the context");
        }

        Kingdom to = getKingdomToSendMessage(rawCommand);
        String message = getMessageToSend(rawCommand);

        Kingdom from = (Kingdom) context.get(InputMessagingStartCommand.INPUT_MESSAGE_FROM_KINDOM_KEY);
        boolean accepted = kingdomService.sendMessage(from, to, message);

        return "Message sent from " + from + " to " + to + " was" + (accepted ? " " : " NOT ") + "successful";
    }

    @Override
    String getRegexPattern() {
        return INPUT_MESSAGE_REGEX;
    }

    /* added for testing */
    Kingdom getKingdomToSendMessage(String rawCommand) {
        String kingdom = rawCommand.split(",")[0].trim().split(":")[1].trim().toUpperCase();
        return Kingdom.valueOf(kingdom);
    }

    /* added for testing */
    String getMessageToSend(String rawCommand) {
        String messageWithQuote = rawCommand.split(",")[1].trim();
        return messageWithQuote.substring(1, messageWithQuote.length() - 1);
    }

}
