package com.example.week6_wizard_add_redis_cache.REPOSITORY;

import com.example.week6_wizard_add_redis_cache.POJO.Wizard;
import com.example.week6_wizard_add_redis_cache.POJO.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "Wizard")
    public List<Wizard> retrieveWizard() {
        return repository.findAll();
    }
    public Wizard findByID(String id) {
        return repository.getWizardByID(id);
    }
    @CacheEvict(value="Wizard", allEntries = true)
    public Wizard addWizard(Wizard wizard) {
        return repository.save(wizard);
    }
    @CachePut(value = "Wizard")
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


        @CacheEvict(value="Wizard", allEntries = true)
        public boolean deleteWizard(String id){
        Wizard wizard = findByID(id);
        repository.delete(wizard);
        return true;
    }



}
