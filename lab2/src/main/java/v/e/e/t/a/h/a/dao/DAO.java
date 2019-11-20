package v.e.e.t.a.h.a.dao;

import v.e.e.t.a.h.a.models.*;

import java.util.List;

public class DAO {
    IDAOImpl<News> newsRepo;
    IDAOImpl<User> userRepo;
    IDAOImpl<NewsRating> newsRatingRepo;
    IDAOImpl<NewsComment> newsCommentRepo;

    public DAO(
        IDAOImpl<News> newsRepo,
        IDAOImpl<User> userRepo,
        IDAOImpl<NewsRating> newsRatingRepo,
        IDAOImpl<NewsComment> newsCommentRepo
    ) {
        this.newsRepo = newsRepo;
        this.userRepo = userRepo;
        this.newsRatingRepo = newsRatingRepo;
        this.newsCommentRepo = newsCommentRepo;
    }

    News getNews(long id) { return newsRepo.getEntity(id); }
    List<News> getNewsList() { return newsRepo.getEntityList(); }
    User getUser(long id) { return userRepo.getEntity(id); }
    List<User> getUserList() { return userRepo.getEntityList(); }
    NewsRating getNewsRating(long id) { return newsRatingRepo.getEntity(id); }
    List<NewsRating> getNewsRatingList() { return newsRatingRepo.getEntityList(); }
    NewsComment getNewsComment(long id) { return newsCommentRepo.getEntity(id); }
    List<NewsComment> getNewsCommentList() { return newsCommentRepo.getEntityList(); }
}
