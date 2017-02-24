package com.posts.Service;

import com.posts.Model.News;
import com.posts.DAO.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author inoob
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;


    @Transactional
    public List<News> getNews() {
        return newsDAO.getNews();
    }

    @Transactional
    public void saveNews(String id,String title,String content,String category, Date date){
        newsDAO.saveNews(Long.valueOf(id),title,content,category,date);
    }

    @Transactional
    public void createNews(News news) {
        newsDAO.createNews(news);
    }

    @Transactional
    public News getNewsById(long id) {
        return newsDAO.getNewsById(id);
    }

    @Transactional
    public void deleteNews(long id) {
        newsDAO.deleteNews(id);
    }

    @Transactional
    public List<News> findByTitleLike(String title) {
        return newsDAO.findByTitleLike(title);
    }

    @Transactional
    public List<News> findByCategoryLike(String category) {
        return newsDAO.findByCategoryLike(category);
    }

    @Transactional
    public List<News> findByContentLike(String content) {
        return newsDAO.findByContentLike(content);
    }

}
