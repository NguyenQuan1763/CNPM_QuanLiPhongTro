package edu.huflit.cnpm_lt_v3;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_lt_v3.Adapter.ThongBaoAdapter;
import edu.huflit.cnpm_lt_v3.Model.YeuCau;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongBaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongBaoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThongBaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongBaoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongBaoFragment newInstance(String param1, String param2) {
        ThongBaoFragment fragment = new ThongBaoFragment();
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
    ArrayList<YeuCau> yeuCaus;
    ThongBaoAdapter thongBaoAdapter;
    FirebaseFirestore db;

    RecyclerView rvThongBao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_bao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvThongBao = view.findViewById(R.id.rvThongBao);
        yeuCaus = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        thongBaoAdapter = new ThongBaoAdapter(getContext(),yeuCaus);
        rvThongBao.setAdapter(thongBaoAdapter);
        rvThongBao.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
//        rvThongBao.addItemDecoration(itemDecoration);

    }

    @Override
    public void onStart() {
        super.onStart();
        yeuCaus.clear();
        db.collection("YeuCau").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for ( QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String MaYeuCau = documentSnapshot.getId();
                    String MaHoaDon = (String) documentSnapshot.get("MaHoaDon");
                    String MaPhong = (String) documentSnapshot.get("MaPhong");
                    String MaPhongThue = (String) documentSnapshot.get("MaPhongThue");
                    String NhanLaiCoc = (String) documentSnapshot.get("NhanLaiCoc");
                    String NgayThue = (String) documentSnapshot.get("NgayThue");
                    String PhanHoi = (String) documentSnapshot.get("PhanHoi");
                    String HoTenNguoiThue = documentSnapshot.get("HoTenNguoiThue").toString();
                    String LyDo = documentSnapshot.get("LyDo").toString();
                    String NgayThangGuiYeuCau = documentSnapshot.get("NgayGuiYeuCau").toString();
                    String NgayThangTra = documentSnapshot.get("NgayThangTra").toString();

                    YeuCau yeuCau = new YeuCau(MaYeuCau,MaHoaDon, MaPhong,MaPhongThue,NhanLaiCoc,
                            HoTenNguoiThue,LyDo,NgayThangGuiYeuCau, NgayThangTra,NgayThue,PhanHoi);

                    yeuCaus.add(yeuCau);
                }
                thongBaoAdapter.notifyDataSetChanged();
            }
        });
    }
}