package com.dts.manager.util;

import com.dts.core.common.contants.DbType;
import com.dts.manager.model.DataSourceConfig;
import org.springframework.util.Assert;

import java.sql.*;

public class JdbcUtils {
    /**
     * determine if the database is successfully connected
     *
     * @param dataSourceConfig
     * @return
     */
    public static Boolean isConnSuccess(DataSourceConfig dataSourceConfig) {
        boolean success = false;
        try {
            Connection connection = getConnection(dataSourceConfig);
            success = true;
            release(null, null, connection);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return success;
        }
    }

    /**
     * Get the database connection
     *
     * @param dataSourceConfig
     * @return
     */
    public static Connection getConnection(DataSourceConfig dataSourceConfig) {
        try {
            Driver driver = (Driver) Class.forName(dataSourceConfig.getType().getDriver()).newInstance();
            DriverManager.registerDriver(driver);
            String jdbcUri = jdbcUri(dataSourceConfig.getType(), dataSourceConfig.getUrl(), dataSourceConfig.getPortNo(), dataSourceConfig.getSid());
            return DriverManager.getConnection(jdbcUri, dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("The url or username password of the linked database is incorrect. Please check your configuration file");
        }
    }

    /**
     * release db resource
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        //If the result set is not empty
        if (rs != null) {
            try {
                //Close the result
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        //If the Sql statement object is not empty
        if (stmt != null) {
            try {
                //Processing Sql statement object is closed
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        //If the connection is not empty
        if (conn != null) {
            try {
                //Close the connection
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * according with jdbc to url
     *
     * @param dbType
     * @param url
     * @param portNo
     * @param sid
     * @return
     */
    public static String jdbcUri(DbType dbType, String url, String portNo, String sid) {
        boolean dbJudge = dbType.isOracle() || dbType.isMysql() || dbType.isMYSQL8();
        Assert.isTrue(dbJudge, "db type not support");
        if (dbType.isOracle()) {
            return "jdbc:oracle:thin:@" + url + ":" + portNo + "/" + sid;
        } else if (dbType.isMysql() || dbType.isMYSQL8()) {
            return "jdbc:mysql://" + url + ":" + portNo + "/" + sid;
        }
        return "";
    }

    //TODO 去掉main函数测试
    public static void main(String[] args) {
        System.out.println(jdbcUri(DbType.MYSQL8,
            "127.0.0.1", "3306", "test"));
        System.out.println(jdbcUri(DbType.ORACLE,
            "127.0.0.1", "1521", "orcl"));
    }
}
