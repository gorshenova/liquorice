package com.liquorice.app.android.ui.sections.adapteredrecyclerview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportHeader;
import com.liquorice.app.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 7/13/2016.
 */
public class ReportsHeaderView extends BaseRecyclerItem<ReportHeader> {

    @Bind(R.id.report_header_title)
    TextView titleTextView;

    @Bind(R.id.report_header_icon)
    ImageView iconImageView;

    @Bind(R.id.report_header_arrow)
    ImageView arrowImageView;

    public ReportsHeaderView(Context context) {
        super(context);
    }

    @Override
    public void setUp(@NonNull ReportHeader baseObject) {
        if (baseObject == null) return;
        titleTextView.setText(baseObject.getNameResId());
        iconImageView.setImageResource(baseObject.getIconResId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_report_header;
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
