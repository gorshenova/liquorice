package com.liquorice.app.android.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.liquorice.app.android.R;
import com.liquorice.app.android.helpers.FontsUtils;

/**
 * FontButton
 */
public class FontButton extends Button {
    public FontButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public FontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontButton);
        try {
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int defaultValue = FontsUtils.FONT_LANEHUM;
                int textFont = a.getInt(i, defaultValue);
                if (!isInEditMode()) {
                    FontsUtils.setFonts(this, textFont);
                }
                break;
            }
        } finally {
            a.recycle();
        }
    }

}
