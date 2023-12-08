package jdbtest;

import java.sql.*;

public class OracleConnection {
    private static Connection conn;

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String JDBC_USER = "admin";
    private static String JDBC_PWD = "1234";

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
//            String sql = " select employee_id, emp_name, salary    from  Employees  ";
//            System.out.println(conn);
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                int employeeId = rs.getInt("employee_id");
//                String empName = rs.getString("emp_name");
//                int salary = rs.getInt("salary");
//                System.out.println(employeeId + "," + empName + "," + salary);
//            }
            return conn;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
//        finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException sqle) {
//                    sqle.printStackTrace();
//                }
//            }
//        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

    }
}
