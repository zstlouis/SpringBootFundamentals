package com.zstlouis.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zstlouis.beans.Student;

@RestController
public class StudentController {
	
	public static List<Student> students = new ArrayList<Student>();
	
	public StudentController() {
		students.add(new Student("Dougy", "Fresh"));
		students.add(new Student("Eddy", "Buds"));
		students.add(new Student("Tutty", "Boy"));
		students.add(new Student("Ryan", "Ferda"));
	}
	
	// http://localhost:8080/students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
		
	}
	// http://localhost:8080/student/Eddy/Buds
	@GetMapping("/student/{firstName}/{lastName}")
	public Student studentPathVariable(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return new Student(firstName, lastName);
	}
	
	// http://localhost:8080/student/query?firstName=John&lastName=Smith
	@GetMapping("student/query")
	public Student studentQueryParam(@RequestParam(name="firstName") String firstName,
			@RequestParam(name="lastName") String lastName) {
		return new Student(firstName, lastName);
	}
	// http://localhost:8080/student
	@PostMapping("/student")
	public void addStudent(@RequestBody Student student) {
		students.add(student);
	}
	// http://localhost:8080/student/Eddy
	@PutMapping("/student/{firstName}")
	public void updateStudent(@PathVariable("firstName") String firstName, 
			@RequestBody Student student) {
		for(Student stud : students) {
			if(stud.getFirstName().equals(firstName)) {
				stud.setFirstName(student.getFirstName());
				stud.setLastName(student.getLastName());
			}
		}
	}
	
	@DeleteMapping("/student/{firstName}")
	public void deleteStudent(@PathVariable("firstName") String firstName) {
		for(Student stud : students) {
			if(stud.getFirstName().equals(firstName)) {
				students.remove(stud);
			}
		}
	}

}
