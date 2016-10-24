package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.liquorice.app.android.helpers.ChartHelper;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportSurveyChart;
import com.liquorice.app.android.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 7/14/2016.
 */
public class ReportSurveyChartView extends BaseRecyclerItem<ReportSurveyChart> {
    public static final String COLOR_REPLIED = "#00a708";
    public static final String COLOR_NOT_REPLIED = "#cb0000";

    @Bind(R.id.report_chart)
    PieChart reportChart;

    public ReportSurveyChartView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull ReportSurveyChart baseObject) {
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

    private void setData(ReportSurveyChart chartData) {
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        List<PieEntry> yValues = new ArrayList<PieEntry>();
        PieEntry repliedEntry = new PieEntry(chartData.getRepliedCount(), 0);
        repliedEntry.setLabel(String.format(getResources().getString(R.string.report_chart_status_replied), (int) chartData.getRepliedCount()));
        PieEntry notRepliedEntry = new PieEntry(chartData.getNotRepliedCount(), 1);
        notRepliedEntry.setLabel(String.format(getResources().getString(R.string.report_chart_status_not_replied), (int) chartData.getNotRepliedCount()));
        yValues.add(repliedEntry);
        yValues.add(notRepliedEntry);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor(COLOR_REPLIED));
        colors.add(Color.parseColor(COLOR_NOT_REPLIED));
        dataSet.setColors(colors);
        PieData pData = ChartHelper.setupPieData(dataSet);
        reportChart.setData(pData);

        // undo all highlights
        reportChart.highlightValues(null);

        reportChart.invalidate();
    }
}
