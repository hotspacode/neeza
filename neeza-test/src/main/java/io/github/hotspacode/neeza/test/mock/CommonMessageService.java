package io.github.hotspacode.neeza.test.mock;


import io.github.hotspacode.neeza.deputy.annotation.NeezaMock;
import io.github.hotspacode.neeza.deputy.core.MockSpy;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: MoXingwang 2019-07-02 17:28
 **/
@NeezaMock
public class CommonMessageService {

    public String getString (String aa,String ddd){


        System.out.println("方法未被mock");
        return "OK";
    }



}
