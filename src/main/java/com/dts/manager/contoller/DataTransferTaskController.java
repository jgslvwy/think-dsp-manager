package com.dts.manager.contoller;

import com.dts.manager.model.DataTask;
import com.dts.manager.service.DataTransferTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class DataTransferTaskController {
    @Autowired
    private DataTransferTaskService dttTService;

    @GetMapping("/v1/dts/tasks")
    public Page<DataTask> searchDataTask(@RequestParam PageRequest pageRequest) {
        return dttTService.searchDataTask(pageRequest);
    }

    @GetMapping("/v1/dts/tasks/details/{taskId}")
    public void getDataTaskProcess(@RequestParam String taskId) {
        //TODO 根据迁移任务的编号，该任务的详情，共有多少张表、迁移了多少数据、表结构的状态
        return;
    }

    @PostMapping("/v1/dts/tasks/pre-check")
    public String precheck(DataTask task) {
        //TODO 实现数据源提前check
        return "";
    }

    @PutMapping("/v1/dts/tasks/del")
    public Mono<Boolean> delete(@RequestParam String taskId) {
        return dttTService.delete(taskId);
    }

    @PutMapping("/v1/dts/tasks/start")
    public Mono<Boolean> start(@RequestBody DataTask dataTask) {
        return dttTService.start(dataTask);
    }

    @PutMapping("/v1/dts/tasks/stop")
    public void stop() {
        //TODO 任务强制停止
    }

    @PutMapping("/v1/dts/tasks/pause")
    public void pause() {
        //TODO 任务暂停
    }


    @PostMapping("/v1/dts/tasks/add")
    public Mono<DataTask> add(@RequestBody DataTask dataTask) {
        return dttTService.save(dataTask);
    }
}
