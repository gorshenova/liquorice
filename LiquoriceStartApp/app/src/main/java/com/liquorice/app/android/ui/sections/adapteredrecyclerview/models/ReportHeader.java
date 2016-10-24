package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.views.ReportsHeaderView;

/**
 * Created by eyablonskaya on 7/13/2016.
 */
public class ReportHeader extends BaseObject {

    public enum ReportHeaderType {
        HEADER_APPROVAL,
        HEADER_SURVEY,
        HEADER_CHECKLIST
    }

    private int nameResId;
    private int iconResId;
    private ReportHeaderType type;

    public ReportHeader(int nameResId, int iconResID, ReportHeaderType type) {
        this.nameResId = nameResId;
        this.iconResId = iconResID;
        this.type =  type;
    }

    public int getNameResId() {
        return nameResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public ReportHeaderType getType() {
        return type;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ReportsHeaderView(context);
    }
}
