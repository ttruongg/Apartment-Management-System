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
public class CanHoDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    
    public ArrayList<CanHo> dsCanHo(){
        String sql = "select MaCanHo, DienTich, Gia, TrangThai, SoPhong, MaCuDan, TenKhu from CanHo ch, KhuCanHo kch where ch.MaKhu = kch.MaKhu";
        ArrayList<CanHo> dsCanHo = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                CanHo ch = new CanHo();
                ch.setMaCanHo(rs.getString(1));
                ch.setDienTich(rs.getFloat(2));
                ch.setGia(rs.getLong(3));
                ch.setTrangThai(rs.getBoolean(4));
                ch.setSoPhong(rs.getInt(5));
                ch.setMaCuDan(rs.getString(6));
                ch.setTenKhu(rs.getString(7));
                
                
                dsCanHo.add(ch);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error in CanHoDAL");
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
        return dsCanHo;
        
    }
    
    public ArrayList<String> CbbMaCanHo(){
        String sql ="select * from CanHo";
        ArrayList<String> ListMaCanHo = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                ListMaCanHo.add(rs.getString("MaCanHo"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ListMaCanHo;
    }
    
    public ArrayList<String> CbbMaCanHoDaBan(){
        String sql ="select * from CanHo where TrangThai = 1";
        ArrayList<String> ListMaCanHo = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                ListMaCanHo.add(rs.getString("MaCanHo"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ListMaCanHo;
    }
    
    public boolean inserts(ArrayList<CanHo> list){
        String sql = "INSERT INTO CANHO VALUES (?, ?, ?, ?, ?, ?, ?)";
           try{ 
            for(CanHo c : list){
               dbu = new DBUtils();
               conn = dbu.createConn();
               pres = conn.prepareStatement(sql);

               
                
               pres.setString(1, c.getMaCanHo());
               pres.setFloat(2, c.getDienTich());
               pres.setLong(3,c.getGia());
               pres.setBoolean(4, c.isTrangThai());
               pres.setInt(5, c.getSoPhong());
               pres.setString(6, c.getMaCuDan());
               pres.setString(7, c.getMaKhu());
               
               pres.executeUpdate();
 
            }
            return true;
              
        } catch (SQLException e) {
            return false;
        }
    }
    
    public int updateCanHo(CanHo ch){
        int result = 0;
        String sqlUpdate = "UPDATE CANHO SET Gia = ?,SoPhong = ?, DienTich = ? WHERE MaCanHo = ?";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlUpdate);
            
            pres.setLong(1, ch.getGia());
            
            pres.setInt(2, ch.getSoPhong());
            
            pres.setFloat(3, ch.getDienTich());
            pres.setString(4, ch.getMaCanHo());
            result = pres.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
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
    
    public ArrayList<CanHo> TimKiemCanHo(String info){
        String sql = "select MaCanHo, DienTich, Gia, TrangThai, SoPhong, MaCuDan, kch.TenKhu from CanHo ch, KhuCanHo kch where ch.MaKhu = kch.MaKhu and MaCanHo like '%"+info+"%' or DienTich like '%"+info+"%' or Gia like '%"+info+"%' or TrangThai like '%"+info+"%'or SoPhong like '%"+info+"%' or MaCuDan like '%"+info+"%' or ch.MaKhu like '%"+info+"%' ";
         ArrayList<CanHo> dsCanHo = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                CanHo ch = new CanHo();
                ch.setMaCanHo(rs.getString(1));
                ch.setDienTich(rs.getFloat(2));
                ch.setGia(rs.getLong(3));
                ch.setTrangThai(rs.getBoolean(4));
                ch.setSoPhong(rs.getInt(5));
                ch.setMaCuDan(rs.getString(6));
                ch.setTenKhu(rs.getString(7));
               
                dsCanHo.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAL TIM KIEM CH: " + e.getMessage());
        }
        finally{
            try{
                conn.close();
                pres.close();
                rs.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return dsCanHo;
    }

}
