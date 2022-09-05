package com.ldx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldx.pojo.Actor;
import com.ldx.service.ActorService;
import com.ldx.mapper.ActorMapper;
import com.ldx.utils.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ldx
 * @description 针对表【actor】的数据库操作Service实现
 * @createDate 2022-09-02 09:03:53
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor>
        implements ActorService {

    @Override
    public ResponseResult<List<Actor>> getAllActors(int pageNum, int pageSize) {
        Page<Actor> page = new Page<>(pageNum, pageSize);
        return ResponseResult.okResult(page(page, null).getRecords());
    }
}




