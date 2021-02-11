package logic.model;

public class Review {
	
	private String mountainPathName;
	private Integer vote;
	private String title;
	private String comment;
	private String authorUsername;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPathName() {
		return mountainPathName;
	}

	public void setPathName(String pathName) {
		this.mountainPathName = pathName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return authorUsername;
	}

	public void setAuthor(String author) {
		this.authorUsername = author;
	}
	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
}
