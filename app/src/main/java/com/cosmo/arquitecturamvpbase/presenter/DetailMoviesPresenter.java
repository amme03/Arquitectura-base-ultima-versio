package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.repository.BaseRepository;
import com.cosmo.arquitecturamvpbase.repository.IMoviesRepository;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IDetailMovieView;

/**
 * Created by leidyzulu on 23/09/17.
 */

public class DetailMoviesPresenter extends BasePresenter<IDetailMovieView, BaseRepository> {

    private IMoviesRepository moviesRepository;

    public DetailMoviesPresenter(IMoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }




}
