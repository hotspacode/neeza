package io.github.hotspacode.neeza.test.service;


import io.github.hotspacode.neeza.deputy.annotation.NeezaMock;

@NeezaMock
public class UserTest {
    public void test(){
        System.out.println("没有被mock");
    }
}
