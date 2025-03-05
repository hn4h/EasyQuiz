<%-- 
    Document   : search_blog
    Created on : Mar 6, 2025, 1:31:52 AM
    Author     : DUCA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <link rel="stylesheet" href="./blog/blog.css">
</head>
<body>
    <div class="body-container">
        <h1>Search Results for "${keyword}"</h1>
        <c:choose>
            <c:when test="${empty blogs}">
                <p>No blogs found for "<strong>${keyword}</strong>". Try searching again!</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="blog" items="${blogs}">
                    <div class="blog-card">
                        <h2>${blog.blogTitle}</h2>
                        <p>${blog.blogContent}</p>
                        <span>By ${blog.author.userName} - Created ${blog.createdDate}</span>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <a href="blog.jsp">Back to Blogs</a>
    </div>
</body>
</html>
