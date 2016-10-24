package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.liquorice.app.android.R;
import com.liquorice.app.android.helpers.ChartHelper;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportApprovalChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by eyablonskaya on 7/13/2016.
 */
public class ReportApprovalChartView extends BaseRecyclerItem<ReportApprovalChart> {

    public static final String COLOR_PENDING = "#cb0000";
    public static final String COLOR_DECLINED = "#eea800";
    public static final String COLOR_APPROVED = "#008aa7";

    @Bind(R.id.report_chart)
    PieChart reportChart;

    public ReportApprovalChartView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull ReportApprovalChart baseObject) {
        if (baseObject == null) return;
        setData(baseObject);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_report_chart;
    }

    @Override
    protected void onLinkInterface() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getClickedID() {
        return R.id.content;
    }

    @Override
    protected void onCreateView() {
        ChartHelper.setupPieChart(reportChart);
    }

    private void setData(ReportApprovalChart chartData) {
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        List<PieEntry> yValues = new ArrayList<PieEntry>();
        PieEntry approvedEntry = new PieEntry(chartData.getApprovedCount(), 0);
        approvedEntry.setLabel(String.format(getResources().getString(R.string.report_chart_status_approved), (int) chartData.getApprovedCount()));
        PieEntry pendingEntry = new PieEntry(chartData.getPendingCount(), 1);
        pendingEntry.setLabel(String.format(getResources().getString(R.string.report_chart_status_pending), (int) chartData.getPendingCount()));
        PieEntry declinedEntry = new PieEntry(chartData.getDeclinedCount(), 2);
        declinedEntry.setLabel(String.format(getResources().getString(R.string.report_chart_status_declined), (int) chartData.getDeclinedCount()));
        yValues.add(approvedEntry);
        yValues.add(pendingEntry);
        yValues.add(declinedEntry);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor(COLOR_APPROVED));
        colors.add(Color.parseColor(COLOR_PENDING));
        colors.add(Color.parseColor(COLOR_DECLINED));
        dataSet.setColors(colors);
        PieData pData = ChartHelper.setupPieData(dataSet);
        reportChart.setData(pData);

        // undo all highlights
        reportChart.highlightValues(null);

        reportChart.invalidate();
    }
}