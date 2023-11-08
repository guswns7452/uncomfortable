package domain;

public class BoardVO {
	private int boardNumber;
	private String id;
	private String content;
	private int likeNumber;
	private int dislikeNumber;
	
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	
	public int getDislikeNumber() {
		return dislikeNumber;
	}
	public void setDislikeNumber(int dislikeNumber) {
		this.dislikeNumber = dislikeNumber;
	}
	
	public void printLog() {
		System.out.println("boardNumber : " + getBoardNumber() + " / ID : " + getId() + " / 내용 : " + getContent() + " / 좋아요 수 : " + getLikeNumber() + " / 싫어요 수 : " + getDislikeNumber());
	}
	
	
}
