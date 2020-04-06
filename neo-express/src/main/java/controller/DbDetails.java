/*
 * DbDetails.java
 *
 * Created on Jun 8, 2009 11:07:47 PM
 *
 * Copyright (c) 2002 - 2008 : Swayam Inc.
 *
 * P R O P R I E T A R Y & C O N F I D E N T I A L
 *
 * The copyright of this document is vested in Swayam Inc. without
 * whose prior written permission its contents must not be published,
 * adapted or reproduced in any form or disclosed or
 * issued to any third party.
 */

package controller;

import utils.general.ReadPropertyFile;

/**
 * 
 * @author paawak
 */
class DbDetails {

    private static final String PROPS_FILE = "controller/props/CommonProps";

    private final String driver;
    private final String url;
    private final String user;
    private final String password;

    DbDetails() {
        ReadPropertyFile readMainProps = new ReadPropertyFile(PROPS_FILE);
        driver = readMainProps.getVal("DB_DRIVER");
        url = readMainProps.getVal("DB_URL");
        user = readMainProps.getVal("DB_USER");
        password = readMainProps.getVal("DB_PASSWORD");
    }

    String getDriver() {
        return driver;
    }

    String getUrl() {
        return url;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

}