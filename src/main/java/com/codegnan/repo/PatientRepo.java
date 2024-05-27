package com.codegnan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.entity.Doctors;
import com.codegnan.entity.Patients;

public interface PatientRepo extends JpaRepository<Patients, Integer> {
	List<Patients> findAllByDoctor(Doctors doctor);

}
