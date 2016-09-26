package com.liquoriceutils.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.liquoriceutils.utils.R;

public class LiquoriceProgressBar extends ProgressBar {
    public LiquoriceProgressBar(Context context) {
        super(context);
    }

    public LiquoriceProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiquoriceProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        } else {
            setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rotating_pb_bg));
        }
    }
}
