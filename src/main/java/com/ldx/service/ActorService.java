package com.ldx.service;

import com.ldx.pojo.Actor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldx.utils.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ldx
* @description 针对表【actor】的数据库操作Service
* @createDate 2022-09-02 09:03:53
*/

public interface ActorService extends IService<Actor> {

    ResponseResult<List<Actor>> getAllActors(int pageNum, int pageSize);
}
