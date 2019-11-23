package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.Table;

@Table(name = "blog_posts")
public class BlogPost extends Publication {
    @Column private String promoImgId;
    @Column private String promoBlogText;


    public BlogPost() {}

    public BlogPost(
        long id,
        long creatorId,
        Timestamp creationDate,
        String body,
        String promoImgId,
        String promoBlogText
    ) {
        super(id, creatorId, creationDate, body);
        this.promoImgId = promoImgId;
        this.promoBlogText = promoBlogText;
    }

    public String getPromoBlogText() { return this.promoBlogText; }
    public void setPromoBlogText(String value) { this.promoBlogText = value; }

    public String getPromoImgId() { return this.promoImgId; }
    public void setPromoImgId(String value) { this.promoImgId = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BlogPost)) return false;
        var blogPost = (BlogPost) o;

        return super.equals(o) 
            && Objects.equals(promoImgId, blogPost.promoImgId)
            && Objects.equals(promoBlogText, blogPost.promoBlogText);
    }
}
