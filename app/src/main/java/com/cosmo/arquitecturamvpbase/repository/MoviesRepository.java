package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.helper.TypeDecryption;
import com.cosmo.arquitecturamvpbase.model.MovieInfo;
import com.cosmo.arquitecturamvpbase.model.Records;
import com.cosmo.arquitecturamvpbase.presenter.MoviesPresenter;
import com.cosmo.arquitecturamvpbase.services.IServices;
import retrofit.RetrofitError;
import java.util.ArrayList;

/**
 * Created by ana.marrugo on 11/12/2017.
 */

public class MoviesRepository extends BaseRepository<MoviesPresenter> implements IMoviesRepository {

    private IServices services;

    public MoviesRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.XML);
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public ArrayList<MovieInfo> getMovies() throws RetrofitError {
        Records r = services.getMovies();
        return r.getMovies();
    }

}
