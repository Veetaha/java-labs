package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.Table;

@Table(name = "news")
public class News extends Publication {

    @Column private boolean isBreakingNews;
    @Column private int importanceDegree;

    public News() {}

    public News(
        long id,
        long creatorId,
        Timestamp creationDate,
        String body,
        boolean isBreakingNews,
        int importanceDegree
    ) {
        super(id, creatorId, creationDate, body);
        this.isBreakingNews = isBreakingNews;
        this.importanceDegree = importanceDegree;
    }

    public boolean isBreakingNews() { return this.isBreakingNews; }
    public int getImportanceDegree() { return this.importanceDegree; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof News)) return false;
        var news = (News) o;

        return super.equals(o) 
            && Objects.equals(isBreakingNews, news.isBreakingNews)
            && Objects.equals(importanceDegree, news.importanceDegree);
    }

    @Override
    public String toString() {
        return "News { " +
            "id: " + this.getId() +
            ", creatorId: " + this.getCreatorId() +
            ", creationDate: " + this.getCreationDate() +
            ", body: " + this.getBody() +
            ", isBreakingNews: " + this.isBreakingNews +
            ", importanceDegree: " + this.importanceDegree +
        " }";
    }
}
