package com.dts.manager;

import com.dts.core.common.contants.DbType;
import com.dts.core.factory.ProcessorFactory;
import com.dts.core.factory.WarehouseFactory;
import com.dts.core.model.Event;
import com.dts.core.model.ProcessorEvent;
import com.dts.manager.model.DataSourceConfig;
import com.dts.manager.model.DataTask;
import com.dts.manager.util.JdbcUtils;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

public class ThreadForkTest {
    @Test
    public void testSubmit() throws  Exception{
        Event event = new ProcessorEvent();
        DataTask dataTask = new DataTask();
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setSid("orcl");
        sourceConfig.setPortNo("3306");
        sourceConfig.setType(DbType.ORACLE);
        sourceConfig.setPassword("jgs199272");
        sourceConfig.setUsername("test_dataload");
        sourceConfig.setUrl("127.0.0.1");


        DataSourceConfig targetConfig = new DataSourceConfig();

        targetConfig.setSid("test");
        targetConfig.setPortNo("1521");
        targetConfig.setType(DbType.MYSQL);
        targetConfig.setPassword("1234");
        targetConfig.setUsername("test");
        targetConfig.setUrl("127.0.0.1");

        dataTask.setSourceConfig(sourceConfig);
        dataTask.setTargetConfig(targetConfig);

         sourceConfig = dataTask.getSourceConfig();
         targetConfig = dataTask.getTargetConfig();
        event.setSourceHouse(WarehouseFactory.getInstance().createWarehouse(JdbcUtils.jdbcUri(sourceConfig.getType(),
            sourceConfig.getUrl(), sourceConfig.getPortNo(), sourceConfig.getSid()), sourceConfig.getUsername(),
            sourceConfig.getPassword(), sourceConfig.getType(), null));
        event.setTagertHouse(WarehouseFactory.getInstance().createWarehouse(JdbcUtils.jdbcUri(targetConfig.getType(),
            targetConfig.getUrl(), targetConfig.getPortNo(), targetConfig.getSid()), sourceConfig.getUsername(),
            sourceConfig.getPassword(), sourceConfig.getType(), null));
        event.setRunMode(dataTask.getRunMode());
        ThreadPoolTaskExecutor asyncServiceExecutor = new ThreadPoolTaskExecutor();
        asyncServiceExecutor.setBeanName("thread-p-");
        asyncServiceExecutor.initialize();
        for(int i = 0;i<10;i++){
            ListenableFuture listenableFuture = asyncServiceExecutor.submitListenable(ProcessorFactory.getInstance().create(event));
            System.out.println(listenableFuture.get());
        }

    }
}
