package pl.desz.excel.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PersonResponse {

    private String fullName;
    @JsonFormat(pattern = "dd-MM-YYYY")
    private LocalDate date;
}
