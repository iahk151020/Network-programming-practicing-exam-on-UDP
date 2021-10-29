/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author truon
 */
public class Employee implements Serializable{
    private int id;
    private float hsl;
    private String ten, dob, gt;
    private Hometown qq;
    
    private static int currentId = 1;

    public Employee(int id, float hsl, String ten, String dob, String gt, Hometown qq) {
        this.id = id;
        this.hsl = hsl;
        this.ten = ten;
        this.dob = dob;
        this.gt = gt;
        this.qq = qq;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHsl() {
        return hsl;
    }

    public void setHsl(float hsl) {
        this.hsl = hsl;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public Hometown getQq() {
        return qq;
    }

    public void setQq(Hometown qq) {
        this.qq = qq;
    }

    public static int getCurentId() {
        return currentId;
    }

    public static void setCurrentId() {
        currentId ++;
    }
    
    
    
    
    
    
    
   
}
