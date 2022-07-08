package com.vaitheeswaran.restapi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.vaitheeswaran.restapi.model.Student;
import com.vaitheeswaran.restapi.repository.StudentRepository;


@Path("/students")
public class StudentResource {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		String uploadedFileLocation = "D:/vaitheeswaran/uploaded_files/" + fileDetail.getFileName();

		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/download")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile() {
//		StreamingOutput fileStream =  new StreamingOutput() 
//	    {
//	      @Override
//	      public void write(java.io.OutputStream output) throws IOException, WebApplicationException 
//	      {
//	        try
//	        {
//	          java.nio.file.Path path = Paths.get("D:/vaitheeswaran/uploaded_files/pat.pdf");
//	          byte[] data = Files.readAllBytes(path);
//	          output.write(data);
//	          output.flush();
//	        } 
//	        catch (Exception e) 
//	        {
//	          throw new WebApplicationException("File Not Found !!");
//	        }
//	      }
//	    };
//	    return Response
//	              .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
//	              .header("content-disposition","attachment; filename = downloadfile.txt")
//	              .build();
		File textFile = new File("D:/vaitheeswaran/uploaded_files/pat.txt");
		Response.ResponseBuilder response = Response.ok((Object) textFile);
	    response.header("Content-Disposition", "attachment; filename=\"file.txt\"");
	    return response.build();
	}
	
	@GET
	@Path("/download/pdf")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadPDF() {
		File pdfFile = new File("D:/vaitheeswaran/uploaded_files/sql-cheatsheet.pdf");
		Response.ResponseBuilder response = Response.ok((Object) pdfFile);
	    response.header("Content-Disposition", "attachment; filename=\"sql-cheatsheet.pdf\"");
	    return response.build();
	}
	
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
	
	// save uploaded file to new location
		private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					System.out.println(read);
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
}
