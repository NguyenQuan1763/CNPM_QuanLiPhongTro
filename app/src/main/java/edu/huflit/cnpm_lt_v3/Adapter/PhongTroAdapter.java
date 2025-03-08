package edu.huflit.cnpm_lt_v3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import edu.huflit.cnpm_lt_v3.Model.PhongTro;
import edu.huflit.cnpm_lt_v3.R;
import edu.huflit.cnpm_lt_v3.ThongTinPhongTroActivity;


public class PhongTroAdapter extends RecyclerView.Adapter<PhongTroAdapter.PhongTroVH> implements Filterable {

    Context context;
    ArrayList<PhongTro> phongTros;
    ArrayList<PhongTro> phongTrosold;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    public PhongTroAdapter(ArrayList<PhongTro> phongTros,Context context) {
        this.phongTros = phongTros;
        this.phongTrosold = phongTros;
        this.context = context;
    }


    @NonNull
    @Override
    public PhongTroVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phongtro,parent,false);
        PhongTroVH phongTroVH = new PhongTroVH(view);
        return phongTroVH;
    }

    @Override
    public void onBindViewHolder(@NonNull PhongTroVH holder, int position) {
        PhongTro phongTro = phongTros.get(position);
        holder.mMota.setText(phongTro.getMoTa());
        holder.mDiaChi.setText(phongTro.getDiaChi());
        holder.mGiaTien.setText(phongTro.getGiaTien() + " VND");
        holder.mGioiThieu.setText(phongTro.getGioiThieu());
//        StorageReference storageReference = storage.getReference();
//        StorageReference imgRef3 = storageReference.child(String.valueOf(phongTro.getHinhPhong()));
//        imgRef3.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                holder.mPhongtro.setImageBitmap(bitmap);
//            }
//        });
        Glide.with(holder.itemView.getContext()).load(phongTro.getHinhPhong()).into(holder.mPhongtro);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThongTinPhongTroActivity.class);
                intent.putExtra("ThongTinPhong",phongTro);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return phongTros.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    phongTros = phongTrosold;
                }
                else{
                    ArrayList<PhongTro> list = new ArrayList<>();
                    for(PhongTro phongTro : phongTrosold){
                        //laytheo
                        if(phongTro.getGioiThieu().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(phongTro);
                        }
                    }
                    phongTros = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = phongTros;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                phongTros = (ArrayList<PhongTro>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class PhongTroVH extends RecyclerView.ViewHolder {
        TextView mMota, mDiaChi, mGiaTien,mGioiThieu;
        ImageView mPhongtro;

        public PhongTroVH(@NonNull View itemView) {
            super(itemView);

            mGioiThieu = itemView.findViewById(R.id.tvgioithieu);
            mMota = itemView.findViewById(R.id.tv_mota);
            mDiaChi = itemView.findViewById(R.id.tv_diachi);
            mGiaTien = itemView.findViewById(R.id.tv_giatien);
            mPhongtro = itemView.findViewById(R.id.imgphongtro);
        }
    }

}
