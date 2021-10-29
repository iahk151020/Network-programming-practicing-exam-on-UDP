/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Employee;
import Models.Hometown;
import Models.Message;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class ServerController {
    public static int serverPort = 5555;
    public static String serverhost = "localhost";
    private Connection con;
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket = null;
    private List<Hometown> qqlist;
    
    public ServerController() throws SocketException, SQLException, IOException, ClassNotFoundException{
        //Kết nối CSDL
        getDbConnection("npdb","root","Khai15102@");
        //Tạo Socket cho server để nhận dữ liệu từ các socket của client
        openServer();
        //Lấy dữ liệu danh sách quê quán từ CSDL để gửi cho client view, truy vấn duy nhất 1 lần 
        qqlist = getHometownList();
        while(true){
            listening();
        }
        
    }
    
    private void getDbConnection(String dbName, String username, String password){
    String dbUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName+"?serverTimezone=UTC";
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
          Class.forName(dbClass);
          con = DriverManager.getConnection (dbUrl, username, password);
          System.out.println("connected to DB");
        }catch(Exception e) {
         e.printStackTrace();
        } 
    }
    
    private void openServer() throws SocketException{
        serverSocket = new DatagramSocket(serverPort);
    }
    
    private Object receiveData() throws IOException, ClassNotFoundException{
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
    
    private void sendData(Object result) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(result);
        oos.flush();            
            
        InetAddress IPAddress = receivePacket.getAddress();       
        int clientPort = receivePacket.getPort();
        byte[] sendData = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
        serverSocket.send(sendPacket);
    }
    
    private void listening() throws IOException, ClassNotFoundException, SQLException{
        Message msg = (Message)receiveData();
        int type = msg.getType();
        Object result = null;
        if (type == 1){
            result = addEmployee((Employee) msg.getObj());
        } else if(type == 2){
            result = searchEmployee((String) msg.getObj());
        } else if(type == 3) {
            result = getSameHometownEmployeeList((String)msg.getObj());
        } else {
            result = qqlist;
        }
        sendData(result);
    }
    
    private String addEmployee(Employee e) throws SQLException{
        String query = "INSERT INTO employee VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, e.getId());
        ps.setString(2, e.getTen().toLowerCase());
        ps.setString(3, e.getDob());
        ps.setString(4, e.getGt());
        ps.setFloat(5, e.getHsl());
        ps.setInt(6, e.getQq().getId());
        ps.execute();
        return "ok";
        
    }
    
    private List<Hometown> getHometownList() throws SQLException{
        String query = "SELECT * FROM hometown";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Hometown> res = new ArrayList<Hometown>();
        while (rs.next()){
            int id = rs.getInt(1);
            String dc = rs.getString(2);
            res.add(new Hometown(id,dc));
        }
        return res;
    }
    
    private List<Employee> searchEmployee(String ten) throws SQLException{
        ten = ten.toLowerCase();
        String query = "SELECT * FROM employee WHERE name = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,ten);
        ResultSet rs = ps.executeQuery();
        List<Employee> eList = new ArrayList<Employee>();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String dob = rs.getString(3);
            String gt = rs.getString(4);
            float hsl = rs.getFloat(5);
            int qqid = rs.getInt(6);
            Hometown qq = qqlist.get(qqid - 1);
            eList.add(new Employee(id,hsl,name,dob,gt,qq));
        }
        return eList;
        
        
    }
    
    private List<Employee> getSameHometownEmployeeList(String qqname) throws SQLException{
        int qqid = 0;
        for(int i=0; i<qqlist.size(); i++){
            if (qqlist.get(i).getDc().equals(qqname)){
                qqid = i+1;
                break;
            }
        }
        String query = "SELECT * FROM employee WHERE hometownId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,qqid);
        ResultSet rs = ps.executeQuery();
        List<Employee> eList = new ArrayList<Employee>();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String dob = rs.getString(3);
            String gt = rs.getString(4);
            float hsl = rs.getFloat(5);
            Hometown qq = qqlist.get(qqid - 1);
            eList.add(new Employee(id,hsl,name,dob,gt,qq));
        }
        return eList; 
    }
}
