package EmployeeEarning;

public class Department {
	private final int id;
	private String name;
	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
}
