/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.*;
import Utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class TaiKhoanDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<TaiKhoan> dsTaiKhoan(){
        String sql = "select TenTaiKhoan, MatKhau from TaiKhoan";
        ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                
                
                
                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error in TaiKhoanDAL");
        }
        finally{
            try{
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return dsTaiKhoan;
        
    }
    public TaiKhoan dangNhap(String userName,String passWord) {
		boolean vaiTro;
		TaiKhoan tk=null;
                String sql = "select * from TAIKHOAN where TenTaiKhoan=? and MatKhau=?";
		try {
                    	
                    dbu = new DBUtils();
                    conn = dbu.createConn();
                    pres = conn.prepareStatement(sql);
          
                    pres.setString(1, userName);
                    pres.setString(2, passWord);
                    rs=pres.executeQuery();
                    if (rs.next()) {
                            tk=new TaiKhoan();
                            tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                            tk.setMatKhau(rs.getString("MatKhau"));
                            tk.setVaiTro(rs.getBoolean("VaiTro"));
                            vaiTro=rs.getBoolean("VaiTro");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tk;
	}
    
  
    public int insert(TaiKhoan TK) {
        int result = 0;
        String sql = "Insert into TAIKHOAN values(?,?,?)";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            
            pres.setString(1,TK.getTenTaiKhoan());
            pres.setString(2,TK.getMatKhau());
            pres.setBoolean(3,false);
            result = pres.executeUpdate();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try{
                conn.close();
                pres.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
 
        return result;
    }
    
    public int updateAdmin(TaiKhoan tk){
        int result = 0;
        String sql = "UPDATE TAIKHOAN SET TenTaiKhoan = ?,MatKhau =? WHERE VaiTro =?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            pres.setString(1,tk.getTenTaiKhoan());
            pres.setString(2,tk.getMatKhau());
            pres.setBoolean(3,true);
            result = pres.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try{
                conn.close();
                pres.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int update(String tenTaiKhoan,String matKhau){
        int result = 0;
        String sql = "Update TAIKHOAN set MatKhau = ? where TenTaiKhoan = ?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            
            pres.setString(2,tenTaiKhoan);
            pres.setString(1,matKhau);
            result =  pres.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
           try{
                conn.close();
            pres.close();
           } catch (SQLException e){
               e.printStackTrace();
           }
        }
        return result;
    }
    
    public int delete(TaiKhoan tk){
        int result = 0;
        String sql = "delete from TaiKhoan where TenTaiKhoan = ?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            
            pres.setString(1,tk.getTenTaiKhoan());
            //pres.setString(1,matKhau);
            result =  pres.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
           try{
                conn.close();
            pres.close();
           } catch (SQLException e){
               e.printStackTrace();
           }
        }
        return result;
    }
    
    
    
    
    
}
