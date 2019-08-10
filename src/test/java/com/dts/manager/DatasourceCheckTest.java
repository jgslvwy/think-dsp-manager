package com.dts.manager;

import com.dts.core.common.contants.DbType;
import com.dts.manager.model.DataSourceConfig;
import com.dts.manager.util.JdbcUtils;
import org.junit.Test;

public class DatasourceCheckTest{
    @Test
    public void checkDataSourceConn(){
        DataSourceConfig config = new DataSourceConfig();
        config.setUrl("127.0.0.1");
        config.setUsername("test_dataload");
        config.setPassword("123456");
        config.setType(DbType.ORACLE);
        config.setPortNo("1521");
        config.setSid("orcl");
        Boolean test = JdbcUtils.isConnSuccess(config);
        System.out.println("conn success---->"+test);

        DataSourceConfig mysqlConfig = new DataSourceConfig();
        mysqlConfig.setUrl("127.0.0.1");
        mysqlConfig.setUsername("test");
        mysqlConfig.setPassword("1234");
        mysqlConfig.setType(DbType.MYSQL);
        mysqlConfig.setPortNo("3306");
        mysqlConfig.setSid("test");
        Boolean testM = JdbcUtils.isConnSuccess(mysqlConfig);
        System.out.println("conn success---->"+testM);

    }
}
