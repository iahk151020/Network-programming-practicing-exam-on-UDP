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
public class Hometown implements Serializable{
    private int id;
    private String dc;

    public Hometown(int id, String dc) {
        this.id = id;
        this.dc = dc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }
  
    

   
    
}
