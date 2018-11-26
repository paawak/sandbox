package com.swayam.geektrust.goldencrown;

import com.swayam.geektrust.goldencrown.model.Kingdom;

public interface IncomingMessageChecker {

    boolean messageAccepted(Kingdom kingdom, String rawMessage);

}
