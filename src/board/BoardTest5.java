package board;

import jdbtest.OracleConnection;
import jdk.nashorn.internal.ir.CatchNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTest5 {

    private static Connection conn;
    private static PreparedStatement pstmt;
    private static String sql;
    private static ResultSet rs;

    private BoardTest5() {
        conn = OracleConnection.getConnection();
    }

    public static void main(String[] args) {
        try {
            BoardTest5 boardTest5 = new BoardTest5();
            List<Board> boardList = boardTest5.listBoard();
            Board board = boardTest5.selectBoard(47);
            int result1 = boardTest5.insertBoard(
                    new Board(0, "새로만든저자", "새로만든내용")
            ); //인서트는 비동작
            int result2 = boardTest5.updateBoard(
                    new Board(50, "수정한저자50", "수정한내용50")
            ); //업데이트는 동작

            int result3 = boardTest5.deleteBoard(58);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }  finally {OracleConnection.closeConnection(conn, pstmt, rs);}


    }//m

    private List<Board> listBoard() throws SQLException{
        sql = " select bid, bwriter, bcontent from board ";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        List<Board> boardList = new ArrayList<Board>();
        while (rs.next()) {
            Board bd = new Board();
            bd.setBid(rs.getInt("bid"));
            bd.setBwriter(rs.getString("bwriter"));
            bd.setBcontent(rs.getString("bcontent"));
            boardList.add(bd);

        }  return boardList;
    }

    private Board selectBoard(int bid) throws SQLException {
            String sql = " select bid, bwriter, bcontent from board ";
            sql += " where bid=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bid);
            rs = pstmt.executeQuery();
            Board board=new Board();
            while (rs.next()) {
                board.setBid(rs.getInt("bid"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBcontent(rs.getString("bcontent"));
            }
            return board;
        }

        private int insertBoard (Board board) throws SQLException {
            sql = " insert into board values (seq_board.nextval, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getBwriter());
            pstmt.setString(2, board.getBcontent());
            return pstmt.executeUpdate();
        }

        private int updateBoard (Board board) throws SQLException{
                String sql = " update board set bwriter=?, bcontent=? ";
                sql += " where bid=? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, board.getBwriter());
                pstmt.setString(2, board.getBcontent());
                pstmt.setInt(3, board.getBid());
                return pstmt.executeUpdate();
            }


        private int deleteBoard ( int bid) throws SQLException{
            String sql = " delete board where bid=? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bid);
            return pstmt.executeUpdate();
        }



    }//c
