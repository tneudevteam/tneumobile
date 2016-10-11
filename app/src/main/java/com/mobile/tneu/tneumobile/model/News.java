package com.mobile.tneu.tneumobile.model;

/**
 * Created by underhand on 24.09.2016.
 */
public class News {

  private String title;
  private String date;
  private String topic;
  private String imageLink;
  private String description;
  private String readMoreLink;

  public News() {
  }

  public News(String title, String date, String topic, String imageLink, String description, String readMoreLink) {
    this.title = title;
    this.date = date;
    this.topic = topic;
    this.imageLink = imageLink;
    this.description = description;
    this.readMoreLink = readMoreLink;
  }

  public String getTitle() {
    return title;
  }

  public String getDate() {
    return date;
  }

  public String getTopic() {
    return topic;
  }

  public String getImageLink() {
    return imageLink;
  }

  public String getDescription() {
    return description;
  }

  public String getReadMoreLink() {
    return readMoreLink;
  }


}

