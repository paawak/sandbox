package com.swayam.geektrust.goldencrown.service.command;

public class InvalidCommand implements Command {

    @Override
    public String execute(String rawCommand) {
	throw new UnsupportedOperationException("This command is invalid!");
    }

}
