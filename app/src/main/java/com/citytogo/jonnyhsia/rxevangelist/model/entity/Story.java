package com.citytogo.jonnyhsia.rxevangelist.model.entity;

import com.citytogo.jonnyhsia.rxevangelist.helper.Kits;

/**
 * Created by JonnyHsia on 17/10/21.
 * Story POJO
 */
public class Story {

    private String storyId;
    private String title;
    private String content;
    private String author;
    private String images;
    private String createTime;

    @Override
    public String toString() {
        return String.format("ID: %s;\nTitle: %s;\nContent: %s;\nAuthor: %s;\nCreateTime: %s",
                storyId, title, getContentLite(), author, createTime);
    }

    private String getContentLite() {
        String s = Kits.deleteBlankSpace(content);
        if (s.length() > 50) {
            return s.substring(0, 50) + "…";
        }
        return content;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
