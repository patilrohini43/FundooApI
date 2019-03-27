package com.bridgelabz.fundoo.label.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.note.model.Note;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

	@Query(value="select id from label where labelName=:labelName",nativeQuery=true)
	Optional<Long> findIdByLabelName(@Param("labelName") String labelName);


}
