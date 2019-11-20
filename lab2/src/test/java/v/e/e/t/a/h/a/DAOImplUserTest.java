package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.User;
import java.time.LocalDate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DAOImplUserTest extends DAOImplTest {
    final User mockUser = new User(
        42,
        "Name",
        "Login",
        LocalDate.of(2019, 11, 20),
        "avatar_id"
    );

    Object getNewsProp(String name, User user) {
        switch (name) {
            case "creationDate": return user.getCreationDate();
            case "id":           return user.getId();
            case "login":        return user.getLogin();
            case "name":         return user.getName();
            case "avatarImgId":  return user.getAvatarImgId();
            default: return null;
        }
    }

    @Test
    void getUserById() throws SQLException {
        var userService = new DAOImpl<>(User.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getNewsProp((String) invokation.getArguments()[0], mockUser)
        );

        assertEquals(userService, userService.getEntity(42));
        assertNull(userService.getEntity(412));
    }

    @Test
    void getUsersList() throws SQLException {
        var newsService = new DAOImpl<>(User.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(newsService.getEntityList().size(), 0);

        var users = new User[] { mockUser, mockUser };
        var iter = Arrays.stream(users).iterator();

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            iter.hasNext()
                ? getNewsProp((String)invokation.getArguments()[0], iter.next())
                : null
        );
        var actual = newsService.getEntityList();
        var expected = List.of(mockUser, mockUser);
        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
