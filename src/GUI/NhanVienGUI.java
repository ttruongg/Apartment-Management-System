/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.*;
import BLL.*;
import Utils.DBUtils;
import java.awt.Desktop;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.text.NumberFormat;
import java.util.Locale;
import java.io.File;
import java.io.IOException;
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
public class NhanVienGUI extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienGUI
     */
    public NhanVienGUI() {
        initComponents();
        setTitle("Nhân viên");
        setSize(1000, 800);
        cbbGioiTinh.setSelectedItem(null);
        LoadCbbGioiTinh();
        LoadTableTTMB(ThongTin_MuaBanBLL.ListHopDong());
        LoadTableQLCD(QuanLy_CuDanBLL.dsCuDan());
        LoadTableTTCH(ThongTin_CanHoBLL.dsCanHo());
        LoadCbbMaCH();
        LoadCbbDienTich();
        LoadCbbGia();
        LoadCbbSoPhong();
        txtMaCanHo.setEditable(false);
        txtGiaCanHo.disable();
        LoadCbbGioiTinh1();
        setVisible(true);
    }

    CanHoBLL CanHo = new CanHoBLL();
    ThongTinMuaBanBLL ThongTin_MuaBanBLL = new ThongTinMuaBanBLL();
    QuanLyCuDanBLL QuanLy_CuDanBLL = new QuanLyCuDanBLL();
    ThongTinCanHoBLL ThongTin_CanHoBLL = new ThongTinCanHoBLL();
    DefaultTableModel tblModelThongTinMuaBan;

    public boolean ValidForm() {
        if (Validation.isEmpty(txtTenKH, "Tên khách hàng không được để trống!")) {
            return false;
        }
        if (Validation.isEmpty(txtSoDT, "Số điện thoại không được để trống!")) {
            return false;
        }
        if (Validation.isNumber(txtSoDT, "SĐT phải là số!")) {
            return false;
        }
        if (Validation.isEmpty(txtSoCMND, "CCCD không được để trống!")) {
            return false;
        }
        if (Validation.isNumber(txtSoCMND, "CCCD phải là số!")){
            return false;
        }
        if (Validation.isEmpty(txtDiaChi, "Địa chỉ không được để trống!")) {
            return false;
        }
        return true;
    }

    public void LoadCbbMaCH() {
        ArrayList<String> MaCanHo = new ArrayList<>();
        MaCanHo = CanHo.LoadCbbMaCanHoDaBan();
        cbbMaCH.setModel(new DefaultComboBoxModel<String>(MaCanHo.toArray(new String[0])));
    }

    public void LoadCbbGioiTinh() {
        cbbGioiTinh.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
    }

    public void LoadCbbGioiTinh1() {
        cbbGioiTinh1.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
    }

    public void LoadCbbDienTich() {
        ArrayList<String> DienTich = new ArrayList<>();
        DienTich = ThongTin_CanHoBLL.CbbDienTich();
        cbbDienTich.setModel(new DefaultComboBoxModel<String>(DienTich.toArray(new String[0])));
    }

    public void LoadCbbGia() {
        ArrayList<String> Gia = new ArrayList<>();
        Gia = ThongTin_CanHoBLL.CbbGia();
        cbbGia.setModel(new DefaultComboBoxModel<String>(Gia.toArray(new String[0])));
    }

    public void LoadCbbSoPhong() {
        ArrayList<String> SoPhong = new ArrayList<>();
        SoPhong = ThongTin_CanHoBLL.CbbSoPhong();
        cbbSoPhong.setModel(new DefaultComboBoxModel<String>(SoPhong.toArray(new String[0])));
    }

    public void LoadTableTTMB(ArrayList<HopDong> dsHopDong) {
        tblModelThongTinMuaBan = new DefaultTableModel();

        String tieude[] = {"Mã hợp đồng", "Tên khách hàng", "Mã căn hộ", "Mã cư dân", "Địa chỉ khách hàng", "Giá", "Ngày giao dịch"};
        tblModelThongTinMuaBan.setColumnIdentifiers(tieude);

        HopDong hd = new HopDong();
        for (int i = 0; i < dsHopDong.size(); i++) {
            hd = dsHopDong.get(i);
            String MaHopDong = hd.getMaHopDong();
            String TenKH = hd.getTenKH();
            String MaCanHo = hd.getMaCanHo();
            String MaCuDan = hd.getMaCuDan();
            String DiaChi = hd.getDiaChiKhachHang();
            int Gia = hd.getGia();
            String NgayGiaoDich = hd.getNgayGiaoDich();
            Object[] row = {MaHopDong, TenKH, MaCanHo, MaCuDan, DiaChi, Gia, NgayGiaoDich};

            tblModelThongTinMuaBan.addRow(row);
        }
        tblThongTinMuaBan.setModel(tblModelThongTinMuaBan);
        setVisible(true);

    }
    DefaultTableModel tblModelQuanLyCuDan;

    public void LoadTableQLCD(ArrayList<CuDan> dsCuDan) {
        tblModelQuanLyCuDan = new DefaultTableModel();
        String tieude[] = {"Mã cư dân", " Tên cư dân", "Ngày sinh", "Giới tính", "Số điện thoại", "Số CMT", "Quê quán"};
        tblModelQuanLyCuDan.setColumnIdentifiers(tieude);

        CuDan cd = new CuDan();
        for (int i = 0; i < dsCuDan.size(); i++) {
            cd = dsCuDan.get(i);
            int MaCuDan = cd.getMaCuDan();
            String TenCuDan = cd.getTenCuDan();
            String NgaySinh = cd.getNgaySinh();
            String GioiTinh = cd.getGioiTinh();
            String SoDT = cd.getSoDT();
            String SoCMT = cd.getSoCMT();
            String QueQuan = cd.getQueQuan();
            Object[] row = {MaCuDan, TenCuDan, NgaySinh, GioiTinh, SoDT, SoCMT, QueQuan};
            tblModelQuanLyCuDan.addRow(row);
        }
        tblQuanLyCuDan.setModel(tblModelQuanLyCuDan);
        setVisible(true);
    }

    DefaultTableModel tblModelThongTinCanHo;

    public void LoadTableTTCH(ArrayList<CanHo> dsCanHo) {
        tblModelThongTinCanHo = new DefaultTableModel();
        String tieude[] = {"Mã căn hộ", "Diện tích", "Giá", "Số phòng", "Tên khu"};
        tblModelThongTinCanHo.setColumnIdentifiers(tieude);

        CanHo ch = new CanHo();
        for (int i = 0; i < dsCanHo.size(); i++) {
            ch = dsCanHo.get(i);
            String MaCanHo = ch.getMaCanHo();
            float DienTich = ch.getDienTich();
            long Gia = ch.getGia();
            int SoPhong = ch.getSoPhong();
            String TenKhu = ch.getTenKhu();
            Object[] row = {MaCanHo, DienTich, Gia, SoPhong, TenKhu};
            tblModelThongTinCanHo.addRow(row);

        }
        tblThongTinCanHo.setModel(tblModelThongTinCanHo);
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
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        tabNhanVien = new javax.swing.JTabbedPane();
        tabQuanLyCuDan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        txtTenCD = new javax.swing.JTextField();
        DatNgSinh = new com.toedter.calendar.JDateChooser();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtQueQuan = new javax.swing.JTextField();
        txtSoCM = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnThemCD = new javax.swing.JButton();
        btnXoaCD = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiem1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLyCuDan = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        cbbMaCH = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        btnInDSCuDan = new javax.swing.JButton();
        tabThongTinMuaBan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        JScrollPane = new javax.swing.JScrollPane();
        tblThongTinMuaBan = new javax.swing.JTable();
        btnRefreshHD = new javax.swing.JButton();
        tabThongTinCanHo = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTinCanHo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbbDienTich = new javax.swing.JComboBox<>();
        cbbGia = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbbSoPhong = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtMaCanHo = new javax.swing.JTextField();
        txtGiaCanHo = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        cbbGioiTinh1 = new javax.swing.JComboBox<>();
        txtSoDT = new javax.swing.JTextField();
        txtSoCMND = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnInHopDong = new javax.swing.JButton();
        datNgSinh = new com.toedter.calendar.JDateChooser();
        datNgGD = new com.toedter.calendar.JDateChooser();
        btn_Refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel2.setkEndColor(new java.awt.Color(6, 154, 142));
        kGradientPanel2.setkStartColor(new java.awt.Color(238, 252, 245));
        kGradientPanel2.setkTransparentControls(false);
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 198, 38));
        jLabel1.setText("NHÂN VIÊN");
        kGradientPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-close-window-50 (2).png"))); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        kGradientPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 40, 40));

        jPanel2.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 142));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, -1, -1));

        tabNhanVien.setBackground(new java.awt.Color(238, 252, 245));
        tabNhanVien.setForeground(new java.awt.Color(27, 163, 156));
        tabNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tabQuanLyCuDan.setBackground(new java.awt.Color(255, 255, 255));
        tabQuanLyCuDan.setForeground(new java.awt.Color(51, 0, 255));
        tabQuanLyCuDan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(27, 163, 156));
        jLabel2.setText("Quản Lý Cư Dân");
        tabQuanLyCuDan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(238, 252, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(238, 252, 245));
        jLabel5.setText("Thông tin cư dân");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 59, 102));
        jLabel6.setText("Mã cư dân");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 59, 102));
        jLabel7.setText("Tên cư dân");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 59, 102));
        jLabel8.setText("Ngày sinh");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 59, 102));
        jLabel9.setText("Giới tính");

        txtMaCD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaCD.setForeground(new java.awt.Color(27, 163, 156));

        txtTenCD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenCD.setForeground(new java.awt.Color(27, 163, 156));
        txtTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCDActionPerformed(evt);
            }
        });

        DatNgSinh.setForeground(new java.awt.Color(27, 163, 156));
        DatNgSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbGioiTinh.setForeground(new java.awt.Color(27, 163, 156));
        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbGioiTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbGioiTinhMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 59, 102));
        jLabel10.setText("Số điện thoại");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 59, 102));
        jLabel11.setText("Quê quán");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 59, 102));
        jLabel12.setText("Số CMND");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(27, 163, 156));

        txtQueQuan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQueQuan.setForeground(new java.awt.Color(27, 163, 156));

        txtSoCM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoCM.setForeground(new java.awt.Color(27, 163, 156));

        btnUpdate.setBackground(new java.awt.Color(250, 198, 38));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 59, 102));
        btnUpdate.setText("Cập nhật thông tin");
        btnUpdate.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnThemCD.setBackground(new java.awt.Color(250, 198, 38));
        btnThemCD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemCD.setForeground(new java.awt.Color(0, 59, 102));
        btnThemCD.setText("Thêm Cư Dân");
        btnThemCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCDActionPerformed(evt);
            }
        });

        btnXoaCD.setBackground(new java.awt.Color(250, 198, 38));
        btnXoaCD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaCD.setForeground(new java.awt.Color(0, 59, 102));
        btnXoaCD.setText("Xóa cư dân");
        btnXoaCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCD)
                                    .addComponent(txtTenCD)
                                    .addComponent(DatNgSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                                .addGap(118, 118, 118))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(txtQueQuan)
                                    .addComponent(txtSoCM)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnThemCD, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaCD, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel5)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(DatNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThemCD)
                                .addComponent(btnXoaCD))))
                    .addComponent(txtSoCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabQuanLyCuDan.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 48, 900, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 59, 102));
        jLabel13.setText("Tìm kiếm");
        tabQuanLyCuDan.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        txtTimKiem1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem1KeyReleased(evt);
            }
        });
        tabQuanLyCuDan.add(txtTimKiem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 329, 30));

        jScrollPane1.setBackground(new java.awt.Color(238, 252, 245));
        jScrollPane1.setForeground(new java.awt.Color(27, 163, 156));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblQuanLyCuDan.setBackground(new java.awt.Color(238, 252, 245));
        tblQuanLyCuDan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        tblQuanLyCuDan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblQuanLyCuDan.setForeground(new java.awt.Color(27, 163, 156));
        tblQuanLyCuDan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQuanLyCuDan.setSelectionBackground(new java.awt.Color(27, 163, 156));
        tblQuanLyCuDan.setShowGrid(false);
        tblQuanLyCuDan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyCuDanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLyCuDan);

        tabQuanLyCuDan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 900, 180));

        btnRefresh.setBackground(new java.awt.Color(250, 198, 38));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(0, 59, 102));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        tabQuanLyCuDan.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        cbbMaCH.setBackground(new java.awt.Color(250, 198, 38));
        cbbMaCH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbMaCH.setForeground(new java.awt.Color(27, 163, 156));
        cbbMaCH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaCHActionPerformed(evt);
            }
        });
        tabQuanLyCuDan.add(cbbMaCH, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 120, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 59, 102));
        jLabel22.setText("Mã căn hộ");
        tabQuanLyCuDan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, -1));

        btnInDSCuDan.setBackground(new java.awt.Color(250, 198, 38));
        btnInDSCuDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInDSCuDan.setForeground(new java.awt.Color(0, 59, 102));
        btnInDSCuDan.setText("In danh sách cư dân");
        btnInDSCuDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDSCuDanActionPerformed(evt);
            }
        });
        tabQuanLyCuDan.add(btnInDSCuDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, 200, 30));

        tabNhanVien.addTab("Quản lý cư dân", tabQuanLyCuDan);

        tabThongTinMuaBan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(27, 163, 156));
        jLabel3.setText("Thông Tin Mua Bán");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 59, 102));
        jLabel4.setText("Tìm kiếm");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        JScrollPane.setBackground(new java.awt.Color(238, 252, 245));
        JScrollPane.setForeground(new java.awt.Color(27, 163, 156));

        tblThongTinMuaBan.setBackground(new java.awt.Color(238, 252, 245));
        tblThongTinMuaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblThongTinMuaBan.setForeground(new java.awt.Color(27, 163, 156));
        tblThongTinMuaBan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThongTinMuaBan.setSelectionBackground(new java.awt.Color(27, 163, 156));
        JScrollPane.setViewportView(tblThongTinMuaBan);

        btnRefreshHD.setBackground(new java.awt.Color(250, 198, 38));
        btnRefreshHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefreshHD.setForeground(new java.awt.Color(0, 59, 102));
        btnRefreshHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefreshHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabThongTinMuaBanLayout = new javax.swing.GroupLayout(tabThongTinMuaBan);
        tabThongTinMuaBan.setLayout(tabThongTinMuaBanLayout);
        tabThongTinMuaBanLayout.setHorizontalGroup(
            tabThongTinMuaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                .addGroup(tabThongTinMuaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel4)
                        .addGap(43, 43, 43)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefreshHD, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        tabThongTinMuaBanLayout.setVerticalGroup(
            tabThongTinMuaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                .addGroup(tabThongTinMuaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabThongTinMuaBanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(tabThongTinMuaBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(btnRefreshHD))
                        .addGap(22, 22, 22)
                        .addComponent(txtTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        tabNhanVien.addTab("Thông tin mua bán", tabThongTinMuaBan);

        tabThongTinCanHo.setBackground(new java.awt.Color(255, 255, 255));
        tabThongTinCanHo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(27, 163, 156));
        jLabel14.setText("Thông Tin Căn Hộ");
        tabThongTinCanHo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 51)));

        tblThongTinCanHo.setBackground(new java.awt.Color(238, 252, 245));
        tblThongTinCanHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblThongTinCanHo.setForeground(new java.awt.Color(27, 163, 156));
        tblThongTinCanHo.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThongTinCanHo.setSelectionBackground(new java.awt.Color(27, 163, 156));
        tblThongTinCanHo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinCanHoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThongTinCanHo);

        tabThongTinCanHo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 550, 320));

        jPanel4.setBackground(new java.awt.Color(238, 252, 245));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 59, 102));
        jLabel15.setText("Diện tích");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 59, 102));
        jLabel16.setText("Giá");

        cbbDienTich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 59, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Số phòng");

        cbbSoPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTimKiem.setBackground(new java.awt.Color(250, 198, 38));
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(0, 59, 102));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel15)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(cbbDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cbbGia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnTimKiem))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(55, 55, 55))
        );

        tabThongTinCanHo.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 80));

        jPanel5.setBackground(new java.awt.Color(238, 252, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(27, 163, 156));
        jLabel18.setText("Thông tin hợp đồng");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 59, 102));
        jLabel19.setText("Mã căn hộ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 59, 102));
        jLabel20.setText("Giá căn hộ");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 59, 102));
        jLabel21.setText("Ngày giao dịch");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 59, 102));
        jLabel23.setText("Tên khách hàng");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 59, 102));
        jLabel24.setText("Ngày sinh");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 59, 102));
        jLabel25.setText("Giới tính");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 59, 102));
        jLabel26.setText("SĐT");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 59, 102));
        jLabel27.setText("Số CMND");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 59, 102));
        jLabel28.setText("Địa chỉ");

        cbbGioiTinh1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtSoDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDTActionPerformed(evt);
            }
        });

        btnInHopDong.setBackground(new java.awt.Color(250, 198, 38));
        btnInHopDong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInHopDong.setForeground(new java.awt.Color(0, 59, 102));
        btnInHopDong.setText("In hợp đồng");
        btnInHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHopDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCanHo)
                                    .addComponent(txtGiaCanHo)
                                    .addComponent(datNgGD, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnInHopDong)
                .addGap(113, 113, 113))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txtMaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(txtGiaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(datNgGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(datNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(cbbGioiTinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txtSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnInHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabThongTinCanHo.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 68, 350, -1));

        btn_Refresh.setBackground(new java.awt.Color(250, 198, 38));
        btn_Refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Refresh.setForeground(new java.awt.Color(0, 59, 102));
        btn_Refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btn_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RefreshActionPerformed(evt);
            }
        });
        tabThongTinCanHo.add(btn_Refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, -1));

        tabNhanVien.addTab("Mua bán căn hộ", tabThongTinCanHo);

        jPanel1.add(tabNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 168, 980, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -22, 990, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:

        this.dispose();
        DangNhapGUI dn = new DangNhapGUI();
        dn.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:

        LoadTableTTMB(ThongTin_MuaBanBLL.TimKiem_BLL(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem1KeyReleased
        // TODO add your handling code here:
        LoadTableQLCD(QuanLy_CuDanBLL.TimKiem_BLL(txtTimKiem1.getText()));
    }//GEN-LAST:event_txtTimKiem1KeyReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblQuanLyCuDanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyCuDanMouseClicked
        // TODO add your handling code here:
        int indexTB = tblQuanLyCuDan.getSelectedRow();

        if (indexTB >= 0 && indexTB < tblQuanLyCuDan.getRowCount()) {
            txtMaCD.setText(tblQuanLyCuDan.getValueAt(indexTB, 0).toString());
            txtTenCD.setText(tblQuanLyCuDan.getValueAt(indexTB, 1).toString());

            cbbGioiTinh.setSelectedItem(tblQuanLyCuDan.getValueAt(indexTB, 3).toString());
            txtSDT.setText(tblQuanLyCuDan.getValueAt(indexTB, 4).toString());
            txtSoCM.setText(tblQuanLyCuDan.getValueAt(indexTB, 5).toString());
            txtQueQuan.setText(tblQuanLyCuDan.getValueAt(indexTB, 6).toString());
        }
        try {

            Date dat = new SimpleDateFormat("yyyy-MM-dd").parse((String) tblQuanLyCuDan.getValueAt(indexTB, 2));
            DatNgSinh.setDate(dat);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Lỗi nè");
        }
    }//GEN-LAST:event_tblQuanLyCuDanMouseClicked

    private void cbbGioiTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbGioiTinhMouseClicked
        // TODO add your handling code here:
        cbbGioiTinh.setEditable(true);
    }//GEN-LAST:event_cbbGioiTinhMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        CuDan cd = new CuDan();
        cd.setMaCuDan(Integer.parseInt(txtMaCD.getText()));
        cd.setTenCuDan(txtTenCD.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(DatNgSinh.getDate());
        cd.setNgaySinh(date);
        cd.setGioiTinh(cbbGioiTinh.getSelectedItem().toString());
        cd.setSoDT(txtSDT.getText());
        cd.setSoCMT(txtSoCM.getText());
        cd.setQueQuan(txtQueQuan.getText());

        try {
            int result = QuanLy_CuDanBLL.UpdateCuDan(cd);
            if (result != 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
            LoadTableQLCD(QuanLy_CuDanBLL.dsCuDan());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnUpdateMouseClicked

    private void tblThongTinCanHoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinCanHoMouseClicked
        // TODO add your handling code here:
        int indexTB = tblThongTinCanHo.getSelectedRow();
        if (indexTB >= 0 && indexTB < tblThongTinCanHo.getRowCount()) {
            txtMaCanHo.setText(tblThongTinCanHo.getValueAt(indexTB, 0).toString());
            txtGiaCanHo.setText(tblThongTinCanHo.getValueAt(indexTB, 2).toString());

        }


    }//GEN-LAST:event_tblThongTinCanHoMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:

        ArrayList<CanHo> CanHo = new ArrayList<>();

        CanHo = ThongTin_CanHoBLL.TimKiemCanHo(Float.parseFloat(cbbDienTich.getSelectedItem().toString()), Long.parseLong(cbbGia.getSelectedItem().toString()), Integer.parseInt(cbbSoPhong.getSelectedItem().toString()));
        LoadTableTTCH(CanHo);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnInHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHopDongActionPerformed

        if (ValidForm() == false) {
            return;
        } else {

            ArrayList<CuDan> dsCuDan = new ArrayList<>();
            dsCuDan = QuanLy_CuDanBLL.dsCuDan();
            String mcd = "111111";
            String maHopDong = "";

            for (CuDan cd : dsCuDan) {
                if (cd.getMaCuDan() == Integer.parseInt(mcd)) {
                    mcd = String.valueOf(Integer.parseInt(mcd) + 1);
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(datNgSinh.getDate());
            QuanLy_CuDanBLL.insertCD_DAL(mcd, txtTenKH.getText(), date, cbbGioiTinh1.getSelectedItem().toString(), txtSoDT.getText(), txtSoCMND.getText(), txtDiaChi.getText());

            //mcd, txtTenkh, txtNgsinh, cmbGioitinh1, txtSDT, txtScmt, txtDiachi)
            maHopDong = txtMaCanHo.getText() + mcd;
            ThongTin_CanHoBLL.updateCH_DAL(txtMaCanHo.getText(), mcd);
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(datNgGD.getDate());
            ThongTin_MuaBanBLL.insertHD_DAL(maHopDong, date1, txtDiaChi.getText(), mcd, txtMaCanHo.getText());
            QuanLy_CuDanBLL.insertCTCD(mcd, txtMaCanHo.getText());
            LoadCbbMaCH();
            LoadTableTTCH(ThongTin_CanHoBLL.dsCanHo());

            Document document = new Document(PageSize.A4);
            String filename = "HD";
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("reports/" + filename + ".pdf"));

                document.open();

                File filefontTieuDe = new File("fonts/vuArialBold.ttf");

                File filefontNoiDung = new File("fonts/vuArial.ttf");
                BaseFont bfNoiDung = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font fontNoiDung1 = new Font(bfNoiDung, 13);
                Font fontNoiDung2 = new Font(bfNoiDung, 12);

                Paragraph prgCHXH = new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM", fontNoiDung2);
                prgCHXH.setAlignment(Element.ALIGN_CENTER);

                document.add(prgCHXH);

                Paragraph prgDLTD = new Paragraph("Độc Lập - Tự Do - Hạnh Phúc", fontNoiDung2);
                prgDLTD.setAlignment(Element.ALIGN_CENTER);

                document.add(prgDLTD);

                Paragraph prgKC = new Paragraph("--------------", fontNoiDung2);
                prgKC.setAlignment(Element.ALIGN_CENTER);

                document.add(prgKC);

                Paragraph prgHD = new Paragraph("HỢP ĐỒNG MUA BÁN CHUNG CƯ", fontNoiDung2);
                prgHD.setAlignment(Element.ALIGN_CENTER);

                document.add(prgHD);

                Paragraph prgSo = new Paragraph("Số ....../HD ", fontNoiDung2);
                prgSo.setAlignment(Element.ALIGN_CENTER);

                document.add(prgSo);

                Paragraph prgDate = new Paragraph("Hôm nay, ngày ... tháng ... năm ..., tại ...... hai bên chúng tôi gồm:", fontNoiDung2);
                prgDate.setIndentationLeft(70);

                document.add(prgDate);

                Paragraph prgBenA = new Paragraph("1. BÊN BÁN NHÀ Ở CHUNG CƯ (gọi tắt là 'Bên bán'): Tập đoàn Anland", fontNoiDung1);
                prgBenA.setIndentationLeft(60);
                document.add(prgBenA);

                Paragraph prgTruSo = new Paragraph("Trụ sở chính: Tầng 75, LandMark 81", fontNoiDung1);
                prgTruSo.setIndentationLeft(70);
                document.add(prgTruSo);

                Paragraph prgDT = new Paragraph("Điện thoại: 0908162539", fontNoiDung1);
                prgDT.setIndentationLeft(70);
                document.add(prgDT);

                Paragraph prgTK = new Paragraph("Tài khoản số: 123456478979797", fontNoiDung1);
                prgTK.setIndentationLeft(70);
                document.add(prgTK);

                Paragraph prgST = new Paragraph("Mã số thuế: MSTBCL0701", fontNoiDung1);
                prgST.setIndentationLeft(70);
                document.add(prgST);

                Paragraph prgDD = new Paragraph("Người đại diện: Lưu Vĩnh Phát", fontNoiDung1);
                prgDD.setIndentationLeft(70);
                document.add(prgDD);

                Paragraph prgCV = new Paragraph("Chức vụ: Trưởng phòng nhân sự", fontNoiDung1);
                prgCV.setIndentationLeft(70);
                document.add(prgCV);

                Paragraph prgUQ = new Paragraph("(Theo Giấy ủy quyền ngày.....tháng.....năm..... của...............................)", fontNoiDung1);
                prgUQ.setIndentationLeft(70);
                document.add(prgUQ);

                Paragraph prgBenB = new Paragraph("2. BÊN MUA NHÀ Ở CHUNG CƯ(gọi tắt là 'Bên mua'):", fontNoiDung1);
                prgBenB.setIndentationLeft(60);
                document.add(prgBenB);

                Paragraph prgKH = new Paragraph("Ông/Bà: " + txtTenKH.getText(), fontNoiDung1);
                prgKH.setIndentationLeft(70);
                document.add(prgKH);

                String dat = sdf.format(datNgSinh.getDate());
                String[] arrayNgSinh = dat.split("-");
                String ngay = arrayNgSinh[2];
                String thang = arrayNgSinh[1];
                String nam = arrayNgSinh[0];

                Paragraph prgNS = new Paragraph("Ngày sinh: " + ngay + "/" + thang + "/" + nam, fontNoiDung1);
                prgNS.setIndentationLeft(70);
                document.add(prgNS);

                Paragraph prgCMND = new Paragraph("Số CMND: " + txtSoCMND.getText(), fontNoiDung1);
                prgCMND.setIndentationLeft(70);
                document.add(prgCMND);

                Paragraph prgDC = new Paragraph("Địa chỉ: " + txtDiaChi.getText(), fontNoiDung1);
                prgDC.setIndentationLeft(70);
                document.add(prgDC);

                Paragraph prgSDT = new Paragraph("Số điện thoại: " + txtSoDT.getText() + " \n \n \n ", fontNoiDung1);
                prgSDT.setIndentationLeft(70);
                document.add(prgSDT);

                Paragraph prgBenMua = new Paragraph("Bên mua ", fontNoiDung1);
                prgBenMua.setIndentationLeft(40);
                prgBenMua.setAlignment(Element.ALIGN_LEFT);

                document.add(prgBenMua);

                Paragraph prgBenBan = new Paragraph("Bên bán ", fontNoiDung1);
                prgBenBan.setIndentationRight(40);
                prgBenBan.setAlignment(Element.ALIGN_RIGHT);

                document.add(prgBenBan);

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
        }

    }//GEN-LAST:event_btnInHopDongActionPerformed

    private void txtTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCDActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        LoadTableQLCD(QuanLy_CuDanBLL.dsCuDan());

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btn_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RefreshActionPerformed
        // TODO add your handling code here:
        LoadTableTTCH(ThongTin_CanHoBLL.dsCanHo());
    }//GEN-LAST:event_btn_RefreshActionPerformed

    private void btnThemCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCDActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ThemCuDan ThemCD = new ThemCuDan();
        ThemCD.setVisible(true);

    }//GEN-LAST:event_btnThemCDActionPerformed

    private void txtSoDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDTActionPerformed

    }//GEN-LAST:event_txtSoDTActionPerformed

    private void cbbMaCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaCHActionPerformed
        // TODO add your handling code here:
        ArrayList<CuDan> dsCuDan = new ArrayList<>();
        dsCuDan = QuanLy_CuDanBLL.ResidentByApartment(cbbMaCH.getSelectedItem().toString());
        LoadTableQLCD(dsCuDan);
    }//GEN-LAST:event_cbbMaCHActionPerformed

    private void btnXoaCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCDActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Xác nhận xóa cư dân", "Xóa cư dân", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            int j = QuanLy_CuDanBLL.deleteCTCD(txtMaCD.getText());
            int i = QuanLy_CuDanBLL.deleteCD(txtMaCD.getText());
            if (i != 0) {
                JOptionPane.showMessageDialog(null, "Xóa cư dân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            LoadTableQLCD(QuanLy_CuDanBLL.dsCuDan());
        }

    }//GEN-LAST:event_btnXoaCDActionPerformed

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

    private void btnRefreshHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshHDActionPerformed
        // TODO add your handling code here:
        LoadTableTTMB(ThongTin_MuaBanBLL.ListHopDong());
    }//GEN-LAST:event_btnRefreshHDActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DatNgSinh;
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton btnInDSCuDan;
    private javax.swing.JButton btnInHopDong;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefreshHD;
    private javax.swing.JButton btnThemCD;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoaCD;
    private javax.swing.JButton btn_Refresh;
    private javax.swing.JComboBox<String> cbbDienTich;
    private javax.swing.JComboBox<String> cbbGia;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbGioiTinh1;
    private javax.swing.JComboBox<String> cbbMaCH;
    private javax.swing.JComboBox<String> cbbSoPhong;
    private com.toedter.calendar.JDateChooser datNgGD;
    private com.toedter.calendar.JDateChooser datNgSinh;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTabbedPane tabNhanVien;
    private javax.swing.JPanel tabQuanLyCuDan;
    private javax.swing.JPanel tabThongTinCanHo;
    private javax.swing.JPanel tabThongTinMuaBan;
    private javax.swing.JTable tblQuanLyCuDan;
    private javax.swing.JTable tblThongTinCanHo;
    private javax.swing.JTable tblThongTinMuaBan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGiaCanHo;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextField txtMaCanHo;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoCM;
    private javax.swing.JTextField txtSoCMND;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables
}
