package com.example.zaimzanaruddin.derplist.model;

/**
 * Created by zaimzanaruddin on 4/16/17.
 */

public class ListItem {


        private String title;
        private String imageResId;
        private String location;
        private String date;
        private String time;



    public String getlocation() {
        return location;
    }


    public void setlocation(String location) {
        this.location = location;
    }



    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }



    public String getTitle() {
            return title;
        }

    public void setTitle(String title) {
            this.title = title;
        }


    public String getImageResId() {
            return imageResId;
        }

    public void setImageResId(String imageResId) {
            this.imageResId = imageResId;
        }





}
