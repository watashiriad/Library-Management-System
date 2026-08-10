package service;

import model.Admin;
import model.Member;
import model.User;
import util.PasswordUtil;

        import java.util.ArrayList;


        public class AuthService {

             private ArrayList<User> users;


         public AuthService() {

        users = new ArrayList<>();

         users.add(
                new Admin(
                        "admin", PasswordUtil.hashPassword("1234"))
        );
        users.add(
                new Member(
                        "member", PasswordUtil.hashPassword("1234")
                )
        );
     }


        public User login(
            String username,
            String password) {

        String passwordHash =
                PasswordUtil.hashPassword(password);

        for (User user : users) {

            if (user.getUsername()
                    .equals(username)
                    && user.getPasswordHash()
                    .equals(passwordHash)) {

                return user;
            }
        }

        return null;
    }
}