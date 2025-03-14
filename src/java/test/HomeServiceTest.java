package test;

import dal.AccountDAO;
import dal.BlogDAO;
import dal.QuizSetDAO;
import dal.QuizSetHistoryDAO;
import model.Blog;
import model.Creator;
import model.QuizSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.HomeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    @Mock
    private QuizSetDAO quizSetDAO;

    @Mock
    private BlogDAO blogDAO;

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private QuizSetHistoryDAO quizSetHistoryDAO;

    @InjectMocks
    private HomeService homeService;

    private List<QuizSet> mockQuizSets;
    private List<Blog> mockBlogs;
    private List<Creator> mockCreators;

    @BeforeEach
    void setUp() {
        mockQuizSets = Arrays.asList(new QuizSet(), new QuizSet());
        mockBlogs = Arrays.asList(new Blog(), new Blog());
        mockCreators = Arrays.asList(new Creator(), new Creator());
    }

    @Test
    void testGetPopularQuizSet() {
        when(quizSetDAO.getPopularQuizSet()).thenReturn(mockQuizSets);
        List<QuizSet> result = homeService.getPopularQuizSet();
        assertEquals(2, result.size(), "Expected 2 quiz sets but got " + result.size());
    }

    @Test
    void testGetPopularBlog() {
        when(blogDAO.getPopularBlog()).thenReturn(mockBlogs);
        List<Blog> result = homeService.getPopularBlog();
        assertEquals(2, result.size(), "Expected 2 blogs but got " + result.size());
    }

    @Test
    void testGetTopAuthors() {
        when(accountDAO.getTopQuizSetCreator()).thenReturn(mockCreators);
        List<Creator> result = homeService.getTopAuthors();
        assertEquals(2, result.size(), "Expected 2 authors but got " + result.size());
    }

    @Test
    void testGetQuizSetHistoryTop4ByUserName() {
        String userName = "testUser";
        when(quizSetHistoryDAO.getQuizSetHistoryTop4ByUserName(userName)).thenReturn(mockQuizSets);
        List<QuizSet> result = homeService.getQuizSetHistoryTop4ByUserName(userName);
        assertEquals(2, result.size(), "Expected 2 quiz sets in history but got " + result.size());
    }
}
