package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.NewsComment;
import java.sql.Timestamp;

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
        new Timestamp(2019)
    );

    Object getCommentProp(String name, NewsComment comment) {
        switch (name) {
              case "id": return comment.getId();
              case "commentatorId": return comment.getCommentatorId();
              case "newsId": return comment.getNewsId();
              case "body": return comment.getBody();
              case "creationDate": return comment.getCreationDate(); 
              default: return null;
        }
    }

    @Test
    void getUserById() throws Exception {
        var commentsService = new DAOImpl<>(NewsComment.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getCommentProp((String) invokation.getArguments()[0], mockComment)
        );

        assertEquals(mockComment, commentsService.getEntity(42));
        assertNull(commentsService.getEntity(412));
    }

    @Test
    void getUsersList() throws Exception {
        var commentsService = new DAOImpl<>(NewsComment.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(commentsService.getEntityList().size(), 0);

        when(mockResultSet.getObject(anyString())).thenAnswer(new Answer<Object>() {
            private NewsComment[] comments = new NewsComment[] { mockComment, mockComment };
            private int i;

            public Object answer(InvocationOnMock invokation) {
                return getCommentProp(
                    (String)invokation.getArguments()[0],
                    comments[i++ / NewsComment.class.getDeclaredFields().length]
                );
            }
        });
        var actual = commentsService.getEntityList();
        var expected = List.of(mockComment, mockComment);
        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
