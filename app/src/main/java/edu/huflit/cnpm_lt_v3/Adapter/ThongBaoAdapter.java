package edu.huflit.cnpm_lt_v3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.huflit.cnpm_lt_v3.ChiTietThongBaoActivity;
import edu.huflit.cnpm_lt_v3.Model.YeuCau;
import edu.huflit.cnpm_lt_v3.R;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ThongBaoVH> {

    Context context;
    ArrayList<YeuCau> yeuCaus;

    public ThongBaoAdapter(Context context, ArrayList<YeuCau> yeuCaus) {
        this.context = context;
        this.yeuCaus = yeuCaus;
    }

    @NonNull
    @Override
    public ThongBaoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongbao,parent,false);
        ThongBaoVH thongBaoVH = new ThongBaoVH(view);
        return thongBaoVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoVH holder, int position) {
        YeuCau yeuCau = yeuCaus.get(position);
        holder.tvHoTen.setText(yeuCau.getHoTenNguoiThue());
        holder.tvNgayThangGui.setText(yeuCau.getNgayThangGuiYeuCau());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietThongBaoActivity.class);
                i.putExtra("ThongTinThongBao",yeuCau);
                context.startActivity(i);
            }
        });
        holder.tvphanhoi.setText(yeuCau.getPhanHoi());

        String TinhTrang = (String) holder.tvphanhoi.getText();
        if(TinhTrang.equals("Đã duyệt")){
            holder.tvphanhoi.setText(yeuCau.getPhanHoi());
            holder.tvphanhoi.setTextColor(ContextCompat.getColor(context,R.color.green));
        }
        else {
            holder.tvphanhoi.setTextColor(ContextCompat.getColor(context,R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return yeuCaus.size();
    }

    class ThongBaoVH extends RecyclerView.ViewHolder {
        TextView tvHoTen,tvNgayThangGui,tvphanhoi;
        public ThongBaoVH(@NonNull View itemView) {
            super(itemView);
            tvHoTen = itemView.findViewById(R.id.tv_HoTenNguoiThueYC);
            tvNgayThangGui = itemView.findViewById(R.id.tvNgayThangGuiYeuCau);
            tvphanhoi = itemView.findViewById(R.id.tvphanhoi);
        }
    }
}
