package com.example.quicknews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//model class to handle fragment

public class HomeFragment extends Fragment {
    String apikey="d659c41a2c3840379b82778eec8fd62f";
    //since we are getting  response in array list we will use array list whose type is <model>
    ArrayList<Model> modelArrayList;
    AdapterClass adapterClass;
    String country="in";
    //taking it as private because so many recyle in single activitu and  multiple fragments
    RecyclerView recyclerViewofHome;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.homefragment,null);
       
        recyclerViewofHome=v.findViewById(R.id.recycleViewHome);
        modelArrayList=new ArrayList<>();
        recyclerViewofHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterClass =new AdapterClass(getContext(),modelArrayList);
        recyclerViewofHome.setAdapter(adapterClass);
        findNews();
        return v;
        

    }

    private void findNews() {
        ApiUtility.getApiInterface().getNews(country,100,apikey).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                   modelArrayList.addAll(response.body().getArticles());
                   adapterClass.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"Api not fetch",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Toast.makeText(getContext(),"Api not fetch",Toast.LENGTH_LONG).show();
            }
        });
    }
}
