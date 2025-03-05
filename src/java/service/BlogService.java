/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.BlogDAO;
import java.util.List;
import model.Blog;

/**
 *
 * @author DUCA
 */
public class BlogService {
    private BlogDAO blogDAO = new BlogDAO();

    public List<Blog> getAllBlogs() {
        return blogDAO.getAllBlogs();
    }
}
