/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Employee;
import Models.Hometown;
import Models.Message;
import Views.AddEmpView;
import java.net.DatagramSocket;
import Views.ClientView;
import Views.ListEmpView;
import Views.SearchEmpView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author truon
 */
public class ClientController {
    private int clientPort = 6666;
    private DatagramSocket clientSocket;
    private ClientView view;
    private AddEmpView aev;
    private ListEmpView lev;
    private SearchEmpView sev;
    
    public ClientController(ClientView clientView) throws IOException, ClassNotFoundException{
        openConnection(); //Tạo socket kết nối tới server
        this.view = clientView; //Add client view vào controller
        sendData(new Message(4,null)); //Lấy danh sách cquê quán để hiển thị cho client chọn để nhập thông tin hoặc tìm kiếm
        List<Hometown> qqList = (List<Hometown>)receiveData();
        //Thêm các listner cho các button
        view.addListeners(new addEmployeeListener(), new getEmployeeListener(), new getSameHometownEmployeeListener());
        //Lưu danh sách quê quán tại lớp View để hiển thị
        view.setQqList(qqList);
        //Set id dể nhập nhân viên tiếp theo
        view.setCurrentId(Employee.getCurentId());
    }
    
    
    
    class addEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
  
            //Lấy giá thông tin đã nhập dưới dạng đối tượng Employee
            Employee emp = view.getInput();
            try {
                sendData(new Message(1,emp));
                Object result = receiveData();
                view.clearTextFields();
                Employee.setCurrentId();
                view.setCurrentId(Employee.getCurentId());
                if (result == "ok")
                    JOptionPane.showMessageDialog(view, "Add nhân viên thành công");
                else 
                   JOptionPane.showMessageDialog(view, "Thêm nhân viên thất bại");
          
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
               Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class getEmployeeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //Lấy tên nhân viên cần tìm kiếm
           String employeeName = view.getEmployeeName();
            try {
                sendData(new Message(2,employeeName));
                List<Employee> result = (List<Employee>)receiveData();
                //Truyền dữ liệu danh sách kết quả về cho lớp ClientView để nó hiển thị
                view.displaySearchingResult(result);
                
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    
    class getSameHometownEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
           //Lấy tên tỉnh thành muốn thống kê nhân viên
           String qqName = view.getQqInput();
            try {
                sendData(new Message(3,qqName));
                List<Employee> result = (List<Employee>)receiveData();
                //Truyền dữ liệu danh sách nhân viên về cho lớp ClientView để nó hiển thị
                view.displayEmpList(result);
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
        }
        
    }
    
    public void openConnection() throws SocketException{
        clientSocket = new DatagramSocket(clientPort);
    }
    
    public void closeConnection(){
        clientSocket.close();
    }
    
    public void sendData(Object obj) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        
        InetAddress IPAddress = InetAddress.getByName(ServerController.serverhost);                
        byte[] sendData = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, ServerController.serverPort);
        clientSocket.send(sendPacket);
        System.out.println("sent a packet");
                   
    }
    
    public Object receiveData() throws IOException, ClassNotFoundException{
        //Vì chương trình có thể sẽ nhận lại kết quả là 1 danh sách dài nên cần khai báo size() của receiveData lớn 1 chút
        byte[] receiveData = new byte[1000000];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        System.out.println("received a packet from server");
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
        
         
    }
    
    
}
