package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.Toolbar;
import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.MovieInfo;
import com.cosmo.arquitecturamvpbase.presenter.DetailMoviesPresenter;
import com.cosmo.arquitecturamvpbase.repository.MoviesRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.CastDetalleAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailActivity extends BaseActivity<DetailMoviesPresenter,MoviesRepository> implements IDetailMovieView{
    private ListView directorList;
    private CastDetalleAdapter castDetalleAdapter;
    private MovieInfo movieInfo;
    private TextView movie_title_value;
    private TextView movie_detail_description_value;
    private TextView movie_detail_raiting_value;
    private Typeface style_alexandra;
    private Typeface style_maquina;
    private ImageView movie_detail_poster;
    private Toolbar movie_toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setPresenter(new DetailMoviesPresenter(new MoviesRepository()));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        directorList = (ListView) findViewById(R.id.director_listView);
        movieInfo = ( MovieInfo) getIntent().getSerializableExtra("movie");
        loadViews();
        setDataItem();


    }


    private void setDataItem() {
        String fuente1="fuente/Alexandra.ttf";
        String fuente2="fuente/OldNewspaperTypes.ttf";
        style_alexandra=Typeface.createFromAsset(   getAssets(),fuente1);
        style_maquina=Typeface.createFromAsset(   getAssets(),fuente2);
        movie_title_value.setText(movieInfo.getTitle());
        movie_title_value.setTypeface(style_alexandra);
        movie_detail_description_value.setText(movieInfo.getDescription());
        movie_detail_description_value.setTypeface(style_maquina);
        movie_detail_raiting_value.setText(movieInfo.getRating());
        Picasso.with(MovieDetailActivity.this).load(movieInfo.getPoster()).into(movie_detail_poster);
    }

    private void loadViews() {

        movie_title_value = (TextView) findViewById(R.id.movie_title_value);
        movie_detail_description_value = (TextView) findViewById(R.id.movie_detail_description_value);
        movie_detail_raiting_value = (TextView) findViewById(R.id.movie_detail_raiting_value);
        movie_detail_poster=(ImageView)findViewById(R.id.movie_detail_poster);
        movie_toolbar= (Toolbar)  findViewById(R.id.movie_toolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void showToast(final int message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MovieDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MovieDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    @Override
    public void showCastingList(final ArrayList<String> castArrayList) {
        System.out.print("phoneArrayList "+castArrayList.size());


        if (castArrayList!=null){
            Toast.makeText(this, "no null", Toast.LENGTH_SHORT).show(); }
        else{
            Toast.makeText(this, "nullooo", Toast.LENGTH_SHORT).show();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(castArrayList != null){
                    callAdapter(castArrayList);
                }else{
                    Toast.makeText(null, "run   -----  nullooo", Toast.LENGTH_SHORT).show();
                    callAdapter(movieInfo.getCast());
                }
            }
        });

    }

    private void callAdapter(final ArrayList<String> castArrayList) {
        castDetalleAdapter =  new CastDetalleAdapter(this, R.id.director_listView, castArrayList);

        if (castArrayList!=null && castDetalleAdapter != null){
            directorList.setAdapter(castDetalleAdapter);
        }else{
            Toast.makeText(this, " El reparto esta vacio", Toast.LENGTH_SHORT).show();
        }

    }
    public void launchRoute(View view){
       // Intent intent = new Intent(MovieDetailActivity.this, MovieMapActivity.class);
       // startActivity(intent);
    }
}
