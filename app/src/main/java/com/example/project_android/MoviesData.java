package com.example.project_android;

import java.util.ArrayList;

public class MoviesData {
    private static String[] movieNames = {
            "Fast and Furious 1","Fast and Furious 2", "Fast and Furious 3", "Fast and Furious 4"
    };
    private static String[] deskripsi = {
            "This is Fast and Furious 1 ",
            "This is Fast and Furious 2 ",
            "This is Fast and Furious 3 ",
            "This is Fast and Furious 4 "
    };

    private static int[] img = {
            R.drawable.past_one, R.drawable.past_two, R.drawable.past_one, R.drawable.past_two
    };

    static ArrayList<MoviesActivity> getListData(){
        ArrayList<MoviesActivity> list = new ArrayList<>();
        for (int postition = 0; postition < movieNames.length;postition++){
            MoviesActivity datamovies = new MoviesActivity();
            datamovies.setMovie(movieNames[postition]);
            datamovies.setDeskripsi(deskripsi[postition]);
            datamovies.setImgs(img[postition]);
            list.add(datamovies);
        }
        return list;
    }
}
