package com.swayam.geektrust.goldencrown;

public interface IncomingMessageChecker {

    boolean messageAccepted(Kingdom kingdom, String rawMessage);

}
