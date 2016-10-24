package com.liquorice.app.android.ui.sections.adapteredrecyclerview;

import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ApprovalSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ChecklistSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.PollSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ReportHeader;
import com.liquoriceutils.helpers.log.Logger;

/**
 * Created by eyablonskaya on 10/4/2016.
 */

public class ReportPresenter {

    private Logger logger  = Logger.getLogger(ReportPresenter.class);

    private IReportView view;
    public ReportPresenter(IReportView view){
        this.view =  view;
    }

    public IReportView getView() {
        return view;
    }

    public void init(){
        fetchApprovalSummary();
    }

    public void onHeaderClick(@NonNull BaseObject object) {
        if (object instanceof ReportHeader) {
            ReportHeader header = (ReportHeader) object;
            switch (header.getType()) {
                case HEADER_APPROVAL:
                    //getRouter().onOpenApproversScreen();
                    break;
                case HEADER_SURVEY:
                    //getRouter().onOpenSurveysScreen();
                    break;
                case HEADER_CHECKLIST:
                   // getRouter().onOpenChecklistScreen();
                    break;
                default:
                    logger.error("Header is not identified: " + header.getType());
                    break;
            }

        }
    }

    public void fetchApprovalSummary() {
        ApprovalSummary.ApprovalSummaryResponseBuilder b = new ApprovalSummary.ApprovalSummaryResponseBuilder();
        b.totalReceived(112L);
        b.receivedApproved(70L);
        b.receivedPending(25L);
        b.receivedRejected(17L);

        b.totalRequested(33L);
        b.requestedApproved(19L);
        b.requestedPending(10L);
        b.requestedRejected(4L);

        getView().initializeApprovalSummary(b.build());
        fetchSurveySummary();
    }

    public void fetchSurveySummary() {
        PollSummary.PollSummaryBuilder b = new PollSummary.PollSummaryBuilder();
        b.postedByMeNotReplied(42L);
        b.postedByMeReplied(70L);
        b.participatedReplied(21L);
        b.participatedNotReplied(12L);

        PollSummary pSummary = b.build();
        long totalPostedCount = pSummary.getPostedByMeNotReplied() + pSummary.getPostedByMeReplied();
        long totalParticipatedCount = pSummary.getParticipatedNotReplied() + pSummary.getParticipatedReplied();
        getView().initializeSurveySummary(pSummary, totalPostedCount, totalParticipatedCount);
        fetchChecklistSummary();
    }

    public void fetchChecklistSummary() {
        ChecklistSummary.ChecklistSummaryBuilder b = new ChecklistSummary.ChecklistSummaryBuilder();
        b.createdByMeCompleted(56L);
        b.createdByMeNotCompleted(6L);
        b.createdByOthersCompleted(91L);
        b.createdByOthersCompleted(68L);
        b.createdByOthersNotCompleted(23L);

        ChecklistSummary cSummary = b.build();
        long totalByMe = cSummary.getCreatedByMeCompleted() + cSummary.getCreatedByMeNotCompleted();
        long totalByOthers = cSummary.getCreatedByOthersCompleted() + cSummary.getCreatedByOthersNotCompleted();
        getView().initializeChecklistSummary(cSummary, totalByMe, totalByOthers);
    }
}
