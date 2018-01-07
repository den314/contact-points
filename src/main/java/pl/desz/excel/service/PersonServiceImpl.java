package pl.desz.excel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.desz.excel.model.Person;
import pl.desz.excel.repository.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonRepository pr;

    public PersonServiceImpl(PersonRepository pr) {
        this.pr = pr;
    }

    @Override
    public Person checkPersonByDate(LocalDate today) {
        log.debug("Checking escalation person on day: " + today);
        return pr.findByDutyDatesEquals(newArrayList(today));
    }
}
