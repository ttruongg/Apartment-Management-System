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
public class QuanLyCuDanDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    
    public ArrayList<CuDan> ListCuDan() {
        //ResultSet rs = ConnectSQL.connect().createStatement().executeQuery(query);
        String sql = "select * from CUDAN";
        
        ArrayList<CuDan> dsCuDan = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                CuDan cd = new CuDan();
                cd.setMaCuDan(rs.getInt(1));
                cd.setTenCuDan(rs.getString(2));
                cd.setNgaySinh(rs.getString(3));
                cd.setGioiTinh(rs.getString(4));
                cd.setSoDT(rs.getString(5));
                cd.setSoCMT(rs.getString(6));
                cd.setQueQuan(rs.getString(7));

                dsCuDan.add(cd);
            }
        } catch (SQLException e) {
            System.out.println("Error in QuanLyCuDanDAL: " + e.getMessage());
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
        return dsCuDan;
    }
    
    public ArrayList<CuDan> TimKiem_DAL(String info){
        String sql = "select * from CUDAN where "
                + "MaCuDan like '%"+info+"%' "
                + "or TenCuDan like '%"+info+"%' "
                + "or NgaySinh like '%"+info+"%' "
                + "or GioiTinh like '%"+info+"%' "
                + "or SoDT like '%"+info+"%' "
                + " or QueQuan like '%"+info+"%' "
                + "or SoCMT like '%"+info+"%'";
        ArrayList <CuDan> dsCuDan = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                CuDan cd = new CuDan();
                cd.setMaCuDan(rs.getInt(1));
                cd.setTenCuDan(rs.getString(2));
                cd.setNgaySinh(rs.getString(3));
                cd.setGioiTinh(rs.getString(4));
                cd.setSoDT(rs.getString(5));
                cd.setSoCMT(rs.getString(6));
                cd.setQueQuan(rs.getString(7));
                
                dsCuDan.add(cd);
            } 
            
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi TimKiemDAL QLCD");
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
        return dsCuDan;
        
    }
    
    public int UpdateCuDan(CuDan cd){
        int result = 0;
        String sqlUpdate = "update CUDAN set  TenCuDan = ?, NgaySinh = ?, GioiTinh = ?, SoDT = ?, SoCMT =?, QueQuan =? where MaCuDan = ?";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlUpdate);
           // pres.setString(1, cd.getMaCuDan());
            pres.setString(1, cd.getTenCuDan());
            pres.setString(2, cd.getNgaySinh());
            pres.setString(3, cd.getGioiTinh());
            pres.setString(4, cd.getSoDT());
            pres.setString(5, cd.getSoCMT());
            pres.setString(6, cd.getQueQuan());
            pres.setInt(7, cd.getMaCuDan());
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
        
        }
        
        
        
        return result;
    
    
    }
    
    public ArrayList<String> CbbMaCuDan(){
        String sql ="select * from CuDan";
        ArrayList<String> ListMaCuDan = new ArrayList<>();
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                ListMaCuDan.add(rs.getString("MaCuDan"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ListMaCuDan;
    }
    
    public int insertCD_DAL(String macd,String tencd, String ngsinh, String gioitinh, String sdt, String socmt, String quequan) {
        int result = 0;
        String sql = "insert CUDAN values ('" + macd + "',N'" + tencd + "',"
                    + "'" + ngsinh + "',N'" + gioitinh + "','" + sdt + "','" + socmt + "',N'" + quequan + "')";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi insert cư dân");
        }
        return result;
    }
    
    public ArrayList<CuDan> ResidentByApartment(String MaCanHo){
        String sql ="select cd.MaCuDan, TenCuDan, NgaySinh, GioiTinh, SoDT, SoCMT, QueQuan\n" +
                    "from CTCD CT, CuDan cd, CanHo ch\n" +
                    "where  CT.MaCuDan = cd.MaCuDan and CT.MaCanHo = ch.MaCanHo and ch.MaCanHo = '" + MaCanHo + "'";
        ArrayList<CuDan> dsCuDan = new ArrayList<>(); 
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                CuDan cd = new CuDan();
                cd.setMaCuDan(rs.getInt(1));
                cd.setTenCuDan(rs.getString(2));
                cd.setNgaySinh(rs.getString(3));
                cd.setGioiTinh(rs.getString(4));
                cd.setSoDT(rs.getString(5));
                cd.setSoCMT(rs.getString(6));
                cd.setQueQuan(rs.getString(7));

                dsCuDan.add(cd);
            }
        } catch (SQLException e) {
            System.out.println("Error in ResidentByApartment: " + e.getMessage());
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
        return dsCuDan;
     
    }
    
    public int insertCTCD(String mcd, String mch){
        int result = 0;
        String sql = "Insert into CTCD values ('" + mcd + "','" + mch + "')";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi insert ctcd");
        }
        return result;
        
   
    }
    
    public int deleteCTCD(String mcd){
        int result = 0;
        String sql = "delete from CTCD where MaCuDan = '" + mcd + "'";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public int deleteCD(String mcd){
        int result = 0;
        String sql = "delete from CuDan where MaCuDan = '" + mcd + "'";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi xóa cư dan");
        }
        return result;
        
   
    }
    
    
}
