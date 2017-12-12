package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cosmo.arquitecturamvpbase.R;

/**
 * Created by ana.marrugo on 18/11/2017.
 */

public class CustomView extends AppCompatTextView {


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
            setAllCaps(true);
            setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            setHeight(100);
            setBottom(10);

    }
}
