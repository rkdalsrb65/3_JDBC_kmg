package edu.kh.jdbc1.model.vo;

public class Employee {
	
	private String empName;
	private String jobName;
	private int salary;
	private int annualIncome; // 연봉(연간 수입)
	
	public Employee() { } // 기본 생성자
	
	public Employee(String empName, String jobName, int salary, int annualIncome) { // 매개변수 생성자
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annualIncome = annualIncome;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	
	@Override
	public String toString() {
		return empName + " / " + jobName + " / " + salary + " / " + annualIncome;
//		return "이름 : " + empName + " / 직급명 : " + jobName + " / 급여 : " + salary + " / 연봉 : " + annualIncome;
	}	
	
}
