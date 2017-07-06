package EmployeeEarning;

public class Employee {
	private final int id;
	private String name;
	private int salary;
	private Integer departmentId;
	public Employee(final int id, final String Name,
			final int Salary, final Integer DepartmentId) {
		this.id = id;
		this.name = Name;
		this.salary = Salary;
		this.departmentId = DepartmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public int getId() {
		return id;
	}
	
}
