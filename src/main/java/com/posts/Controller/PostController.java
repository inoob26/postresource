package com.posts.Controller;

import com.google.gson.Gson;
import com.posts.Model.News;
import com.posts.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author inoob
 */
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private NewsService newsService;

    /**
     * Method return all news
     *
     *
     * @return List of News as json
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String listNews(){
        List<News> news = newsService.getNews();

        Gson gson = new Gson();

        return gson.toJson(news);
    }

    /**
     * Create news
     *
     *
     * @param title for News
     * @param content news text
     * @param category news
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public void createNews(@RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("category") String category){
        Date date1 = Calendar.getInstance().getTime();
        System.out.println("Title: " + title +" content: " + content + " category " + category + " date: " + date1.toString());
        newsService.createNews(new News(title,content,date1,category));
    }

    /**
     * Update News
     *
     * @param id
     * @param title for News
     * @param content news text
     * @param category news
     */
    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    @ResponseBody
    public void saveNews(@RequestParam("id") String id,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("category") String category){
        Date date1 = Calendar.getInstance().getTime();
        System.out.println("id: "+ id + "Title: " + title +" content: " + content + " category " + category + " date: " + date1.toString());
        newsService.saveNews(id,title,content,category,date1);
    }

    /**
     * Get news by id
     * @param id
     * @return one news as json
     */
    @RequestMapping(value = "/getnews",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getNewsById(@RequestParam("id") String id){
        Gson gson = new Gson();
        return gson.toJson(newsService.getNewsById(Long.valueOf(id)));
    }

    /**
     * Get news where title like value of search field
     *
     * @param title
     * @return List of news as json
     */
    @RequestMapping(value = "/getNewsByTitle",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getNewsByTitle(@RequestParam("title") String title){
        Gson gson = new Gson();
        return gson.toJson(newsService.findByTitleLike(title));
    }


    /**
     * Get news where content like value of search field
     *
     * @param content
     * @return List of news as json
     */
    @RequestMapping(value = "/getContentLike",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getContentLike(@RequestParam("content") String content){
        Gson gson = new Gson();
        return gson.toJson(newsService.findByContentLike(content));
    }

    /**
     * Get news where category like value of search field
     *
     * @param category
     * @return List of news as json
     */
    @RequestMapping(value = "/getCategoryLike",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getCategoryLike(@RequestParam("category") String category){
        Gson gson = new Gson();
        return gson.toJson(newsService.findByCategoryLike(category));
    }

    /**
     * Delete news by id
     *
     * @param id
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestParam("id") String id){
        newsService.deleteNews(Long.valueOf(id));
    }

    /**
     *
     * @return main page
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(){
        return "index";
    }
}
