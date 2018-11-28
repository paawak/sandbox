package com.swayam.geektrust.goldencrown.service.command;

import java.util.regex.Pattern;

public abstract class AbstractPatternMatchingCommand implements Command {

    @Override
    public final boolean canExecute(String rawCommand) {
        return Pattern.matches(getRegexPattern(), rawCommand.toLowerCase());
    }

    abstract String getRegexPattern();

}
