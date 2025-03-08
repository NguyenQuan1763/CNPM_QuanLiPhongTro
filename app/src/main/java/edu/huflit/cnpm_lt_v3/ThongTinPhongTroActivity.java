package edu.huflit.cnpm_lt_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import edu.huflit.cnpm_lt_v3.Model.PhongTro;

public class ThongTinPhongTroActivity extends AppCompatActivity {

    PhongTro phongTro;
    ImageView imgphongtro;
    TextView tvGt_phongtro,tvDiaChi_phongtro,tvGiatien_phongtro,tvMoTa_phongtro,tvLienHe_phongtro;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phong_tro);
        Anhxa();
        phongTro = (PhongTro) getIntent().getSerializableExtra("ThongTinPhong");
        tvGt_phongtro.setText(phongTro.getGioiThieu());
        tvDiaChi_phongtro.setText(phongTro.getDiaChi());
        tvGiatien_phongtro.setText(phongTro.getGiaTien());
        tvMoTa_phongtro.setText(phongTro.getMoTa());
        tvLienHe_phongtro.setText(phongTro.getLienHe());

//        StorageReference storageReference = storage.getReference();
//        StorageReference imgRef3 = storageReference.child(String.valueOf(phongTro.getHinhPhong()));
//        imgRef3.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                imgphongtro.setImageBitmap(bitmap);
//            }
//        });
        Glide.with(ThongTinPhongTroActivity.this).load(phongTro.getHinhPhong()).into(imgphongtro);
    }
    public void Anhxa(){
        tvGt_phongtro = findViewById(R.id.tv_gioithieu);
        tvDiaChi_phongtro = findViewById(R.id.tv_diachi_TT);
        tvGiatien_phongtro  = findViewById(R.id.tv_giaTT);
        tvMoTa_phongtro  = findViewById(R.id.tv_motaTT);
        imgphongtro = findViewById(R.id.imgphongtro);
        tvLienHe_phongtro = findViewById(R.id.tv_LienHe);
    }
}