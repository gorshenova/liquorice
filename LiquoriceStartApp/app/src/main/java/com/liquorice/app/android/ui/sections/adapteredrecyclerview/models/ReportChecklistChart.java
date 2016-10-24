package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.ReportChecklistChartView;

/**
 * Created by eyablonskaya on 7/14/2016.
 */
public class ReportChecklistChart extends BaseObject {

    private long totalCount;
    private long completeCount;
    private long incompleteCount;

    public ReportChecklistChart(long totalCount, long completeCount, long incompleteCount) {
        this.totalCount = totalCount;
        this.completeCount = completeCount;
        this.incompleteCount = incompleteCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(long completeCount) {
        this.completeCount = completeCount;
    }

    public long getIncompleteCount() {
        return incompleteCount;
    }

    public void setIncompleteCount(long incompleteCount) {
        this.incompleteCount = incompleteCount;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ReportChecklistChartView(context);
    }

    @Override
    public String toString() {
        return "ReportChecklistChart{" +
                "totalCount=" + totalCount +
                ", completeCount=" + completeCount +
                ", incompleteCount=" + incompleteCount +
                '}';
    }
}
