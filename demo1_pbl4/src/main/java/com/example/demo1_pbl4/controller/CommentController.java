package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Comment;
import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.CommentService;
import com.example.demo1_pbl4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @PostMapping("/create_comments")
    public String makeNewComment(Model model, @RequestParam("postId") Long postId, @RequestParam(value = "commentContent") String content,
                                 HttpSession session) {
        System.out.println("postId" + postId);
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Comment comment = new Comment();
            comment.setUser(user);
            Post post = postService.getPostById(postId);
            comment.setPost(post);
            System.out.println("postId: " + post.getPostId());
            comment.setCommentDate(new Date(System.currentTimeMillis()));
            comment.setCommentContent(content);
            commentService.insertComment(comment);
            return "redirect:/posts/get?id=" + post.getPostId();
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/delete")
    public String deleteComment(Model model, @RequestParam(value = "commentId") Long commentId) {
        Comment cmt = commentService.getCommentById(commentId);
        Post post = cmt.getPost();
        commentService.deleteComment(commentId);
        return "redirect:/posts/get?id=" + post.getPostId();
    }
}
