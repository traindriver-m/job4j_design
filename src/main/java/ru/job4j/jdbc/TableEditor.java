package ru.job4j.jdbc;

import java.io.FileInputStream;
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
                "create table if not exists %s (%s);", tableName,
                "id serial primary key"
        );
        request(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table %s", tableName);
        request(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("alter table %s add %s %s", tableName, columnName, type);
        request(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("alter table %s drop %s", tableName, columnName);
        request(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("alter table %s rename %s to %s", tableName, columnName, newColumnName);
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
        Properties properties = new Properties();
        properties.load(new FileInputStream("app.properties"));
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
