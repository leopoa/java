package com.amarilho.loja.service;

import com.amarilho.loja.model.Venda;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAsync
@EnableScheduling
public class ContabilidadeService {

    public static final int FIRST = 0;
    Log log = LogFactory.getLog(ContabilidadeService.class);

    private FileService fileService;

    @Value("${pathIn}")
    private String pathIn;

    @Value("${pathOut}")
    private String pathOut;

    @Value("${cliente}")
    private String cliente;

    @Value("${vendedor}")
    private String vendedor;

    @Value("${venda}")
    private String venda;

    @Value("${extensao}")
    private String extensao;

    @Autowired
    public ContabilidadeService(FileService fileService){
        this.fileService = fileService;
    }

    @Scheduled(fixedDelay=25000)
    public boolean init() {
        fileService
                .getAllFilesInDirectoryByExtension(pathIn, extensao)
                .parallelStream()
                .forEach(pathFiles -> summarize(pathFiles));

        return Boolean.TRUE;
    }

    protected boolean summarize(Path filePath){
        List<Venda> vendasOrderByTotal = summarizeVendasOrderByTotal(filePath);

        List<String> result = buildResponseData(summarizeTotalByType(filePath, cliente),
                                                summarizeTotalByType(filePath, vendedor),
                                                summarizeIdMaiorVenda(vendasOrderByTotal),
                                                summarizePiorVendedor(vendasOrderByTotal));

        fileService.renameFile(filePath);

        return fileService.createResponseFile(pathOut, filePath.getFileName().toString(), result);
    }

    private List<String> buildResponseData(long totalClientes, long totalVendedores, String idMaiorVenda, String piorVendedor) {
        return Arrays.asList("Total clientes: " + totalClientes,
                             "Total vendedores: " + totalVendedores,
                             "Id maior venda: " + idMaiorVenda,
                             "Pior vendedor: " + piorVendedor);
    }

    protected List<Venda> summarizeVendasOrderByTotal(Path filePath){
        return fileService
                .readFileByType(filePath, venda)
                .map(line -> new Venda(line))
                .sorted(Comparator.comparing(Venda::getTotal).reversed())
                .collect(Collectors.toList());
    }

    protected Long summarizeTotalByType(Path filePath, String type){
        return fileService
                .readFileByType(filePath, type)
                .count();
    }

    protected String summarizeIdMaiorVenda(List<Venda> vendasOrderByTotal){
        return vendasOrderByTotal.size() > 0 ? vendasOrderByTotal.get(FIRST).getId() : "";
    }

    protected String summarizePiorVendedor(List<Venda> vendasOrderByTotal){
        return vendasOrderByTotal.size() > 0 ? vendasOrderByTotal.get(lastIndex(vendasOrderByTotal)).getVendedor() : "";
    }

    private int lastIndex(List<Venda> vendasOrderByTotal) {
        return vendasOrderByTotal.size()-1;
    }

    public void setPathIn(String pathIn) {
        this.pathIn = pathIn;
    }

    public void setPathOut(String pathOut) {
        this.pathOut = pathOut;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public void setVenda(String venda) {
        this.venda = venda;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
}
