package edu.huflit.cnpm_lt_v3.Adapter;

import android.app.job.JobInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_lt_v3.Model.HoaDon;
import edu.huflit.cnpm_lt_v3.R;
import edu.huflit.cnpm_lt_v3.YeuCauTraTroActivity;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonVH> {

    Context context;
    ArrayList<HoaDon> hoaDons;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseFirestore db;
    public HoaDonAdapter(FirebaseFirestore Db,ArrayList<HoaDon> hoaDons,Context context) {
        this.db = Db;
        this.hoaDons = hoaDons;
        this.context = context;
    }
    @NonNull
    @Override
    public HoaDonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon,parent,false);
        HoaDonAdapter.HoaDonVH hoaDonVH = new HoaDonAdapter.HoaDonVH(view);
        return hoaDonVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonVH holder, int position) {
        HoaDon hoaDon = hoaDons.get(position);
        holder.mDiaChi.setText(hoaDon.getDiaChi());
        holder.mGiaTien.setText(hoaDon.getGiaTien() + " VND");
        holder.mHoTen.setText(hoaDon.getHoTenNguoiThue());
        holder.mCCCD.setText(hoaDon.getCCCD());
        holder.mSDT.setText(hoaDon.getSDT());
        holder.mHoTen.setText(hoaDon.getHoTenNguoiThue());
        holder.mCCCD.setText(hoaDon.getCCCD());
        holder.mSDT.setText(hoaDon.getSDT());
        holder.mTienDien.setText(hoaDon.getTienDien());
        holder.mTienNuoc.setText(hoaDon.getTienNuoc());
        holder.mThang.setText(hoaDon.getThang());
        holder.mTongTien.setText(String.valueOf(Integer.parseInt(hoaDon.getGiaTien())+ Integer.parseInt(hoaDon.getTienDien())+ Integer.parseInt(hoaDon.getTienNuoc())));
        holder.mTinhTrangHD.setText(hoaDon.getTinhTrangHD());
        holder.mNgayThanhToan.setText(hoaDon.getNgayThanhToan());

        //Kiểm tra đã trả phòng chưa

        db.collection("PhongTro").document(hoaDon.getMaPhong())
                .get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot.exists()){
                            String TinhTrang = documentSnapshot.getString("TinhTrang");
                            if(TinhTrang.equals("Trống")){
                                holder.btnTraTro.setText("Đã trả phòng");
                                holder.btnTraTro.setEnabled(false);
                            }
                        }
                    }
                });
        Glide.with(holder.itemView.getContext()).load(hoaDon.getHinhPhong()).into(holder.mPhongtro);
        String TinhTrang = (String) holder.mTinhTrangHD.getText();
        //
        if(TinhTrang.equals("Đã thanh toán")){
            holder.btnThanhToan.setEnabled(false);
            holder.btnThanhToan.setText("Đã thanh toán");
        }
        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseFirestore.getInstance();
                //lấy ngày
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = date.format(formatter);

                Map<String,Object> ThanhToanHD= new HashMap<>();
                ThanhToanHD.put("TinhTrangHD","Đã thanh toán");
                ThanhToanHD.put("NgayThanhToan",formattedDate);
                db.collection("HoaDon").document(hoaDon.getMaHoaDon()).update(ThanhToanHD).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        notifyDataSetChanged();
                    }
                });
                holder.btnThanhToan.setText("Đã thanh toán");
                holder.btnThanhToan.setEnabled(false);

            }
        });
        holder.btnTraTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, YeuCauTraTroActivity.class);
                i.putExtra("Yeucautratro",hoaDon);
                context.startActivity(i);
            }
        });


    }
    @Override
    public int getItemCount() {
        return hoaDons.size();
    }

    class HoaDonVH extends RecyclerView.ViewHolder {
        ImageView mPhongtro;
        Button btnThanhToan,btnTraTro;
        TextView mDiaChi, mGiaTien,mHoTen,mCCCD,mSDT,mThang,mTienDien,mTienNuoc,mTongTien,mTinhTrangHD,mNgayThanhToan;
        public HoaDonVH(@NonNull View itemView) {
            super(itemView);
            mPhongtro = itemView.findViewById(R.id.img_hd);
            mDiaChi = itemView.findViewById(R.id.tv_diachi);
            mGiaTien = itemView.findViewById(R.id.tv_giatien);
            mHoTen = itemView.findViewById(R.id.tv_HoTenNguoiThue);
            mCCCD = itemView.findViewById(R.id.tv_CCCDNguoiThue);
            mSDT = itemView.findViewById(R.id.tv_SDTNguoiThue);
            mThang  = itemView.findViewById(R.id.tv_thangthu);
            mTienDien  = itemView.findViewById(R.id.tv_TienDien);
            mTienNuoc  = itemView.findViewById(R.id.tv_TienNuoc);
            mTongTien  = itemView.findViewById(R.id.tvTongTien);
            mTinhTrangHD = itemView.findViewById(R.id.tv_TinhTrangHD);
            mNgayThanhToan = itemView.findViewById(R.id.tv_NgayThanhToan);

            btnTraTro = itemView.findViewById(R.id.btnTraTro);
            btnThanhToan = itemView.findViewById(R.id.btnThanhToan);
        }
    }
}
