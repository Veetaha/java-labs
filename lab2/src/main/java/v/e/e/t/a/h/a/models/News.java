package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.Table;

@Table(name = "news")
public class News {
    @Column(primary = true) private long id;
    @Column private long creatorId;
    @Column private Timestamp creationDate;
    @Column private String body;
    @Column private String promoImgId;

    public News() {}

    public News(
        long id,
        long creatorId,
        Timestamp creationDate,
        String body,
        String promoImgId
    ) {
        this.id = id;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.body = body;
        this.promoImgId = promoImgId;
    }

    public long getId() { return id; }
    public long getCreatorId() { return creatorId; }
    public Timestamp getCreationDate() { return creationDate; }
    public String getBody() { return body; }
    public String getPromoImgId() { return promoImgId; }
    public void setId(long value) { this.id = value; }
    public void setCreatorId(long value) { this.creatorId = value; }
    public void setCreationDate(Timestamp value) { this.creationDate = value; }
    public void setBody(String value) { this.body = value; }
    public void setPromoImgId(String value) { this.promoImgId = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var news = (News) o;
        return Objects.equals(id, news.id)
            && Objects.equals(creatorId, news.creatorId)
            && Objects.equals(creationDate, news.creationDate)
            && Objects.equals(body, news.body)
            && Objects.equals(promoImgId, news.promoImgId);
    }

    @Override
    public String toString() {
        return "News { " +
            "id: " + id +
            ", creatorId: " + creatorId +
            ", creationDate: " + creationDate +
            ", body: " + body +
            ", promoImgId: " + promoImgId +
        " }";
    }
}
