package com.swayam.geektrust.goldencrown.service.command;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public interface Command {

    boolean canExecute(String rawCommand);

    String execute(Map<String, Object> context, String rawCommand);

    default Optional<Entry<String, Object>> newContextEntry() {
        return Optional.empty();
    }

}
