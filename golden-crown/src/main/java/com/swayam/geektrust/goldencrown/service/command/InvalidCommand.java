package com.swayam.geektrust.goldencrown.service.command;

import java.util.Map;

class InvalidCommand implements Command {

    @Override
    public String execute(Map<String, Object> context, String rawCommand) {
        throw new UnsupportedOperationException("This command is invalid!");
    }

    @Override
    public boolean canExecute(String rawCommand) {
        return false;
    }

}
