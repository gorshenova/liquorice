package com.liquorice.app.android.ui.sections.adapteredrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredBaseCallback;
import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.ui.views.AdapteredRecyclerView;
import com.liquorice.app.android.R;
import com.liquorice.app.android.ui.fragments.abs.BaseFragment;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ApprovalSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ChecklistSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.PollSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportApprovalChart;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportChecklistChart;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportHeader;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportSurveyChart;
import com.liquoriceutils.helpers.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 10/4/2016.
 */

public class ReportFragment extends BaseFragment implements IReportView{

    @Bind(R.id.recycler_view_reports)
    AdapteredRecyclerView uiRecyclerView;

    private Logger logger  = Logger.getLogger(ReportFragment.class);
    private ReportPresenter presenter;
    private List<BaseObject> data = new ArrayList<>();

    public static ReportFragment getInstance(Bundle args) {
        ReportFragment frag = new ReportFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        presenter =  new ReportPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, view);

        uiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        uiRecyclerView.setActionCallback(new OnAdapteredBaseCallback() {
            @Override
            public void onItemClick(int index, @NonNull BaseObject object) {
                getPresenter().onHeaderClick(object);
            }

            @Override
            public void onActionReceived(@NonNull RecycleEvent recycleEvent, int index, @NonNull BaseObject object) {

            }
        });
        getPresenter().init();
        return view;
    }

    public ReportPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void initializeApprovalSummary(ApprovalSummary summary) {
        if (summary == null) {
            logger.error("Approval summary is null.");
        }
        data.add(new ReportHeader(R.string.report_header_approvals, R.drawable.icon_approvals, ReportHeader.ReportHeaderType.HEADER_APPROVAL));
        //received
        data.add(new ReportSummary(R.string.report_summary_received, summary.getTotalReceived()));
        ReportApprovalChart receivedChart = new ReportApprovalChart();
        receivedChart.setApprovedCount(summary.getReceivedApproved());
        receivedChart.setDeclinedCount(summary.getReceivedRejected());
        receivedChart.setPendingCount(summary.getReceivedPending());
        receivedChart.setTotalCount(summary.getTotalReceived());
        data.add(receivedChart);
        //requested
        data.add(new ReportSummary(R.string.report_summary_requested, summary.getTotalRequested()));
        ReportApprovalChart requestedChart = new ReportApprovalChart();
        requestedChart.setApprovedCount(summary.getRequestedApproved());
        requestedChart.setDeclinedCount(summary.getRequestedRejected());
        requestedChart.setPendingCount(summary.getRequestedPending());
        requestedChart.setTotalCount(summary.getTotalRequested());
        data.add(requestedChart);
        updateReportView();
    }

    @Override
    public void initializeSurveySummary(PollSummary summary, long totalPostedCount, long totalParticipatedCount) {
        data.add(new ReportHeader(R.string.report_header_surveys, R.drawable.icon_report_surveys, ReportHeader.ReportHeaderType.HEADER_SURVEY));
        //posted
        data.add(new ReportSummary(R.string.report_summary_posted, totalPostedCount));
        data.add(new ReportSurveyChart(totalPostedCount, summary.getPostedByMeReplied(), summary.getPostedByMeNotReplied()));
        //participated
        data.add(new ReportSummary(R.string.report_summary_participated, totalParticipatedCount));
        data.add(new ReportSurveyChart(totalParticipatedCount, summary.getParticipatedReplied(), summary.getParticipatedNotReplied()));
        updateReportView();
    }

    @Override
    public void initializeChecklistSummary(ChecklistSummary summary, long totalCreatedByMe, long totalCreatedByOthers) {
        data.add(new ReportHeader(R.string.report_header_checklists, R.drawable.icon_report_checklists, ReportHeader.ReportHeaderType.HEADER_CHECKLIST));
        //created by me
        data.add(new ReportSummary(R.string.report_summary_created_by_me, totalCreatedByMe));
        data.add(new ReportChecklistChart(totalCreatedByMe, summary.getCreatedByMeCompleted(), summary.getCreatedByMeNotCompleted()));
        //created by others
        data.add(new ReportSummary(R.string.report_summary_created_by_others, totalCreatedByOthers));
        data.add(new ReportChecklistChart(totalCreatedByOthers, summary.getCreatedByOthersCompleted(), summary.getCreatedByOthersNotCompleted()));
        updateReportView();
    }


    public void updateReportView() {
        uiRecyclerView.set(data);
        uiRecyclerView.notifyDataSetChanged();
    }
}
