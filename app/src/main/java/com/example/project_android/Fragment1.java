package com.example.project_android;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private ArrayList<MoviesActivity> list;
    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.addAll(MoviesData.getListData());

        RecyclerView movies = view.findViewById(R.id.recycleView);
        movies.setHasFixedSize(true);
        ReAdapter recycleviewAdapter = new ReAdapter(list);
        movies.setLayoutManager(new LinearLayoutManager(getActivity()));
        movies.setAdapter(recycleviewAdapter);
    }

}
