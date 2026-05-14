package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private static final String STARS_FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {

        Path path = Paths.get(STARS_FILE_PATH);
        List<String> lines = Files.readAllLines(path);

        return String.join("\n", lines);
    }

    @Override
    public String importStars() throws IOException {
        String fileContent = this.readStarsFileContent();

        StarDto[] dtos = this.gson.fromJson(fileContent, StarDto[].class);

        List<String> result = new ArrayList<>();

        for (StarDto dto : dtos) {
            boolean isValid = validateDto(dto);


            if (!isValid) {
                result.add("Invalid star");
                continue;
            } else {

                    Star star = new Star();
                    Optional<Constellation> constellation =
                            this.constellationRepository.findById(dto.getConstellation());
                    star.setConstellation(constellation.get());
                    star.setName(dto.getName());
                    star.setDescription(dto.getDescription());
                    star.setStarType(dto.getStarType());
                    star.setLightYears(dto.getLightYears());

                result.add(String.format("Successfully imported star %s - %.2f light years",
                        dto.getName(), dto.getLightYears()));
                this.starRepository.save(star);
            }


        }

        return String.join("\n", result);
    }

    private boolean validateDto(StarDto dto) {

        if (dto.getName() == null || dto.getName().length() < 2 || dto.getName().length() > 30) {
        return false;
        }

        if (dto.getLightYears() == null || dto.getLightYears() < 0) {
            return false;
        }

        if (dto.getDescription() == null || dto.getDescription().length() < 6) {
            return false;
        }

        if (dto.getStarType() == null || (!dto.getStarType().equals("RED_GIANT") && !dto.getStarType().equals("WHITE_DWARF") && !dto.getStarType().equals("NEUTRON_STAR"))) {
            return false;
        }

        Optional<Constellation> constellation = this.constellationRepository.findById(dto.getConstellation());

        if (constellation.isEmpty()) {
            return false;
        }

        int count = this.starRepository.countByName(dto.getName());

        return count == 0;
    }

    @Override
    public String exportStars() {
        List<Star> stars = this.starRepository.findByStarTypeAndObserversIsEmptyOrderByLightYearsAsc("RED_GIANT");
        return stars.stream().map(Star::toString).collect(Collectors.joining("\n"));
    }
}