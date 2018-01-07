package pl.desz.excel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id @GeneratedValue
    private Long id;

    private String fullName;

    @ElementCollection(targetClass = ArrayList.class, fetch = FetchType.EAGER)
    private List<LocalDate> dutyDates;
}
