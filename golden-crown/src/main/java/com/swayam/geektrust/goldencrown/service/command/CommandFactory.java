package com.swayam.geektrust.goldencrown.service.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.swayam.geektrust.goldencrown.service.KingdomService;

public class CommandFactory {

    private final KingdomService kingdomService;
    private final List<Command> allAvailableCommands;

    public CommandFactory(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
        allAvailableCommands = initializeCommands();
    }

    public List<Command> getAllAvailableCommands() {
        return allAvailableCommands;
    }

    public Command getUnhandledCommand() {
        return new InvalidCommand();
    }

    private List<Command> initializeCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new AlliesOfKingFinderCommand(kingdomService));
        commands.add(new AlliesOfRulerFinderCommand(kingdomService));
        commands.add(new InputMessagingStartCommand(kingdomService));
        commands.add(new RulerFinderCommand(kingdomService));
        commands.add(new SingleMessageCommand(kingdomService));
        return Collections.unmodifiableList(commands);
    }

}
