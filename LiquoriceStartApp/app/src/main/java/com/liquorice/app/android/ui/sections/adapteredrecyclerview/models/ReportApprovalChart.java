package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.ReportApprovalChartView;

/**
 * ReportApprovalChart
 */
public class ReportApprovalChart extends BaseObject {
    private long pendingCount;
    private long declinedCount;
    private long approvedCount;
    private long totalCount;

    public long getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(long pendingCount) {
        this.pendingCount = pendingCount;
    }

    public long getDeclinedCount() {
        return declinedCount;
    }

    public void setDeclinedCount(long declinedCount) {
        this.declinedCount = declinedCount;
    }

    public long getApprovedCount() {
        return approvedCount;
    }

    public void setApprovedCount(long approvedCount) {
        this.approvedCount = approvedCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ReportApprovalChart{" +
                "pendingCount=" + pendingCount +
                ", declinedCount=" + declinedCount +
                ", approvedCount=" + approvedCount +
                ", totalCount=" + totalCount +
                '}';
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ReportApprovalChartView(context);
    }
}
