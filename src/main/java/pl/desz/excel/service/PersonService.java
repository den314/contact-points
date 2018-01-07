package pl.desz.excel.service;

import pl.desz.excel.model.Person;

import java.time.LocalDate;

public interface PersonService {

    public abstract Person checkPersonByDate(LocalDate today);
}
