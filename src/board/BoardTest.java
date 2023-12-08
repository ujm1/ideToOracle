package board;

import jdbtest.OracleConnection;

import java.sql.*;

public class BoardTest {

    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt = null;
        ResultSet rs=null;
        try {
             conn = OracleConnection.getConnection();
             stmt = conn.createStatement();
            int result = 0;

            String insertSql = " insert into board values (seq_board.nextval, '홍길동', '내용1') ";
            result = stmt.executeUpdate(insertSql);
            if (result > 0) System.out.println("입력성공");

            String updateSql="update board set bcontent='수정된내용1' where bid=21 ";
            result = stmt.executeUpdate(updateSql);
            if (result > 0) System.out.println("수정성공");

            String deleteSql=" delete board where bid=22 ";
            result=stmt.executeUpdate(deleteSql);
            if(result>0) System.out.println("삭제 성공");

            for(int i=1; i<4; i++) {
                StringBuilder sb=new StringBuilder();
                sb.append("insert into board values (seq_board.nextval");
                sb.append(", '홍길동"+i);
                sb.append("', '내용"+i+"')");
                System.out.println(sb.toString());
                String insertSql2=sb.toString();
                result=stmt.executeUpdate(insertSql2);
            }

            String selectSql=" select bid, bwriter, bcontent from board ";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(selectSql);
            while(rs.next()) {
                System.out.println(rs.getInt("bid") + ","
                        + rs.getString("bwriter") + ","
                        + rs.getString("bcontent"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            OracleConnection.closeConnection(conn,stmt,rs);
        }

    }


}
