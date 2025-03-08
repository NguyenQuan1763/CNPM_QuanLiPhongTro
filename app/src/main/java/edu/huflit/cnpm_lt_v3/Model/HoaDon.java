package edu.huflit.cnpm_lt_v3.Model;

import java.io.Serializable;

public class HoaDon implements Serializable {

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    private String MaHoaDon;

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }

    private String MaPhong;
    public HoaDon(String maHoaDon,String maPhong,String maPhongThue, String gioiThieu, String giaTien, String diaChi, String moTa, String hinhPhong, String hoTenNguoiThue, String CCCD, String SDT,String ngayThue, String thang, String tienDien, String tienNuoc,String tinhTrangHD,String ngayThanhToan) {

        MaHoaDon = maHoaDon;
        MaPhong = maPhong;
        MaPhongThue = maPhongThue;
        GioiThieu = gioiThieu;
        GiaTien = giaTien;
        DiaChi = diaChi;
        MoTa = moTa;
        HinhPhong = hinhPhong;
        HoTenNguoiThue = hoTenNguoiThue;
        this.CCCD = CCCD;
        this.SDT = SDT;
        Thang = thang;
        TienDien = tienDien;
        TienNuoc = tienNuoc;
        TinhTrangHD = tinhTrangHD;
        NgayThanhToan = ngayThanhToan;
        NgayThue = ngayThue;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String ngayThue) {
        NgayThue = ngayThue;
    }

    private String NgayThue;
    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        NgayThanhToan = ngayThanhToan;
    }

    private String NgayThanhToan;
    public String getTinhTrangHD() {
        return TinhTrangHD;
    }

    public void setTinhTrangHD(String tinhTrangHD) {
        TinhTrangHD = tinhTrangHD;
    }

    private String TinhTrangHD;

    public String getMaPhongThue() {
        return MaPhongThue;
    }

    public void setMaPhongThue(String maPhongThue) {
        MaPhongThue = maPhongThue;
    }

    private String MaPhongThue;
    private String GioiThieu;
    private String  GiaTien;
    private String DiaChi;
    private String MoTa;
    private String HinhPhong;
    private String HoTenNguoiThue;
    private String CCCD;
    private String SDT;
    private  String Thang;

    private String TienDien;
    private String TienNuoc;


    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
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

    public String getHoTenNguoiThue() {
        return HoTenNguoiThue;
    }

    public void setHoTenNguoiThue(String hoTenNguoiThue) {
        HoTenNguoiThue = hoTenNguoiThue;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getThang() {
        return Thang;
    }

    public void setThang(String thang) {
        Thang = thang;
    }

    public String getTienDien() {
        return TienDien;
    }

    public void setTienDien(String tienDien) {
        TienDien = tienDien;
    }

    public String getTienNuoc() {
        return TienNuoc;
    }

    public void setTienNuoc(String tienNuoc) {
        TienNuoc = tienNuoc;
    }


}

