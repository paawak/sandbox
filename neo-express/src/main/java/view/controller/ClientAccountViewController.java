/*
 * ClientAccountViewController.java
 *
 * Created on April 1, 2004, 1:50 AM
 */

package view.controller;

/**
 *
 * @author  paawak
 */
import javax.swing.*;

import view.*;
import controller.*;
import model.database.*; 
import utils.database.*;

public class ClientAccountViewController implements TableListDB,ClientAccountDB{
    
    private ClientAccountView clientAccountView = null;
    private SemaphoresController smCntr = new SemaphoresController(clientaccount);
    private Database clAccDb = new Database(clientaccount);
    
    /** Creates a new instance of ClientAccountViewController */
    public ClientAccountViewController() {
    }
    
    /** method to set ClientAccountView panel to recieve fresh values: in new mode
     *
     *
     */
    public JPanel getNewClientAccountPanel() {
        clientAccountView = new ClientAccountView();
        clientAccountView.setAccNum(smCntr.getID());
        clientAccountView.setFormToAdd();
        return clientAccountView;
    }
    
    /**
     *set form to modify mode
     */
    public JPanel getModifyClientAccountPanel() {
        clientAccountView = new ClientAccountView();
        clientAccountView.setFormToModify();
        return clientAccountView;
    }    
    
}
