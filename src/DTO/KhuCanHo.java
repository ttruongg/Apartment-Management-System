/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author PC
 */
public class KhuCanHo {
    private String maKhu;
    private String tenKhu;
    private int soTang;
    private int soCanTT;
    private  String diaChi;

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }

    public int getSoCanTT() {
        return soCanTT;
    }

    public void setSoCanTT(int soCanTT) {
        this.soCanTT = soCanTT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public KhuCanHo() {
    }

    public KhuCanHo(String maKhu, String tenKhu, int soTang, int soCanTT, String diaChi) {
        this.maKhu = maKhu;
        this.tenKhu = tenKhu;
        this.soTang = soTang;
        this.soCanTT = soCanTT;
        this.diaChi = diaChi;
    }
    
    
    
}
