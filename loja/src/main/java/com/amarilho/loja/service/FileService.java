package com.amarilho.loja.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileService {

    public static final String OLD = ".old";
    public static final String RESPONSE_FILE = ".done.dat";

    Log log = LogFactory.getLog(FileService.class);

    public List<Path> getAllFilesInDirectoryByExtension(String path, String extension){
        List<Path> listPaths = new ArrayList<>();

        if(StringUtils.isEmpty(path)) return listPaths;

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(pathFile -> filterByExtension(pathFile.getFileName().toString(), extension))
                    .collect(Collectors.toList());

        } catch (IOException e){
            log.fatal("Erro ao buscar informacoes do diretorio", e);
        }

        return listPaths;
    }

    public Stream<String> readFileByType(Path pathFile, String typeFilter){
        try {
            return Files
                    .lines(pathFile)
                    .filter(line -> line.startsWith(typeFilter));

        } catch (IOException e) {
            log.fatal("Erro ao ler arquivo - PATH_FILE["+pathFile+"]", e);
            return Stream.of();
        }
    }

    public boolean createResponseFile(String path, String fileName, List<String> lines){
        String pathFile = path + fileName.replaceAll(".dat", RESPONSE_FILE);

        try {
            Files.write(Paths.get(pathFile), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return Boolean.TRUE;

        } catch (IOException e) {
            log.fatal("Erro ao criar arquivo de saida - PATH_FILE[" + pathFile + "]", e);
            return Boolean.FALSE;
        }
    }

    public Path renameFile(Path pathFile){
        try {
            return Files.move(pathFile, pathFile.resolveSibling(pathFile.getFileName() + OLD));
        } catch (IOException e){
            log.fatal("Erro ao renomear arquivo- PATH_FILE["+pathFile+"]", e);
            return null;
        }
    }

    private boolean filterByExtension(String pathFile, String extension) {
        return StringUtils.isEmpty(extension) ? true : pathFile.endsWith(extension);
    }


}
