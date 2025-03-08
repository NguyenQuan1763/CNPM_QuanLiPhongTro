package edu.huflit.cnpm_lt_v3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.huflit.cnpm_lt_v3.Adapter.PhongTroAdapter;
import edu.huflit.cnpm_lt_v3.Model.PhongTro;

public class SearchActivity extends AppCompatActivity {

    ArrayList<PhongTro> phongTros;
    PhongTroAdapter phongTroAdapter;
    FirebaseFirestore db;
    androidx.appcompat.widget.SearchView searchView;

    RecyclerView rvPhongTro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvPhongTro = findViewById(R.id.rvsearch);

        phongTros = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        phongTroAdapter = new PhongTroAdapter(phongTros,SearchActivity.this);
        rvPhongTro.setAdapter(phongTroAdapter);

        rvPhongTro.setLayoutManager(new LinearLayoutManager(SearchActivity.this,RecyclerView.VERTICAL,false));

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

//        setup vai thuoc tinh
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                phongTroAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                phongTroAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }
}