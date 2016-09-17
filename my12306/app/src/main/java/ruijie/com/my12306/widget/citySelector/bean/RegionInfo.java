package ruijie.com.my12306.widget.citySelector.bean;

public class RegionInfo {
	
	private int id;
	private int parent;
	private String name;
	private int type;
	public RegionInfo() {
		super();
	}
	
	public RegionInfo(int id, int parent, String name) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
