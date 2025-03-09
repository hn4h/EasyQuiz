/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountDAO;
import dal.BlogDAO;
import dal.QuizSetDAO;
import dal.QuizSetHistoryDAO;
import java.util.List;
import model.Blog;
import model.Creator;
import model.QuizSet;

/**
 *
 * @author 11
 */
public class HomeService {
    public List<QuizSet> getPopularQuizSet(){
       QuizSetDAO qsd = new QuizSetDAO();
         return qsd.getPopularQuizSet(); 
    }
    public List<Blog> getPopularBlog(){
        BlogDAO bd = new BlogDAO();
        return bd.getPopularBlog();
    }
    public List<Creator> getTopAuthors(){
        AccountDAO ad = new AccountDAO();
        return ad.getTopQuizSetCreator();
    }
    public List<QuizSet> getQuizSetHistoryTop4ByUserName(String userName){
        QuizSetHistoryDAO qshd = new QuizSetHistoryDAO();
        return qshd.getQuizSetHistoryTop4ByUserName(userName);
    }
    public void Main(){
        System.out.println(System.getenv(""));
    }
}
