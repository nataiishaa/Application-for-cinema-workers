package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.example.model.Movie;

import java.util.List;

import org.example.model.Session;
import org.example.util.LocalStorageManager;

/**
 * The LocalStorageManagerImpl class provides an implementation of the LocalStorageManager interface
 * using the Jackson library for JSON serialization and deserialization.
 * <p>
 * It includes methods to write and read generic data to/from a file, as well as specific methods
 * for reading and writing lists of Movie and Session objects.
 **/
public class LocalStorageManagerImpl<T> implements LocalStorageManager<T> {
    private final String fileName;
    private final TypeReference<T> typeReference;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String RESOURCES_PATH = "src/main/resources/";

    public LocalStorageManagerImpl(String fileName, TypeReference<T> typeReference) {
        this.fileName = fileName;
        this.typeReference = typeReference;
    }

    public void writeToFile(T data) throws IOException {
        String jsonString = objectMapper.writeValueAsString(data);
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        Files.writeString(filePath, jsonString);
    }

    @Override
    public T readFromFile() throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        String jsonString = Files.readString(filePath);
        return objectMapper.readValue(jsonString, typeReference);
    }

    // Метод для чтения фильмов из файла
    public List<Movie> readMoviesFromFile(String fileName) throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        String jsonString = Files.readString(filePath);
        return objectMapper.readValue(jsonString, new TypeReference<List<Movie>>() {
        });
    }

    public void writeMoviesToFile(List<Movie> movies, String fileName) throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        String jsonString = objectMapper.writeValueAsString(movies);
        Files.writeString(filePath, jsonString);
    }

    // Метод для записи сессий в файл
    public void writeSessionsToFile(List<Session> sessions, String fileName) throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        String jsonString = objectMapper.writeValueAsString(sessions);
        Files.writeString(filePath, jsonString);
    }

    // Метод для чтения сессий из файла
    public List<Session> readSessionsFromFile(String fileName) throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH + fileName);
        String jsonString = Files.readString(filePath);
        return objectMapper.readValue(jsonString, new TypeReference<List<Session>>() {
        });
    }
}
