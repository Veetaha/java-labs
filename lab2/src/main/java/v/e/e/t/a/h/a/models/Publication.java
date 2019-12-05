package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.MappedSuperclass;

@MappedSuperclass
public abstract class Publication {
    @Column(primary = true) private long id;
    @Column private long creatorId;
    @Column private Timestamp creationDate;
    @Column private String body;


    public Publication() {}

    public Publication(
        long id,
        long creatorId,
        Timestamp creationDate,
        String body
    ) {
        this.id = id;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.body = body;
    }


    public long getId() { return id; }
    public long getCreatorId() { return creatorId; }
    public Timestamp getCreationDate() { return creationDate; }
    public String getBody() { return body; }
    
    public void setId(long value) { this.id = value; }
    public void setCreatorId(long value) { this.creatorId = value; }
    public void setCreationDate(Timestamp value) { this.creationDate = value; }
    public void setBody(String value) { this.body = value; }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Publication)) return false;
        var news = (Publication) o;
        return Objects.equals(id, news.id)
            && Objects.equals(creatorId, news.creatorId)
            && Objects.equals(creationDate, news.creationDate)
            && Objects.equals(body, news.body);
    }
}
