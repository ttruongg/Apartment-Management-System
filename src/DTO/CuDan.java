/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
//import java.util.Date;
/**
 *
 * @author PC
 */
public class CuDan {
    private int maCuDan;
    private String tenCuDan;
    private String ngaySinh;
    private String gioiTinh;
    private String soDT;
    private String soCMT;
    private String queQuan;
    private String maCanHo;

    public int getMaCuDan() {
        return maCuDan;
    }

    public void setMaCuDan(int maCuDan) {
        this.maCuDan = maCuDan;
    }

    

    public String getTenCuDan() {
        return tenCuDan;
    }

    public void setTenCuDan(String tenCuDan) {
        this.tenCuDan = tenCuDan;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    

    
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    

    
    

    

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getSoCMT() {
        return soCMT;
    }

    public void setSoCMT(String soCMT) {
        this.soCMT = soCMT;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getMaCanHo() {
        return maCanHo;
    }

    public void setMaCanHo(String maCanHo) {
        this.maCanHo = maCanHo;
    }

    public CuDan() {
    }

    public CuDan(int maCuDan, String tenCuDan, String ngaySinh, String gioiTinh, String soDT, String soCMT, String queQuan, String maCanHo) {
        this.maCuDan = maCuDan;
        this.tenCuDan = tenCuDan;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.soCMT = soCMT;
        this.queQuan = queQuan;
        this.maCanHo = maCanHo;
    }

    public CuDan(int maCuDan, String maCanHo) {
        this.maCuDan = maCuDan;
        this.maCanHo = maCanHo;
    }
    
    
    
}
