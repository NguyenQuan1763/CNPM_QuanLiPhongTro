package edu.huflit.cnpm_lt_v3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                display(item.getItemId());
                return true;
            }
        });
        display(R.id.mnu_trangchu);
    }
    void display(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.mnu_trangchu:
                fragment = new TrangChuFragment();
                break;
            case R.id.mnu_canhan:
                fragment = new CaNhanFragment();
                break;
            case R.id.mnu_thanhtoan:
                fragment = new ThanhToanFragment();
                break;
            case R.id.mnu_thongbao:
                fragment = new ThongBaoFragment();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fcontent, fragment);
        ft.commit();
}}