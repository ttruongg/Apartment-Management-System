/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DTO.*;
import DAL.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ThongTinCanHoBLL {
    ThongTinCanHoDAL ThongTin_CanHoDAL = new ThongTinCanHoDAL();
    public ArrayList<CanHo> dsCanHo(){
        return ThongTin_CanHoDAL.dsCanHo();
    }
    
    public  ArrayList<CanHo> dsCanHoDaBan(){
        return ThongTin_CanHoDAL.dsCanHoDaBan();
    }
    
    public ArrayList<String> CbbSoPhong(){
        return ThongTin_CanHoDAL.CbbSoPhong();
    }
    
    public ArrayList<String> CbbGia(){
        return ThongTin_CanHoDAL.CbbGia();
    }
    
    public ArrayList<String> CbbDienTich(){
        return ThongTin_CanHoDAL.CbbDienTich();
    }
    
    public ArrayList<CanHo> TimKiemCanHo(Float DT, long gia, int phong ){
        return ThongTin_CanHoDAL.TimKiemCanHo(DT, gia, phong);
    }
    
    public int updateCH_DAL(String mach,String macd){
        int result = ThongTin_CanHoDAL.updateCH_DAL(mach, macd);
        return result;
    }
    
    public ArrayList<CanHo> ListCanHoDaBan(String month, String year){
        return ThongTin_CanHoDAL.ListCanHoDaBan(month, year);
    }
}
