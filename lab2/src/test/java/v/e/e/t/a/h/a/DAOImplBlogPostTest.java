package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.BlogPost;

import java.sql.Timestamp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DAOImplBlogPostTest extends DAOImplTest {
    final BlogPost mockNews = new BlogPost(
        42, 32, new Timestamp(2019), 
        "BlogPost body!", 
        "c0d291fa-017c-4c9e-aa1b-af22412d269b",
        "sad"
    );

    Object getBlogPostProp(String name, BlogPost news) {
        switch (name) {
            case "creationDate":  return news.getCreationDate();
            case "id":            return news.getId();
            case "body":          return news.getBody();
            case "creatorId":     return news.getCreatorId();
            case "promoImgId":    return news.getPromoImgId();
            case "promoBlogText": return news.getPromoBlogText();
            default: return null;
        }
    }

    @Test
    void getBlogPostById() throws Exception {
        var blogPostService = new DAOImpl<>(BlogPost.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getBlogPostProp((String) invokation.getArguments()[0], mockNews)
        );

        assertEquals(mockNews, blogPostService.getEntity(42));
        assertNull(blogPostService.getEntity(412));
    }

    @Test
    void getBlogPostList() throws Exception {
        var newsService = new DAOImpl<>(BlogPost.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(newsService.getEntityList().size(), 0);

        when(mockResultSet.getObject(anyString())).thenAnswer(new Answer<Object>() {
            private BlogPost[] news = new BlogPost[] { mockNews, mockNews };
            private int i;

            public Object answer(InvocationOnMock invokation) {
                return getBlogPostProp(
                    (String)invokation.getArguments()[0],
                    news[i++ / (BlogPost.class.getDeclaredFields().length +
                        BlogPost.class.getSuperclass().getDeclaredFields().length
                    )]
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
