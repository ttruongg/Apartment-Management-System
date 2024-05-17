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
public class ThongTinCanHoDAL {

    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<CanHo> ListCanHoDaBan(String month, String year) {
        
        String sql = "select distinct ch.MaCanHo,DienTich,Gia,SoPhong,TenKhu\n"
                + " from CANHO ch, KHUCANHO kch, HopDong hd \n"
                + " where TrangThai=1 and ch.Makhu=kch.MaKhu \n"
                + "	and hd.MaCanHo = ch.MaCanHo and month(hd.NgayGiaoDich) ='"+ month + "' and year(hd.NgayGiaoDich) = '"+ year +"'";
        
        ArrayList<CanHo> ListCanHo = new ArrayList<>();
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
                ch.setSoPhong(rs.getInt(4));
                ch.setTenKhu(rs.getString(5));
                ListCanHo.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Error in ThongTinCanHoDAL: " + e.getMessage());
        }
        finally {
            try{
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        
        
        return ListCanHo;
    }

    public ArrayList<CanHo> dsCanHo() {
        String sql = "select distinct MaCanHo,DienTich,Gia,SoPhong,TenKhu from CANHO ch join KHUCANHO kch on ch.Makhu=kch.MaKhu where TrangThai=0";

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
                ch.setSoPhong(rs.getInt(4));
                ch.setTenKhu(rs.getString(5));
                dsCanHo.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Error in ThongTinCanHoDAL: " + e.getMessage());
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsCanHo;
    }

    public ArrayList<CanHo> dsCanHoDaBan() {
        String sql = "select distinct MaCanHo,DienTich,Gia,SoPhong,TenKhu from CANHO ch join KHUCANHO kch on ch.Makhu=kch.MaKhu where TrangThai=1";
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
                ch.setSoPhong(rs.getInt(4));
                ch.setTenKhu(rs.getString(5));
                dsCanHo.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Error in ThongTinCanHoDAL: " + e.getMessage());
        }
        return dsCanHo;
    }

    public ArrayList<String> CbbSoPhong() {
        String sql = "select * from CanHo";
        ArrayList<String> ListSoPhong = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                ListSoPhong.add(rs.getString("SoPhong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListSoPhong;
    }

    public ArrayList<String> CbbGia() {
        String sql = "select * from CanHo";
        ArrayList<String> ListGia = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                ListGia.add(rs.getString("Gia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListGia;
    }

    public ArrayList<String> CbbDienTich() {
        String sql = "select * from CanHo";
        ArrayList<String> ListDienTich = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                ListDienTich.add(rs.getString("DienTich"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListDienTich;
    }

    public ArrayList<CanHo> TimKiemCanHo(Float DT, long gia, int phong) {
        String sql = "Select * from CanHo where TrangThai = 0 and DienTich >= '" + DT + "' and Gia >= '" + gia + "' and SoPhong >= '" + phong + "'";
        ArrayList<CanHo> CanHo = new ArrayList<>();
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
                ch.setSoPhong(rs.getInt(5));
                ch.setTenKhu(rs.getString(7));

                CanHo.add(ch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CanHo;
    }

    public int updateCH_DAL(String mach, String macd) {
        int result = 0;
        String sql = "update CANHO set TrangThai=1, MaCuDan='" + macd + "' where MaCanHo='" + mach + "'";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
