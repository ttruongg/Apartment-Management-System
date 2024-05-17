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
public class QuanLyCuDanBLL {
    QuanLyCuDanDAL QuanLy_CuDanDAL = new QuanLyCuDanDAL();
    
    public ArrayList<CuDan> dsCuDan(){
        return QuanLy_CuDanDAL.ListCuDan();
    }
    public ArrayList<CuDan> TimKiem_BLL(String info){
        
        return QuanLy_CuDanDAL.TimKiem_DAL(info);
    }
    
    public int UpdateCuDan (CuDan cd){
        int result = QuanLy_CuDanDAL.UpdateCuDan(cd);
        return result;
    }
    
     public ArrayList<String> LoadCbbMaCuDan(){
         return QuanLy_CuDanDAL.CbbMaCuDan();
     }
     
      public int insertCD_DAL(String macd,String tencd, String ngsinh, String gioitinh, String sdt, String socmt, String quequan){
          int result = QuanLy_CuDanDAL.insertCD_DAL(macd, tencd, ngsinh, gioitinh, sdt, socmt, quequan);
          return result;
      }
      
      public ArrayList<CuDan> ResidentByApartment(String Mach){
        return QuanLy_CuDanDAL.ResidentByApartment(Mach);
    }
      
      public int insertCTCD(String mcd, String mch){
          int result = QuanLy_CuDanDAL.insertCTCD(mcd, mch);
          return result;
      }
      public int deleteCTCD(String mcd){
          int result = QuanLy_CuDanDAL.deleteCTCD(mcd);
          return result;
      }
      public int deleteCD(String mcd){
          int result = QuanLy_CuDanDAL.deleteCD(mcd);
          return result;
      }
}
