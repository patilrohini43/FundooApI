package com.bridgelabz.fundoo.label.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.label.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

}
