package edu.huflit.cnpm_lt_v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import edu.huflit.cnpm_lt_v3.Model.YeuCau;

public class ChiTietThongBaoActivity extends AppCompatActivity {
    YeuCau thongbao;
    TextView tv_HoTenNguoiThongBao,tv_ngayguiyeucauThongBao,
            tv_NgayThangTraThongBao,tv_nhanlaicocThongBao,tv_tinhtrangThongBao,tv_nhacnho;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_bao);

        thongbao = (YeuCau) getIntent().getSerializableExtra("ThongTinThongBao");
        Anhxa();

        tv_HoTenNguoiThongBao.setText(thongbao.getHoTenNguoiThue());
        tv_ngayguiyeucauThongBao.setText(thongbao.getNgayThangGuiYeuCau());
        tv_NgayThangTraThongBao.setText(thongbao.getNgayThangTra());
        tv_nhanlaicocThongBao.setText(thongbao.getNhanLaiCoc());
        tv_tinhtrangThongBao.setText(thongbao.getPhanHoi());
        SetMau();
        if(tv_tinhtrangThongBao.getText().toString().equals("Đã duyệt")){
            tv_nhacnho.setText("Bạn còn 3 ngày để thu xếp");
        }
        db = FirebaseFirestore.getInstance();
    }
    private void SetMau() {
        String kiemtracoc = (String) tv_nhanlaicocThongBao.getText();
        if(kiemtracoc.equals("Thỏa điều kiện")){
            tv_nhanlaicocThongBao.setTextColor(ContextCompat.getColor(ChiTietThongBaoActivity.this,R.color.green));
        }
        String kiemtraduyet = (String) tv_tinhtrangThongBao.getText();
        if(kiemtraduyet.equals("Đã duyệt")){
            tv_tinhtrangThongBao.setTextColor(ContextCompat.getColor(ChiTietThongBaoActivity.this,R.color.green));
        }
    }
    private void Anhxa() {
        tv_nhacnho = findViewById(R.id.tv_nhacnho);
        tv_HoTenNguoiThongBao = findViewById(R.id.tv_HoTenNguoiThongBao);
        tv_ngayguiyeucauThongBao = findViewById(R.id.tv_ngayguiyeucauThongBao);
        tv_NgayThangTraThongBao = findViewById(R.id.tv_NgayThangTraThongBao);
        tv_nhanlaicocThongBao = findViewById(R.id.tv_nhanlaicocThongBao);
        tv_tinhtrangThongBao = findViewById(R.id.tv_tinhtrangThongBao);
    }
}