package cn.wjx34t0901to0906.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author WuChangJian
 * @date 2020/5/23 11:03
 */
public class Movie {


    /**
     * rating : {"max":10,"average":8.2,"details":{"1":13,"2":122,"3":2532,"4":10820,"5":5103},"stars":"45","min":0}
     * genres : ["剧情"]
     * title : 理查德·朱维尔的哀歌
     * casts : [{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp"},"name_en":"Paul Walter Hauser","name":"保罗·沃尔特·豪泽","alt":"https://movie.douban.com/celebrity/1268250/","id":"1268250"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp"},"name_en":"Sam Rockwell","name":"山姆·洛克威尔","alt":"https://movie.douban.com/celebrity/1047972/","id":"1047972"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp"},"name_en":"Kathy Bates","name":"凯西·贝茨","alt":"https://movie.douban.com/celebrity/1010555/","id":"1010555"}]
     * durations : ["131分钟"]
     * collect_count : 70625
     * mainland_pubdate : 2020-01-10
     * has_video : true
     * original_title : Richard Jewell
     * subtype : movie
     *    : [{"avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"}]
     * pubdates : ["2019-11-20(AFI Fest)","2019-12-13(美国)","2020-01-10(中国大陆)"]
     * year : 2019
     * images : {"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp"}
     * alt : https://movie.douban.com/subject/25842038/
     * id : 25842038
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String mainland_pubdate;
    private boolean has_video;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<String> durations;
    private List<DirectorsBean> directors;
    private List<String> pubdates;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 8.2
         * details : {"1":13,"2":122,"3":2532,"4":10820,"5":5103}
         * stars : 45
         * min : 0
         */

        private int max;
        private double average;
        private DetailsBean details;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public static class DetailsBean {
            /**
             * 1 : 13
             * 2 : 122
             * 3 : 2532
             * 4 : 10820
             * 5 : 5103
             */

            @SerializedName("1")
            private int _$1;
            @SerializedName("2")
            private int _$2;
            @SerializedName("3")
            private int _$3;
            @SerializedName("4")
            private int _$4;
            @SerializedName("5")
            private int _$5;

            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
         * large : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
         * medium : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
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
    }

    public static class CastsBean {
        /**
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp"}
         * name_en : Paul Walter Hauser
         * name : 保罗·沃尔特·豪泽
         * alt : https://movie.douban.com/celebrity/1268250/
         * id : 1268250
         */

        private AvatarsBean avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
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
        }
    }

    public static class DirectorsBean {
        /**
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp"}
         * name_en : Clint Eastwood
         * name : 克林特·伊斯特伍德
         * alt : https://movie.douban.com/celebrity/1054436/
         * id : 1054436
         */

        private AvatarsBeanX avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
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
        }
    }
}
