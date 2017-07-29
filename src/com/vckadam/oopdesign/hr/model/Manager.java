package com.vckadam.oopdesign.hr.model;

import java.util.List;

public class Manager {
	private Employee manager;
	private List<Employee> team;
	
	public Manager(Employee manager, List<Employee> team) {
		this.manager = manager;
		this.team = team;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getTeam() {
		return team;
	}

	public void setTeam(List<Employee> team) {
		this.team = team;
	}
	
	public String toString() {
		return this.manager.getEmpId()+" ";
	}
}
