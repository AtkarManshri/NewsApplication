package com.example.quicknews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem home,science,health,technology,entertainment,sports;
    PageAdapter pageAdapter;


    //getSupportFragmentManager().beginTransaction().replace(R.id.fr_container,new HomeFragment()).commit();
    Toolbar toolbar = findViewById(R.id.toolbar);

    String apikey="d659c41a2c3840379b82778eec8fd62f";
    String[] permissions={"android.permission.INTERNET"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setSupportActionBar(toolbar); //to set action bar on tool bar

        home=findViewById(R.id.Home);
        science=findViewById(R.id.Science);
        health=findViewById(R.id.Health);
        technology=findViewById(R.id.Technology);
        entertainment=findViewById(R.id.Entertainment);
        sports=findViewById(R.id.Sports);

        ViewPager viewPager=findViewById(R.id.fragment_container);
        tabLayout=findViewById(R.id.tablayout);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pageAdapter);
        //also to move to tab on clickiing tab (swiping is already)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab)
                                               {
                                                   viewPager.setCurrentItem(tab.getPosition());
                                                   //if user click on any of tab to notify adapterClass
                                                   if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5){
                                                       pageAdapter.notifyDataSetChanged();
                                                   }
                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab)
                                               {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab)
                                               {

                                               }
                                           });
        //to change page on swipe
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}

