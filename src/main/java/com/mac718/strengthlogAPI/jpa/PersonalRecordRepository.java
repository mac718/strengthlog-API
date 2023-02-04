package com.mac718.strengthlogAPI.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mac718.strengthlogAPI.user.PersonalRecord;

public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, Integer> {
	@Query(value="SELECT * FROM Personal_Record WHERE movement = ? AND user_id = ?", nativeQuery=true)
	List<PersonalRecord> getUserPrsByMovement(String movement, Integer user_id);
}
