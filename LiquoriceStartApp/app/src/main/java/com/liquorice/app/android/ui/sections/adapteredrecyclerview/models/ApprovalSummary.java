package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

/**
 * ApprovalSummary
 */

public class ApprovalSummary {


    private Long requestedRejected;


    private Long receivedPending;


    private Long requestedPending;


    private Long receivedRejected;


    private Long totalRequested;


    private Long totalReceived;


    private Long requestedApproved;


    private Long receivedApproved;

    public Long getRequestedRejected() {
        return requestedRejected;
    }

    public Long getReceivedPending() {
        return receivedPending;
    }

    public Long getRequestedPending() {
        return requestedPending;
    }

    public Long getReceivedRejected() {
        return receivedRejected;
    }

    public Long getTotalRequested() {
        return totalRequested;
    }

    public Long getTotalReceived() {
        return totalReceived;
    }

    public Long getRequestedApproved() {
        return requestedApproved;
    }

    public Long getReceivedApproved() {
        return receivedApproved;
    }


    /**
     * Builder for ApprovalSummary
     **/
    public static class ApprovalSummaryResponseBuilder {
        private ApprovalSummary toBuild = new ApprovalSummary();

        public ApprovalSummaryResponseBuilder() {
        }

        public ApprovalSummary build() {
            return toBuild;
        }

        public ApprovalSummaryResponseBuilder requestedRejected(Long value) {
            toBuild.requestedRejected = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder receivedPending(Long value) {
            toBuild.receivedPending = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder requestedPending(Long value) {
            toBuild.requestedPending = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder receivedRejected(Long value) {
            toBuild.receivedRejected = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder totalRequested(Long value) {
            toBuild.totalRequested = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder totalReceived(Long value) {
            toBuild.totalReceived = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder requestedApproved(Long value) {
            toBuild.requestedApproved = value;
            return this;
        }

        public ApprovalSummaryResponseBuilder receivedApproved(Long value) {
            toBuild.receivedApproved = value;
            return this;
        }
    }
}
