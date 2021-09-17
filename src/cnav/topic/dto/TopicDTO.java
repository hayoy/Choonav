package cnav.topic.dto;

import java.sql.Timestamp;

public class TopicDTO {

	private Integer topNum;
	private String topTitle;
	private String topContent;
	private Timestamp topDate;
	private String userId;
	private Integer readcount;
	private Integer recnt;
	
	public Integer getRecnt() {
		return recnt;
	}
	public void setRecnt(Integer recnt) {
		this.recnt = recnt;
	}
	public Integer getReadcount() {
		return readcount;
	}
	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
	public Integer getTopNum() {
		return topNum;
	}
	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}
	public String getTopTitle() {
		return topTitle;
	}
	public void setTopTitle(String topTitle) {
		this.topTitle = topTitle;
	}
	public String getTopContent() {
		return topContent;
	}
	public void setTopContent(String topContent) {
		this.topContent = topContent;
	}
	public Timestamp getTopDate() {
		return topDate;
	}
	public void setTopDate(Timestamp topDate) {
		this.topDate = topDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	// toString()
	// boardVO -> TopicDTO
	// bno -> topNum
	// title -> topTitle
	// content -> topContent
	// writer -> userId
	// userName
	// regdate -> topDate
	// viewcnt -> readcount
	// recnt -> recnt
	@Override
	public String toString() {
        return "TopicDTO [topNum=" + topNum + ", topTitle=" + topTitle + ", topContent=" + topContent + ", userId=" + userId
                + ", topDate=" + topDate + ", readcount=" + readcount + ", recnt=" + recnt + "]";
    }
}
