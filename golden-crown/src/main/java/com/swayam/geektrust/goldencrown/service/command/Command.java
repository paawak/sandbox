package com.swayam.geektrust.goldencrown.service.command;

public interface Command {

    String execute(String rawCommand);

    default int getRepeatTime() {
	return 0;
    }

}
