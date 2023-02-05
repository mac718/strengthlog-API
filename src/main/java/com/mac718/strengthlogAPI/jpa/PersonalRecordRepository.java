package com.mac718.strengthlogAPI.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mac718.strengthlogAPI.user.PersonalRecord;

@Repository
public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, Integer> {
	@Query(value="SELECT * FROM Personal_Record WHERE movement = ? AND user_id = ? ORDER BY date", nativeQuery=true)
	List<PersonalRecord> getUserPrsByMovement(String movement, Integer user_id);
}
