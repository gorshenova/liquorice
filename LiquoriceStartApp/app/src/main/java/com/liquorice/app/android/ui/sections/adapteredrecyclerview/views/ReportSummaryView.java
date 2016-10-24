package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportSummary;
import com.liquorice.app.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 7/13/2016.
 */
public class ReportSummaryView extends BaseRecyclerItem<ReportSummary> {

    @Bind(R.id.report_summary_name)
    TextView name;

    @Bind(R.id.report_summary_total_number)
    TextView totalNumber;

    public ReportSummaryView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull ReportSummary baseObject) {
        if(baseObject == null) return;
        totalNumber.setText(String.valueOf(baseObject.getTotalNumber()));
        name.setText(baseObject.getNameResId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_report_summary;
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
