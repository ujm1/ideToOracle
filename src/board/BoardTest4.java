package board;

import jdbtest.OracleConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTest4 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;

        try {
            conn = OracleConnection.getConnection();
            int result = 0;
            String sql = null;

            Board board = new Board(11, "dd", "tt");
            sql = " insert into board values (seq_board.nextval, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1,"보드테스트4저자");
//            pstmt.setString(2,"보드테스트4내용");
            //이러면 보드테스트4저자, 보드테스트4내용이 들어감

            pstmt.setString(1, board.getBwriter());
            pstmt.setString(2, board.getBcontent());
            //이러면 dd, tt가 들어감

            System.out.println(sql);
            result = pstmt.executeUpdate();

            if (result > 0) System.out.println("입력성공");



            sql = null;
            pstmt = null;
            result = 0;
            Board board2 = new Board(0, "aa", "bb");
            sql = " update board set bwriter=?, bcontent=? ";
            sql += " where bid=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board2.getBwriter());
            pstmt.setString(2, board2.getBcontent());
            pstmt.setInt(3, board2.getBid());
            System.out.println(sql);
            result = pstmt.executeUpdate();

            if (result > 0) System.out.println("수정성공"); //업데이트가 안된다

            List<Board> boardList = new ArrayList<Board>();
            sql = " select bid, bwriter, bcontent from board ";
            sql += " where bid=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 47);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("bid") + "," + rs.getString("bwriter")+","
                        + rs.getString("bcontent"));
            }



        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {OracleConnection.closeConnection(conn, pstmt, rs);}
    }
}









