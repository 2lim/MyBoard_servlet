package board.model;

import java.util.Date;

public class myBoardVO {

//	bno NUMBER(10) NOT NULL PRIMARY KEY,
//    bwriter VARCHAR2(30) NOT NULL,
//    btitle VARCHAR2(120) NOT NULL,
//    bdate DATE DEFAULT SYSDATE,
//    bCONTENT VARCHAR2(4000) NOT NULL,
//    bVIEW NUMBER DEFAULT 0
    
    private int bno;
	private String bwriter;
	private String btitle;
	private Date bdate;
	private String bcontent;
	private int bview;
	private int bcount;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getBview() {
		return bview;
	}
	public void setBview(int bview) {
		this.bview = bview;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	
}
