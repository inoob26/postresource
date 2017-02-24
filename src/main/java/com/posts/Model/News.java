package com.posts.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Model describe news table
 *
 * @author inoob
 */
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue
    @Column(name = "idnews",nullable = false)
    private long id;

    @Column(name = "newstitle",nullable = false,length = 50)
    private String title;

    @Column(name = "newscontent",nullable = false)
    private String content;

    @Column(name = "newsdate",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "category",nullable = false,length = 80)
    private String category;

    public News(){

    }

    public News(String title, String content, Date date, String category) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", title:'" + title + '\'' +
                ", content:'" + content + '\'' +
                ", date:" + date +
                ", category:'" + category + '\'' +
                '}';
    }

    public long getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
