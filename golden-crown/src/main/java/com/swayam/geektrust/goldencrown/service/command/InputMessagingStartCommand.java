package com.swayam.geektrust.goldencrown.service.command;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class InputMessagingStartCommand extends AbstractPatternMatchingCommand {

    public static final String INPUT_MESSAGE_FROM_KINDOM_KEY = "_INPUT_MESSAGE_FROM_KINDOM_KEY_";

    private static final String START_SENDING_MESSAGES_REGEX = "^messages\\s+to\\s+kingdoms\\s+from\\s+king\\s+\\w+\\s*$";

    private final KingdomService kingdomService;

    private Kingdom from;

    public InputMessagingStartCommand(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @Override
    public String execute(Map<String, Object> context, String rawCommand) {

        String[] tokens = rawCommand.trim().split("\\s");
        String kingName = tokens[tokens.length - 1].trim();
        from = kingdomService.getKingdomData(kingName).getKingdom();

        return "Enter the messages to send";
    }

    @Override
    public Optional<Entry<String, Object>> newContextEntry() {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new SimpleEntry<>(INPUT_MESSAGE_FROM_KINDOM_KEY, from));
    }

    @Override
    String getRegexPattern() {
        return START_SENDING_MESSAGES_REGEX;
    }

}
