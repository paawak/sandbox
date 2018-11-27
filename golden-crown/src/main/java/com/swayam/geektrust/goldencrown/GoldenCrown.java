package com.swayam.geektrust.goldencrown;

import java.util.Scanner;

import com.swayam.geektrust.goldencrown.service.CommandInterpreter;
import com.swayam.geektrust.goldencrown.service.KingdomService;
import com.swayam.geektrust.goldencrown.service.command.Command;

public class GoldenCrown {

    public static void main(String[] args) {

	KingdomService kingdomService = null;
	CommandInterpreter commandInterpreter = new CommandInterpreter(kingdomService);

	Command commandToRepeat = null;
	int currentRepeatCount = 0;

	try (Scanner scanner = new Scanner(System.in);) {
	    while (scanner.hasNextLine()) {
		String rawCommand = scanner.nextLine();

		try {
		    Command command = commandInterpreter.parseCommand(rawCommand);

		    int repeatCount = command.repeatTimes();

		    if ((currentRepeatCount == 0) && (repeatCount > 0)) {
			currentRepeatCount = repeatCount;
			commandToRepeat = command;
		    }

		} catch (Exception e) {
		    System.err.println(e.getMessage());
		}

	    }
	}

    }

}
