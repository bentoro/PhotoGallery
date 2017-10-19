package com.example.ben.gallery;

/**
 * Created by Ben on 2017-09-17.
 */

public class imgGallery {
    private int id;
    private String image;
    private int year;
    private int day;
    private int month;
    private String caption;
    private String location;
    //private String caption;
    //private String location;

    public imgGallery(){

    }

    public imgGallery(int id,String location, String caption,String image,int year,int month,int day){
        this.id = id;
        this.location = location;
        this.caption = caption;
        this.image = image;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getId(){
        return this.id;
    }
    public String getLocation(){

        return this.location;
    }
    public String getCaption(){

        return this.caption;
    }

    public String getImage(){

        return this.image;
    }

    public int getYear(){

        return this.year;
    }

    public int getMonth(){

        return this.month;
    }

    public int getDay(){

        return this.day;
    }


    public void setYear(int year){

         this.year = year;
    }

    public void setId(int id){
         this.id = id;
    }
    public void setLocation(String location){

        this.location = location;
    }
    public void setCaption(String caption){

        this.caption = caption;
    }
    public void setImage(String image){

        this.image = image;
    }

    public void setMonth(int month){

        this.month = month;
    }

    public void setDay(int day){

        this.day = day;
    }


}
