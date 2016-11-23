package com.hq.expandablelistviewexample.bean;

import java.math.BigDecimal;
import java.util.List;

public class CollocationPackageBean {
    private BigDecimal totalPrice;
    private String name;
    private List<CollocationSkuBean> collocationSkuDoList;
    private BigDecimal discountFee;

    public static class CollocationSkuBean {
        private String skuTitle;
        private String imageMd5;

        public CollocationSkuBean(String skuTitle, String imageMd5) {
            this.skuTitle = skuTitle;
            this.imageMd5 = imageMd5;
        }

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }

        public String getImageMd5() {
            return imageMd5;
        }

        public void setImageMd5(String imageMd5) {
            this.imageMd5 = imageMd5;
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CollocationSkuBean> getCollocationSkuDoList() {
        return collocationSkuDoList;
    }

    public void setCollocationSkuDoList(List<CollocationSkuBean> collocationSkuDoList) {
        this.collocationSkuDoList = collocationSkuDoList;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }
}
