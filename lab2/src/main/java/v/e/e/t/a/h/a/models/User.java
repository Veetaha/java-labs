package v.e.e.t.a.h.a.models;

import java.sql.Timestamp;
import java.util.Objects;

import v.e.e.t.a.h.a.veeorm.annotations.Column;
import v.e.e.t.a.h.a.veeorm.annotations.Table;

@Table(name = "users")
public class User {
    @Column(primary = true) private long id;
    @Column private String name;
    @Column private String login;
    @Column private Timestamp creationDate;
    @Column private String avatarImgId;

    public User() { }

    public User(
        long id,
        String name,
        String login,
        Timestamp creationDate,
        String avatarImgId
    ) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.creationDate = creationDate;
        this.avatarImgId = avatarImgId;
    }

    public long getId() {  return id; }
    public void setId(long value) { this.id = value; }
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
    public String getLogin() { return login; }
    public void setLogin(String value) { this.login = value; }
    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp value) { this.creationDate = value; }
    public String getAvatarImgId() { return avatarImgId; }
    public void setAvatarImgId(String value) { this.avatarImgId = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
            && Objects.equals(name, user.name)
            && Objects.equals(login, user.login)
            && Objects.equals(creationDate, user.creationDate)
            && Objects.equals(avatarImgId, user.avatarImgId)
        ;
    }
}
