package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

/**
 * ChecklistSummary
 */

public class ChecklistSummary {

        private Long createdByMeNotCompleted;


        private Long createdByOthersNotCompleted;


        private Long createdByMeCompleted;


        private Long createdByOthersCompleted;

        public Long getCreatedByMeNotCompleted() {
            return createdByMeNotCompleted;
        }

        public Long getCreatedByOthersNotCompleted() {
            return createdByOthersNotCompleted;
        }

        public Long getCreatedByMeCompleted() {
            return createdByMeCompleted;
        }

        public Long getCreatedByOthersCompleted() {
            return createdByOthersCompleted;
        }


        /**
         * Builder for ChecklistSummaryResponse
         **/
        public static class ChecklistSummaryBuilder {
            private ChecklistSummary toBuild = new ChecklistSummary();

            public ChecklistSummaryBuilder() {
            }

            public ChecklistSummary build() {
                return toBuild;
            }

            public ChecklistSummaryBuilder createdByMeNotCompleted(Long value) {
                toBuild.createdByMeNotCompleted = value;
                return this;
            }

            public ChecklistSummaryBuilder createdByOthersNotCompleted(Long value) {
                toBuild.createdByOthersNotCompleted = value;
                return this;
            }

            public ChecklistSummaryBuilder createdByMeCompleted(Long value) {
                toBuild.createdByMeCompleted = value;
                return this;
            }

            public ChecklistSummaryBuilder createdByOthersCompleted(Long value) {
                toBuild.createdByOthersCompleted = value;
                return this;
            }
        }

}
