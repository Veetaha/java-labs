package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.News;

import java.sql.Timestamp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DAOImplNewsTest extends DAOImplTest {
    final News mockNews = new News(
        42, 32, new Timestamp(2019), 
        "News body!", "c0d291fa-017c-4c9e-aa1b-af22412d269b"
    );

    Object getNewsProp(String name, News news) {
        switch (name) {
            case "creationDate": return news.getCreationDate();
            case "id":           return news.getId();
            case "body":         return news.getBody();
            case "creatorId":    return news.getCreatorId();
            case "promoImgId":   return news.getPromoImgId();
            default: return null;
        }
    }

    @Test
    void getNewsById() throws Exception {
        var newsService = new DAOImpl<>(News.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getNewsProp((String) invokation.getArguments()[0], mockNews)
        );

        assertEquals(mockNews, newsService.getEntity(42));
        assertNull(newsService.getEntity(412));
    }

    @Test
    void getNewsList() throws Exception {
        var newsService = new DAOImpl<>(News.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(newsService.getEntityList().size(), 0);

        when(mockResultSet.getObject(anyString())).thenAnswer(new Answer<Object>() {
            private News[] news = new News[] { mockNews, mockNews };
            private int i;

            public Object answer(InvocationOnMock invokation) {
                return getNewsProp(
                    (String)invokation.getArguments()[0],
                    news[i++ / News.class.getDeclaredFields().length]
                );
            }
        });
        var actual = newsService.getEntityList();
        var expected = List.of(mockNews, mockNews);
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
