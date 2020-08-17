package com.code.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class CustomImageView extends ImageView {


    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        Double doubleHeight = 1.43 * widthSize;

        int height = doubleHeight.intValue();
        Log.d("CustomImageViewHeight", " height is " + height);
        setMeasuredDimension(widthSize, height);
    }

}

