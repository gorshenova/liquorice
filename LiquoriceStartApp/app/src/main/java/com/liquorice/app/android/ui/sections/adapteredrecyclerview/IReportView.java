package com.liquorice.app.android.ui.sections.adapteredrecyclerview;

import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ApprovalSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.ChecklistSummary;
import com.liquorice.app.android.ui.sections.adapteredrecyclerview.models.PollSummary;

/**
 * Created by eyablonskaya on 10/4/2016.
 */

public interface IReportView {

    void initializeApprovalSummary(ApprovalSummary summary);

    void initializeSurveySummary(PollSummary summary, long totalPostedCount, long totalParticipatedCount);

    void initializeChecklistSummary(ChecklistSummary summary, long totalCreatedByMe, long totalCreatedByOthers);
}
