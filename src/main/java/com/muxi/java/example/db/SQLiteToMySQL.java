package com.muxi.java.example.db;

import java.sql.*;

public class SQLiteToMySQL {

    private Connection getSqliteconn() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:D:\\sqlite.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getMysqlconn() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deal() throws SQLException {
        // SQLite数据库
        Connection iteconn = getSqliteconn();
        Statement itestmt = iteconn.createStatement();
        ResultSet iters = itestmt.executeQuery("select * from lagou_position");

        // 结果集获取到的长度
        int size = iters.getMetaData().getColumnCount();
        // 比较懒，拼接insert into 语句
        StringBuffer sbf = new StringBuffer();
        sbf.append("insert into lagou values (");
        String link = "";
        for (int i = 0; i < size; i++) {
            sbf.append(link).append("?");
            link = ",";
        }
        sbf.append(")");
        // MySQL数据库
        Connection mysqlconn = getMysqlconn();
        PreparedStatement mysqlpstmt = mysqlconn.prepareStatement(sbf.toString());

        // 取出结果集并向MySQL数据库插入数据 ( 使用批处理 )
        // 完成条数
        int count = 0;
        int num = 0;
        // 取消事务(不写入日志)
        mysqlconn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        while (iters.next()) {
            ++count;
            for (int i = 1; i <= size; i++) {
                mysqlpstmt.setObject(i, iters.getObject(i));
            }

            // 将预先语句存储起来，这里还没有向数据库插入
            mysqlpstmt.addBatch();
            // 当count 到达 20000条时 向数据库提交
            if (count % 20000 == 0) {
                ++num;
                mysqlpstmt.executeBatch();
                System.out.println("第" + num + "次提交,耗时:" + (System.currentTimeMillis() - start) / 1000.0 + "s");
            }
        }
        // 防止有数据未提交
        mysqlpstmt.executeBatch();
        // 提交
        mysqlconn.commit();
        System.out.println("完成 " + count + " 条数据,耗时:" + (System.currentTimeMillis() - start) / 1000.0 + "s");
        // 恢复事务
        //  mysqlconn.setAutoCommit(true);

        // 关闭资源
        close(mysqlconn, mysqlpstmt, null);
        close(iteconn, itestmt, iters);
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SQLiteToMySQL test = new SQLiteToMySQL();
        try {
            test.deal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
