/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.*;
import BLL.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PC
 */
public class ThemCuDan extends javax.swing.JFrame {

    /**
     * Creates new form ThemCuDan
     */
    public ThemCuDan() {
        initComponents();
        setTitle("Thêm Cư Dân");
        LoadCbbMaCH();
        LoadCbbGioiTinh();
        LoadTieuDeTable();

    }

    QuanLyCuDanBLL QuanLy_CuDanBLL = new QuanLyCuDanBLL();
    CanHoBLL CanHo = new CanHoBLL();
    public boolean ValidForm() {
        if (Validation.isNumber(txtSDT, "Số điện thoại phải là số!")) {
            return false;
        }

        if (Validation.isEmpty(txtTenCD, "Tên cư dân không được trống!")) {
            return false;
        }

        if (Validation.isEmpty(txtQueQuan, "Quê quán không được trống!")) {
            return false;
        }

        return true;

    }

    public void LoadCbbGioiTinh() {
        cbbGioiTinh.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
    }

    public void LoadCbbMaCH() {
        ArrayList<String> MaCanHo = new ArrayList<>();
        MaCanHo = CanHo.LoadCbbMaCanHoDaBan();
        cbbMaCanHo.setModel(new DefaultComboBoxModel<String>(MaCanHo.toArray(new String[0])));
    }
    DefaultTableModel tblModelQuanLyCuDan;

    public void LoadTieuDeTable() {
        tblModelQuanLyCuDan = new DefaultTableModel();
        String tieude[] = {"Mã cư dân", " Tên cư dân", "Ngày sinh", "Giới tính", "Số điện thoại", "Số CMT", "Quê quán"};
        tblModelQuanLyCuDan.setColumnIdentifiers(tieude);
        tblCuDan.setModel(tblModelQuanLyCuDan);
    }

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
        tblCuDan.setModel(tblModelQuanLyCuDan);
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
        cbbMaCanHo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuDan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenCD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQueQuan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSoCMND = new javax.swing.JTextField();
        btnThemCD = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        DatNgSinh = new com.toedter.calendar.JDateChooser();
        btnRefresh = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 252, 245));

        cbbMaCanHo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaCanHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaCanHoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 59, 102));
        jLabel2.setText("Mã Căn Hộ");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jScrollPane1.setViewportView(tblCuDan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addGap(53, 53, 53)
                .addComponent(cbbMaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbMaCanHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(238, 252, 245));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 59, 102));
        jLabel3.setText("Tên cư dân");

        txtTenCD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenCD.setForeground(new java.awt.Color(27, 163, 156));
        txtTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 59, 102));
        jLabel4.setText("Ngày Sinh");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 59, 102));
        jLabel5.setText("Giới tính");

        cbbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbGioiTinh.setForeground(new java.awt.Color(27, 163, 156));
        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 59, 102));
        jLabel6.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(27, 163, 156));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 59, 102));
        jLabel7.setText("Quê quán");

        txtQueQuan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQueQuan.setForeground(new java.awt.Color(27, 163, 156));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 59, 102));
        jLabel8.setText("Số CMT");

        txtSoCMND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoCMND.setForeground(new java.awt.Color(27, 163, 156));

        btnThemCD.setBackground(new java.awt.Color(250, 198, 38));
        btnThemCD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemCD.setForeground(new java.awt.Color(0, 59, 102));
        btnThemCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/adduser.png"))); // NOI18N
        btnThemCD.setText("Thêm Cư Dân");
        btnThemCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCDActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(250, 198, 38));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 59, 102));
        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        DatNgSinh.setForeground(new java.awt.Color(27, 163, 156));
        DatNgSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnRefresh.setBackground(new java.awt.Color(250, 198, 38));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(0, 59, 102));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenCD)
                            .addComponent(DatNgSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT)
                            .addComponent(txtQueQuan)
                            .addComponent(txtSoCMND, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(btnThemCD, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7)
                        .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DatNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCD)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(6, 154, 142));
        kGradientPanel1.setkStartColor(new java.awt.Color(238, 252, 245));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 198, 38));
        jLabel1.setText("Thông tin cư dân");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                    .addGap(0, 272, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 287, Short.MAX_VALUE)))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                    .addGap(0, 33, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 40, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbMaCanHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaCanHoActionPerformed
        // TODO add your handling code here:
        ArrayList<CuDan> dsCuDan = new ArrayList<>();
        dsCuDan = QuanLy_CuDanBLL.ResidentByApartment(cbbMaCanHo.getSelectedItem().toString());
        LoadTableQLCD(dsCuDan);
    }//GEN-LAST:event_cbbMaCanHoActionPerformed

    private void txtTenCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCDActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        NhanVienGUI nv = new NhanVienGUI();
        nv.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnThemCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCDActionPerformed
        // TODO add your handling code here:
        if (ValidForm() == false) {
            return;
        } else {
            ArrayList<CuDan> dsCuDan = new ArrayList<>();
            dsCuDan = QuanLy_CuDanBLL.dsCuDan();
            String mcd = "111111";

            for (CuDan cd : dsCuDan) {
                if (cd.getMaCuDan() == Integer.parseInt(mcd)) {
                    mcd = String.valueOf(Integer.parseInt(mcd) + 1);
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(DatNgSinh.getDate());
            int i = QuanLy_CuDanBLL.insertCD_DAL(mcd, txtTenCD.getText(), date, cbbGioiTinh.getSelectedItem().toString(), txtSDT.getText(), txtSoCMND.getText(), txtQueQuan.getText());
            int j = QuanLy_CuDanBLL.insertCTCD(mcd, cbbMaCanHo.getSelectedItem().toString());
            if (i != 0 || j != 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemCDActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        txtTenCD.setText("");
        txtSDT.setText("");
        txtQueQuan.setText("");
        txtSoCMND.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(ThemCuDan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemCuDan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemCuDan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemCuDan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemCuDan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DatNgSinh;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnThemCD;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbMaCanHo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JTable tblCuDan;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoCMND;
    private javax.swing.JTextField txtTenCD;
    // End of variables declaration//GEN-END:variables
}
