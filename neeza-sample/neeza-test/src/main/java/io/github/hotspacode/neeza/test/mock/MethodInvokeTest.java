package io.github.hotspacode.neeza.test.mock;

public class MethodInvokeTest {
    public static void main(String[] args) {
        MethodInvokeTest methodInvokeTest = new MethodInvokeTest();
        methodInvokeTest.test1();
        System.out.println(1);
    }

    public void test1(){
        test2();
    }
    public void test2(){
        test3(null);
    }
    public void test3(String a ){
        test4();
    }
    public String test4(){

        return "OK";
    }
}
