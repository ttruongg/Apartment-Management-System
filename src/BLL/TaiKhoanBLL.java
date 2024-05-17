/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.*;
import DTO.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableModel;
/**
 *
 * @author PC
 */
public class TaiKhoanBLL {
    
    
    TaiKhoanDAL TK = new TaiKhoanDAL();
    
    public ArrayList<TaiKhoan> dsTaiKhoan(){
        return TK.dsTaiKhoan();
    }
    
    
    public TaiKhoan dangNhapBLL (String userName,String passWord){
        TaiKhoan tk = TK.dangNhap(userName, passWord);
        return tk;
    }
    
    public boolean GetVaiTro(TaiKhoan tk){
        return tk.isVaiTro();
    }
    
    public int insert(TaiKhoan tk){
        int result = TK.insert(tk);
        return result;
    }
    
    public int updateNV(String tenTaiKhoan,String matKhau){
        int result = TK.update(tenTaiKhoan,matKhau);
        return result;
    }
    
    public int deleteNV(TaiKhoan tk){
        int result = TK.delete(tk);
        return result;
    }
    
    public int updateAdmin(TaiKhoan tk){
        int result = TK.updateAdmin(tk);
        return result;
    }
    
    
}
