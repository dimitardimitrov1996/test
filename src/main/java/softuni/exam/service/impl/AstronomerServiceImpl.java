package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private static final String ASTRONOMERS_FILE_PATH = "src/main/resources/files/json/astronomers.json";
    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final Gson gson;
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {

        Path path = Paths.get(ASTRONOMERS_FILE_PATH);

        List<String> lines = Files.readAllLines(path);

        return String.join("\n", lines);
    }

    @Override
    public String importAstronomers() throws IOException {

        String fileContent = this.readAstronomersFromFile();
        AstronomerDto[] dtos = this.gson.fromJson(fileContent, AstronomerDto[].class);

        List<String> result = new ArrayList<>();

        for (AstronomerDto dto : dtos) {
            boolean isValid = validateDto(dto);

            if (!isValid) {
                result.add("Invalid astronomer");
                continue;
            } else {
                Astronomer astronomer = new Astronomer();
                Optional<Star> star = this.starRepository.findById(dto.getObservingStarId());
                astronomer.setStar(star.get());
                astronomer.setFirstName(dto.getFirstName());
                astronomer.setLastName(dto.getLastName());
                astronomer.setBirthday(LocalDate.parse(dto.getBirthday()));
                astronomer.setSalary(dto.getSalary());
                astronomer.setAverageObservationHours(dto.getAverageObservationHours());


                result.add(String.format("Successfully imported astronomer %s %s - %.2f"
                        , dto.getFirstName(), dto.getLastName(),
                dto.getAverageObservationHours()));
                this.astronomerRepository.save(astronomer);
            }

        }

        return String.join("\n", result);
    }

    private boolean validateDto(AstronomerDto dto) {

        if (dto.getFirstName() == null || dto.getLastName() == null) {
            return false;
        }

        if (dto.getFirstName().length() < 2 || dto.getFirstName().length() > 30) {
            return false;
        }

        if (dto.getLastName().length() < 2 || dto.getLastName().length() > 30) {
            return false;
        }

        if (dto.getSalary() == null || dto.getSalary() < 15000.00) {
            return false;
        }

        if (dto.getAverageObservationHours() == null || dto.getAverageObservationHours() < 500.00) {
            return false;
        }

        Optional<Star> star = this.starRepository.findById(dto.getObservingStarId());
        if (star.isEmpty()) {
            return false;
        }

        int count = this.astronomerRepository.countByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

        return count == 0;
    }
}