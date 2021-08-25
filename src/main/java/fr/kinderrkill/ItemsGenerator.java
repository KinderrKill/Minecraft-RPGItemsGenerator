package fr.kinderrkill;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsGenerator {

    private static ItemsGenerator instance;

    public ItemsGenerator() {
        instance = this;
    }

    public void init() {
        log("INIT");
    }

    public void start() {
        log("INIT");
    }

    public void close() {
        log("INIT");
    }

    public List<File> getAllFilesFromResource(String folder) throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(folder);

        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());

        return collect;
    }

    public void log(String message) {
        System.out.println("[Generator] " + message);
    }
    //Utils
    public static ItemsGenerator getInstance() {
        return instance;
    }
}
