package v.e.e.t.a.h.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import v.e.e.t.a.h.a.dao.DAOImpl;
import v.e.e.t.a.h.a.models.NewsRating;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DAOImplNewsRatingTest extends DAOImplTest {
    final NewsRating mockRating = new NewsRating(
        42,
        324,
        24,
        false
    );

    Object getRatingProp(String name, NewsRating rating) {
        switch (name) {
            case "id": return rating.getId();
            case "raterId": return rating.getRaterId();
            case "newsId": return rating.getNewsId();
            case "hasLiked": return rating.getHasLiked();
            default: return null;
        }
    }

    @Test
    void getNewsRatingById() throws Exception {
        var ratingService = new DAOImpl<>(NewsRating.class, mockConnection);

        when(mockResultSet.next()).thenReturn(true, false);

        when(mockResultSet.getObject(anyString())).thenAnswer(invokation ->
            getRatingProp((String) invokation.getArguments()[0], mockRating)
        );

        assertEquals(mockRating, ratingService.getEntity(42));
        assertNull(ratingService.getEntity(412));
    }

    @Test
    void getNewsRatingsList() throws Exception {
        var ratingService = new DAOImpl<>(NewsRating.class, mockConnection);

        when(mockResultSet.next()).thenReturn(false, true, true, false);

        assertEquals(ratingService.getEntityList().size(), 0);

        when(mockResultSet.getObject(anyString())).thenAnswer(new Answer<Object>() {
            private NewsRating[] ratings = new NewsRating[] { mockRating, mockRating };
            private int i;

            public Object answer(InvocationOnMock invokation) {
                return getRatingProp(
                    (String)invokation.getArguments()[0],
                    ratings[i++ / NewsRating.class.getDeclaredFields().length]
                );
            }
        });
        var actual = ratingService.getEntityList();
        var expected = List.of(mockRating, mockRating);
        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
