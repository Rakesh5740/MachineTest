package com.tejendramachinetest.imagetaskmodule;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageListModel implements Serializable {


    /**
     * total : 14300
     * totalHits : 500
     * hits : [{"id":434918,"pageURL":"https://pixabay.com/photos/legs-window-car-dirt-road-relax-434918/","type":"photo","tags":"legs, window, car","previewURL":"https://cdn.pixabay.com/photo/2014/09/03/20/15/legs-434918_150.jpg","previewWidth":150,"previewHeight":100,"webformatURL":"https://pixabay.com/get/52e3d14a4b5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":429,"largeImageURL":"https://pixabay.com/get/52e3d14a4b5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":2165,"imageHeight":1453,"imageSize":587598,"views":522368,"downloads":199735,"favorites":1592,"likes":1247,"comments":203,"user_id":2323,"user":"Greyerbaby","userImageURL":"https://cdn.pixabay.com/user/2014/11/14/05-39-07-629_250x250.jpg"},{"id":887272,"pageURL":"https://pixabay.com/photos/vintage-1950s-pretty-woman-887272/","type":"photo","tags":"vintage 1950s, pretty woman, vintage car","previewURL":"https://cdn.pixabay.com/photo/2015/08/13/17/24/vintage-1950s-887272_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/5ee8d2414d50b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/5ee8d2414d50b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":5760,"imageHeight":3840,"imageSize":2128873,"views":174728,"downloads":83278,"favorites":873,"likes":801,"comments":78,"user_id":334088,"user":"JillWellington","userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg"},{"id":362150,"pageURL":"https://pixabay.com/photos/car-repair-car-workshop-repair-shop-362150/","type":"photo","tags":"car repair, car workshop, repair shop","previewURL":"https://cdn.pixabay.com/photo/2014/06/04/16/36/car-repair-362150_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/55e6d7424f52b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/55e6d7424f52b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":6000,"imageHeight":4000,"imageSize":8544150,"views":236423,"downloads":92137,"favorites":518,"likes":490,"comments":78,"user_id":123690,"user":"RyanMcGuire","userImageURL":"https://cdn.pixabay.com/user/2014/06/04/17-13-09-273_250x250.jpg"},{"id":1197800,"pageURL":"https://pixabay.com/photos/cuba-oldtimer-old-car-forest-red-1197800/","type":"photo","tags":"cuba, oldtimer, old car","previewURL":"https://cdn.pixabay.com/photo/2016/02/13/13/11/cuba-1197800_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/57e1dc444252ac14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/57e1dc444252ac14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5760,"imageHeight":3840,"imageSize":10114533,"views":170816,"downloads":96466,"favorites":408,"likes":465,"comments":44,"user_id":2019050,"user":"Noel_Bauza","userImageURL":"https://cdn.pixabay.com/user/2016/02/07/19-28-24-618_250x250.jpg"},{"id":381233,"pageURL":"https://pixabay.com/photos/taxi-cab-traffic-cab-new-york-381233/","type":"photo","tags":"taxi cab, traffic, cab","previewURL":"https://cdn.pixabay.com/photo/2014/07/01/12/35/taxi-cab-381233_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/55e8d4414951b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":425,"largeImageURL":"https://pixabay.com/get/55e8d4414951b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":5450,"imageHeight":3623,"imageSize":5604087,"views":396468,"downloads":223486,"favorites":809,"likes":706,"comments":90,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10     -34-2_250x250.jpg"},{"id":1149997,"pageURL":"https://pixabay.com/photos/car-traffic-man-hurry-1149997/","type":"photo","tags":"car, traffic, man","previewURL":"https://cdn.pixabay.com/photo/2016/01/19/17/57/car-1149997_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/57e1d14a435bab14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/57e1d14a435bab14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5564,"imageHeight":3710,"imageSize":3110498,"views":204368,"downloads":106246,"favorites":463,"likes":437,"comments":53,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10-34-2_250x250.jpg"},{"id":1853936,"pageURL":"https://pixabay.com/photos/automobile-automotive-beach-beetle-1853936/","type":"photo","tags":"automobile, automotive, beach","previewURL":"https://cdn.pixabay.com/photo/2016/11/23/17/24/automobile-1853936_150.jpg","previewWidth":150,"previewHeight":100,"webformatURL":"https://pixabay.com/get/57e8d0404351aa14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":427,"largeImageURL":"https://pixabay.com/get/57e8d0404351aa14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":7360,"imageHeight":4912,"imageSize":13444851,"views":80070,"downloads":43621,"favorites":426,"likes":309,"comments":26,"user_id":2286921,"user":"Pexels","userImageURL":"https://cdn.pixabay.com/user/2016/03/26/22-06-36-459_250x250.jpg"},{"id":788747,"pageURL":"https://pixabay.com/photos/auto-car-cadillac-oldtimer-788747/","type":"photo","tags":"auto, car, cadillac","previewURL":"https://cdn.pixabay.com/photo/2015/05/28/23/12/auto-788747_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/51e8dd444e55b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/51e8dd444e55b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":3000,"imageHeight":2000,"imageSize":1508628,"views":93311,"downloads":46066,"favorites":283,"likes":276,"comments":39,"user_id":524252,"user":"JOERG-DESIGN","userImageURL":"https://cdn.pixabay.com/user/2015/01/15/11-36-33-997_250x250.jpg"},{"id":3046424,"pageURL":"https://pixabay.com/photos/car-vehicle-motor-transport-3046424/","type":"photo","tags":"car, vehicle, motor","previewURL":"https://cdn.pixabay.com/photo/2017/12/28/23/41/car-3046424_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/55e0d1454e50a814f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/55e0d1454e50a814f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5456,"imageHeight":3632,"imageSize":8284052,"views":133699,"downloads":92376,"favorites":281,"likes":298,"comments":29,"user_id":17511,"user":"melkhagelslag","userImageURL":"https://cdn.pixabay.com/user/2017/03/05/17-08-40-993_250x250.jpg"},{"id":1851246,"pageURL":"https://pixabay.com/photos/buildings-car-city-porsche-rainy-1851246/","type":"photo","tags":"buildings, car, city","previewURL":"https://cdn.pixabay.com/photo/2016/11/22/23/44/buildings-1851246_150.jpg","previewWidth":150,"previewHeight":65,"webformatURL":"https://pixabay.com/get/57e8d0424856aa14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":278,"largeImageURL":"https://pixabay.com/get/57e8d0424856aa14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":4800,"imageHeight":2092,"imageSize":1208358,"views":81189,"downloads":46897,"favorites":332,"likes":271,"comments":27,"user_id":2286921,"user":"Pexels","userImageURL":"https://cdn.pixabay.com/user/2016/03/26/22-06-36-459_250x250.jpg"},{"id":4322521,"pageURL":"https://pixabay.com/ph     otos/fiat-fiat-500-auto-oldtimer-4322521/","type":"photo","tags":"fiat, fiat 500, auto","previewURL":"https://cdn.pixabay.com/photo/2019/07/07/14/03/fiat-4322521_150.jpg","previewWidth":150,"previewHeight":120,"webformatURL":"https://pixabay.com/get/52e3d7414f50ad14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":514,"largeImageURL":"https://pixabay.com/get/52e3d7414f50ad14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":4000,"imageHeight":3215,"imageSize":2954155,"views":193419,"downloads":163022,"favorites":391,"likes":461,"comments":97,"user_id":2364555,"user":"pixel2013","userImageURL":"https://cdn.pixabay.com/user/2019/07/15/18-51-07-612_250x250.jpg"},{"id":1209321,"pageURL":"https://pixabay.com/photos/girls-lying-classic-car-young-1209321/","type":"photo","tags":"girls, lying, classic car","previewURL":"https://cdn.pixabay.com/photo/2016/02/19/10/37/girls-1209321_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/57e2d54a4950ad14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/57e2d54a4950ad14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5292,"imageHeight":3528,"imageSize":1364179,"views":99200,"downloads":53655,"favorites":667,"likes":441,"comments":28,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10-34-2_250x250.jpg"},{"id":984159,"pageURL":"https://pixabay.com/photos/car-vintage-classic-retro-984159/","type":"photo","tags":"car, vintage, classic","previewURL":"https://cdn.pixabay.com/photo/2015/10/12/15/05/car-984159_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/5fe8d1424f5bb108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":423,"largeImageURL":"https://pixabay.com/get/5fe8d1424f5bb108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":4754,"imageHeight":3149,"imageSize":4174805,"views":104836,"downloads":75734,"favorites":259,"likes":174,"comments":12,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10-34-2_250x250.jpg"},{"id":887273,"pageURL":"https://pixabay.com/photos/vintage-1950s-pretty-woman-887273/","type":"photo","tags":"vintage 1950s, pretty woman, vintage car","previewURL":"https://cdn.pixabay.com/photo/2015/08/13/17/24/vintage-1950s-887273_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/5ee8d2414d51b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/5ee8d2414d51b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":5760,"imageHeight":3840,"imageSize":1575798,"views":41075,"downloads":19503,"favorites":250,"likes":232,"comments":20,"user_id":334088,"user":"JillWellington","userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg"},{"id":690275,"pageURL":"https://pixabay.com/photos/car-hood-vintage-classic-oldschool-690275/","type":"photo","tags":"car, hood, vintage","previewURL":"https://cdn.pixabay.com/photo/2015/03/26/09/46/car-690275_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/50e9d5414d57b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":424,"largeImageURL":"https://pixabay.com/get/50e9d5414d57b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":2516,"imageHeight":1667,"imageSize":870742,"views":64475,"downloads":25872,"favorites":310,"likes":163,"comments":9,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10-34-2_250x250.jpg"},{"id":123978,"pageURL":"https://pixabay.com/photos/morocco-africa-desert-marroc-sand-123978/","type":"photo","tags":"morocco, africa, desert","previewURL":"https://cdn.pixabay.com/photo/2013/06/10/09/23/morocco-123978_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/57e2d64a4d5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":425,"largeImageURL":"https://pixabay.com/get/57e2d64a4d5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":3008,"imageHeight":2000,"imageSize":1262490,"views":85469,"downloads":36781,"favorites":234,"likes":205,"comments":28,"user_id":37005,"user":"makunin","userImageURL":"https://cdn.pixabay.com/user/2013/07/14/17-56-11-696_250x250.jpg"},{"id":887286,"pageURL":"https://pixabay.com/photos/woman-s-legs-high-heels-vintage-car-887286/","type":"photo","tags":"woman's legs, high heels, vintage car","previewURL":"https://cdn.pixabay.com/photo/2015/08/13/17/30/womans-legs-887286_150.jpg","previewWidth":100,"previewHeight":150,"webformatURL":"https://pixabay.com/get/5ee8d2414254b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":427,"webformatHeight":640,"largeImageURL":"https://pixabay.com/get/5ee8d2414254b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":3381,"imageHeight":5071,"imageSize":3675298,"views":129460,"downloads":77303,"favorites":712,"likes":494,"comments":49,"user_id":334088,"user":"JillWellington","userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg"},{"id":1850465,"pageURL":"https://pixabay.com/photos/adorable-animal-canine-car-cute-1850465/","type":"photo","tags":"adorable, animal, canine","previewURL":"https://cdn.pixabay.com/photo/2016/11/22/20/10/adorable-1850465_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/57e8d0434e54a914f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/57e8d0434e54a914f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5210,"imageHeight":3473,"imageSize":3192926,"views":40887,"downloads":22485,"favorites":228,"likes":165,"comments":15,"user_id":2286921,"user":"Pexels","userImageURL":"https://cdn.pixabay.com/user/2016/03/26/22-06-36-459_250x250.jpg"},{"id":2705402,"pageURL":"https://pixabay.com/photos/ford-mustang-v8-67-ford-mustang-2705402/","type":"photo","tags":"ford, mustang, v8","previewURL":"https://cdn.pixabay.com/photo/2017/09/01/20/23/ford-2705402_150.jpg","previewWidth":150,"previewHeight":96,"webformatURL":"https://pixabay.com/get/54e7d5464e52ae14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_640.jpg","webformatWidth":640,"webformatHeight":413,"largeImageURL":"https://pixabay.com/get/54e7d5464e52ae14f6da8c7dda79367b1138dce151596c48702778d79745c459bf_1280.jpg","imageWidth":5999,"imageHeight":3872,"imageSize":7886198,"views":175211,"downloads":112597,"favorites":423,"likes":484,"comments":59,"user_id":1032521,"user":"Tama66","userImageURL":"https://cdn.pixabay.com/user/2019/12/21/18-41-09-325_250x250.jpg"},{"id":801994,"pageURL":"https://pixabay.com/photos/steering-wheel-car-drive-driving-801994/","type":"photo","tags":"steering wheel, car, drive","previewURL":"https://cdn.pixabay.com/photo/2015/06/08/15/18/steering-wheel-801994_150.jpg","previewWidth":150,"previewHeight":99,"webformatURL":"https://pixabay.com/get/5ee0d44a4356b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg","webformatWidth":640,"webformatHeight":426,"largeImageURL":"https://pixabay.com/get/5ee0d44a4356b108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg","imageWidth":3888,"imageHeight":2592,"imageSize":4207631,"views":123973,"downloads":52209,"favorites":217,"likes":233,"comments":20,"user_id":242387,"user":"Free-Photos","userImageURL":"https://cdn.pixabay.com/user/2014/05/07/00-10-34-2_250x250.jpg"}]
     */

    private int total;
    private int totalHits;
    private ArrayList<HitsBean> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<HitsBean> getHits() {
        return hits;
    }

    public void setHits(ArrayList<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean implements Serializable {
        /**
         * id : 434918
         * pageURL : https://pixabay.com/photos/legs-window-car-dirt-road-relax-434918/
         * type : photo
         * tags : legs, window, car
         * previewURL : https://cdn.pixabay.com/photo/2014/09/03/20/15/legs-434918_150.jpg
         * previewWidth : 150
         * previewHeight : 100
         * webformatURL : https://pixabay.com/get/52e3d14a4b5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_640.jpg
         * webformatWidth : 640
         * webformatHeight : 429
         * largeImageURL : https://pixabay.com/get/52e3d14a4b5ab108f5d084609629327a123ddae75b4c704c7d2c78d49f4cc45f_1280.jpg
         * imageWidth : 2165
         * imageHeight : 1453
         * imageSize : 587598
         * views : 522368
         * downloads : 199735
         * favorites : 1592
         * likes : 1247
         * comments : 203
         * user_id : 2323
         * user : Greyerbaby
         * userImageURL : https://cdn.pixabay.com/user/2014/11/14/05-39-07-629_250x250.jpg
         */

        private int id;
        private String pageURL;
        private String type;
        private String tags;
        private String previewURL;
        private int previewWidth;
        private int previewHeight;
        private String webformatURL;
        private int webformatWidth;
        private int webformatHeight;
        private String largeImageURL;
        private int imageWidth;
        private int imageHeight;
        private int imageSize;
        private int views;
        private int downloads;
        private int favorites;
        private int likes;
        private int comments;
        private int user_id;
        private String user;
        private String userImageURL;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public int getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(int previewWidth) {
            this.previewWidth = previewWidth;
        }

        public int getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(int previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public int getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(int webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public int getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(int webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getImageSize() {
            return imageSize;
        }

        public void setImageSize(int imageSize) {
            this.imageSize = imageSize;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        @NonNull
        @Override
        public String toString() {
            return largeImageURL;
        }


    }
}
