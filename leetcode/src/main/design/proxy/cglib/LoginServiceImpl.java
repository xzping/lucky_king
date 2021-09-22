package main.design.proxy.cglib;

public class LoginServiceImpl {
    public void login(String username, String password) {
        System.out.println("Login. username:" + username + ",password:" + password);
    }
}
