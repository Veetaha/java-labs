package v.e.e.t.a.h.a;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.News;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 2");
        
        try {
            var newsService = new DAOImpl<>(News.class);
            var entity = newsService.getEntity(1);

            System.out.println(entity.toString());
            System.out.println(newsService.getEntityList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

