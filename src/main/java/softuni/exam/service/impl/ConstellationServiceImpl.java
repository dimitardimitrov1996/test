package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String CONSTELLATIONS_FILE_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    public ConstellationServiceImpl(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {

        Path path = Paths.get(CONSTELLATIONS_FILE_PATH);

        List<String> lines = Files.readAllLines(path);

        return String.join("\n", lines);
    }

    @Override
    public String importConstellations() throws IOException {

        String fileContent = this.readConstellationsFromFile();

        ConstellationDto[] dtos = this.gson.fromJson(fileContent, ConstellationDto[].class);

        List<String> result = new ArrayList<>();

        for (ConstellationDto dto : dtos) {
            boolean isValid = validateDto(dto);

            if (!isValid) {
                result.add("Invalid constellation");
                continue;
            } else {

                Constellation constellation = new Constellation();
                constellation.setName(dto.getName());
                constellation.setDescription(dto.getDescription());

                result.add("Successfully imported constellation "
                        + dto.getName() + " - " + dto.getDescription());
                this.constellationRepository.save(constellation);
            }

        }

        return String.join("\n", result);
    }

    private boolean validateDto(ConstellationDto dto) {

        if (dto.getName() == null || dto.getDescription() == null) {
            return false;
        }

        if (dto.getName().length() < 3 || dto.getName().length() > 20) {
            return false;
        }

        if (dto.getDescription().length() <= 5) {
            return false;
        }


        int count = this.constellationRepository.countByName(dto.getName());

        return count == 0;
    }
}