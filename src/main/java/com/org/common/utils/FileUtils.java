package com.org.common.utils;

import com.org.common.config.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    public String getFilePath(String fileName){
        Environment env=new Environment();
        File rootDirectory = new File(System.getProperty("user.dir"));
        final List<File> foundFiles = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
            walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith(fileName)) {
                            foundFiles.add(f.toFile());
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i< foundFiles.size();i++){
            String environment=env.readProperties("env");
            if(foundFiles.get(i).toString().contains(environment)){
                return foundFiles.get(i).toString();
            }
        }
        return "FILE NOT FOUND";
    }
}
