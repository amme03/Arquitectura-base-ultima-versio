package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.helper.TypeDecryption;
import com.cosmo.arquitecturamvpbase.model.Breakfast_menu;
import com.cosmo.arquitecturamvpbase.services.IServices;

/**
 * Created by ana.marrugo on 11/11/2017.
 */

public class MenuRepository implements IMenuRepository {
    private IServices services;

    public MenuRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.XML);
        services = (IServices) servicesFactory.getInstance(IServices.class);

    }

    @Override
    public Breakfast_menu getMenu() {

        Breakfast_menu menu = services.getMenu();
        return menu;
    }
}