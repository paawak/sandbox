package com.swayam.geektrust.goldencrown.service;

import com.swayam.geektrust.goldencrown.model.KingdomData;

public interface IncomingMessageChecker {

    boolean messageAccepted(KingdomData kingdom, String rawMessage);

}
