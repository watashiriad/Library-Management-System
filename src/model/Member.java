package model;

public class Member extends User {

    private static final long serialVersionUID = 1L;


    public Member(String username, String passwordHash) {
        super(username, passwordHash);
    }

    @Override
    public void showRole() {
        System.out.println("Logged in as: MEMBER");
    }
}