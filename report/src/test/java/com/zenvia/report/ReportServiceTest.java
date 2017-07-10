package com.zenvia.report;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportServiceTest {

    private ReportService service;

    @Before
    public void setUp(){
        service = new ReportService();
    }

    @Test
    public void processLayoutB(){
        Report report = service.process("layout_b.txt", "delimit");
        System.out.println(report.getHeader());
        Assert.assertEquals("Layout B", report.getName());
    }

    @Test
    public void processLayoutA(){
        Report report = service.process("layout_a.txt", "position");
        System.out.println(report.getHeader());
        Assert.assertEquals("Layout A", report.getName());
    }

    @Test
    public void processConflictHeaderStartColunmRange(){
        Report report = service.process("layout_header_conflict_start_range.txt", "position");
        Assert.assertNull(report);
    }

    @Test
    public void processConflictHeaderEndColunmRange(){
        Report report = service.process("layout_header_conflict_end_range.txt", "position");
        Assert.assertNull(report);
    }

    @Test
    public void processInvalidStartNumberColumn(){
        Report report = service.process("layout_header_invalid_start_number_colunm.txt","position");
        Assert.assertNull(report);
    }

    @Test
    public void processInvalidEndNumberColumn(){
        Report report = service.process("layout_header_invalid_end_number_colunm.txt", "position");
        Assert.assertNull(report);
    }

    @Test
    public void processInvalidStartEndNumberColumn(){
        Report report = service.process("layout_header_invalid_start_end_number_colunm.txt", "position");
        Assert.assertNull(report);
    }


}
