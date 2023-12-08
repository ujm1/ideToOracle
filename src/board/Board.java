package board;

public class Board {

    private int bid;
    private String bwriter;
    private String bcontent;

    public Board() {
    }

    public Board(int bid, String bwriter, String bcontent) {
        this.bid = bid;
        this.bwriter = bwriter;
        this.bcontent = bcontent;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }



    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }
}
