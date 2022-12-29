package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired// While running cannot import
    private UserService userService;

    public UserController(UserService userService) {
        super();//?
        this.userService = userService;
    }

    @GetMapping("")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @GetMapping("/insertForm")
    public String showCreateForm(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "insert_user";
    }

    @PostMapping("/insertUser")// Voi bien se dung @RequestParam nhung voi doi tuong thi
    public ModelAndView createUser(@ModelAttribute("myUser") User myUser) {
        if (myUser != null) {
            userService.insertUser(myUser);
        }       // Chỗ này cần phải tối ưu.
        return new ModelAndView("redirect:/users");
    }

    // Trang quản lý tình nguyện viên
    @GetMapping("/admin")
    public String showUserOnAdmin(Model model, HttpSession session) {
        try {
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (user.getRole().getRoleName().equals("ADMIN")) {
                    model.addAttribute("myUser", user);
                    model.addAttribute("users", userService.getAllUsers());
                    return "admin/UserManager";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "500Page";
        }
        return "403Page";
    }

    //search
    @PostMapping("/admin")
    public String View(Model model, @RequestParam("keyword") String keyword, HttpSession session) {
        try {
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (user.getRole().getRoleName().equals("ADMIN")) {
                    model.addAttribute("myUser", user);
                    List<User> listuser = userService.search(keyword);
                    model.addAttribute("users", listuser);
                    model.addAttribute("keyword", keyword);
                    return "admin/UserManager";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "500Page";
        }
        return "403Page";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/admin";
    }


    @GetMapping("/change_password")
    public String showFormChangePassword(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "user/change_password";
        } else {
            return "403Page";
        }
    }

    @PostMapping("/change_password")
    public String changePassword(Model model, @RequestParam("currentPass") String oldPass, @RequestParam("newPass") String newPass,
                                 HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u.getPassword().equals(oldPass)) { // Không dùng == mà dùng equals
            u.setPassword(newPass);
            userService.updateUser(u);// Lưu
            model.addAttribute("successMessage", "Thay đổi mật khẩu thành công. Hãy đăng nhập lại");
            return "homepage/login_form";
        } else {
            model.addAttribute("message", "Mật khẩu cũ không chính xác");
            return "user/change_password";
        }
    }
}






