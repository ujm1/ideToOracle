package board;

import jdbtest.OracleConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardTest2 {
    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt = null;
        ResultSet rs=null;
        try {
            conn = OracleConnection.getConnection();
            int result = 0;

            Board board = new Board(0, "강감찬", "내용100");
            String sql = "insert into board values (seq_board.nextval, ";
            sql += "'" + board.getBwriter() + "',";
            sql += "'" + board.getBcontent() + "')";
            System.out.println(sql);

            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
            if (result > 0) System.out.println("입력성공");

            List<Board> boardList = new ArrayList<Board>();
            sql = "select bid, bwriter, bcontent from board";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Board bd = new Board();
                bd.setBid(rs.getInt("bid"));
                bd.setBwriter(rs.getString("bwriter"));
                bd.setBcontent(rs.getString("bcontent"));
                boardList.add(bd);
            }
            ;

            if (boardList != null) {
                for (Board brd : boardList) {
                    System.out.println(brd.getBid() + "," +
                            brd.getBwriter() + "," +
                            brd.getBcontent());
                }

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            OracleConnection.closeConnection(conn,stmt,rs);
        }
    }
}
