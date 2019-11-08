package io.github.hotspacode.neeza.test.mock;


import io.github.hotspacode.neeza.core.annotation.NeezaMock;

/**
 * @description:
 * @author: MoXingwang 2019-07-02 17:28
 **/
@NeezaMock
public class CommonMessageService {

    public String getString (){
        System.out.println("方法未被mock");
        return "OK";
    }



}
