package service;

import dal.AccountDAO;
import dal.BlogDAO;
import dal.QuizSetDAO;
import dal.QuizSetHistoryDAO;
import model.Blog;
import model.Creator;
import model.QuizSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class HomeServiceTest {
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
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetPopularQuizSet() {
        List<QuizSet> expectedQuizSets = Arrays.asList(new QuizSet(), new QuizSet());
        when(quizSetDAO.getPopularQuizSet()).thenReturn(expectedQuizSets);

        List<QuizSet> actualQuizSets = homeService.getPopularQuizSet();

        assertEquals(expectedQuizSets, actualQuizSets);
        verify(quizSetDAO, times(1)).getPopularQuizSet();
    }

    @Test
    void testGetPopularBlog() {
        List<Blog> expectedBlogs = Arrays.asList(new Blog(), new Blog());
        when(blogDAO.getPopularBlog()).thenReturn(expectedBlogs);

        List<Blog> actualBlogs = homeService.getPopularBlog();

        assertEquals(expectedBlogs, actualBlogs);
        verify(blogDAO, times(1)).getPopularBlog();
    }

    @Test
    void testGetTopAuthors() {
        List<Creator> expectedCreators = Arrays.asList(new Creator(), new Creator());
        when(accountDAO.getTopQuizSetCreator()).thenReturn(expectedCreators);

        List<Creator> actualCreators = homeService.getTopAuthors();

        assertEquals(expectedCreators, actualCreators);
        verify(accountDAO, times(1)).getTopQuizSetCreator();
    }

    @Test
    void testGetQuizSetHistoryTop4ByUserName() {
        String userName = "testUser";
        List<QuizSet> expectedQuizSets = Arrays.asList(new QuizSet(), new QuizSet());
        when(quizSetHistoryDAO.getQuizSetHistoryTop4ByUserName(userName)).thenReturn(expectedQuizSets);

        List<QuizSet> actualQuizSets = homeService.getQuizSetHistoryTop4ByUserName(userName);

        assertEquals(expectedQuizSets, actualQuizSets);
        verify(quizSetHistoryDAO, times(1)).getQuizSetHistoryTop4ByUserName(userName);
    }
}