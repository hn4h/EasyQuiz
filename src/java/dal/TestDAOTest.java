package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Arrays;

import model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class TestDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private QuizDAO quizDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Khởi tạo DAO với connection mock
        quizDAO = new QuizDAO();

        // Inject connection mock vào DBContext bằng Reflection
        Field connectionField = DBContext.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(quizDAO, connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testGetQuizzesBySetId() throws Exception {
        int quizSetId = 1;

        // Giả lập dữ liệu trả về từ ResultSet
        when(resultSet.next()).thenReturn(true, true, false); // Có 2 dòng dữ liệu
        when(resultSet.getInt("Quiz_ID")).thenReturn(101, 102);
        when(resultSet.getInt("Quiz_Set_ID")).thenReturn(1, 1);
        when(resultSet.getString("Quiz_content")).thenReturn("Question 1", "Question 2");

        List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(quizSetId);

        assertEquals(2, quizzes.size()); // Đảm bảo có 2 kết quả

        assertEquals(101, quizzes.get(0).getQuizID());
        assertEquals(1, quizzes.get(0).getQuizSetID());
        assertEquals("Question 1", quizzes.get(0).getContent());

        assertEquals(102, quizzes.get(1).getQuizID());
        assertEquals(1, quizzes.get(1).getQuizSetID());
        assertEquals("Question 2", quizzes.get(1).getContent());

        // Kiểm tra xem có gọi đúng câu lệnh SQL không
        verify(preparedStatement, times(1)).setInt(1, quizSetId);
        verify(preparedStatement, times(1)).executeQuery();
    }
}