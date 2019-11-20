package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.NewsComment;
import java.time.LocalDate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DAOImplNewsCommentTest extends DAOImplTest {
    final NewsComment mockComment = new NewsComment(
        42,
        324,
        24,
        "baadi",
        LocalDate.of(2019, 11, 20)
    );

    Object getCommentProp(String name, NewsComment comment) {
        switch (name) {
              case "id": return comment.getId();
              case "commentatorId": return comment.getCommentatorId();
              case "newsId": return comment.getNewsId();
              case "body": return comment.getBody();
              case "creationDate": comment.getCreationDate(); 
              default: return null;
        }
    }

    @Test
    void getUserById() throws SQLException {
        var commentsService = new DAOImpl<>(NewsComment.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getCommentProp((String) invokation.getArguments()[0], mockComment)
        );

        assertEquals(commentsService, commentsService.getEntity(42));
        assertNull(commentsService.getEntity(412));
    }

    @Test
    void getUsersList() throws SQLException {
        var commentsService = new DAOImpl<>(NewsComment.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(commentsService.getEntityList().size(), 0);

        var users = new NewsComment[] { mockComment, mockComment };
        var iter = Arrays.stream(users).iterator();

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            iter.hasNext()
                ? getCommentProp((String)invokation.getArguments()[0], iter.next())
                : null
        );
        var actual = commentsService.getEntityList();
        var expected = List.of(mockComment, mockComment);
        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
