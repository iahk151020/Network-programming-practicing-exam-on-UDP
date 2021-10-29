/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerApplication;

import Controllers.ClientController;
import Controllers.ServerController;
import Views.ClientView;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class ClientApplication {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientView cv = new ClientView();
        ClientController cc = new ClientController(cv);
        cv.setVisible(true);
       
    }
}
