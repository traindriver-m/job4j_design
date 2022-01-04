package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }


    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        connection = DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password"));
    }

    private void request(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists " + tableName + "(%s);",
                "id serial primary key"
        );
        request(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = "drop table " + tableName;
        request(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = "alter table " + tableName + " add " + columnName + " " + type;
        request(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = "alter table " + tableName + " drop " + columnName;
        request(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = "alter table " + tableName + " rename " + columnName + " to " + newColumnName;
        request(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config("app.properties");
        config.load();
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", config.value("hibernate.connection.driver_class"));
        properties.put("hibernate.connection.url", config.value("hibernate.connection.url"));
        properties.put("hibernate.connection.username", config.value("hibernate.connection.username"));
        properties.put("hibernate.connection.password", config.value("hibernate.connection.password"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("one_tbl");
        System.out.println(getTableScheme(tableEditor.connection, "one_tbl"));
        tableEditor.addColumn("one_tbl", "name", "varchar(50)");
        System.out.println(getTableScheme(tableEditor.connection, "one_tbl"));
        tableEditor.renameColumn("one_tbl", "name", "surname");
        System.out.println(getTableScheme(tableEditor.connection, "one_tbl"));
        tableEditor.dropColumn("one_tbl", "surname");
        System.out.println(getTableScheme(tableEditor.connection, "one_tbl"));
        tableEditor.dropTable("one_tbl");
    }
}
