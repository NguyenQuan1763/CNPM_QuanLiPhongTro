package edu.huflit.cnpm_lt_v3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.huflit.cnpm_lt_v3.Adapter.HoaDonAdapter;
import edu.huflit.cnpm_lt_v3.Adapter.PhongTroAdapter;
import edu.huflit.cnpm_lt_v3.Model.HoaDon;
import edu.huflit.cnpm_lt_v3.Model.PhongTro;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThanhToanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThanhToanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThanhToanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThanhToanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThanhToanFragment newInstance(String param1, String param2) {
        ThanhToanFragment fragment = new ThanhToanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thanh_toan, container, false);
    }
    ArrayList<HoaDon> hoaDons;
    HoaDonAdapter hoaDonAdapter;
    FirebaseFirestore db;

    RecyclerView rvHoaDon;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvHoaDon = view.findViewById(R.id.rvHoaDon);
        hoaDons = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        hoaDonAdapter = new HoaDonAdapter(db,hoaDons,getContext());
        rvHoaDon.setAdapter(hoaDonAdapter);
        rvHoaDon.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

    }

    @Override
    public void onStart() {
        super.onStart();
        hoaDons.clear();
        db.collection("HoaDon").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for ( QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            String MaHoaDon = documentSnapshot.getId();
                            String MaPhong = (String) documentSnapshot.get("MaPhong");
                            String MaPhongThue = documentSnapshot.get("MaPhongThue").toString();
                            String GioiThieu = documentSnapshot.get("GioiThieu").toString();
                            String DiaChi = documentSnapshot.get("DiaChi").toString();
                            String GiaTien = documentSnapshot.get("GiaTien").toString();
                            String MoTa = documentSnapshot.get("MoTa").toString();
                            String HinhPhong = documentSnapshot.get("HinhPhong").toString();
                            String HoTenNguoiThue = documentSnapshot.get("HoTenNguoiThue").toString();
                            String CCCD = documentSnapshot.get("CCCD").toString();
                            String SDT = documentSnapshot.get("SDT").toString();
                            String NgayThue = documentSnapshot.get("NgayThue").toString();
                            String Thang = documentSnapshot.get("Thang").toString();
                            String TienDien = documentSnapshot.get("TienDien").toString();
                            String TienNuoc = documentSnapshot.get("TienNuoc").toString();
                            String TinhTrangHD = documentSnapshot.get("TinhTrangHD").toString();
                            String NgayThanhToan = documentSnapshot.get("NgayThanhToan").toString();

                            HoaDon hoaDon = new HoaDon(MaHoaDon,MaPhong,MaPhongThue,GioiThieu,GiaTien,DiaChi,MoTa,HinhPhong,HoTenNguoiThue,CCCD,SDT,NgayThue,Thang,TienDien,TienNuoc,TinhTrangHD,NgayThanhToan);
                            hoaDons.add(hoaDon);
                        }
                        hoaDonAdapter.notifyDataSetChanged();
                    }
                });

    }
}