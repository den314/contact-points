package pl.desz.excel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.desz.excel.model.Person;
import pl.desz.excel.service.PersonService;
import pl.desz.excel.util.PersonResponse;

import java.time.LocalDate;

@RestController
@RequestMapping("/ro")
public class RomaniaController {

    private static final Logger log = LoggerFactory.getLogger(RomaniaController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/today")
    public ResponseEntity<PersonResponse> checkEscalationGuy() {

        LocalDate now = LocalDate.now();
        Person person = personService.checkPersonByDate(now);
        String personName =  person.getFullName();

        PersonResponse response = new PersonResponse(personName, now);
        return ResponseEntity.ok(response);
    }
}
