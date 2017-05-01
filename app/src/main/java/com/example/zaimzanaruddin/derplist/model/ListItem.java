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
        private String description;
        private int likes;



    public int getLikes() {return likes;}

    public void setLikes(int likes){
        this.likes =likes;
    }

    public void upVote(){
        this.likes++;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

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
