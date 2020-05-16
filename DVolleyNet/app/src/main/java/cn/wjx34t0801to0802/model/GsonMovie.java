package cn.wjx34t0801to0802.model;

/**
 * @author WuChangJian
 * @date 2020/5/16 8:09
 */
public class GsonMovie {

    /**
     * rating : {}
     * genres : ["科幻","惊悚"]
     * title : 灭绝
     * casts : []
     * durations : ["95分钟","93分钟(中国大陆)"]
     * collect_count : 16100
     * mainland_pubdate : 2020-01-18
     * has_video : true
     * original_title : Extinction
     * subtype : movie
     * directors : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp"},"name_en":"Ben Young","name":"本·扬","alt":"https://movie.douban.com/celebrity/1373883/","id":"1373883"}]
     * pubdates : ["2018-07-27(美国)","2020-01-18(中国大陆)"]
     * year : 2018
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp"}
     * alt : https://movie.douban.com/subject/26871938/
     * id : 26871938
     */

    private String title;
    private ImagesBean images;

    public static class RatingBean {
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "ImagesBean{" +
                    "small='" + small + '\'' +
                    ", large='" + large + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "GsonMovie{" +
                "title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
