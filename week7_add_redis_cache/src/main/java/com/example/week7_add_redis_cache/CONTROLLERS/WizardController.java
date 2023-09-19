package com.example.week7_add_redis_cache.CONTROLLERS;

import com.example.week7_add_redis_cache.POJO.Wizard;
import com.example.week7_add_redis_cache.POJO.Wizards;
import com.example.week7_add_redis_cache.REPOSITORY.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
//    เพื่อเลือกข-อมูลท้ังหมดจาก MongoDB ข้ึนมา
    public ResponseEntity<List<Wizard>> retrieveWizard() {
        List<Wizard> wizards = wizardService.retrieveWizard();

        return ResponseEntity.ok(wizards);
    }

    ;

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<Wizard> addWizard(@RequestBody MultiValueMap<String, String> formdata) {
        Map<String, String> d = formdata.toSingleValueMap();
        int mon = Integer.parseInt(d.get("dollar"));
        Wizard wizard = wizardService.addWizard(
                new Wizard(null, d.get("sex"), d.get("fullname"), d.get("position"), mon, d.get("school"), d.get("house"))
        );
        return ResponseEntity.ok(wizard);
//        return wizardService.addWizard(wizard);
    }

    ;

    //    เพื่อเพิ่มข-อมูลใหม2ลง MongoDB
    @RequestMapping(value = "/updateWizard", method = RequestMethod.POST)
    public ResponseEntity<Wizard> updateWizard(@RequestBody MultiValueMap<String, String> formdata) {
        Map<String, String> d = formdata.toSingleValueMap();
        int mon = Integer.parseInt(d.get("dollar"));

        Wizard wizard = wizardService.updateWizard(
                new Wizard(
                        d.get("id") // ถ้าใส่ null มันจะ insert 
                        , d.get("sex"), d.get("fullname"), d.get("position"), mon, d.get("school"), d.get("house"))
        );
        return ResponseEntity.ok(wizard);
//        return wizardService.updateWizard(wizard);
    }

    //    เพ่ือแก-ไขข-อมูลใน MongoDB
    @RequestMapping(value = "/deleteWizard/{id}", method = RequestMethod.POST)
    public String deleteWizard(@PathVariable String id) {
        boolean status = wizardService.deleteWizard(id);
        return (status ? "Deleted" : "Something went wrong");
    }
//    เพื่อลบข-อมูลใน MongoDB
}
