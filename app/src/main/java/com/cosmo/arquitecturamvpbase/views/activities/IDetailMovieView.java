package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 23/09/17.
 */

public interface IDetailMovieView extends IBaseView {

    void showToast(int message);
    public void showCastingList(final ArrayList<String> castArrayList);

    void showToast(String message);
}
