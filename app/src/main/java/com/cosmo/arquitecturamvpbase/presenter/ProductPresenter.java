package com.cosmo.arquitecturamvpbase.presenter;

import android.annotation.SuppressLint;
import android.graphics.Movie;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Breakfast_menu;
import com.cosmo.arquitecturamvpbase.model.MovieInfo;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.BaseRepository;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.MenuRepository;
import com.cosmo.arquitecturamvpbase.repository.MoviesRepository;
import com.cosmo.arquitecturamvpbase.repository.NoteRepository;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductPresenter extends BasePresenter<IProductView, BaseRepository> {

    private ProductRepository productRepository;
    private NoteRepository noteRepository;
    private MoviesRepository moviesRepository;
    private MenuRepository menuRepository;


    public ProductPresenter() {
        productRepository = new ProductRepository();
        noteRepository=new NoteRepository();
        moviesRepository=new MoviesRepository();
        menuRepository=new MenuRepository();

    }

    public void getListProduct() {
        if (getValidateInternet().isConnected()) {
            createThreadProduct();
        } else {
            getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadProduct() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProductList();
            }
        });
        thread.start();
    }

    @SuppressLint("LongLogTag")
    private void getProductList() {

        try {
          ArrayList<Product> productArrayList = productRepository.getProductList();
          //  Note note=noteRepository.getNote();
          // Log.i("NOTE=================="+note.getFrom(),"NOTESSSSSS"+note.getTo());
          //  Breakfast_menu menu= menuRepository.getMenu();
           // Log.i("Menu==================",menu.toString());
            ArrayList<MovieInfo> movies = moviesRepository.getMovies();
            Log.i("Movies==================","Pelicula"+movies.toString());
          getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError) {

            Log.e("ERR", retrofitError.getMessage());

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }


}
