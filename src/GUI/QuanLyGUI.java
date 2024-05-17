/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import BLL.*;
import DTO.*;
import Utils.DBUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author PC
 */
public class QuanLyGUI extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyGUI
     */
    public QuanLyGUI() {
        initComponents();
        setTitle("Quản lý");
        setSize(1260,800);
        LoadTableKCH(KhuCan_HoBLL.ListKhuCanHo());
        LoadCbbMaKhu();
        LoadTableCH(CanHo_BLL.ListCanHo());
        LoadCbbMaCanHo();
        LoadTableTK(TaiKhoan_BLL.dsTaiKhoan());
        LoadTableTTMB(ThongTin_MuaBanBLL.ListHopDong());
        LoadTableCD(QuanLy_CuDanBLL.dsCuDan());
        LoadTableCanHoDaBan(ThongTin_CanHoBLL.dsCanHoDaBan());
        LoadCbbMaCH();
        LoadCbbMonth();
        LoadCbbYear();
        cbbMaCH.disable();
        txtMaCD.disable();
        txtKhu.disable();
        setVisible(true);
    }
    KhuCanHoBLL KhuCan_HoBLL = new KhuCanHoBLL();
    CanHoBLL CanHo_BLL = new CanHoBLL();
    TaiKhoanBLL TaiKhoan_BLL = new TaiKhoanBLL();
    ThongTinMuaBanBLL ThongTin_MuaBanBLL = new ThongTinMuaBanBLL();
    QuanLyCuDanBLL QuanLy_CuDanBLL = new QuanLyCuDanBLL();
    ThongTinCanHoBLL ThongTin_CanHoBLL = new ThongTinCanHoBLL();
    CanHoBLL CanHo = new CanHoBLL();
    public void LoadCbbMaCH() {
        ArrayList<String> MaCanHo = new ArrayList<>();
        MaCanHo = CanHo.LoadCbbMaCanHoDaBan();
        cbbMaCanHo.setModel(new DefaultComboBoxModel<String>(MaCanHo.toArray(new String[0])));
    }
    
     public void LoadCbbMonth() {
        cbbMonth.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2","3","4","5","6","7","8","9","10","11","12"}));
    }
     public void LoadCbbYear() {
        cbbYear.setModel(new DefaultComboBoxModel<>(new String[]{"2019", "2020","2021","2022","2023"}));
    }
    
    
    DefaultTableModel tblModelKhuCanHo;
    public void LoadTableKCH(ArrayList <KhuCanHo> dsKhuCanHo){
        tblModelKhuCanHo = new DefaultTableModel();
        String tieude[] ={"Mã khu", "Tên khu", "Số tầng", "Số căn/tầng", "Địa chỉ"};
        tblModelKhuCanHo.setColumnIdentifiers(tieude);
        KhuCanHo kch = new KhuCanHo();
        
        for (int i = 0; i< dsKhuCanHo.size(); i++){
            kch = dsKhuCanHo.get(i);
            String MaKhu = kch.getMaKhu();
            String TenKhu = kch.getTenKhu();
            int SoTang = kch.getSoTang();
            int SoCanTT = kch.getSoCanTT();
            String DiaChi = kch.getDiaChi();
            Object row[] = {MaKhu, TenKhu, SoTang, SoCanTT, DiaChi};
            tblModelKhuCanHo.addRow(row);
        }
        tblKhuCanHo.setModel(tblModelKhuCanHo);
        setVisible(true);
        
        
        
    }
    DefaultTableModel tblModelCH;
    public void LoadTableCH(ArrayList <CanHo> dsCanHo){
        tblModelCH = new DefaultTableModel();
        String tieude[] ={"Mã căn hộ", "Diện tích", "Giá", "Trạng thái", "Số phòng", "Mã cư dân", "Tên khu"};
        tblModelCH.setColumnIdentifiers(tieude);
        CanHo ch = new CanHo();
        
        for (int i = 0; i<dsCanHo.size(); i++){
            ch = dsCanHo.get(i);
            String MaCanHo = ch.getMaCanHo();
            Float DienTich = ch.getDienTich();
            long Gia = ch.getGia();
            Boolean TrangThai = ch.isTrangThai();
            int SoPhong = ch.getSoPhong();
            String MaCD = ch.getMaCuDan();
            String TenKhu = ch.getTenKhu();
            Object row[] = {MaCanHo, DienTich, Gia, TrangThai, SoPhong, MaCD, TenKhu};
            tblModelCH.addRow(row);
        }
        tblCanHo.setModel(tblModelCH);
        setVisible(true);
    }
    DefaultTableModel tblModelTK;
    public void LoadTableTK(ArrayList <TaiKhoan> dsTaiKhoan){
        tblModelTK = new DefaultTableModel();
        String tieude[] ={"Tên tài khoản","Mật khẩu"};
        tblModelTK.setColumnIdentifiers(tieude);
        TaiKhoan tk = new TaiKhoan();
        for (int i=0; i<dsTaiKhoan.size(); i++){
            tk = dsTaiKhoan.get(i);
            String TenTK = tk.getTenTaiKhoan();
            String MatKhau = tk.getMatKhau();
            Object row[] ={TenTK, MatKhau};
            tblModelTK.addRow(row);
            
            
        }
        tblTaiKhoan.setModel(tblModelTK);
        setVisible(true);
    }
    DefaultTableModel tblModelMuaBan;
    public void LoadTableTTMB(ArrayList <HopDong> dsHopDong){
        tblModelMuaBan = new DefaultTableModel();
        
        String tieude[] = {"Mã hợp đồng", "Tên khách hàng", "Mã căn hộ", "Mã cư dân", "Địa chỉ khách hàng", "Giá", "Ngày giao dịch"};
        tblModelMuaBan.setColumnIdentifiers(tieude);
        
        
        HopDong hd = new HopDong();
        for (int i = 0; i < dsHopDong.size(); i++ ){
            hd = dsHopDong.get(i);
            String MaHopDong = hd.getMaHopDong();
            String TenKH = hd.getTenKH();
            String MaCanHo = hd.getMaCanHo();
            String MaCuDan = hd.getMaCuDan();
            String DiaChi = hd.getDiaChiKhachHang();
            int Gia = hd.getGia();
            String NgayGiaoDich = hd.getNgayGiaoDich();
            Object[] row = {MaHopDong, TenKH, MaCanHo, MaCuDan, DiaChi, Gia, NgayGiaoDich};
            
            tblModelMuaBan.addRow(row);
        }
        tblMuaBan.setModel(tblModelMuaBan);
        
        setVisible(true);
            
    }
    
    DefaultTableModel tblModelCuDan;
    
    public void LoadTableCD(ArrayList <CuDan> dsCuDan){
        tblModelCuDan = new DefaultTableModel();
        String tieude[] ={"Mã cư dân"," Tên cư dân", "Ngày sinh", "Giới tính", "Số điện thoại", "Số CMT", "Quê quán"};
        tblModelCuDan.setColumnIdentifiers(tieude);
        
        CuDan cd = new CuDan();
        for (int i = 0; i< dsCuDan.size(); i++){
            cd = dsCuDan.get(i);
            int MaCuDan = cd.getMaCuDan();
            String TenCuDan = cd.getTenCuDan();
            String NgaySinh = cd.getNgaySinh();
            String GioiTinh = cd.getGioiTinh();
            String SoDT = cd.getSoDT();
            String SoCMT = cd.getSoCMT();
            String QueQuan = cd.getQueQuan();
            Object[] row ={MaCuDan, TenCuDan, NgaySinh, GioiTinh, SoDT, SoCMT, QueQuan};
            tblModelCuDan.addRow(row);
        }
        tblCuDan.setModel(tblModelCuDan);
        
        txtTongCuDan.setText(String.valueOf(tblCuDan.getRowCount()));
        setVisible(true);
    }
    
//    public void LoadCbbGioiTinh2(){
//        cbbGioiTinh2.setModel(new DefaultComboBoxModel<>(new String[]{"Nam","Nữ"}));
//    }
    
    public void LoadCbbMaKhu(){
        ArrayList <String> MaKhu = new ArrayList<>();
        MaKhu = KhuCan_HoBLL.LoadCbbMaKhu();
        cbbMaKhu.setModel(new DefaultComboBoxModel<String>(MaKhu.toArray(new String[0])));
        
    }
    
    public void LoadCbbMaCanHo(){
        ArrayList <String> MaCanHo = new ArrayList<>();
        MaCanHo = CanHo_BLL.LoadCbbMaCanHo();
        cbbMaCH.setModel(new DefaultComboBoxModel<String>(MaCanHo.toArray(new String[0])));
        
    }
    
//    public void LoadCbbMaCanHoMuaBan(){
//        ArrayList <String> MaCanHo = new ArrayList<>();
//        MaCanHo = CanHo_BLL.LoadCbbMaCanHo();
//        cbbMaCanHoMuaBan.setModel(new DefaultComboBoxModel<String>(MaCanHo.toArray(new String[0])));
//    }
    
//    public void LoadCbbMaCuDanMuaBan(){
//        ArrayList <String> MaCuDan = new ArrayList<>();
//        MaCuDan = QuanLy_CuDanBLL.LoadCbbMaCuDan();
//        cbbMaCuDanMuaBan.setModel(new DefaultComboBoxModel<String>(MaCuDan.toArray(new String[0])));
//    }
    DefaultTableModel tblModelCanHoDaBan;
    public void LoadTableCanHoDaBan(ArrayList <CanHo> dsCanHo){
        tblModelCanHoDaBan = new DefaultTableModel();
        
        String tieude[] = {"Mã căn hộ", "Diện tích", "Giá", "Số phòng", "Tên khu"};
        tblModelCanHoDaBan.setColumnIdentifiers(tieude);
        
        
        CanHo ch = new CanHo();
        for (int i = 0; i < dsCanHo.size(); i++ ){
            ch = dsCanHo.get(i);
            String MaCanHo = ch.getMaCanHo();
            Float DienTich = ch.getDienTich();
            long Gia = ch.getGia();
            int SoPhong = ch.getSoPhong();
           
            String TenKhu = ch.getTenKhu();
            Object row[] = {MaCanHo, DienTich, Gia, SoPhong, TenKhu};
            tblModelCanHoDaBan.addRow(row);   
        }
        tblCanHoDaBan.setModel(tblModelCanHoDaBan);
        txtTongDoanhThu.setText(String.valueOf(ThongTin_MuaBanBLL.ThongKeDoanhThu(dsCanHo))+"\tVND");
        txtSoCanDaBan.setText(String.valueOf(tblCanHoDaBan.getRowCount()));
        setVisible(true);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabKhuCanHo = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuCanHo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbMaKhu = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKhu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoTang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoCanTT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCanHo = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbMaCH = new javax.swing.JComboBox<>();
        txtGia = new javax.swing.JTextField();
        txtSoPhong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDienTich = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtKhu = new javax.swing.JTextField();
        btnSua_CanHo = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTimKiemCH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabQuanLy = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        pnChange = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTenTK_QL_New = new javax.swing.JTextField();
        txtMK_QL_New = new javax.swing.JTextField();
        txtMK_XacNhan = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        tabNhanVien = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTenTK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        btnThem_TKNV = new javax.swing.JButton();
        btnSua_TKNV = new javax.swing.JButton();
        btnXoa_TKNV = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        tblMuaBan = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txtTimKiemHopDong = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtTimKiemCD = new javax.swing.JTextField();
        cbbMaCanHo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCuDan = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        txtTongCuDan = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnInDSCuDan = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCanHoDaBan = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtSoCanDaBan = new javax.swing.JTextField();
        txtTongDoanhThu = new javax.swing.JTextField();
        cbbMonth = new javax.swing.JComboBox<>();
        cbbYear = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(101, 198, 187));

        jPanel1.setBackground(new java.awt.Color(101, 198, 187));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1237, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabKhuCanHo.setBackground(new java.awt.Color(238, 252, 245));
        tabKhuCanHo.setForeground(new java.awt.Color(27, 163, 156));
        tabKhuCanHo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabKhuCanHo.setMinimumSize(new java.awt.Dimension(972, 591));
        tabKhuCanHo.setPreferredSize(new java.awt.Dimension(985, 579));

        jPanel3.setBackground(new java.awt.Color(238, 252, 245));

        tblKhuCanHo.setBackground(new java.awt.Color(238, 252, 245));
        tblKhuCanHo.setBorder(new javax.swing.border.MatteBorder(null));
        tblKhuCanHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblKhuCanHo.setForeground(new java.awt.Color(27, 163, 156));
        tblKhuCanHo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhuCanHo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuCanHoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuCanHo);

        jPanel2.setBackground(new java.awt.Color(238, 252, 245));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 59, 102));
        jLabel2.setText("Mã khu");

        cbbMaKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaKhuActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 59, 102));
        jLabel3.setText("Tên khu");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 59, 102));
        jLabel4.setText("Địa chỉ");

        txtTenKhu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 59, 102));
        jLabel5.setText("Số tầng");

        txtSoTang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 59, 102));
        jLabel6.setText("Số căn/ tầng");

        txtSoCanTT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtSoCanTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoCanTTActionPerformed(evt);
            }
        });

        txtDiaChi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem.setBackground(new java.awt.Color(250, 198, 38));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 59, 102));
        btnThem.setText("Thêm Khu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(250, 198, 38));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 59, 102));
        btnSua.setText("Cập nhật Khu");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMaKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoCanTT, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoTang, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(179, 179, 179))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbMaKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoCanTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtTimKiem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 59, 102));
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        tabKhuCanHo.addTab("Khu Căn Hộ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(238, 252, 245));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBar(null);

        tblCanHo.setBackground(new java.awt.Color(238, 252, 245));
        tblCanHo.setBorder(new javax.swing.border.MatteBorder(null));
        tblCanHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCanHo.setForeground(new java.awt.Color(27, 163, 156));
        tblCanHo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCanHo.setGridColor(new java.awt.Color(255, 255, 255));
        tblCanHo.setOpaque(false);
        tblCanHo.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblCanHo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCanHoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCanHo);

        jPanel9.setBackground(new java.awt.Color(238, 252, 245));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 59, 102));
        jLabel11.setText("Giá");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 59, 102));
        jLabel12.setText("Số phòng");

        cbbMaCH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtGia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtSoPhong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 59, 102));
        jLabel13.setText("Diện tích");

        txtDienTich.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 59, 102));
        jLabel14.setText("Mã cư dân");

        txtMaCD.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 59, 102));
        jLabel15.setText("Khu");

        txtKhu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtKhu.setForeground(new java.awt.Color(0, 255, 255));
        txtKhu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSua_CanHo.setBackground(new java.awt.Color(250, 198, 38));
        btnSua_CanHo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua_CanHo.setForeground(new java.awt.Color(0, 59, 102));
        btnSua_CanHo.setText("Cập nhật");
        btnSua_CanHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_CanHoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 59, 102));
        jLabel10.setText("Mã căn hộ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbMaCH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGia)
                    .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDienTich)
                    .addComponent(txtMaCD)
                    .addComponent(txtKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(253, 253, 253))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(554, 554, 554)
                .addComponent(btnSua_CanHo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaCH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMaCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnSua_CanHo)
                .addGap(74, 74, 74))
        );

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 59, 102));
        jLabel25.setText("Tìm kiếm");

        txtTimKiemCH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemCHKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(jLabel25)
                .addGap(39, 39, 39)
                .addComponent(txtTimKiemCH, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemCH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabKhuCanHo.addTab("Căn hộ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(250, 198, 38));
        jTabbedPane1.setForeground(new java.awt.Color(0, 59, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tabQuanLy.setBackground(new java.awt.Color(238, 252, 245));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(195, 174, 32));
        jLabel16.setText("TẠO TÀI KHOẢN");

        pnChange.setBackground(new java.awt.Color(238, 252, 245));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 59, 102));
        jLabel17.setText("Tên tài khoản mới");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 59, 102));
        jLabel18.setText("Mật khẩu mới");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 59, 102));
        jLabel19.setText("Xác nhận mật khẩu mới");

        txtTenTK_QL_New.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtMK_QL_New.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtMK_XacNhan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLuu.setBackground(new java.awt.Color(250, 198, 38));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 59, 102));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(250, 198, 38));
        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(0, 59, 102));
        btnThoat.setText("Thoát");

        javax.swing.GroupLayout pnChangeLayout = new javax.swing.GroupLayout(pnChange);
        pnChange.setLayout(pnChangeLayout);
        pnChangeLayout.setHorizontalGroup(
            pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChangeLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnChangeLayout.createSequentialGroup()
                        .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addGap(37, 37, 37)
                        .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenTK_QL_New)
                            .addComponent(txtMK_QL_New)
                            .addComponent(txtMK_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnChangeLayout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                .addGap(198, 198, 198))
        );
        pnChangeLayout.setVerticalGroup(
            pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChangeLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTenTK_QL_New, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMK_QL_New, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMK_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabQuanLyLayout = new javax.swing.GroupLayout(tabQuanLy);
        tabQuanLy.setLayout(tabQuanLyLayout);
        tabQuanLyLayout.setHorizontalGroup(
            tabQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQuanLyLayout.createSequentialGroup()
                .addGroup(tabQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabQuanLyLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(pnChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabQuanLyLayout.createSequentialGroup()
                        .addGap(449, 449, 449)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabQuanLyLayout.setVerticalGroup(
            tabQuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQuanLyLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Quản lý", tabQuanLy);

        tabNhanVien.setBackground(new java.awt.Color(238, 252, 245));

        jLabel8.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 59, 102));
        jLabel8.setText("Tên tài khoản");

        txtTenTK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 59, 102));
        jLabel9.setText("Mật khẩu");

        txtMatKhau.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem_TKNV.setBackground(new java.awt.Color(250, 198, 38));
        btnThem_TKNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem_TKNV.setForeground(new java.awt.Color(0, 59, 102));
        btnThem_TKNV.setText("Thêm");
        btnThem_TKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_TKNVActionPerformed(evt);
            }
        });

        btnSua_TKNV.setBackground(new java.awt.Color(250, 198, 38));
        btnSua_TKNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua_TKNV.setForeground(new java.awt.Color(0, 59, 102));
        btnSua_TKNV.setText("Cập nhật");
        btnSua_TKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_TKNVActionPerformed(evt);
            }
        });

        btnXoa_TKNV.setBackground(new java.awt.Color(250, 198, 38));
        btnXoa_TKNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa_TKNV.setForeground(new java.awt.Color(0, 59, 102));
        btnXoa_TKNV.setText("Xóa");
        btnXoa_TKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_TKNVActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(238, 252, 245));
        jScrollPane3.setBorder(null);

        tblTaiKhoan.setBackground(new java.awt.Color(238, 252, 245));
        tblTaiKhoan.setBorder(new javax.swing.border.MatteBorder(null));
        tblTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTaiKhoan.setForeground(new java.awt.Color(27, 163, 156));
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTaiKhoan);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(27, 163, 156));
        jLabel31.setText("Danh Sách Tài Khoản");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(27, 163, 156));
        jLabel32.setText("Chỉnh Sửa Tài Khoản");

        jPanel12.setBackground(new java.awt.Color(0, 59, 102));
        jPanel12.setForeground(new java.awt.Color(102, 102, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tabNhanVienLayout = new javax.swing.GroupLayout(tabNhanVien);
        tabNhanVien.setLayout(tabNhanVienLayout);
        tabNhanVienLayout.setHorizontalGroup(
            tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabNhanVienLayout.createSequentialGroup()
                .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabNhanVienLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabNhanVienLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabNhanVienLayout.createSequentialGroup()
                        .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenTK)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabNhanVienLayout.createSequentialGroup()
                        .addComponent(btnThem_TKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(tabNhanVienLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnSua_TKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnXoa_TKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(108, 108, 108))))
        );
        tabNhanVienLayout.setVerticalGroup(
            tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabNhanVienLayout.createSequentialGroup()
                .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabNhanVienLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel32)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabNhanVienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31)
                        .addGap(36, 36, 36)))
                .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabNhanVienLayout.createSequentialGroup()
                        .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(tabNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem_TKNV)
                    .addComponent(btnSua_TKNV)
                    .addComponent(btnXoa_TKNV))
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhân Viên", tabNhanVien);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        tabKhuCanHo.addTab("Tài Khoản", jPanel5);

        jPanel6.setBackground(new java.awt.Color(238, 252, 245));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setForeground(new java.awt.Color(51, 0, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );

        ScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        tblMuaBan.setBackground(new java.awt.Color(238, 252, 245));
        tblMuaBan.setBorder(new javax.swing.border.MatteBorder(null));
        tblMuaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMuaBan.setForeground(new java.awt.Color(27, 163, 156));
        tblMuaBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMuaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMuaBanMouseClicked(evt);
            }
        });
        ScrollPane.setViewportView(tblMuaBan);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 59, 102));
        jLabel20.setText("Tìm kiếm");

        txtTimKiemHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHopDongActionPerformed(evt);
            }
        });
        txtTimKiemHopDong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHopDongKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(27, 163, 156));
        jLabel22.setText("Quản lý mua bán");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1252, 1252, 1252))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel20)
                        .addGap(69, 69, 69)
                        .addComponent(txtTimKiemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(426, 426, 426)
                        .addComponent(jLabel22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel22)
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTimKiemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabKhuCanHo.addTab("Quản lý mua bán", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(51, 0, 255));

        jPanel13.setForeground(new java.awt.Color(51, 0, 255));

        jPanel14.setBackground(new java.awt.Color(238, 252, 245));
        jPanel14.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 59, 102));
        jLabel21.setText("Tìm kiếm");

        txtTimKiemCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemCDKeyReleased(evt);
            }
        });

        cbbMaCanHo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaCanHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaCanHoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(txtTimKiemCD, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(cbbMaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemCD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbMaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCuDan.setBackground(new java.awt.Color(238, 252, 245));
        tblCuDan.setBorder(new javax.swing.border.MatteBorder(null));
        tblCuDan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCuDan.setForeground(new java.awt.Color(27, 163, 156));
        tblCuDan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCuDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCuDanMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCuDan);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 59, 102));
        jLabel40.setText("Tổng số cư dân");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(27, 163, 156));
        jLabel42.setText("Quản Lý Cư Dân");

        jButton1.setBackground(new java.awt.Color(250, 198, 38));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 59, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnInDSCuDan.setBackground(new java.awt.Color(250, 198, 38));
        btnInDSCuDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInDSCuDan.setForeground(new java.awt.Color(0, 59, 102));
        btnInDSCuDan.setText("In danh sách cư dân");
        btnInDSCuDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDSCuDanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInDSCuDan)
                        .addGap(246, 246, 246)
                        .addComponent(jLabel40)
                        .addGap(79, 79, 79)
                        .addComponent(txtTongCuDan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))
                    .addComponent(jScrollPane5)))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(473, 473, 473)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 434, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel42))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongCuDan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(btnInDSCuDan))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tabKhuCanHo.addTab("Quản lý cư dân", jPanel7);

        jPanel8.setBackground(new java.awt.Color(238, 252, 245));
        jPanel8.setMaximumSize(new java.awt.Dimension(3000, 5000));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(27, 163, 156));
        jLabel27.setText("Danh sách căn hộ đã bán");

        jScrollPane4.setBackground(new java.awt.Color(238, 252, 245));

        tblCanHoDaBan.setBackground(new java.awt.Color(238, 252, 245));
        tblCanHoDaBan.setBorder(new javax.swing.border.MatteBorder(null));
        tblCanHoDaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCanHoDaBan.setForeground(new java.awt.Color(27, 163, 156));
        tblCanHoDaBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblCanHoDaBan);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 59, 102));
        jLabel28.setText("Tổng số căn hộ đã bán");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 59, 102));
        jLabel29.setText("Tổng doanh thu");

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMonthActionPerformed(evt);
            }
        });

        cbbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 59, 102));
        jLabel23.setText("Tháng");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 59, 102));
        jLabel24.setText("Năm");

        btnRefresh.setBackground(new java.awt.Color(250, 198, 38));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(50, 50, 50)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoCanDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnRefresh)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(26, 26, 26)))
                .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(cbbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 28, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addGap(39, 39, 39))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoCanDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(78, 78, 78))
        );

        tabKhuCanHo.addTab("Thống kê doanh thu", jPanel8);

        kGradientPanel1.setkEndColor(new java.awt.Color(6, 154, 142));
        kGradientPanel1.setkStartColor(new java.awt.Color(238, 252, 245));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(250, 198, 38));
        jLabel7.setText("QUẢN LÝ");
        kGradientPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 180, 55));
        kGradientPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 290, 120));

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-close-window-50 (2).png"))); // NOI18N
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1163, 40, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabKhuCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabKhuCanHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        DangNhapGUI dn = new DangNhapGUI();
        dn.setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void tblCanHoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCanHoMouseClicked
        // TODO add your handling code here:
        int indexTB = tblCanHo.getSelectedRow();

        if (indexTB >=0 && indexTB <tblCanHo.getRowCount()){
            cbbMaCH.setSelectedItem(tblCanHo.getValueAt(indexTB,0).toString());
            txtDienTich.setText(tblCanHo.getValueAt(indexTB,1).toString());
            txtGia.setText(tblCanHo.getValueAt(indexTB,2).toString());
            txtSoPhong.setText(tblCanHo.getValueAt(indexTB,4).toString());
            //txtMaCD.setText(tblCanHo.getValueAt(indexTB,5).toString());
            if((tblCanHo.getValueAt(indexTB, 5))==null)
            txtMaCD.setText("");
            else
            txtMaCD.setText((tblCanHo.getValueAt(indexTB, 5).toString()));
            txtKhu.setText(tblCanHo.getValueAt(indexTB,6).toString());
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    }//GEN-LAST:event_tblCanHoMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        LoadTableKCH(KhuCan_HoBLL.TimKiem_BLL(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        KhuCanHo kch = new KhuCanHo();
        kch.setMaKhu(cbbMaKhu.getSelectedItem().toString());
        kch.setTenKhu(txtTenKhu.getText());
        kch.setSoTang(Integer.parseInt(txtSoTang.getText()));
        kch.setSoCanTT(Integer.parseInt(txtSoCanTT.getText()));
        kch.setDiaChi(txtDiaChi.getText());

        try{
            int result = KhuCan_HoBLL.updateKhuCanHo(kch);
            if (result != 0){
                JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
            LoadTableKCH(KhuCan_HoBLL.ListKhuCanHo());

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ThemKhu_PhongGUI TK = new ThemKhu_PhongGUI();
        TK.setVisible(true);

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoCanTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoCanTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoCanTTActionPerformed

    private void cbbMaKhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaKhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaKhuActionPerformed

    private void tblKhuCanHoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuCanHoMouseClicked
        // TODO add your handling code here:
        int indexTB = tblKhuCanHo.getSelectedRow();

        if (indexTB >=0 && indexTB <tblKhuCanHo.getRowCount()){
            cbbMaKhu.setSelectedItem(tblKhuCanHo.getValueAt(indexTB,0).toString());
            txtTenKhu.setText(tblKhuCanHo.getValueAt(indexTB,1).toString());
            txtSoTang.setText(tblKhuCanHo.getValueAt(indexTB,2).toString());
            txtSoCanTT.setText(tblKhuCanHo.getValueAt(indexTB,3).toString());
            txtDiaChi.setText(tblKhuCanHo.getValueAt(indexTB,4).toString());
        }
    }//GEN-LAST:event_tblKhuCanHoMouseClicked

    private void tblCuDanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCuDanMouseClicked
        // TODO add your handling code here:
//
//        int indexTB = tblCuDan.getSelectedRow();
//
//        if (indexTB >=0 && indexTB <tblCuDan.getRowCount()){
//            txtMaCuDan.setText(tblCuDan.getValueAt(indexTB, 0).toString());
//            txtTenCuDan.setText(tblCuDan.getValueAt(indexTB, 1).toString());
//
//            cbbGioiTinh2.setSelectedItem(tblCuDan.getValueAt(indexTB, 3).toString());
//            txtSoDienThoai.setText(tblCuDan.getValueAt(indexTB, 4).toString());
//            txtSoCMT.setText(tblCuDan.getValueAt(indexTB, 5).toString());
//            txtQueQuan.setText(tblCuDan.getValueAt(indexTB, 6).toString());
//        }
//        try{
//
//            Date dat = new SimpleDateFormat("yyyy-MM-dd").parse((String)tblCuDan.getValueAt(indexTB, 2));
//            datNgSinh.setDate(dat);
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, e);
//            System.out.println("Lỗi nè");
//        }
    }//GEN-LAST:event_tblCuDanMouseClicked

    private void tblMuaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMuaBanMouseClicked
        // TODO add your handling code here:
//        int indexTB = tblMuaBan.getSelectedRow();
//
//        if (indexTB >=0 && indexTB <tblMuaBan.getRowCount()){
//            txtMaHopDong.setText(tblMuaBan.getValueAt(indexTB, 0)+"");
//            txtTenKH.setText(tblMuaBan.getValueAt(tblMuaBan.getSelectedRow(), 1)+"");
//            cbbMaCanHoMuaBan.setSelectedItem(tblMuaBan.getValueAt(tblMuaBan.getSelectedRow(), 2)+"");
//            cbbMaCuDanMuaBan.setSelectedItem(tblMuaBan.getValueAt(tblMuaBan.getSelectedRow(),3)+"");
//            txtDiaChiKH.setText(tblMuaBan.getValueAt(tblMuaBan.getSelectedRow(), 4)+"");
//            txtGiaCanHo.setText(tblMuaBan.getValueAt(tblMuaBan.getSelectedRow(), 5)+"\tVND");
//
//            try{
//
//                Date dat = new SimpleDateFormat("yyyy-MM-dd").parse((String)tblMuaBan.getValueAt(indexTB, 6));
//                datNgGiaoDich.setDate(dat);
//            } catch (Exception e){
//                JOptionPane.showMessageDialog(null, e);
//                System.out.println("Lỗi nè");
//            }
//        }
    }//GEN-LAST:event_tblMuaBanMouseClicked

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        // TODO add your handling code here:
        int indexTB = tblTaiKhoan.getSelectedRow();
        txtTenTK.setText((tblTaiKhoan.getValueAt(indexTB,0)).toString());
        txtMatKhau.setText((tblTaiKhoan.getValueAt(indexTB,1)).toString());
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnXoa_TKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_TKNVActionPerformed
        // TODO add your handling code here:
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(txtTenTK.getText());
        tk.setMatKhau(txtMatKhau.getText());
        tk.setVaiTro(false);
        try{
            int result = TaiKhoan_BLL.deleteNV(tk);
            if (result != 0){
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
            LoadTableTK(TaiKhoan_BLL.dsTaiKhoan());

        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoa_TKNVActionPerformed

    private void btnSua_TKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_TKNVActionPerformed
        // TODO add your handling code here:
        //        TaiKhoan tk = new TaiKhoan();
        //        tk.setTenTaiKhoan(txtTenTK.getText());
        //        tk.setMatKhau(txtMatKhau.getText());
        //        tk.setVaiTro(false);
        try{
            int result = TaiKhoan_BLL.updateNV(txtTenTK.getText(), txtMatKhau.getText());
            if (result != 0){
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
            LoadTableTK(TaiKhoan_BLL.dsTaiKhoan());

        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSua_TKNVActionPerformed

    private void btnThem_TKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_TKNVActionPerformed
        // TODO add your handling code here:
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(txtTenTK.getText());
        tk.setMatKhau(txtMatKhau.getText());
        tk.setVaiTro(false);

        try{
            int result = TaiKhoan_BLL.insert(tk);
            if (result != 0){
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
            LoadTableTK(TaiKhoan_BLL.dsTaiKhoan());

        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThem_TKNVActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (txtTenTK_QL_New.getText().equals(""))
        JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else if (txtMK_QL_New.getText().equals(""))
        JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else if (txtMK_XacNhan.getText().equals(""))
        JOptionPane.showMessageDialog(null, "Vui lòng xác nhận lại mật khẩu ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        else
        {

            TaiKhoan tk = new TaiKhoan();
            tk.setTenTaiKhoan(txtTenTK_QL_New.getText());
            tk.setMatKhau(txtMK_QL_New.getText());
            tk.setVaiTro(true);
            try{
                if (txtMK_QL_New.getText().equals(txtMK_XacNhan.getText())){
                    int result = TaiKhoan_BLL.updateAdmin(tk);
                    if (result != 0){
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }

                    LoadTableTK(TaiKhoan_BLL.dsTaiKhoan());
                }
                else
                JOptionPane.showMessageDialog(null, "Xác nhận sai mật khẩu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSua_CanHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_CanHoActionPerformed
        // TODO add your handling code here:

        try{
            if(txtGia.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Giá không được bỏ trống!");
            }else if(Long.parseLong(txtGia.getText())<1){
                JOptionPane.showMessageDialog(null,"Vui lòng giá là số dương!");
            }else if(txtSoPhong.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Số phòng không được bỏ trống");
            }else if(Integer.parseInt(txtSoPhong.getText())<1){
                JOptionPane.showMessageDialog(null,"Vui lòng số phòng là số dương!");
            }else{
                CanHo ch = new CanHo();
                ch.setMaCanHo(cbbMaCH.getSelectedItem().toString());
               
                ch.setGia(Long.parseLong(txtGia.getText()));
                ch.setSoPhong(Integer.parseInt(txtSoPhong.getText()));
                ch.setDienTich(Float.parseFloat(txtDienTich.getText()));
                try{
                    int result = CanHo_BLL.updateCanHo(ch);
                    if (result !=0 ){
                        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    }
                    LoadTableCH(CanHo_BLL.ListCanHo());
                } catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSua_CanHoActionPerformed

    private void txtTimKiemHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemHopDongActionPerformed

    private void txtTimKiemHopDongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHopDongKeyReleased
        LoadTableTTMB(ThongTin_MuaBanBLL.TimKiem_BLL(txtTimKiemHopDong.getText())); 
    }//GEN-LAST:event_txtTimKiemHopDongKeyReleased

    private void txtTimKiemCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCDKeyReleased
        // TODO add your handling code here:
        LoadTableCD(QuanLy_CuDanBLL.TimKiem_BLL(txtTimKiemCD.getText()));
    }//GEN-LAST:event_txtTimKiemCDKeyReleased

    private void cbbMaCanHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaCanHoActionPerformed
        // TODO add your handling code here:
        ArrayList<CuDan> dsCuDan = new ArrayList<>();
        dsCuDan = QuanLy_CuDanBLL.ResidentByApartment(cbbMaCanHo.getSelectedItem().toString());
        LoadTableCD(dsCuDan);
    }//GEN-LAST:event_cbbMaCanHoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoadTableCD(QuanLy_CuDanBLL.dsCuDan());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMonthActionPerformed
        // TODO add your handling code here:
        LoadTableCanHoDaBan(ThongTin_CanHoBLL.ListCanHoDaBan(cbbMonth.getSelectedItem().toString(), cbbYear.getSelectedItem().toString()));
        
    }//GEN-LAST:event_cbbMonthActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        LoadTableCanHoDaBan(ThongTin_CanHoBLL.dsCanHoDaBan());
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtTimKiemCHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCHKeyReleased
        // TODO add your handling code here:
        LoadTableCH(CanHo_BLL.TimKiemCanHo(txtTimKiemCH.getText()));
    }//GEN-LAST:event_txtTimKiemCHKeyReleased

    private void btnInDSCuDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDSCuDanActionPerformed
        // TODO add your handling code here:
        Document document = new Document(PageSize.A4);
        String filename = "Danh sách cư dân";
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("reports/" + filename + ".pdf"));
            document.open();

            File filefontTieuDe = new File("fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(filefontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            File filefontNoiDung = new File("fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font fontNoiDung1 = new Font(bfNoiDung, 13);
            Font fontNoiDung2 = new Font(bfNoiDung, 12);

            Font fontTieuDe3 = new Font(bfTieuDe, 13);
            Font fontTieuDe4 = new Font(bfTieuDe, 12);

            Paragraph tieude = new Paragraph("Danh sách cư dân", fontNoiDung2);
            tieude.setAlignment(Element.ALIGN_CENTER);

            document.add(tieude);

            PdfPTable tableDV = new PdfPTable(7);
            tableDV.setWidthPercentage(110);
            tableDV.setSpacingBefore(10);
            tableDV.setSpacingAfter(10);

            float[] tableDV_columnWidths = {100, 220, 200, 80, 200, 210, 150};
            tableDV.setWidths(tableDV_columnWidths);

            PdfPCell cellTDTT = new PdfPCell(new Paragraph("Mã cư dân", fontTieuDe4));
            cellTDTT.setBorderColor(BaseColor.BLACK);
            cellTDTT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDTT.setMinimumHeight(30);
            tableDV.addCell(cellTDTT);

            PdfPCell cellTDMaDV = new PdfPCell(new Paragraph("Tên cư dân", fontTieuDe4));
            cellTDMaDV.setBorderColor(BaseColor.BLACK);
            cellTDMaDV.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDMaDV.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDMaDV.setMinimumHeight(30);
            tableDV.addCell(cellTDMaDV);

            PdfPCell cellTDTenDV = new PdfPCell(new Paragraph("Ngày sinh", fontTieuDe4));
            cellTDTenDV.setBorderColor(BaseColor.BLACK);
            cellTDTenDV.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTenDV.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDTenDV.setMinimumHeight(30);
            tableDV.addCell(cellTDTenDV);

            PdfPCell cellTDDonGia = new PdfPCell(new Paragraph("Giới tính", fontTieuDe4));
            cellTDDonGia.setBorderColor(BaseColor.BLACK);
            cellTDDonGia.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDDonGia.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDDonGia.setMinimumHeight(30);
            tableDV.addCell(cellTDDonGia);

            PdfPCell cellTDSL = new PdfPCell(new Paragraph("Số điện thoại", fontTieuDe4));
            cellTDSL.setBorderColor(BaseColor.BLACK);
            cellTDSL.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDSL.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDSL.setMinimumHeight(30);
            tableDV.addCell(cellTDSL);

            PdfPCell cellTDThanhTien = new PdfPCell(new Paragraph("Số CMT", fontTieuDe4));
            cellTDThanhTien.setBorderColor(BaseColor.BLACK);
            cellTDThanhTien.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDThanhTien.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDThanhTien.setMinimumHeight(30);
            tableDV.addCell(cellTDThanhTien);

            PdfPCell QueQuan = new PdfPCell(new Paragraph("Quê quán", fontTieuDe4));
            QueQuan.setBorderColor(BaseColor.BLACK);
            QueQuan.setHorizontalAlignment(Element.ALIGN_CENTER);
            QueQuan.setVerticalAlignment(Element.ALIGN_MIDDLE);
            QueQuan.setMinimumHeight(30);
            tableDV.addCell(QueQuan);

            try {
                Connection conn = new DBUtils().createConn();
                String strSQL = "Select * from CuDan";
                PreparedStatement pres = conn.prepareStatement(strSQL);

                ResultSet rs = pres.executeQuery();
            
                while (rs.next()) {
                    CuDan cd = new CuDan();
                    PdfPCell cellMaCD = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("MaCuDan")), fontNoiDung2));
                    cellMaCD.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellMaCD.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellMaCD.setMinimumHeight(20);
                    tableDV.addCell(cellMaCD);

                    PdfPCell cellTenCD = new PdfPCell(new Paragraph(String.valueOf(rs.getString("TenCuDan")), fontNoiDung2));
                    cellTenCD.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTenCD.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellTenCD.setMinimumHeight(20);
                    tableDV.addCell(cellTenCD);

                    PdfPCell cellNgSinh = new PdfPCell(new Paragraph(String.valueOf(rs.getString("NgaySinh")), fontNoiDung2));
                    cellNgSinh.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellNgSinh.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellNgSinh.setMinimumHeight(20);
                    tableDV.addCell(cellNgSinh);

                    PdfPCell cellGioiTinh = new PdfPCell(new Paragraph(String.valueOf(rs.getString("GioiTinh")), fontNoiDung2));
                    cellGioiTinh.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellGioiTinh.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellGioiTinh.setMinimumHeight(20);
                    tableDV.addCell(cellGioiTinh);

                    PdfPCell cellSoDT = new PdfPCell(new Paragraph(String.valueOf(rs.getString("SoDT")), fontNoiDung2));
                    cellSoDT.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellSoDT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellSoDT.setMinimumHeight(20);
                    tableDV.addCell(cellSoDT);

                    PdfPCell cellThanhTien = new PdfPCell(new Paragraph(String.valueOf(rs.getString("SoCMT")), fontNoiDung2));
                    cellThanhTien.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellThanhTien.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellThanhTien.setMinimumHeight(20);
                    tableDV.addCell(cellThanhTien);

                    PdfPCell cellQueQuan = new PdfPCell(new Paragraph(String.valueOf(rs.getString("QueQuan")), fontNoiDung2));
                    cellQueQuan.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellQueQuan.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellQueQuan.setMinimumHeight(20);
                    tableDV.addCell(cellQueQuan);

                }

            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Lỗi");
            }
            document.add(tableDV);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("reports/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInDSCuDanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnInDSCuDan;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua_CanHo;
    private javax.swing.JButton btnSua_TKNV;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem_TKNV;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa_TKNV;
    private javax.swing.JComboBox<String> cbbMaCH;
    private javax.swing.JComboBox<String> cbbMaCanHo;
    private javax.swing.JComboBox<String> cbbMaKhu;
    private javax.swing.JComboBox<String> cbbMonth;
    private javax.swing.JComboBox<String> cbbYear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JPanel pnChange;
    private javax.swing.JTabbedPane tabKhuCanHo;
    private javax.swing.JPanel tabNhanVien;
    private javax.swing.JPanel tabQuanLy;
    private javax.swing.JTable tblCanHo;
    private javax.swing.JTable tblCanHoDaBan;
    private javax.swing.JTable tblCuDan;
    private javax.swing.JTable tblKhuCanHo;
    private javax.swing.JTable tblMuaBan;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienTich;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtKhu;
    private javax.swing.JTextField txtMK_QL_New;
    private javax.swing.JTextField txtMK_XacNhan;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSoCanDaBan;
    private javax.swing.JTextField txtSoCanTT;
    private javax.swing.JTextField txtSoPhong;
    private javax.swing.JTextField txtSoTang;
    private javax.swing.JTextField txtTenKhu;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTenTK_QL_New;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemCD;
    private javax.swing.JTextField txtTimKiemCH;
    private javax.swing.JTextField txtTimKiemHopDong;
    private javax.swing.JTextField txtTongCuDan;
    private javax.swing.JTextField txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
