package v.e.e.t.a.h.a;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.News;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 2");
        
        try {
            var newsService = new DAOImpl<>(News.class);
            System.out.println(newsService.getEntity(3));
            System.out.println(newsService.getEntityList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

