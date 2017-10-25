package com.remote.glasses.bean;

import java.util.List;

/**
 * Created by Feics on 2016/10/31.
 */
public class QuestionsBean {
    private String name;
    private List<Question> question;

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public class Question{
        private String title;
        private String media;
        private List<Options> options;

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public List<Options> getOptions() {
            return options;
        }

        public void setOptions(List<Options> options) {
            this.options = options;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Question{" +
                    "title='" + title + '\'' +
                    ", media='" + media + '\'' +
                    ", options=" + options +
                    '}';
        }

        public class Options{
            private String desc;
            private String remark;
            private String media;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            @Override
            public String toString() {
                return "Options{" +
                        "desc='" + desc + '\'' +
                        ", remark='" + remark + '\'' +
                        ", media='" + media + '\'' +
                        '}';
            }
        }

    }

    @Override
    public String toString() {
        return "QuestionsBean{" +
                "name='" + name + '\'' +
                ", question=" + question +
                '}';
    }
}

