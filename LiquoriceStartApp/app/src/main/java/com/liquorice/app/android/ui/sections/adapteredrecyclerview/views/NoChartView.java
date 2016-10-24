package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.NoChart;
import com.liquorice.app.android.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 7/28/2016.
 */
public class NoChartView extends BaseRecyclerItem<NoChart> {

    @Bind(R.id.report_no_chart_text)
    TextView noChartTextView;

    public NoChartView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull NoChart baseObject) {
        if(baseObject == null || baseObject.getText().isEmpty()) return;
        noChartTextView.setText(baseObject.getText());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_report_no_chart;
    }

    @Override
    protected void onLinkInterface() {

    }

    @Override
    protected int getClickedID() {
        return 0;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
    }
}
