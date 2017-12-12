package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.MovieInfo;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 11/12/2017.
 */


public interface IMoviesView extends IBaseView {

    void showMoviesList( ArrayList<MovieInfo> moviesList );

    void showAlertDialogInternet(int title, int message);

    void showAlertError(int title, String message);

}