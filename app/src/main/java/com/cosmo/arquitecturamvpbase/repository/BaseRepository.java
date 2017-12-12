package com.cosmo.arquitecturamvpbase.repository;


import com.cosmo.arquitecturamvpbase.presenter.BasePresenter;

/**
 * Created by jose.cardenas on 19/09/2017.
 */
public class BaseRepository<T extends BasePresenter> {

    private T presenter;

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

}
