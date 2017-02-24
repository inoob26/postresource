package com.posts.Service;

import com.posts.Model.News;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author inoob
 */
@Service
public interface NewsService {
    public List<News> getNews();
    public void saveNews(String id,String title,String content,String category, Date date);
    public void createNews(News news);
    public News getNewsById(long id);
    public void deleteNews(long id);
    public List<News> findByTitleLike(String title);
    public List<News> findByCategoryLike(String category);
    public List<News> findByContentLike(String content);
}
