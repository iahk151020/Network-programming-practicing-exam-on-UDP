/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerApplication;

import Controllers.ServerController;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;

/**
 *
 * @author truon
 */
public class ServerApplication {
    public static void main(String[] args) throws SocketException, SQLException, IOException, ClassNotFoundException {
        ServerController server = new ServerController();
    }
}
