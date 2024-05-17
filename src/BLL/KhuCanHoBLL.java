/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.*;
import DTO.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author PC
 */
public class KhuCanHoBLL {
    KhuCanHoDAL KhuCan_HoDAL = new KhuCanHoDAL();
    CanHoDAL CanHo_DAL = new CanHoDAL();
    public ArrayList<KhuCanHo> ListKhuCanHo(){
       return  KhuCan_HoDAL.dsKhuCanHo();
    }
    public ArrayList<String> LoadCbbMaKhu(){
        return KhuCan_HoDAL.CbbMaKhu();
    }
    
    public ArrayList<KhuCanHo> TimKiem_BLL (String info){
//        ArrayList <KhuCanHo> dsKhuCanHoTK = new ArrayList<KhuCanHo>();
//        dsKhuCanHoTK = KhuCan_HoDAL.TimKiem_DAL(info);
        return KhuCan_HoDAL.TimKiem_DAL(info);
    }
    public int deleteKhuCanHo(KhuCanHo kch){
        int result = KhuCan_HoDAL.deleteKhuCanHo(kch);
        return result;
        
    }
    
    public int updateKhuCanHo(KhuCanHo kch){
        int result = KhuCan_HoDAL.updateKhuCanHo(kch);
        return result;
    }
    
    public boolean KiemTraTrungTenKhu(String TenKhu){
        ArrayList <KhuCanHo> list = new ArrayList<>();
        list = KhuCan_HoDAL.dsKhuCanHo();
        for(KhuCanHo khu : list){
            if(TenKhu.trim().equalsIgnoreCase(khu.getTenKhu().trim()))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean insert(String tenKhu,int soTang,int soCanTT, String diaChi, float dienTich, long gia,int soPhong){

        String lastID;
        char first_lastID;
        char second_lastID;
        String maKhu = null;
        
        if(KhuCan_HoDAL.dsKhuCanHo().isEmpty()){
             maKhu = "EA";// chưa có khu nào lấy giá trị mặc định mã khu là EA
        }else{
            lastID = KhuCan_HoDAL.dsKhuCanHo().get(KhuCan_HoDAL.dsKhuCanHo().size()-1).getMaKhu();
            first_lastID =  lastID.charAt(0);
            second_lastID = lastID.charAt(1);
            
            if(first_lastID == 'Z' && second_lastID == 'Z'){ //gioi han ten khu ZZ
                JOptionPane.showMessageDialog(null,"System Error!");
            }else if(second_lastID == 'Z'){
                first_lastID = (char)((int)first_lastID+1);
                second_lastID = 'A';
                maKhu = String.valueOf(first_lastID) + String.valueOf(second_lastID);
            }else{
                second_lastID = (char)((int)second_lastID+1);
                maKhu = String.valueOf(first_lastID) + String.valueOf(second_lastID);
            }
        } // tao ma khu chay tu dong
        
        ArrayList<CanHo> list = new ArrayList<>();// tạo danh sách là những căn hộ trong khu
        for(int i = 1;i<=soTang;i++) {
            for(int j=1;j<=soCanTT;j++) {
		if(i<10 && j<10) { // chi hien dung khi so tang <100 va so can/tang <100

                    list.add(new CanHo(maKhu+"0"+i+"0"+j, dienTich, gia, false, soPhong,null, maKhu));
                    
		} else if (i <10) {
                    
                    list.add(new CanHo(maKhu+"0"+i+j, dienTich, gia, false, soPhong,null, maKhu)); 
                    
		} else if (j<10) {
                    
                    list.add(new CanHo(maKhu+i+"0"+j, dienTich, gia, false, soPhong,null, maKhu));
                    
		} else {
                    
                    list.add(new CanHo(maKhu+i+j, dienTich, gia, false, soPhong,null, maKhu));
                    
		}
            }		
	}
        
        return KhuCan_HoDAL.insert(new KhuCanHo(maKhu, tenKhu, soTang, soCanTT, diaChi)) && CanHo_DAL.inserts(list);
        // trả về true nếu thêm thành công cả khu và căn hộ trong khu
    }
}
