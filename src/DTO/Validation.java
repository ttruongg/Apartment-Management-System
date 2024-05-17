/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Validation {
    
    public static boolean isEmpty(JTextField txtField, String msg){
        String txtValue = txtField.getText().trim();
        if (txtValue.length() == 0){
            JOptionPane.showMessageDialog(null, msg);
            txtField.setBackground(Color.yellow);
            txtField.requestFocus();
            return true;
        }
        txtField.setBackground(Color.white);
        return false;

    }
    
    public static boolean isNumber(JTextField txtField, String msg){
        String txtValue = txtField.getText().trim();
        String strPtn = "^\\d{1,}$"; // tìm kiếm Regular Expression in java
        if (!txtValue.matches(strPtn)){
            JOptionPane.showMessageDialog(null, msg);
            txtField.setBackground(Color.yellow);
            txtField.requestFocus();
            return true;
        }
        txtField.setBackground(Color.white);
        return false;

    }
    
    
    
    
}
