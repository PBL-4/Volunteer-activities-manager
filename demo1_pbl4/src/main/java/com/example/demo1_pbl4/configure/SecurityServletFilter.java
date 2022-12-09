//package com.example.demo1_pbl4.configure;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class SecurityServletFilter extends HttpFilter {
//
//    @Override
//    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        UsernamePasswordToken token = extractUsernameAndPasswordFrom(request);  // (1)
//
//        if (notAuthenticated(token)) {  // (2)
//            // either no or wrong username/password
//            // unfortunately the HTTP status code is called "unauthorized", instead of "unauthenticated"
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
//            return;
//        }
//
//        if (notAuthorized(token, request)) { // (3)
//            // you are logged in, but don't have the proper rights
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
//            return;
//        }
//
//        // allow the HttpRequest to go to Spring's DispatcherServlet
//        // and @RestControllers/@Controllers.
//        chain.doFilter(request, response); // (4)
//    }
//
//    private UsernamePasswordToken extractUsernameAndPasswordFrom(HttpServletRequest request) {
//        // Either try and read in a Basic Auth HTTP Header, which comes in the form of user:password
//        // Or try and find form login request parameters or POST bodies, i.e. "username=me" & "password="myPass"
//        return checkVariousLoginOptions(request);
//    }
//
//
//    private boolean notAuthenticated(UsernamePasswordToken token) {
//        // compare the token with what you have in your database...or in-memory...or in LDAP...
//        return false;
//    }
//
//    private boolean notAuthorized(UsernamePasswordToken token, HttpServletRequest request) {
//        // check if currently authenticated user has the permission/role to access this request's /URI
//        // e.g. /admin needs a ROLE_ADMIN , /callcenter needs ROLE_CALLCENTER, etc.
//        return false;
//    }
//}
