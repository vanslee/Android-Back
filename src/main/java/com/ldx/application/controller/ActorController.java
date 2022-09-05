package com.ldx.application.controller;

import com.ldx.pojo.Actor;
import com.ldx.service.ActorService;
import com.ldx.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("actor")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping("{pageNum}/{pageSize}")
    public ResponseResult<List<Actor>> getList(@PathVariable int pageNum, @PathVariable int pageSize) {
        return actorService.getAllActors(pageNum, pageSize);
    }
}
