package io.github.hotspacode.neeza.test.mock;


import io.github.hotspacode.neeza.deputy.annotation.NeezaMock;

/**
 * @description:
 * @author: MoXingwang 2019-07-02 17:28
 **/
@NeezaMock
public class CommonMessageService {

    public String getString(String aa, String ddd) {


        System.out.println("方法未被mock");
        return "OK";
    }


    public int getInt(String aa, String ddd) {


        System.out.println("方法未被mock");
        return 1;
    }

    public void getVoid(String aa, String ddd) {


        System.out.println("方法未被mock");
    }

}
