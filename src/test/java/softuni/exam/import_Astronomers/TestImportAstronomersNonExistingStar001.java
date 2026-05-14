package softuni.exam.import_Astronomers;
//TestImportAstronomersNonExistingStar001

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
public class TestImportAstronomersNonExistingStar001 {

    @Autowired
    private AstronomerServiceImpl astronomerService;

    @Sql({"/constellation-test-imports.sql", "/stars-test-imports.sql"})
    @Test
    void importAstronomersValidateWithNonExistingStar001() throws IOException {
        rewriteFileForTest();

        String expected = "Successfully imported astronomer Helena Bishop - 22836.19\n" +
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
                "    \"averageObservationHours\": 22836.19,\n" +
                "    \"birthday\": \"1980-01-16\",\n" +
                "    \"firstName\": \"Helena\",\n" +
                "    \"lastName\": \"Bishop\",\n" +
                "    \"salary\": 294111.44,\n" +
                "    \"observingStarId\": 80\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 31049.67,\n" +
                "    \"birthday\": \"1966-05-05\",\n" +
                "    \"firstName\": \"Dorian\",\n" +
                "    \"lastName\": \"Sawyer\",\n" +
                "    \"salary\": 161438.11,\n" +
                "    \"observingStarId\": 101\n" +
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