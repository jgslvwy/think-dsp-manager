package com.dts.manager.service.impl;

import com.dts.core.factory.ProcessorFactory;
import com.dts.core.factory.WarehouseFactory;
import com.dts.core.model.Event;
import com.dts.core.model.ProcessorEvent;
import com.dts.manager.common.RunState;
import com.dts.manager.config.ThreadPoolConfig;
import com.dts.manager.dao.DataTransferTaskRepository;
import com.dts.manager.model.DataSourceConfig;
import com.dts.manager.model.DataTask;
import com.dts.manager.service.DataTransferTaskService;
import com.dts.manager.util.JdbcUtils;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

@Service
public class DataTransferTaskServiceImpl implements DataTransferTaskService {

  @Autowired
  ThreadPoolConfig threadPoolConfig;

  @Autowired
  ApplicationContext applicationContext;

  @Autowired
  private DataTransferTaskRepository dataTransferTaskRepository;

  @Override
  public Mono<DataTask> save(DataTask dataTask) {
    return Mono.just(dataTransferTaskRepository.save(dataTask));
  }

  @Override
  public Page<DataTask> searchDataTask(PageRequest pageRequest) {
    return dataTransferTaskRepository.findAll(pageRequest);
  }

  @Override
  public Mono<Boolean> delete(String taskId) {
    DataTask dataTask = dataTransferTaskRepository.findById(taskId).get();
    Assert.notNull(dataTask, "this task is null");
    boolean determineIfDelete = Objects.equals(dataTask.getRunState(), RunState.RUNING) || Objects
        .equals(dataTask.getRunState(), RunState.END);
    if (determineIfDelete) {
      throw new RuntimeException("this task can't delete");
    }
    dataTransferTaskRepository.deleteById(taskId);
    return Mono.just(Boolean.TRUE);
  }

  @Override
  public Mono<Boolean> start(DataTask dataTask) {
    boolean determine = Objects.equals(dataTask.getRunState(), RunState.RUNING) || Objects
        .equals(dataTask.getRunState(), RunState.END);
    if (determine) {
      throw new RuntimeException("this task can't start");
    } else {
      dataTask.setRunState(RunState.RUNING);
      if (!JdbcUtils.isConnSuccess(dataTask.getTargetConfig())) {
        throw new RuntimeException("target datasource connect fail");
      }
      if (!JdbcUtils.isConnSuccess(dataTask.getSourceConfig())) {
        throw new RuntimeException("source datasource connect fail");
      }
      dataTransferTaskRepository.save(dataTask);
      ThreadPoolTaskExecutor asyncServiceExecutor = (ThreadPoolTaskExecutor) applicationContext
          .getBean("asyncServiceExecutor");
      if (null == asyncServiceExecutor) {
        throw new RuntimeException("this executor is null");
      }
      asyncServiceExecutor
          .submitListenable(ProcessorFactory.getInstance().create(toEvent(dataTask)));
      return Mono.just(Boolean.TRUE);
    }
  }

  private Event toEvent(DataTask dataTask) {
    Event event = new ProcessorEvent();
    DataSourceConfig sourceConfig = dataTask.getSourceConfig();
    DataSourceConfig targetConfig = dataTask.getTargetConfig();
    event.setSourceHouse(
        WarehouseFactory.getInstance().createWarehouse(JdbcUtils.jdbcUri(sourceConfig.getType(),
            sourceConfig.getUrl(), sourceConfig.getPortNo(), sourceConfig.getSid()),
            sourceConfig.getUsername(),
            sourceConfig.getPassword(), sourceConfig.getType(), null));
    event.setTagertHouse(
        WarehouseFactory.getInstance().createWarehouse(JdbcUtils.jdbcUri(targetConfig.getType(),
            targetConfig.getUrl(), targetConfig.getPortNo(), targetConfig.getSid()),
            sourceConfig.getUsername(),
            sourceConfig.getPassword(), sourceConfig.getType(), null));
    event.setRunMode(dataTask.getRunMode());
    return event;
  }
}
