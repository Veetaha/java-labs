package v.e.e.t.a.h.a.models;

import java.util.Objects;

public class NewsRating {
    long id;
    long raterId;
    long newsId;
    boolean hasLiked;

    public NewsRating() {}

    public NewsRating(
        long id,
        long raterId,
        long newsId,
        boolean hasLiked
    ) {
        this.id = id;
        this.raterId = raterId;
        this.newsId = newsId;
        this.hasLiked = hasLiked;
    }

    public long getId() { return id; }
    public void setId(long value) { id = value; }
    public long getRaterId() { return raterId; }
    public void setRaterId(long value) { raterId = value; }
    public long getNewsId() { return newsId; }
    public void setNewsId(long value) { newsId = value; }
    public boolean getHasLiked() { return hasLiked; }
    public void setHasLiked(boolean value) { hasLiked = value; }    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var rating = (NewsRating) o;
        return Objects.equals(raterId, rating.raterId)
            && Objects.equals(newsId, rating.newsId)
            && Objects.equals(hasLiked, rating.hasLiked);
    }
}
