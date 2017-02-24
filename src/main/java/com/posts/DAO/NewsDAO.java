package com.posts.DAO;

import com.posts.Model.News;

import java.util.Date;
import java.util.List;

/**
 * Created by inoob on 23/02/2017.
 */
public interface NewsDAO {
    public List<News> getNews();
    public void saveNews(long id,String title,String content,String category, Date date);
    public void createNews(News news);
    public News getNewsById(long id);
    public void deleteNews(long id);
    public List<News> findByTitleLike(String title);
    public List<News> findByCategoryLike(String category);
    public List<News> findByContentLike(String content);
}
