package examples.timeline.timeline.utils;

import examples.timeline.timeline.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
    }
}