package com.digitalsurveys.mohammadfaizan.digitalsurveys.Models;

/**
 * Created by mohammad.faizan on 2/29/2016.
 */
public class Outlet {

    long id;
    String username;
    String ref_number;
    String ref_number_time_date;
    String shop_nature;
    long shop_number;
    String shop_status;
    String latitude;
    String longitude;
    String shop_save_time_date;
    int image_count;
    String image_location;

    public Outlet(String username, String ref_number, String ref_number_time_date, String shop_nature, long shop_number, String shop_status, String latitude, String longitude, String shop_save_time_date, int image_count, String image_location) {
        this.username = username;
        this.ref_number = ref_number;
        this.ref_number_time_date = ref_number_time_date;
        this.shop_nature = shop_nature;
        this.shop_number = shop_number;
        this.shop_status = shop_status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.shop_save_time_date = shop_save_time_date;
        this.image_count = image_count;
        this.image_location = image_location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public String getRef_number_time_date() {
        return ref_number_time_date;
    }

    public void setRef_number_time_date(String ref_number_time_date) {
        this.ref_number_time_date = ref_number_time_date;
    }

    public String getShop_nature() {
        return shop_nature;
    }

    public void setShop_nature(String shop_nature) {
        this.shop_nature = shop_nature;
    }

    public long getShop_number() {
        return shop_number;
    }

    public void setShop_number(long shop_number) {
        this.shop_number = shop_number;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getShop_save_time_date() {
        return shop_save_time_date;
    }

    public void setShop_save_time_date(String shop_save_time_date) {
        this.shop_save_time_date = shop_save_time_date;
    }

    public int getImage_count() {
        return image_count;
    }

    public void setImage_count(int image_count) {
        this.image_count = image_count;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
    }
}
