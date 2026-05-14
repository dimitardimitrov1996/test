package softuni.exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

// TODO: Implement all methods
public interface AstronomerService {

    boolean areImported();

    String readAstronomersFromFile() throws IOException;

	String importAstronomers() throws IOException;

}
