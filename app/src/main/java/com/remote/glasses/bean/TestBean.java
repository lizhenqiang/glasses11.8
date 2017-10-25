package com.remote.glasses.bean;

import java.util.List;

/**
 * Created by admin on 2016/10/27.
 */
public class TestBean {


    /**
     * a : ["1","2"]
     * b : 2
     * c : 3
     * d : 4
     * e : 冀
     * f : 帅
     */

    private InfoBean info;
    /**
     * info : {"a":["1","2"],"b":"2","c":"3","d":"4","e":"冀","f":"帅"}
     * message : 成功
     */

    private String message;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class InfoBean {
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private List<String> a;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }

        public List<String> getA() {
            return a;
        }

        public void setA(List<String> a) {
            this.a = a;
        }
    }
}
