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

import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidPatientIdException;
import com.codegnan.service.PatientService;
@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
	PatientService patientService;

	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Patient> findPatient(@PathVariable("id") int id) throws InvalidPatientIdException{
		Patient patient = patientService.findPatientById(id);
		ResponseEntity<Patient> responseEntity = new ResponseEntity<Patient>(patient, HttpStatus.FOUND);
		return responseEntity;
	}
	@GetMapping
	public ResponseEntity<List<Patient>> findAllPatients(){
		List<Patient> patients = patientService.findAllPatients();
		ResponseEntity<List<Patient>> responseEntity = new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
		return responseEntity;
	}
	@PostMapping
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
		Patient savedPatient = patientService.savePatient(patient);
		ResponseEntity<Patient> responseEntity = new ResponseEntity<Patient>(savedPatient, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Patient> editStudent(@PathVariable("id") int id, @RequestBody Patient patient) throws InvalidPatientIdException{
		if( id != patient.getId() ) {
			throw new InvalidPatientIdException("id "+id+" is not matching with patient.id "+patient.getId());
		}
		Patient editedPatient = patientService.editPatient(patient);
		ResponseEntity<Patient> responseEntity = new ResponseEntity<Patient>(editedPatient, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> deleteStudent(@PathVariable int id) throws InvalidPatientIdException{
		Patient patient = patientService.deletePatient(id);
		ResponseEntity<Patient> responseEntity = new ResponseEntity<Patient>(patient, HttpStatus.ACCEPTED);
		return responseEntity;
	}
}

	
	


