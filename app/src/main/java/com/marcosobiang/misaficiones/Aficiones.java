package com.marcosobiang.misaficiones;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.Toast;

import com.marcosobiang.misaficiones.databinding.ActivityAficionesBinding;
import com.marcosobiang.misaficiones.fr.aficiones.Comer;
import com.marcosobiang.misaficiones.ui.fragments.Paginador;

public class Aficiones extends AppCompatActivity {

    private ActivityAficionesBinding binding;
   private  FragmentManager fm;
   private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAficionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         fm = getSupportFragmentManager();
        Paginador paginador = new Paginador(this, fm);
         viewPager = binding.viewPager;
        viewPager.setAdapter(paginador);


    binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, PerfilUsuario.class);
            startActivity(intent);

        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
        setViewPagerHeigth();
    }



    void setViewPagerHeigth(){
        int screenHeigth = binding.constraintLayoutPrincipal.getHeight();
        int toolbarHeigth=binding.toolbar.getHeight();
        binding.viewPager.getLayoutParams().height=screenHeigth-toolbarHeigth;
    }


    String getCurrentTabTitle(){

int currentIndex = viewPager.getCurrentItem();
switch (currentIndex){
    case 0:
        return "Comer";
    case 1:
        return "Dormir";
        case 2:
        return "Programar";
    default:
        return null;
}



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sobre_mi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sobre_mi) {
            Toast toast= Toast.makeText(this,"Como me husta lo que me gusta", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (id == R.id.miGithubButton) {
         Intent intent= new Intent(Intent.ACTION_VIEW);
         intent.setData(Uri.parse("https://github.com/marcosobiang"));
         startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}