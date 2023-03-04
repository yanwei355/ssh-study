package com.seehope.entity;

public class Student {
	private Integer id;		//主键id
	private String studentName;	//学生姓名
	private String gender;	//性别
	private Integer age;	//年龄
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentname=" + studentName + ", age=" + age + ", gender=" + gender + "]";
	}
}
