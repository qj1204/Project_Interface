package com.xiaoxin.projectinterface.controller;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片处理控制器
 *
 * @author 14290
 */
@RestController
@RequestMapping("/document")
public class ImageController {

    @PostMapping("/saveImage")
    public ResultVO<String> saveImage(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("type") String type,
            @RequestParam("id") String id
    ) {
        String s = Utils.saveImage(photo, type, id);
        if (s != null) {
            return ResultVO.successResponse(s);
        }
        return ResultVO.failedResponse("操作失败");
    }

}
