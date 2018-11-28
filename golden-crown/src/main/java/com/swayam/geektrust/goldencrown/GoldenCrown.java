package com.swayam.geektrust.goldencrown;

import java.util.Scanner;

import com.swayam.geektrust.goldencrown.dao.KingdomRepository;
import com.swayam.geektrust.goldencrown.dao.KingdomRepositoryImpl;
import com.swayam.geektrust.goldencrown.service.IncomingMessageChecker;
import com.swayam.geektrust.goldencrown.service.IncomingMessageCheckerImpl;
import com.swayam.geektrust.goldencrown.service.KingdomService;
import com.swayam.geektrust.goldencrown.service.KingdomServiceImpl;
import com.swayam.geektrust.goldencrown.service.command.Command;
import com.swayam.geektrust.goldencrown.service.command.CommandInterpreter;

public class GoldenCrown {

    public static void main(String[] args) {

	KingdomRepository kingdomRepository = new KingdomRepositoryImpl("/goldencrown/data/kingdom_data.properties");
	IncomingMessageChecker incomingMessageChecker = new IncomingMessageCheckerImpl();
	KingdomService kingdomService = new KingdomServiceImpl(kingdomRepository, incomingMessageChecker);
	CommandInterpreter commandInterpreter = new CommandInterpreter(kingdomService);

	Command commandToRepeat = null;
	int currentRepeatCount = 0;

	try (Scanner scanner = new Scanner(System.in);) {
	    while (scanner.hasNextLine()) {
		String rawCommand = scanner.nextLine();

		try {

		    Command command;

		    if (currentRepeatCount > 0) {
			currentRepeatCount--;
			command = commandToRepeat;
		    } else {
			command = commandInterpreter.parseCommand(rawCommand);
			int repeatCount = command.getRepeatTime();

			if (repeatCount > 0) {
			    currentRepeatCount = repeatCount;
			    commandToRepeat = command;
			} else {
			    currentRepeatCount = 0;
			    commandToRepeat = null;
			}
		    }

		    String result = command.execute(rawCommand);

		    if ((result != null) && (result.trim().length() > 0)) {
			System.out.println(result);
		    }

		} catch (Exception e) {
		    System.err.println(e.getMessage());
		}

	    }
	}

    }

}
