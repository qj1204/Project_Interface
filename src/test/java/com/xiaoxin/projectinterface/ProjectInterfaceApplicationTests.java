package com.xiaoxin.projectinterface;

import com.xiaoxin.projectinterface.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectInterfaceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testIdentify(){
        String path1 = "src/main/resources/static/image/face/12.png";
        String path2 = "src/main/resources/static/image/check/12_60.png";
        System.out.println(Utils.doIdentify(path1,path2));
    }

    @Test
    void testRename(){
        String s = Utils.renameImage("image/avatars/course-default.png","c25");
        System.out.println(s);
    }
}
