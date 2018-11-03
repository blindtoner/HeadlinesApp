package mainPackage;

public class HeadLinesClass {
	private String by;
	private int descendants;
	private int id;
	private int[] kids;
	private int score;
	private int time;
	private String title; 
	private String type;
	private String url;
	
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public int getDescendants() {
		return descendants;
	}
	public void setDescendants(int descendants) {
		this.descendants = descendants;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getKids() {
		return kids;
	}
	public void setKids(int[] kids) {
		this.kids = kids;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		if (url!=null)
			return url;
		else
			return "";
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Title=");
		builder.append(title);
		builder.append(", by=");
		builder.append(by);
		builder.append(", type=");
		builder.append(type);
		builder.append(", url=");
		builder.append(url);
		return builder.toString();
	} 
}
