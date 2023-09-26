package com.example.week6_repository_pattern_for_microservice.REPOSITORY;

import com.example.week6_repository_pattern_for_microservice.POJO.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface WizardRepository extends MongoRepository<Wizard, String> {
    @Query(value="{_id: '?0'}")
    public Wizard getWizardByID(String _id);

//    public Wizard updateWizard(Wizard wizard);
}
