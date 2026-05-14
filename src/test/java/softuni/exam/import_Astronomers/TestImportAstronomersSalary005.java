package softuni.exam.import_Astronomers;
//TestImportAstronomersSalary005

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import softuni.exam.service.impl.AstronomerServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestImportAstronomersSalary005 {

    @Autowired
    private AstronomerServiceImpl astronomerService;

    @Sql({"/constellation-test-imports.sql", "/stars-test-imports.sql"})
    @Test
    void importAstronomersSalary005() throws IOException {
        rewriteFileForTest();

        String expected = "Successfully imported astronomer Avril Sp - 209054.25\n" +
                "Successfully imported astronomer Wesle Mara MaraVeg Spencer von de Bergen ScwharzV - 209054.25\n" +
                "Invalid astronomer\n" +
                "Invalid astronomer";
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = astronomerService.importAstronomers();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void rewriteFileForTest() {
        File originalJsonFile = getOriginalFile();

        String testJSON = "[\n" +
                "  {\n" +
                "    \"averageObservationHours\": 209054.25,\n" +
                "    \"birthday\": \"1988-05-03\",\n" +
                "    \"firstName\": \"Avril\",\n" +
                "    \"lastName\": \"Sp\",\n" +
                "    \"salary\": 15000,\n" +
                "    \"observingStarId\": 78\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 209054.25,\n" +
                "    \"birthday\": \"1988-05-03\",\n" +
                "    \"firstName\": \"Wesle Mara MaraVeg\",\n" +
                "    \"lastName\": \"Spencer von de Bergen ScwharzV\",\n" +
                "    \"salary\": 15001,\n" +
                "    \"observingStarId\": 78\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 34635.09,\n" +
                "    \"birthday\": \"1995-08-14\",\n" +
                "    \"firstName\": \"Xorhe\",\n" +
                "    \"lastName\": \"Sirius\",\n" +
                "    \"salary\": 14999,\n" +
                "    \"observingStarId\": 87\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 83542.93,\n" +
                "    \"birthday\": \"1980-12-10\",\n" +
                "    \"firstName\": \"Spencer von de Bergen\",\n" +
                "    \"lastName\": \"Spencer\",\n" +
                "    \"salary\": 0.0,\n" +
                "    \"observingStarId\": 91\n" +
                "  }\n" +
                "]";
        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOriginalFile() {
        return new File("src/main/resources/files/json/astronomers.json");
    }

    private void returnOriginalValue() {
        try {
            FileWriter f2 = new FileWriter(getOriginalFile(), false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/astronomers.json"));
            f2.write(testOriginalFile);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}