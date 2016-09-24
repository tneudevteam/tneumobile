package com.mobile.tneu.tneumobile.model;

/**
 * Created by underhand on 24.09.2016.
 */
public class News {

    private String mTitle;
    private String mDate;
    private String mTopic;
    private String mImageLink;
    private String mDescription;
    private String mReadMoreLink;

    public News(){}

    public News(String title, String date, String topic, String imageLink, String description, String readMoreLink) {
        this.mTitle = title;
        this.mDate = date;
        this.mTopic = topic;
        this.mImageLink = imageLink;
        this.mDescription = description;
        this.mReadMoreLink = readMoreLink;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getTopic() {
        return mTopic;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getReadMoreLink() {
        return mReadMoreLink;
    }


}

