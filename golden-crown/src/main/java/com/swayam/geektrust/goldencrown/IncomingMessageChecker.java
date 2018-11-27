package com.swayam.geektrust.goldencrown;

import com.swayam.geektrust.goldencrown.model.KingdomData;

public interface IncomingMessageChecker {

    boolean messageAccepted(KingdomData kingdom, String rawMessage);

}
