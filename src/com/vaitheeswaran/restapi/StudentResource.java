package com.vaitheeswaran.restapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vaitheeswaran.restapi.model.Student;
import com.vaitheeswaran.restapi.repository.StudentRepository;


@Path("/students")
public class StudentResource {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> students() {
		return StudentRepository.getAllStudents();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student newStudent) {
		System.out.println("Student added"+ newStudent.getName());
		StudentRepository.addStudent(newStudent);
		return newStudent;
	}
	
	@GET
	@Path("/{rollno}")
	public Student getStudent(@PathParam("rollno") int rollno) {
		System.out.println("Student id is "+ rollno);
		Student student = StudentRepository.getStudent(rollno);
		return student;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Hello get";
	}
	
//	@POST
//	@Produces(MediaType.TEXT_PLAIN)
//	public String tesPost() {
//		return "Hello POST";
//	}
}
