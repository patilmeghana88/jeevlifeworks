package com.sms.util.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DbUtil {
    private static final String PROPERTIES_FILE = "/db.properties";
    private static Properties props = new Properties();

    static {
        try (InputStream input = DbUtil.class.getResourceAsStream(PROPERTIES_FILE)) {
            props.load(input);
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            props.getProperty("jdbc.url"),
            props.getProperty("jdbc.username"),
            props.getProperty("jdbc.password")
        );
    }
}