package com.vckadam.oopdesign.hr.model;

import java.util.List;

public class MinSalGradeEmp {
	private Job job;
	private List<Employee> emps;
	
	public MinSalGradeEmp(Job job, List<Employee> emps) {
		this.job = job;
		this.emps = emps;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
	
	
}
