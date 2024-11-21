package com.marcosobiang.misaficiones;

import android.content.Intent;
import android.content.SharedPreferences;
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

import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.Toast;
import android.widget.Toolbar;

import com.marcosobiang.misaficiones.databinding.ActivityAficionesBinding;
import com.marcosobiang.misaficiones.fr.aficiones.Comer;
import com.marcosobiang.misaficiones.ui.fragments.Paginador;

import java.util.Map;

public class Aficiones extends AppCompatActivity {

    private ActivityAficionesBinding binding;
   private  FragmentManager fm;
   private ViewPager viewPager;
   Map<String, ?> aficiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAficionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         fm = getSupportFragmentManager();
        Paginador paginador = new Paginador(this, fm);
         viewPager = binding.viewPager;
        viewPager.setAdapter(paginador);
        setSupportActionBar(binding.toolbar);;



    binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, PerfilUsuario.class);
            Bundle bundle = new Bundle();
aficiones.forEach((k,v)->{
    bundle.putString(k,v.toString());
});
            intent.putExtras(bundle);


    intent.putExtra("aficiones",bundle);
            startActivity(intent);

        });

    binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }



        @Override
        public void onPageSelected(int position) {
            String tabTitle = getCurrentTabTitle();
            aficiones = getFavoritos();
            if (aficiones.containsKey(tabTitle)) {
                modificarTextoFavorito(true);
            } else {
                modificarTextoFavorito(false);


            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

    }
    public void modificarTextoFavorito(boolean esFavorito){
        String tabTitle = getCurrentTabTitle();
        MenuItem menuItem = binding.toolbar.getMenu().findItem(R.id.favorite);
        CharSequence title = menuItem.getTitle();
        if(esFavorito){
            menuItem.setTitle("Favorito❤️");
        }else{
            menuItem.setTitle("Añadir a favoritos");
        }

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
        setViewPagerHeigth();
      aficiones=  getFavoritos();
        String tabTitle = getCurrentTabTitle();
        aficiones = getFavoritos();
        if (aficiones.containsKey(tabTitle)) {
            modificarTextoFavorito(true);
        } else {
            modificarTextoFavorito(false);


        }
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

        if(id==R.id.favorite){

           String aficion= getCurrentTabTitle();

           if(aficiones.containsKey(aficion)){
               quitarFavoritos(aficion);
               aficiones=  getFavoritos();
               modificarTextoFavorito(false);
               Toast toast= Toast.makeText(this,"Eliminado de favoritos", Toast.LENGTH_SHORT);
               toast.show();
           }
           else{
               guardarFavoritos(aficion);
               aficiones=  getFavoritos();
               modificarTextoFavorito(true);
               Toast toast= Toast.makeText(this,"Añadido a favoritos", Toast.LENGTH_SHORT);
               toast.show();

           }
        }


        return super.onOptionsItemSelected(item);
    }


    public void guardarFavoritos(String aficion){
        SharedPreferences sharedPreferences = getSharedPreferences("MisAficiones", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(aficion, aficion);
        editor.apply();

    }

    public Map<String, ?> getFavoritos(){
        Map<String, ?> aficiones=null;
        try{
            SharedPreferences sharedPreferences = getSharedPreferences("MisAficiones", MODE_PRIVATE);
            aficiones=    sharedPreferences.getAll();

        }catch(Exception e){
            Toast.makeText(this, "No hay favoritos", Toast.LENGTH_SHORT).show();
        }

        return aficiones;


    }

    public void quitarFavoritos(String aficion){
        SharedPreferences sharedPreferences = getSharedPreferences("MisAficiones", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(aficion);
        editor.apply();
    }
}