package edu.huflit.cnpm_lt_v3.Model;

import java.io.Serializable;

public class YeuCau implements Serializable {
    private String HoTenNguoiThue;

    public YeuCau(String maYeucau, String maHoaDon, String maPhong, String maPhongThue, String nhanLaiCoc , String hoTenNguoiThue, String lyDo, String ngayThangGuiYeuCau, String ngayThangTra, String ngayThue, String phanHoi) {
        MaYeuCau = maYeucau;
        HoTenNguoiThue = hoTenNguoiThue;
        LyDo = lyDo;
        MaHoaDon = maHoaDon;
        MaPhong = maPhong;
        MaPhongThue = maPhongThue;
        NgayThue = ngayThue;
        NhanLaiCoc = nhanLaiCoc;
        PhanHoi = phanHoi;
        NgayThangGuiYeuCau = ngayThangGuiYeuCau;
        NgayThangTra = ngayThangTra;
    }

    public String getMaYeuCau() {
        return MaYeuCau;
    }

    public void setMaYeuCau(String maYeuCau) {
        MaYeuCau = maYeuCau;
    }

    private String MaYeuCau;
    private String MaHoaDon;
    private  String MaPhong;

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String maPhong) {
        MaPhong = maPhong;
    }

    public String getMaPhongThue() {
        return MaPhongThue;
    }

    public void setMaPhongThue(String maPhongThue) {
        MaPhongThue = maPhongThue;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String ngayThue) {
        NgayThue = ngayThue;
    }

    public String getNhanLaiCoc() {
        return NhanLaiCoc;
    }

    public void setNhanLaiCoc(String nhanLaiCoc) {
        NhanLaiCoc = nhanLaiCoc;
    }

    public String getPhanHoi() {
        return PhanHoi;
    }

    public void setPhanHoi(String phanHoi) {
        PhanHoi = phanHoi;
    }

    private String MaPhongThue;
    private String NgayThue;
    private String NhanLaiCoc;
    private String PhanHoi;
    private String LyDo;
    private String NgayThangGuiYeuCau;
    private String NgayThangTra;

    public String getHoTenNguoiThue() {
        return HoTenNguoiThue;
    }

    public void setHoTenNguoiThue(String hoTenNguoiThue) {
        HoTenNguoiThue = hoTenNguoiThue;
    }

    public String getLyDo() {
        return LyDo;
    }

    public void setLyDo(String lyDo) {
        LyDo = lyDo;
    }

    public String getNgayThangGuiYeuCau() {
        return NgayThangGuiYeuCau;
    }

    public void setNgayThangGuiYeuCau(String ngayThangGuiYeuCau) {
        NgayThangGuiYeuCau = ngayThangGuiYeuCau;
    }

    public String getNgayThangTra() {
        return NgayThangTra;
    }

    public void setNgayThangTra(String ngayThangTra) {
        NgayThangTra = ngayThangTra;
    }


}
