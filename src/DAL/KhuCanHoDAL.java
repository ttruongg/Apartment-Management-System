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
public class KhuCanHoDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    
    public ArrayList<KhuCanHo> dsKhuCanHo(){
        String sql = "select * from KhuCanHo";
        ArrayList<KhuCanHo> dsKhuCanHo = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                KhuCanHo kch = new KhuCanHo();
                kch.setMaKhu(rs.getString(1));
                kch.setTenKhu(rs.getString(2));
                kch.setSoTang(rs.getInt(3));
                kch.setSoCanTT(rs.getInt(4));
                kch.setDiaChi(rs.getString(5));
                
                dsKhuCanHo.add(kch);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error in KhuCanHoDAL");
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
        return dsKhuCanHo;
        
    }
    public ArrayList<String> CbbMaKhu(){
        String sql ="select * from KhuCanHo";
        ArrayList<String> ListMaKhu = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                ListMaKhu.add(rs.getString("MaKhu"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ListMaKhu;
    }
    
    
    public int deleteKhuCanHo(KhuCanHo kch){
        int result = 0;
        String sqlDelete = "delete from KhuCanHo where MaKhu = ?";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlDelete);
            pres.setString(1, kch.getMaKhu());
            result = pres.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }    
        finally {
            try{
                conn.close();
                pres.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return result;
        }
            
        
    }
    
    public int updateKhuCanHo(KhuCanHo kch){
        int result = 0;
        String sqlUpdate = "update KhuCanHo set TenKhu = ?, SoTang = ?, SoCanTT = ?, DiaChi = ? where MaKhu = ? ";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlUpdate);
            pres.setString(1, kch.getTenKhu());
            pres.setInt(2, kch.getSoTang());
            pres.setInt(3, kch.getSoCanTT());
            pres.setString(4, kch.getDiaChi());
            pres.setString(5, kch.getMaKhu());
            result = pres.executeUpdate();
        } catch(SQLException e){
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
    
    public boolean insert(KhuCanHo khu){
        String sql = "INSERT INTO KHUCANHO VALUES (?, ?, ?, ?, ?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            pres.setString(1, khu.getMaKhu());
            pres.setString(2, khu.getTenKhu());
            pres.setInt(3, khu.getSoTang());
            pres.setInt(4, khu.getSoCanTT());
            pres.setString(5,khu.getDiaChi());
            pres.executeUpdate();
              
            return true;
            
        } catch (SQLException e){
            return false;
        }
    }
    
    public ArrayList<KhuCanHo> TimKiem_DAL(String info) {
        String sql = "select * from KhuCanHo where MaKhu like '%"+info+"%' or TenKhu like '%"+info+"%'" + "or SoTang like '%"+info+"%' or SoCanTT like '%"+info+"%' or DiaChi like '%"+info+"%'";
        //ResultSet rs = ConnectSQL.connect().createStatement().executeQuery(query);
        ArrayList<KhuCanHo> dsKhuCanHo = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                KhuCanHo kch = new KhuCanHo();
                kch.setMaKhu(rs.getString(1));
                kch.setTenKhu(rs.getString(2));
                kch.setSoTang(rs.getInt(3));
                kch.setSoCanTT(rs.getInt(4));
                kch.setDiaChi(rs.getString(5));
                
                dsKhuCanHo.add(kch);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAL TIM KIEM KCH: " + e.getMessage());
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
        return dsKhuCanHo;
    }
}
