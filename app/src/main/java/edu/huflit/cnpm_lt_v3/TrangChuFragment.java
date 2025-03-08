package edu.huflit.cnpm_lt_v3;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_lt_v3.Adapter.PhongTroAdapter;
import edu.huflit.cnpm_lt_v3.Model.PhongTro;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }
    ArrayList<PhongTro> phongTros;
    PhongTroAdapter phongTroAdapter;
    FirebaseFirestore db;
    SearchView searchView;
    ImageButton btnSearch;

    RecyclerView rvPhongTro;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSearch = view.findViewById(R.id.btnsearch);
        rvPhongTro = view.findViewById(R.id.rvPhongTro);

        phongTros = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        phongTroAdapter = new PhongTroAdapter(phongTros,getContext());
        rvPhongTro.setAdapter(phongTroAdapter);

        rvPhongTro.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        db.collection("PhongTro").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for ( QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            String MaPhong = documentSnapshot.getId();
                            String GioiThieu = documentSnapshot.get("GioiThieu").toString();
                            String DiaChi = documentSnapshot.get("DiaChi").toString();
                            String GiaTien = documentSnapshot.get("GiaTien").toString();
                            String MoTa = documentSnapshot.get("MoTa").toString();
                            String HinhPhong = documentSnapshot.get("HinhPhong").toString();
                            String LienHe = documentSnapshot.get("LienHe").toString();
                            PhongTro phongTro = new PhongTro(MaPhong,GioiThieu,GiaTien,DiaChi,MoTa,HinhPhong,LienHe);
                            phongTros.add(phongTro);
                        }
                        phongTroAdapter.notifyDataSetChanged();
                    }
                });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),SearchActivity.class);
                startActivity(i);
            }
        });

    }

}