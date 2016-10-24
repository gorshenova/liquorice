package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportChecklistChart;
import com.liquorice.app.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 7/14/2016.
 */
public class ReportChecklistChartView extends BaseRecyclerItem<ReportChecklistChart> {

    @Bind(R.id.report_chart_progressBar)
    ProgressBar progressBar;

    @Bind(R.id.report_chart_status_complete)
    TextView completeStatusTextView;


    @Bind(R.id.report_chart_status_incomplete)
    TextView incompleteStatusTextView;

    public ReportChecklistChartView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull ReportChecklistChart baseObject) {
        if (baseObject == null) return;

        progressBar.setMax((int)baseObject.getTotalCount());
        progressBar.setProgress((int)baseObject.getCompleteCount());
        String completeText = String.format(getResources().getString(R.string.report_chart_status_complete), (int) baseObject.getCompleteCount());
        String incompleteText = String.format(getResources().getString(R.string.report_chart_status_incomplete), (int)baseObject.getIncompleteCount());
        completeStatusTextView.setText(completeText);
        incompleteStatusTextView.setText(incompleteText);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_report_chart_checklist;
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

    }
}
