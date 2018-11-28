package com.swayam.geektrust.goldencrown;

import java.io.InputStream;
import java.io.PrintStream;

import com.swayam.geektrust.goldencrown.dao.KingdomRepository;
import com.swayam.geektrust.goldencrown.dao.KingdomRepositoryImpl;
import com.swayam.geektrust.goldencrown.service.IncomingMessageChecker;
import com.swayam.geektrust.goldencrown.service.IncomingMessageCheckerImpl;
import com.swayam.geektrust.goldencrown.service.InputOutputHandler;
import com.swayam.geektrust.goldencrown.service.KingdomService;
import com.swayam.geektrust.goldencrown.service.KingdomServiceImpl;
import com.swayam.geektrust.goldencrown.service.command.CommandFactory;

public class GoldenCrown {

    public static void main(String[] args) {

        InputStream inputStream = System.in;
        PrintStream standardOut = System.out;
        PrintStream errorOut = System.err;

        KingdomRepository kingdomRepository = new KingdomRepositoryImpl("/goldencrown/data/kingdom_data.properties");
        IncomingMessageChecker incomingMessageChecker = new IncomingMessageCheckerImpl();
        KingdomService kingdomService = new KingdomServiceImpl(kingdomRepository, incomingMessageChecker);
        CommandFactory commandFactory = new CommandFactory(kingdomService);

        new InputOutputHandler(commandFactory).invokeAndWait(inputStream, standardOut, errorOut);

    }

}
