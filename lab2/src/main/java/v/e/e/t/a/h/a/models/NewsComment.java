package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.Table;

@Table(name = "news_comments")
public class NewsComment {
    @Column(primary = true) private long id;
    @Column private long commentatorId;
    @Column private long newsId;
    @Column private String body;
    @Column private Timestamp creationDate;

    public NewsComment() {}

    public NewsComment(
        long id,
        long commentatorId,
        long newsId,
        String body,
        Timestamp creationDate
    ) {
        this.id = id;
        this.commentatorId = commentatorId;
        this.newsId = newsId;
        this.body = body;
        this.creationDate = creationDate;

    }
    public long getId() { return id; }
    public void setId(long value) { id = value; }
    public long getNewsId() { return newsId; }
    public void setNewsId(long value) { newsId = value; }
    public String getBody() { return body; }
    public void setBody(String value) { body = value; }
    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp value) { creationDate = value; }
    public long getCommentatorId() { return commentatorId; }
    public void setCommentatorId(long value) { commentatorId = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var comment = (NewsComment) o;
        return Objects.equals(id, comment.id)
            && Objects.equals(commentatorId, comment.commentatorId)
            && Objects.equals(newsId, comment.newsId)
            && Objects.equals(body, comment.body)
            && Objects.equals(creationDate, comment.creationDate);
    }
}
