package models;

public interface IDisplayContents {

	void setUrl(String url);

	String getUrl();

	void setType(String type);

	String getType();

	void setTitle(String title);

	String getTitle();

	void setTime(int time);

	int getTime();

	void setScore(int score);

	int getScore();

	void setKids(int[] kids);

	int[] getKids();

	void setId(int id);

	int getId();

	void setDescendants(int descendants);

	int getDescendants();

	void setBy(String by);

	String getBy();
}
