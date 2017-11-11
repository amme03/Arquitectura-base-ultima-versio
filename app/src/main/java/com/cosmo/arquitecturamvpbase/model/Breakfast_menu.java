package com.cosmo.arquitecturamvpbase.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by ana.marrugo on 11/11/2017.
 */
@Root(name="breakfast_menu")
public class Breakfast_menu
{
    @ElementList(entry="food",inline = true)
    private ArrayList<Food> food;

    public  ArrayList<Food> getFood ()
    {
        return food;
    }

    public void setFood ( ArrayList<Food> food)
    {
        this.food = food;
    }

    @Override
    public String toString()
    {  String string="";
       int i=1;
        for (Food fo:food
             ) {
            string=string +"\n "+fo.toString(i);
            i++;
        }
        return "[food = "+string+"]";
    }
}