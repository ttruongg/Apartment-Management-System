/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DTO.*;
import DAL.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ThongTinMuaBanBLL {
    ThongTinMuaBanDAL ThongTin_MuaBanDAL = new ThongTinMuaBanDAL();
    ThongTinCanHoDAL ThongTin_CanHoDAL = new ThongTinCanHoDAL();
    public ArrayList<HopDong> ListHopDong(){
        return ThongTin_MuaBanDAL.dsHopDong();
    }
    
    public ArrayList<HopDong> TimKiem_BLL (String info){
        ArrayList <HopDong> dsHopDongTK = new ArrayList<HopDong>();
        dsHopDongTK = ThongTin_MuaBanDAL.TimKiem_DAL(info);
        return dsHopDongTK;
    }
    
    public long ThongKeDoanhThu(ArrayList<CanHo> dsCanHo){
        long doanhthu = 0;
        long gia = 0;
        
        for (CanHo dsCh : dsCanHo) {
                gia= dsCh.getGia();
                doanhthu +=gia;
            }
        return doanhthu;
    }
//    public long doanhThu(){
//         long doanhThu=0;
//        
//            ArrayList<CanHo> dsCanHo=ThongTin_CanHoDAL.dsCanHoDaBan();
//           
//            long gia=0;
//            for (CanHo dsCh : dsCanHo) {
//                gia= dsCh.getGia();
//                doanhThu +=gia;
//            }
//        
//        return doanhThu;
//    }
    
    public int insertHD_DAL(String mahd,String ngaygd,String diachikh,String macd,String mach){
        int result = ThongTin_MuaBanDAL.insertHD_DAL(mahd, ngaygd, diachikh, macd, mach);
        return result;
    }
    
    
}
