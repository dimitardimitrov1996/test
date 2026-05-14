package softuni.exam.readFile;
//TestAstronomerServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.AstronomerServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestAstronomerServiceReadFromFile {

    @InjectMocks
    private AstronomerServiceImpl astronomerService;

    @Test
    void readAstronomersFromFile() throws IOException {
        String expected = "[\n" +
                "  {\n" +
                "    \"averageObservationHours\": 176858.79,\n" +
                "    \"birthday\": \"1989-01-01\",\n" +
                "    \"firstName\": \"Drake\",\n" +
                "    \"lastName\": \"Hawthorne\",\n" +
                "    \"salary\": 207615.71,\n" +
                "    \"observingStarId\": 50\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 300.79,\n" +
                "    \"birthday\": \"1989-01-01\",\n" +
                "    \"firstName\": \"Drake\",\n" +
                "    \"lastName\": \"Hawthorne\",\n" +
                "    \"salary\": 207615.71,\n" +
                "    \"observingStarId\": 50\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 176858.79,\n" +
                "    \"birthday\": \"1989-01-01\",\n" +
                "    \"firstName\": \"Drake\",\n" +
                "    \"lastName\": \"Hawthorne\",\n" +
                "    \"salary\": 207615.71,\n" +
                "    \"observingStarId\": 50\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 55537.43,\n" +
                "    \"birthday\": \"1966-11-03\",\n" +
                "    \"firstName\": \"Elena\",\n" +
                "    \"lastName\": \"Sullivan\",\n" +
                "    \"salary\": 98319.24,\n" +
                "    \"observingStarId\": 91\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 105443.87,\n" +
                "    \"birthday\": \"1954-09-02\",\n" +
                "    \"firstName\": \"Cassandra\",\n" +
                "    \"lastName\": \"Bellamy\",\n" +
                "    \"salary\": 1499.52,\n" +
                "    \"observingStarId\": 28\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 72795.68,\n" +
                "    \"birthday\": \"1993-08-14\",\n" +
                "    \"firstName\": \"Victor\",\n" +
                "    \"lastName\": \"Adams\",\n" +
                "    \"salary\": 316456.16,\n" +
                "    \"observingStarId\": 42\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 105443.87,\n" +
                "    \"birthday\": \"1954-09-02\",\n" +
                "    \"firstName\": \"Cassandra\",\n" +
                "    \"lastName\": \"Bellamy\",\n" +
                "    \"salary\": 111076.52,\n" +
                "    \"observingStarId\": 28\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 106936.42,\n" +
                "    \"birthday\": \"1959-06-03\",\n" +
                "    \"firstName\": \"Nathaniel\",\n" +
                "    \"lastName\": \"Reynolds\",\n" +
                "    \"salary\": 101754.41,\n" +
                "    \"observingStarId\": 16\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 241603.8,\n" +
                "    \"birthday\": \"1958-09-04\",\n" +
                "    \"firstName\": \"Serena\",\n" +
                "    \"lastName\": \"Chandler\",\n" +
                "    \"salary\": 98328.09,\n" +
                "    \"observingStarId\": 16\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 153446.68,\n" +
                "    \"birthday\": \"1951-03-10\",\n" +
                "    \"firstName\": \"Gabriel\",\n" +
                "    \"lastName\": \"Fletcher\",\n" +
                "    \"salary\": 62358.41,\n" +
                "    \"observingStarId\": 54\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 244764.96,\n" +
                "    \"birthday\": \"1989-05-24\",\n" +
                "    \"firstName\": \"Lorelei\",\n" +
                "    \"lastName\": \"Blackwell\",\n" +
                "    \"salary\": 153955.54,\n" +
                "    \"observingStarId\": 36\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 183403.7,\n" +
                "    \"birthday\": \"1983-04-12\",\n" +
                "    \"firstName\": \"Sebastian\",\n" +
                "    \"lastName\": \"Maxwell\",\n" +
                "    \"salary\": 54559.97,\n" +
                "    \"observingStarId\": 23\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 150617.05,\n" +
                "    \"birthday\": \"1980-07-26\",\n" +
                "    \"firstName\": \"Aurora\",\n" +
                "    \"lastName\": \"Hamilton\",\n" +
                "    \"salary\": 211196.44,\n" +
                "    \"observingStarId\": 74\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 16667.34,\n" +
                "    \"birthday\": \"1960-01-02\",\n" +
                "    \"firstName\": \"Julian\",\n" +
                "    \"lastName\": \"Whitaker\",\n" +
                "    \"salary\": 66661.49,\n" +
                "    \"observingStarId\": 64\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 121317.97,\n" +
                "    \"birthday\": \"1976-04-03\",\n" +
                "    \"firstName\": \"Genevieve\",\n" +
                "    \"lastName\": \"Archer\",\n" +
                "    \"salary\": 67686.33,\n" +
                "    \"observingStarId\": 18\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 148528.89,\n" +
                "    \"birthday\": \"1958-04-26\",\n" +
                "    \"firstName\": \"Elias\",\n" +
                "    \"lastName\": \"Donovan\",\n" +
                "    \"salary\": 288559.38,\n" +
                "    \"observingStarId\": 42\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 55643.78,\n" +
                "    \"birthday\": \"1986-07-15\",\n" +
                "    \"firstName\": \"Isadora\",\n" +
                "    \"lastName\": \"Fitzgerald\",\n" +
                "    \"salary\": 334024.69,\n" +
                "    \"observingStarId\": 64\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 209054.25,\n" +
                "    \"birthday\": \"1988-05-03\",\n" +
                "    \"firstName\": \"Lysander\",\n" +
                "    \"lastName\": \"Spencer\",\n" +
                "    \"salary\": 108576.5,\n" +
                "    \"observingStarId\": 78\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 34635.09,\n" +
                "    \"birthday\": \"1995-08-14\",\n" +
                "    \"firstName\": \"Astrid\",\n" +
                "    \"lastName\": \"Webster\",\n" +
                "    \"salary\": 104422.67,\n" +
                "    \"observingStarId\": 87\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 83542.93,\n" +
                "    \"birthday\": \"1980-12-10\",\n" +
                "    \"firstName\": \"Leander\",\n" +
                "    \"lastName\": \"Valentine\",\n" +
                "    \"salary\": 377608.72,\n" +
                "    \"observingStarId\": 91\n" +
                "  },\n" +
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
                "    \"observingStarId\": 77\n" +
                "  },\n" +
                "  {\n" +
                "    \"averageObservationHours\": 20537.9,\n" +
                "    \"birthday\": \"1957-09-04\",\n" +
                "    \"firstName\": \"Vivienne\",\n" +
                "    \"lastName\": \"Griffin\",\n" +
                "    \"salary\": 252195.46,\n" +
                "    \"observingStarId\": 24\n" +
                "  }\n" +
                "]";

        String actual = astronomerService.readAstronomersFromFile();

        Assertions.assertEquals(expected, actual);
    }
}