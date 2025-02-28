package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.QuizSet;
public class QuizSetHistoryDAO extends DBContext {
    public List<QuizSet> getQuizSetHistoryByUserName(String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT * FROM Quiz_Set_History WHERE userName = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                QuizSet qs = new QuizSet();
                qs.setQuizSetId(rs.getInt("quizSetID"));
                qs.setQuizSetName(rs.getString("quizSetName"));
                qs.setQuizSetDescription(rs.getString("description"));
                qs.setUserName(rs.getString("author"));
                list.add(qs);
            }
            return list;
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    //Get popular quiz set
}
