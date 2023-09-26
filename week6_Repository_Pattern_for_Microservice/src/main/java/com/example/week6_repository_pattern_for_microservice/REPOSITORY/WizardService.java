package com.example.week6_repository_pattern_for_microservice.REPOSITORY;

import com.example.week6_repository_pattern_for_microservice.POJO.Wizard;
import com.example.week6_repository_pattern_for_microservice.POJO.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.print.Book;
import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public List<Wizard> retrieveWizard() {
        return repository.findAll();
    }
    public Wizard findByID(String id) {
        return repository.getWizardByID(id);
    }
    public Wizard addWizard(Wizard wizard) {
        return repository.save(wizard);
    }

    public Wizard updateWizard(Wizard wizard) {
//        if (repository.findById(wizard.get_id())) {
//
////            Wizard wizard1 = new Wizard(wizard.)
//
//        }
        return repository.save(wizard);
    }

//    public boolean deleteWizard(Wizard wizard) {
//        try {
//            repository.delete(wizard);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

        public boolean deleteWizard(String id){
        Wizard wizard = findByID(id);
        repository.delete(wizard);
        return true;
    }



}
