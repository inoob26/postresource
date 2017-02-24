package com.posts.DAO;

import com.posts.Model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by inoob on 23/02/2017.
 */
@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFac;

    public List<News> getNews() {
        Session session = sessionFac.getCurrentSession();

        Query<News> query = session.createQuery("from News ",News.class);

        List<News> news = query.getResultList();

        return news;
    }


    public void saveNews(long id,String title,String content,String category, Date date) {
        Session session = sessionFac.getCurrentSession();

        News news = getNewsById(id);

        news.setTitle(title);
        news.setCategory(category);
        news.setContent(content);
        news.setDate(date);

        session.saveOrUpdate(news);
    }

    public void createNews(News news){
        Session session = sessionFac.getCurrentSession();

        session.saveOrUpdate(news);
    }

    public News getNewsById(long id) {

        Session session = sessionFac.getCurrentSession();

        News news = session.get(News.class, id);

        return news;
    }

    public void deleteNews(long id) {

        Session session = sessionFac.getCurrentSession();

        Query query = session.createQuery("delete from News where id=:id");
        query.setParameter("id",id);

        query.executeUpdate();
    }

    public List<News> findByTitleLike(String title) {

        return executeQuery("from News n where n.title like '%" + title + "%'");
    }

    public List<News> findByCategoryLike(String category) {

        return executeQuery("from News n where n.category like '%" + category + "%'");
    }



    public List<News> findByContentLike(String content) {

        return executeQuery("from News n where n.content like '%" + content + "%'");
    }

    public List<News> executeQuery(String q){
        Session session = sessionFac.getCurrentSession();

        Query query = session.createQuery(q);

        List<News> news = query.getResultList();

        return news;
    }
}
