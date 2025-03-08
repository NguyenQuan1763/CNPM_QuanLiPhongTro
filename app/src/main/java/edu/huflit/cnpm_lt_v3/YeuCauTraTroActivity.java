package edu.huflit.cnpm_lt_v3;

import androidx.annotation.NonNull;
import androidx.annotation.OpenForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_lt_v3.Model.HoaDon;
import edu.huflit.cnpm_lt_v3.Model.PhongTro;

public class YeuCauTraTroActivity extends AppCompatActivity {

    TextView tvHoTenNguoiTra,tvNgayTraPhong,tvKiemTra;
    EditText edtLyDoTra;
    HoaDon hoaDon;
    int monthsDiff;
    Button btnNopDon,btnKiemtra;
    ImageButton btnCalendar;
    FirebaseFirestore db;
    String NgayThangGuiYeuCau;
    Boolean NhanCoc = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_cau_tra_tro);
        AnhXa();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        NgayThangGuiYeuCau = date.format(formatter);

        tvNgayTraPhong.setText(NgayThangGuiYeuCau);

        hoaDon = (HoaDon) getIntent().getSerializableExtra("Yeucautratro");
        tvHoTenNguoiTra.setText(hoaDon.getHoTenNguoiThue());

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogrentdate();
            }
        });

        btnKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TinhTrangThanhToan = hoaDon.getTinhTrangHD();
                String NgayThue = hoaDon.getNgayThue();
                String NgayTra = (String) tvNgayTraPhong.getText();
                int ngaychenhlech = NgayChenhLech(NgayThangGuiYeuCau,NgayTra);
                int thangChenhLech =  ThangChenhLech(NgayThue,NgayTra);
                if(TinhTrangThanhToan.equals("Chưa thanh toán")){
                    tvKiemTra.setText("Vui lòng thanh toán trước");
                }
                else if(ngaychenhlech < 0){
                    tvKiemTra.setText("Vui lòng chọn ngày >= ngày hiện tại");
                }
                else if(thangChenhLech < 6){
                    tvKiemTra.setText("Số tháng của bạn: "+ thangChenhLech + "\n"+"Chú ý : Nếu số tháng dưới 6, bạn sẽ mất tiền cọc");
                    NhanCoc = false;
                    btnNopDon.setEnabled(true);
                }
                else {
                    tvKiemTra.setTextColor(ContextCompat.getColor(YeuCauTraTroActivity.this,R.color.green));
                    tvKiemTra.setText("Bạn đủ điều kiện :D");
                    NhanCoc = true;
                    btnNopDon.setEnabled(true);
                }
            }
        });
        btnNopDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MaHoaDon = hoaDon.getMaHoaDon();
                String MaPhong = hoaDon.getMaPhong();
                String  MaPhongThue = hoaDon.getMaPhongThue();
                String HoTenNguoiThue = hoaDon.getHoTenNguoiThue();
                String LyDo = String.valueOf(edtLyDoTra.getText());
                String NgayThue = hoaDon.getNgayThue();
                String PhanHoi = "Chưa phản hồi";
                String NgayGuiYeuCau = NgayThangGuiYeuCau;
                String NgayThangTra = (String) tvNgayTraPhong.getText();
                String NhanLaiCoc = NhanLaiCoc();


                db = FirebaseFirestore.getInstance();
                Map<String,Object> YeuCau = new HashMap<>();
                YeuCau.put("MaHoaDon",MaHoaDon);
                YeuCau.put("HoTenNguoiThue",HoTenNguoiThue);
                YeuCau.put("LyDo",LyDo);
                YeuCau.put("MaPhong",MaPhong);
                YeuCau.put("MaPhongThue",MaPhongThue);
                YeuCau.put("NgayThue",NgayThue);
                YeuCau.put("PhanHoi",PhanHoi);
                YeuCau.put("NgayGuiYeuCau",NgayGuiYeuCau);
                YeuCau.put("NgayThangTra",NgayThangTra);
                YeuCau.put("NhanLaiCoc",NhanLaiCoc);
                db.collection("YeuCau").add(YeuCau).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(YeuCauTraTroActivity.this, "Đã gửi yêu cầu", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });



    }
    public String NhanLaiCoc(){
        if(NhanCoc == false){
            return "Không đủ điều kiện ";
        }
        return "Thỏa điều kiện";
    }
    private void showdialogrentdate(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog rentDatePickerDialog = new DatePickerDialog(YeuCauTraTroActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String rentDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        tvNgayTraPhong.setText(rentDate);
                        btnNopDon.setEnabled(false);
                    }
                }, mYear, mMonth, mDay);
        rentDatePickerDialog.setTitle("Chọn ngày trả");
        rentDatePickerDialog.show();
    }
    public void AnhXa(){
        tvKiemTra  = findViewById(R.id.tvKiemTra);
        tvNgayTraPhong = findViewById(R.id.tvNgayTraPhong_yc);
        tvHoTenNguoiTra = findViewById(R.id.tv_hotenyctt);
        edtLyDoTra = findViewById(R.id.edt_lydotraphong);
        btnCalendar = findViewById(R.id.btnCalendar);
        btnNopDon = findViewById(R.id.btnnopdonyeucau);
        btnKiemtra = findViewById(R.id.btnKiemtra);
        btnNopDon.setEnabled(false);
    }
    public int ThangChenhLech(String NgayThue, String NgayTra){

        String[] parts2 = NgayTra.split("/");
        int day2 = Integer.parseInt(parts2[0]);
        int month2 = Integer.parseInt(parts2[1]);
        int year2 = Integer.parseInt(parts2[2]);

        String[] parts = NgayThue.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        LocalDate NgayThueT = LocalDate.of(year, month, day); // Ngày thuê
        LocalDate NgayTraT = LocalDate.of(year2, month2, day2); // Ngày trả
        int monthsDiff = (int) ChronoUnit.MONTHS.between(NgayThueT,NgayTraT); // Số tháng chênh lệch

        return  monthsDiff;
    }
    public int NgayChenhLech(String NgayThue, String NgayTra){

        String[] parts2 = NgayTra.split("/");
        int day2 = Integer.parseInt(parts2[0]);
        int month2 = Integer.parseInt(parts2[1]);
        int year2 = Integer.parseInt(parts2[2]);

        String[] parts = NgayThue.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        LocalDate NgayThueT = LocalDate.of(year, month, day); // Ngày thuê
        LocalDate NgayTraT = LocalDate.of(year2, month2, day2); // Ngày trả
        int monthsDiff = (int) ChronoUnit.DAYS.between(NgayThueT,NgayTraT); // Số tháng chênh lệch

        return  monthsDiff;
    }
}