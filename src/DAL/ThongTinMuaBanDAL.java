
package DAL;
import DTO.*;
import Utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
        
/**
 *
 * @author PC
 */
public class ThongTinMuaBanDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<HopDong> dsHopDong()  {
        String sql = "select distinct hd.MaHopDong,cd.TenCuDan,hd.MaCanHo,hd.MaCuDan,hd.DiaChiKH,ch.Gia,hd.NgayGiaoDich "
                + "from HOPDONG hd inner join CANHO ch on hd.MaCanHo=ch.MaCanHo "
                + "inner join CUDAN cd on hd.MaCuDan=cd.MaCuDan";
        
        
        ArrayList<HopDong> dsHopDong = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                HopDong hd = new HopDong();
                hd.setMaHopDong(rs.getString(1));
                hd.setTenKH(rs.getString(2));
                hd.setMaCanHo(rs.getString(3));
                hd.setMaCuDan(rs.getString(4));
                hd.setDiaChiKhachHang(rs.getString(5));
                hd.setGia(rs.getInt(6));
                hd.setNgayGiaoDich(rs.getString(7));

                dsHopDong.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in ThongTinMuaBanDAL: ");
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
        return dsHopDong;
    }
    
    public ArrayList<HopDong> TimKiem_DAL(String info) {
        String sql = "select distinct hd.MaHopDong,cd.TenCuDan,hd.MaCanHo,hd.MaCuDan,hd.DiaChiKH,ch.Gia,hd.NgayGiaoDich from HOPDONG hd inner join CANHO ch on hd.MaCanHo=ch.MaCanHo inner join CUDAN cd on hd.MaCuDan=cd.MaCuDan where hd.MaHopDong like '%"+info+"%' or hd.NgayGiaodich like '%"+info+"%'" + "or hd.DiaChiKH like '%"+info+"%' or hd.MaCuDan like '%"+info+"%' or hd.MaCanHo like '%"+info+"%' or cd.TenCuDan like N'%"+info+"%' or ch.Gia like '%"+info+"%'";
        //ResultSet rs = ConnectSQL.connect().createStatement().executeQuery(query);
        ArrayList<HopDong> dsHopDong = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                HopDong hd = new HopDong();
                hd.setMaHopDong(rs.getString(1));
                hd.setTenKH(rs.getString(2));
                hd.setMaCanHo(rs.getString(3));
                hd.setMaCuDan(rs.getString(4));
                hd.setDiaChiKhachHang(rs.getString(5));
                hd.setGia(rs.getInt(6));
                hd.setNgayGiaoDich(rs.getString(7));
                
                dsHopDong.add(hd);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi DAL TIM KIEM TTMB: " + e.getMessage());
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
        return dsHopDong;
    }
    
    public int insertHD_DAL(String mahd,String ngaygd,String diachikh,String macd,String mach){
        int result = 0;
        String sql="insert HOPDONG values ('"+mahd+"','"+ngaygd+"',N'"+diachikh+"','"+macd+"','"+mach+"')";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            result = pres.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    
    
}
