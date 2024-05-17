/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.*;
import DTO.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class CanHoBLL {
    CanHoDAL CanHo_DAL = new CanHoDAL();
    
    public ArrayList<CanHo> ListCanHo(){
        return CanHo_DAL.dsCanHo();
        
    }
    
    public ArrayList<String> LoadCbbMaCanHo(){
        return CanHo_DAL.CbbMaCanHo();
    }
    
    public int updateCanHo(CanHo ch){
        int result = CanHo_DAL.updateCanHo(ch);
        return result;
    }
    
    public ArrayList<String> LoadCbbMaCanHoDaBan(){
        return CanHo_DAL.CbbMaCanHoDaBan();
    }
    
    public ArrayList<CanHo> TimKiemCanHo(String info){
        return CanHo_DAL.TimKiemCanHo(info);
    }
}
