package com.liquorice.app.android.ui.sections.adapteredrecyclerview.models;

/**
 * PollSummary
 */

public class PollSummary {

        private Long participatedNotReplied;


        private Long participatedReplied;


        private Long postedByMeReplied;


        private Long postedByMeNotReplied;

        public Long getParticipatedNotReplied() {
            return participatedNotReplied;
        }

        public Long getParticipatedReplied() {
            return participatedReplied;
        }

        public Long getPostedByMeReplied() {
            return postedByMeReplied;
        }

        public Long getPostedByMeNotReplied() {
            return postedByMeNotReplied;
        }


        /**
         * Builder for PollSummary
         **/
        public static class PollSummaryBuilder {
            private PollSummary toBuild = new PollSummary();

            public PollSummaryBuilder() {
            }

            public PollSummary build() {
                return toBuild;
            }

            public PollSummaryBuilder participatedNotReplied(Long value) {
                toBuild.participatedNotReplied = value;
                return this;
            }

            public PollSummaryBuilder participatedReplied(Long value) {
                toBuild.participatedReplied = value;
                return this;
            }

            public PollSummaryBuilder postedByMeReplied(Long value) {
                toBuild.postedByMeReplied = value;
                return this;
            }

            public PollSummaryBuilder postedByMeNotReplied(Long value) {
                toBuild.postedByMeNotReplied = value;
                return this;
            }
        }
   }
