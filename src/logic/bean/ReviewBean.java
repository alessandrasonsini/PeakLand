package logic.bean;

public class ReviewBean extends ItemBean {
	
	private String mountainPathName;
	private Integer vote;
	private String title;
	private String comment;
	private String author;
	
	
	public String getPathName() {
		return mountainPathName;
	}

	public void setPathName(String pathName) {
		this.mountainPathName = pathName;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
