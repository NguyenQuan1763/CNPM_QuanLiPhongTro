package edu.huflit.cnpm_lt_v3.Model;

import java.io.Serializable;

public class PhongTro implements Serializable {

    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
    }

    private String GioiThieu;
    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinhPhong() {
        return HinhPhong;
    }

    public void setHinhPhong(String hinhPhong) {
        HinhPhong = hinhPhong;
    }

    private String MaPhong;
    private String  GiaTien;
    private String DiaChi;
    private String MoTa;
    private String HinhPhong;

    public String getLienHe() {
        return LienHe;
    }

    public void setLienHe(String lienHe) {
        LienHe = lienHe;
    }

    private String LienHe;

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    private String TinhTrang;
    public PhongTro(String maPhong, String giaTien, String diaChi, String moTa) {
        MaPhong = maPhong;
        GiaTien = giaTien;
        DiaChi = diaChi;
        MoTa = moTa;
    }
    public PhongTro(String maPhong, String giaTien, String diaChi, String moTa,String hinhPhong) {
        MaPhong = maPhong;
        GiaTien = giaTien;
        DiaChi = diaChi;
        MoTa = moTa;
        HinhPhong = hinhPhong;
    }
    public PhongTro(String maPhong,String gioiThieu, String giaTien, String diaChi, String moTa,String hinhPhong,String lienHe) {
        MaPhong = maPhong;
        GioiThieu = gioiThieu;
        GiaTien = giaTien;
        DiaChi = diaChi;
        MoTa = moTa;
        HinhPhong = hinhPhong;
        LienHe = lienHe;
    }




}
