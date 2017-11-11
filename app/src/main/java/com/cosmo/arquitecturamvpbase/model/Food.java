package com.cosmo.arquitecturamvpbase.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
/**
 * Created by ana.marrugo on 11/11/2017.
 */

@Root(name="food")
public class Food
{

    @Element(name="price")
    private String price;

    @Element(name="description")
    private String description;

    @Element(name="name")
    private String name;

    @Element(name="calories")
    private String calories;


    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCalories ()
    {
        return calories;
    }

    public void setCalories (String calories)
    {
        this.calories = calories;
    }


    public String toString(int i)
    {   String srt="Comida "+i+":: [price = "+price+", description = "+description+", name = "+name+", calories = "+calories+"]";
        return srt;
    }
}