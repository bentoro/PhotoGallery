package com.example.ben.gallery;

/**
 * Created by Ben on 2017-09-17.
 */

public class imgGallery {
    private String image;
    private String caption;
    private String location;
    private String time;

    public imgGallery(String image, String caption, String location, String time){
        this.image = image;
        this.caption = caption;
        this.location = location;
        this.time = time;
    }

    public String getImage(){
        return this.image;
    }

    public String getCaption(){
        return this.caption;
    }

    public String getLocation(){
        return this.location;
    }

    public String getTime(){
        return this.time;
    }

}
