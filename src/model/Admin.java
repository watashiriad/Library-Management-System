package model;

        public class Admin extends User {

        private static final long serialVersionUID = 1L;

        public Admin(String username, String passwordHash) {
        super(username, passwordHash);
     }
        @Override
        public void showRole() {
         System.out.println("Logged in as: ADMIN");
    }
}