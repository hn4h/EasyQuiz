package dal;

import java.util.List;
import model.QuizSet;
public class QuizSetHistoryDAO extends DBContext {
    public List<QuizSet> getQuizSetHistoryByUserName(String userName) {
        List<QuizSet> list = new ArrayList<>();
        Strign sql = "SELECT * FROM Quiz_Set_History WHERE userName = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while(rs.next()){
                QuizSet qs = new QuizSet();
                qs.setQuizSetID(rs.getInt("quizSetID"));
                qs.setQuizSetName(rs.getString("quizSetName"));
                qs.setDescription(rs.getString("description"));
                qs.setAuthor(rs.getString("author"));
                list.add(qs);
            }
            return list;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    //Get popular quiz set
}
