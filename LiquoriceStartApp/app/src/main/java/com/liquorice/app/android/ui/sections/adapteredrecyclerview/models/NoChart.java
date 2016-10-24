package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.NoChartView;

/**
 * Created by eyablonskaya on 7/28/2016.
 */
public class NoChart extends BaseObject {
    private String text;

    public NoChart(){
        this("");
    }

    public NoChart(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new NoChartView(context);
    }
}
