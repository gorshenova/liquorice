package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.ReportSurveyChartView;

/**
 * Created by eyablonskaya on 7/14/2016.
 */
public class ReportSurveyChart extends BaseObject {
    private long totalCount;
    private long repliedCount;
    private long notRepliedCount;

    public ReportSurveyChart(long totalCount, long repliedCount, long notRepliedCount) {
        this.repliedCount = repliedCount;
        this.notRepliedCount = notRepliedCount;
        this.totalCount = totalCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getRepliedCount() {
        return repliedCount;
    }

    public void setRepliedCount(long repliedCount) {
        this.repliedCount = repliedCount;
    }

    public long getNotRepliedCount() {
        return notRepliedCount;
    }

    public void setNotRepliedCount(long notRepliedCount) {
        this.notRepliedCount = notRepliedCount;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ReportSurveyChartView(context);
    }

    @Override
    public String toString() {
        return "ReportSurveyChart{" +
                "totalCount=" + totalCount +
                ", repliedCount=" + repliedCount +
                ", notRepliedCount=" + notRepliedCount +
                '}';
    }
}
