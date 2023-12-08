package board;

import jdbtest.OracleConnection;

import java.sql.*;

public class BoardTest3 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;


        conn = OracleConnection.getConnection();

        int result = 0;
        String sql = null;

        try {
            sql = "insert into board values (seq_board.nextval, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "유관순");
            pstmt.setString(2, "내용");
            result = pstmt.executeUpdate();
            if (result > 0) System.out.println("입력 성공");

//            sql="update board set bcontent='수정된내용' where bwriter='유관순'";
//            pstmt=conn.prepareStatement(sql);
//            result=pstmt.executeUpdate();
//            if(result>0) System.out.println("수정 완료"); 로 해도 되고

            sql = "update board set bwriter=?, bcontent=?";
            sql += "where bid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "수정유관순");
            pstmt.setString(2, "수정내용");
            pstmt.setInt(3, 41);
            result = pstmt.executeUpdate();
            if (result > 0) System.out.println("수정 완료");


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            OracleConnection.closeConnection(conn, pstmt, null);
        }
    }
}
