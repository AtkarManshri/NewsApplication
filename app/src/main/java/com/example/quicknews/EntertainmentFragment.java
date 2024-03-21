package com.example.quicknews;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

public class EntertainmentFragment extends Fragment {
    String apikey="d659c41a2c3840379b82778eec8fd62f";
    //since we are getting  response in array list we will use array list whose type is <model>
    ArrayList<Model> modelArrayList;
    AdapterClass adapterClass;
    String country="in";
    //taking it as private because so many recyle in single activitu and  multiple fragments
    private RecyclerView recyclerViewofEntertainment;
    private String category="entertainment";
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflater to add scrren on diaplay
        View v=inflater.inflate(R.layout.entertainmentfragment,null);
        recyclerViewofEntertainment=v.findViewById(R.id.recycleViewEntertainment);
        modelArrayList=new ArrayList<>();
        recyclerViewofEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterClass =new AdapterClass(getContext(),modelArrayList);
        recyclerViewofEntertainment.setAdapter(adapterClass);
        progressBar=v.findViewById(R.id.progressbar);
        progressBar.setVisibility(VISIBLE);
        findNews();
        return v;


    }

    private void findNews() {
        ApiUtility.getApiInterface().getCategory(country,category,30,apikey).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    adapterClass.notifyDataSetChanged();
                    progressBar.setVisibility(GONE);
                }

            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });

    }
}
