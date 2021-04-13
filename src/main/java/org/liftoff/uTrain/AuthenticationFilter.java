package org.liftoff.uTrain;

import org.liftoff.uTrain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.liftoff.uTrain.controllers.AuthenticationController;
public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/reg", "/logout", "/css",
            "/styles.css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot: whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            //returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        //the user is logged in
        if (user != null) {
            return true;
        }

        //the user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }
}
