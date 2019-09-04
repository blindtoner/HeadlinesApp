package mainPackage;

public class HNHeadLinesModel implements IDisplayContents {
	private String by;
	private int descendants;
	private int id;
	private int[] kids;
	private int score;
	private int time;
	private String title;
	private String type;
	private String url;

	@Override
	public String getBy() {
		return by;
	}

	@Override
	public void setBy(String by) {
		this.by = by;
	}

	@Override
	public int getDescendants() {
		return descendants;
	}

	@Override
	public void setDescendants(int descendants) {
		this.descendants = descendants;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int[] getKids() {
		return kids;
	}

	@Override
	public void setKids(int[] kids) {
		this.kids = kids;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getUrl() {
		if (url != null)
			return url;
		else
			return "";
	}

	@Override
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
