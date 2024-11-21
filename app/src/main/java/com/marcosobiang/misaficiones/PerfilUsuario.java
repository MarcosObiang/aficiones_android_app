package com.marcosobiang.misaficiones;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.marcosobiang.misaficiones.databinding.ActivityPerfilUsuarioBinding;

import java.util.ArrayList;

public class PerfilUsuario extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPerfilUsuarioBinding binding;
    private Bundle bundle;
    ArrayList<String>aficiones=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPerfilUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_perfil_usuario);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }
@Override

public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
    getAficiones();

}

    public void getAficiones(){
        bundle= getIntent().getBundleExtra("aficiones");
        if(bundle!=null){
            for(String aficion:bundle.keySet()){
                aficiones.add(bundle.getString(aficion));
            }

        }





    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_perfil_usuario);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}