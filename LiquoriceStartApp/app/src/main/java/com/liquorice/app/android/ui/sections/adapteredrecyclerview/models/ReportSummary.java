package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.ReportSummaryView;

/**
 * Created by eyablonskaya on 7/13/2016.
 */
public class ReportSummary extends BaseObject {
    private int nameResId;
    private long totalNumber;

    public ReportSummary(int nameResId, long totalNumber) {
        this.nameResId = nameResId;
        this.totalNumber = totalNumber;
    }

    public int getNameResId() {
        return nameResId;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ReportSummaryView(context);
    }
}
