package design.proxy.dynamic;

public class ReadSubjectA implements SubjectA {
    @Override
    public void setUser(String username, String password) {
        System.out.println("set user. userName:" + username + ",password:" + password);
    }
}
