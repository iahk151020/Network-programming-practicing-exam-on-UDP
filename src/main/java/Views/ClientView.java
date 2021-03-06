/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Models.Employee;
import Models.Hometown;
import Views.AddEmpView;
import Views.ListEmpView;
import Views.SearchEmpView;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author truon
 */
public class ClientView extends javax.swing.JFrame {
    
    private AddEmpView aev;
    private SearchEmpView sev;
    private ListEmpView lev;
    /**
     * Creates new form ClientView
     */
    public ClientView() {
        initComponents();
        aev = new AddEmpView();
        sev = new SearchEmpView();
        lev = new ListEmpView();
        jTabbedPane1.add("add employee", aev);
        jTabbedPane1.add("search employee", sev);
        jTabbedPane1.add("list employee", lev);       
    }
    
    //set danh sách quê quán lên các nút combo box
    public void setQqList(List<Hometown> list){
        aev.setQqList(list);
        lev.setQqList(list);
    }
    
    //set các listner cho các button
    public void addListeners(ActionListener aevAl, ActionListener sevAl, ActionListener levAl ){
        System.out.println("added listeners to panels");
        aev.addListener(aevAl);
        sev.addListener(sevAl);
        lev.addListener(levAl);
    }
    
    //lấy thông tin người dùng để thêm vào csdl
    public Employee getInput(){
        return aev.getEmployeeInput();
    }
    
    //lấy tên quê quán muốn thống kê
    public String getQqInput(){
        return lev.getQqInput();
    }
    
    //Lấy tên nhân viên muốn tìm kiếm
    public String getEmployeeName(){
        return sev.getEmployeeName();
    }
    
    //Hiểm thị danh sách nhân viên trùng với tên tìm kiếm
    public void displaySearchingResult(List<Employee> result){
        sev.displaySearchingResult(result);
    }
    
    //Hiển trị danh sách nhân viên ở cùng quê quán
    public void displayEmpList(List<Employee> result){
        lev.displayEmpList(result);
    }
    
    public void setCurrentId(int currentId){
        aev.setCurrentId(currentId);
    }
    
    public void clearTextFields(){
        aev.clearTextFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
