package com.amarilho.loja.service;

import com.amarilho.loja.service.FileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;


public class FileServiceTest {

    FileService service;

    @Before
    public void setUp(){
        service = new FileService();
    }

    @Test
    public void getAllFilesInDirectory() {
        Assert.assertNotNull(service.getAllFilesInDirectoryByExtension("src/test/resources", ".dat"));
    }

    @Test
    public void getAllFilesInDirectoryWhenNotFound() {
        Assert.assertEquals(0, service.getAllFilesInDirectoryByExtension("src/test/resources", ".pdf").size());
    }

    @Test
    public void getAllFilesInDirectoryWhenInvalidExtension() {
        Assert.assertNotNull(service.getAllFilesInDirectoryByExtension("src/test/resources", null));
    }

    @Test
    public void getAllDatFilesInDirectoryWhenInvalidData() {
        Assert.assertEquals(0, service.getAllFilesInDirectoryByExtension(null, null).size());
    }

    @Test
    public void getAllClientLines() {
        Assert.assertEquals(2, service.readFileByType(Paths.get("src/test/resources/dados.dat"), "002").count());
    }

}