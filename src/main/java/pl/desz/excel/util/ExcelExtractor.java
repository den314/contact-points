package pl.desz.excel.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.desz.excel.model.Person;
import pl.desz.excel.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class ExcelExtractor {

    private static final Logger log = LoggerFactory.getLogger(ExcelExtractor.class);

    private String file;
    private PersonRepository pr;

    public ExcelExtractor(@Value("${xlsx.location}") String dataSource, PersonRepository pr) {
        this.file = dataSource;
        this.pr = pr;
    }


    @PostConstruct
    public void extractPeople() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());

        Map<String, List<LocalDate>> personToDate = new HashMap<>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            Date date = row.getCell(0).getDateCellValue();

            String personName = row.getCell(1).getStringCellValue();
            LocalDate parsedDate = LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));

            if (personToDate.containsKey(personName)) {
                personToDate.get(personName).add(parsedDate);
            } else {
                ArrayList<LocalDate> dates = new ArrayList<>();
                dates.add(parsedDate);
                personToDate.put(personName, dates);
            }

            log.debug("Got date: {} and person: {}", parsedDate, personName);
        }


        for (String s : personToDate.keySet()) {
            log.debug("key: {}, values: {}", s, personToDate.get(s));
            Person person = new Person();
            person.setFullName(s);
            person.setDutyDates(personToDate.get(s));
            pr.save(person);
        }
    }
}
