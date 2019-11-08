package top.moxingwang.simplemock.sample.service;

public class UserTest2 {
    public static int i = 0;
    public void test() {
        System.out.println("没有被mock");
    }

    public int add() {
        return i=i+1;
    }
}
