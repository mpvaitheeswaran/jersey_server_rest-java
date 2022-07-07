package com.vaitheeswaran.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaitheeswaran.restapi.model.Student;
public class StudentRepository {

	private static final List<Student> students = createStudents();
	
	
	
	public StudentRepository(List<Student> students) {
		students = students;
	}

	public static void addStudent(Student newStudent) {
		students.add(newStudent);
	}
	
	public static Student getStudent(int rollNo) {
		Optional<Student> matchingObject = students.stream()
				.filter(s->s.getRollNo()==rollNo)
				.findFirst();
		Student student = matchingObject.get();
		return student;
	}
	
	public Student updateStudent(int rollNo, Student updatingStudent) {
		Optional<Student> matchingObject = this.students.stream()
				.filter(s->s.getRollNo()==rollNo)
				.findFirst();
		Student student = matchingObject.get();
		return student;
	}

	public static List<Student> getAllStudents(){
		return students;
	}
	
	public static List<Student> createStudents(){
		List<Student> students = new ArrayList<>();
		Student s1 = new Student(101,"Vaitheeswaran","BCA",21);
		Student s2 = new Student(102,"RajKumar","BE",25);
		students.add(s1);
		students.add(s2);
		return students;
	}
	
}
