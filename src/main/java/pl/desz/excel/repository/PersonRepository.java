package pl.desz.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.desz.excel.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person findByDutyDatesEquals(LocalDate date);
    public Person findByDutyDatesEquals(ArrayList date);
}
