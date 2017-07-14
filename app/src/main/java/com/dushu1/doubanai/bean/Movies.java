package com.dushu1.doubanai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class Movies implements Serializable{

    /**
     * rating : {"max":10,"average":4.8,"stars":"25","min":0}
     * genres : ["动作","奇幻","冒险"]
     * title : 新木乃伊
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054435/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/567.jpg","large":"http://img3.doubanio.com/img/celebrity/large/567.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/567.jpg"},"name":"汤姆·克鲁斯","id":"1054435"},{"alt":"https://movie.douban.com/celebrity/1016674/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/17524.jpg","large":"http://img7.doubanio.com/img/celebrity/large/17524.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/17524.jpg"},"name":"罗素·克劳","id":"1016674"},{"alt":"https://movie.douban.com/celebrity/1315815/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/33650.jpg","large":"http://img7.doubanio.com/img/celebrity/large/33650.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/33650.jpg"},"name":"安娜贝拉·沃丽丝","id":"1315815"}]
     * collect_count : 34444
     * original_title : The Mummy
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1049959/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/25843.jpg","large":"http://img7.doubanio.com/img/celebrity/large/25843.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/25843.jpg"},"name":"艾里克斯·库兹曼","id":"1049959"}]
     * year : 2017
     * images : {"small":"http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2459198108.webp","large":"http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2459198108.webp","medium":"http://img3.doubanio.com/view/movie_poster_cover/spst/public/p2459198108.webp"}
     * alt : https://movie.douban.com/subject/20451290/
     * id : 20451290
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

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

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean implements Serializable{
        /**
         * max : 10
         * average : 4.8
         * stars : 25
         * min : 0
         */

        private int max;
        private double average;
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
    }

    public static class ImagesBean implements Serializable{
        /**
         * small : http://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2459198108.webp
         * large : http://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2459198108.webp
         * medium : http://img3.doubanio.com/view/movie_poster_cover/spst/public/p2459198108.webp
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

    public static class CastsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1054435/
         * avatars : {"small":"http://img3.doubanio.com/img/celebrity/small/567.jpg","large":"http://img3.doubanio.com/img/celebrity/large/567.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/567.jpg"}
         * name : 汤姆·克鲁斯
         * id : 1054435
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean implements Serializable{
            /**
             * small : http://img3.doubanio.com/img/celebrity/small/567.jpg
             * large : http://img3.doubanio.com/img/celebrity/large/567.jpg
             * medium : http://img3.doubanio.com/img/celebrity/medium/567.jpg
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

    public static class DirectorsBean implements Serializable{
        /**
         * alt : https://movie.douban.com/celebrity/1049959/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/25843.jpg","large":"http://img7.doubanio.com/img/celebrity/large/25843.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/25843.jpg"}
         * name : 艾里克斯·库兹曼
         * id : 1049959
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX implements Serializable{
            /**
             * small : http://img7.doubanio.com/img/celebrity/small/25843.jpg
             * large : http://img7.doubanio.com/img/celebrity/large/25843.jpg
             * medium : http://img7.doubanio.com/img/celebrity/medium/25843.jpg
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
