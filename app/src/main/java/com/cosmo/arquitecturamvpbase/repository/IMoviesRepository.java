package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.MovieInfo;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 11/12/2017.
 */

public interface IMoviesRepository {

    public ArrayList<MovieInfo> getMovies();

}
