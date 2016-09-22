package com.example.zhangyang.Reader.Bean;

import java.util.List;

/**
 * Created by zhangyang on 16/8/23.
 */
public class NewsArticles {
    /**
     * stat : 1
     * msg : ok
     * data:
     */

    private String stat;
    private String msg;
    /**
     * media:
     * tpl_medias:
     * js_ref:
     * ad_url:
     * content
     * pk : 57baa9159490cbf80e00002a
     * content_format : html
     * disclaimer : 原网页由ZAKER转码以便移动设备阅读
     */

    private DataBean data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String ad_url;
        private String content;
        private String pk;
        private String content_format;
        private String disclaimer;
        /**
         * type : image
         * id : 57baa950f5a2243560000007
         * url : http://zkres.myzaker.com/201608/57baa950f5a2243560000007_640.jpg
         * m_url : http://zkres.myzaker.com/201608/57baa950f5a2243560000007_320.jpg
         * min_url : http://zkres.myzaker.com/201608/57baa950f5a2243560000007_120.jpg
         * raw_url : http://dingyue.nosdn.127.net/WtQV1n4xrThPla2GzPv3Z40qsvFVhtIFZTfxaSxmSoeqZ1471828734731compressflag.jpg?imageView&thumbnail=550x325&quality=50
         * w : 550
         * h : 325
         */

        private List<MediaBean> media;
        /**
         * type : image
         * id : 57baa950f5a224356000000b
         * url : http://zkres.myzaker.com/201608/57baa950f5a224356000000b_640.jpg
         * m_url : http://zkres.myzaker.com/201608/57baa950f5a224356000000b_320.jpg
         * min_url : http://zkres.myzaker.com/201608/57baa950f5a224356000000b_120.jpg
         * raw_url : http://dingyue.nosdn.127.net/ek1eUyDlJHLdC5iJq7P6HEPpEPkTw2y71EtfDvyUcr7351471828782042.jpg?imageView&thumbnail=579x538&quality=50
         * w : 579
         * h : 538
         * target : tpl_cover
         */

        private List<TplMediasBean> tpl_medias;
        private List<String> js_ref;

        public String getAd_url() {
            return ad_url;
        }

        public void setAd_url(String ad_url) {
            this.ad_url = ad_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getContent_format() {
            return content_format;
        }

        public void setContent_format(String content_format) {
            this.content_format = content_format;
        }

        public String getDisclaimer() {
            return disclaimer;
        }

        public void setDisclaimer(String disclaimer) {
            this.disclaimer = disclaimer;
        }

        public List<MediaBean> getMedia() {
            return media;
        }

        public void setMedia(List<MediaBean> media) {
            this.media = media;
        }

        public List<TplMediasBean> getTpl_medias() {
            return tpl_medias;
        }

        public void setTpl_medias(List<TplMediasBean> tpl_medias) {
            this.tpl_medias = tpl_medias;
        }

        public List<String> getJs_ref() {
            return js_ref;
        }

        public void setJs_ref(List<String> js_ref) {
            this.js_ref = js_ref;
        }

        public static class MediaBean {
            private String type;
            private String id;
            private String url;
            private String m_url;
            private String min_url;
            private String raw_url;
            private String w;
            private String h;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getM_url() {
                return m_url;
            }

            public void setM_url(String m_url) {
                this.m_url = m_url;
            }

            public String getMin_url() {
                return min_url;
            }

            public void setMin_url(String min_url) {
                this.min_url = min_url;
            }

            public String getRaw_url() {
                return raw_url;
            }

            public void setRaw_url(String raw_url) {
                this.raw_url = raw_url;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }

            public String getH() {
                return h;
            }

            public void setH(String h) {
                this.h = h;
            }
        }

        public static class TplMediasBean {
            private String type;
            private String id;
            private String url;
            private String m_url;
            private String min_url;
            private String raw_url;
            private String w;
            private String h;
            private String target;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getM_url() {
                return m_url;
            }

            public void setM_url(String m_url) {
                this.m_url = m_url;
            }

            public String getMin_url() {
                return min_url;
            }

            public void setMin_url(String min_url) {
                this.min_url = min_url;
            }

            public String getRaw_url() {
                return raw_url;
            }

            public void setRaw_url(String raw_url) {
                this.raw_url = raw_url;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }

            public String getH() {
                return h;
            }

            public void setH(String h) {
                this.h = h;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }
        }
    }
}
