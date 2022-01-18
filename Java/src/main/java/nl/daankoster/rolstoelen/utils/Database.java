package nl.daankoster.rolstoelen.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private HikariDataSource hikariDataSource;

    public Database(String host, String user, String password, int port, String database) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://"+host+":"+port+"/" + database);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("maximumPoolSize", "64");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setLeakDetectionThreshold(60 * 1000);

        hikariDataSource = new HikariDataSource(config);
    }

    public Database(String host, String user, String password, String database) {
        this(host, user, password, 3306, database);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public void close() {
        if (!hikariDataSource.isClosed()) {
            hikariDataSource.close();
        }
    }
}