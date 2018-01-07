## Contact points application

Application load contacts from the excel file into the in-memory database.
The application searches for the person which is contact point on desired date.
By accessing REST endpoint `/ro/today` app finds the guy who is contact point for today.

Front end is accessible via `http://localhost:8080/`

## Source xls
It is possible to change source .xls file in `application.properties`, but
the general XLS data structure must be preserved.

### Running
1. Download `data.xlsx` from this project, adjust to your needs 
2. `java -jar -Dxlsx.location=C:\\path\\to\\file\\data.xlsx app.jar`
