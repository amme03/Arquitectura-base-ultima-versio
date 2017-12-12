package com.cosmo.arquitecturamvpbase.presenter;

import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.MovieInfo;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.MoviesRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IMoviesView;
import retrofit.RetrofitError;
import java.util.ArrayList;

/**
 * Created by ana.marrugo on 11/12/2017.
 */

public class MoviesPresenter  extends BasePresenter<IMoviesView,MoviesRepository> {

    ArrayList<MovieInfo> moviesList = new ArrayList<MovieInfo>();

    public MoviesPresenter() {
    }

    public void getMovies() {
        if (getValidateInternet().isConnected()) {
            createThreadGetMovies();
        } else {
            getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadGetMovies() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getMoviesRepository();
            }
        });
        thread.start();
    }

    private void getMoviesRepository() {
        try {
            if( moviesList.size()==0 ) {
                moviesList = getRepository().getMovies();
            }
            try { Thread.sleep(1000); } catch( Exception e ){}
            getView().showMoviesList(moviesList);
        } catch (RetrofitError retrofitError) {
            Log.w("myApp","error:"+retrofitError.getMessage()+"");
            Log.w("myApp","error2:"+retrofitError.getResponse()+"");
            Log.w("myApp","error3:"+retrofitError.getCause()+"");
           // retrofitError.getCause().printStackTrace();
            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());
        }
    }

    public ArrayList<MovieInfo> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<MovieInfo> moviesList) {
        this.moviesList = moviesList;
    }

}
