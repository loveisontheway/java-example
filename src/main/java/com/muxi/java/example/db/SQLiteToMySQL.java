package com.muxi.java.example.db;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SQLiteToMySQL {

    public static final String SQL_TABLE_ALL = "select * from sqlite_master where type='table' order by name";

    private Connection getSqliteCon(String path) {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getMysqlCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void execute(String path) {
        try {
            // MySQL数据库
            Connection mysqlCon = getMysqlCon();
            PreparedStatement mysqlStm = null;

            // SQLite数据库
            Connection sqliteCon = getSqliteCon(path);
            Statement sqliteStm = Objects.requireNonNull(sqliteCon).createStatement();
            ResultSet sqliteRes = sqliteStm.executeQuery(SQL_TABLE_ALL);

            // 1. SQLite查询ALL表名，MySQL创建ALL表
            List<String> tableList = new ArrayList<>();
            while (sqliteRes.next()) {
                String table = sqliteRes.getString("name");
                String sql = sqliteRes.getString("sql");
                // 不创建 sqlite_sequence
                if (!StrUtil.contains(table, "sqlite_sequence")) {
                    tableList.add(table);
                    // mysql > create > table
                    sql = sql.replaceAll("\"", "`")
                            // 表存在不创建
                            .replace("CREATE TABLE", "CREATE TABLE IF NOT EXISTS")
                            .replace("DEFAULT false", "")
                            .replace("DEFAULT 信息价库", "")
                            .replace("DEFAULT 浙江鼎晟工程项目管理有限公司", "")
                            .replace("varchar", "varchar(500)")
                            .replace("varhcar", "varchar(500)")
                            .replace("smalldatetime", "datetime")
                            .replace("COLLATE NOCASE", "")
                            .replace("COLLATE BINARY", "")
                            .replace("`is_expand` TEXT", "`is_expand` integer")
                            // 自增 sqlite > mysql
                            .replace("AUTOINCREMENT", "AUTO_INCREMENT");
                    mysqlStm = Objects.requireNonNull(mysqlCon).prepareStatement(sql);
                    mysqlStm.execute();
                }
            }

            // 2. SQLite查询Table数据，MySQL Table 添加数据
            for (String table : tableList) {
                String sql = "select * from " + table;
                ResultSet res = sqliteStm.executeQuery(sql);
                // true 有数据 | false 无数据
                boolean flag = res.isBeforeFirst();
                if (flag) {
                    ResultSetMetaData rsmd = res.getMetaData();
                    StringBuilder sb = new StringBuilder();
                    sb.append("insert into ").append(table).append(" values (");
                    // 列名 All + 组装 insert SQL
                    for (int k = 1; k <= rsmd.getColumnCount(); k++) {
                        sb.append("?").append(",");
                    }
                    sb.append(")").deleteCharAt(sb.lastIndexOf(","));

                    mysqlStm = mysqlCon.prepareStatement(sb.toString());
                    // 取消事务(不写入日志)
                    mysqlCon.setAutoCommit(false);
                    int count = 0;
                    while (res.next()) {
                        ++count;
                        // 数据 All
                        for (int k = 1; k <= rsmd.getColumnCount(); k++) {
                            Object obj = res.getObject(k);
                            mysqlStm.setObject(k, ObjectUtil.isEmpty(obj) ? null : obj);
                        }
                        System.out.println("SQL: " + mysqlStm.toString());
                        // 添加需要批量处理的SQL语句或是参数（缓存未操作DB）
                        mysqlStm.addBatch();
                        // 500 提交一次
                        if (count % 500 == 0) {
                            // 执行批量处理语句；
                            mysqlStm.executeBatch();
                        }
                    }

                    // 防止有数据未提交
                    mysqlStm.executeBatch();
                    // 提交
                    mysqlCon.commit();
                    // 清空缓存的数据
                    mysqlStm.clearBatch();
                }
            }

            // 恢复事务
            Objects.requireNonNull(mysqlCon).setAutoCommit(true);

            // 3. 关闭资源
            close(mysqlCon, mysqlStm, null);
            close(sqliteCon, sqliteStm, sqliteRes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(Connection con, Statement stm, ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SQLiteToMySQL test = new SQLiteToMySQL();
        try {
            test.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
