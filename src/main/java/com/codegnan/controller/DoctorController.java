package com.codegnan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Doctor;
import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidDoctorIDException;
import com.codegnan.service.DoctorService;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
	DoctorService doctorService;
	PatientService patientService;
	public DoctorController(DoctorService doctorService, PatientService patientService) {
		super();
		this.doctorService = doctorService;
		this.patientService = patientService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctor(@PathVariable int id) throws InvalidDoctorIDException {
		Doctor doctor = doctorService.findDoctorById(id);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() throws InvalidDoctorIDException {
		List<Doctor> doctors = doctorService.findAllDoctors();
		ResponseEntity<List<Doctor>> responseEntity = new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/{id}/doctors")
	public ResponseEntity<List<Patient>> getPatientByDoctor(@PathVariable int id) throws InvalidDoctorIDException {
		Doctor doctor = doctorService.findDoctorById(id);
		List<Patient> patients = patientService.findPatientByDoctor(doctor);
		ResponseEntity<List<Patient>> responseEntity = new ResponseEntity<>(patients, HttpStatus.FOUND);
		return responseEntity;
	}
	
	@PostMapping 
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
		Doctor doctorSaved = doctorService.saveDoctor(doctor); 
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<Doctor>(doctorSaved, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> editBranch(@PathVariable("id") int id, @RequestBody Doctor doctor) throws InvalidDoctorIDException {
		if(id != doctor.getId()) {
			throw new InvalidDoctorIDException("Doctor ID is not valid "+id);
		}
		Doctor doctorEdited = doctorService.editDoctor(doctor) ;
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<Doctor>(doctorEdited, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor> deleteBranch(@PathVariable("id") int id) throws InvalidDoctorIDException {
		Doctor doctor = doctorService.deleteDoctor(id) ;
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<Doctor>(doctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
}

	

	
	


