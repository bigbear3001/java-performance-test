package com.perhab.napalm;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by bigbear3001 on 01.07.17.
 */
@Slf4j
public class HtmlFileOutPrinter implements Printer {

    private static final long STARTTIME_NOT_SET = -1L;

    private final File file;

    public HtmlFileOutPrinter(String filename) {
        file = new File(filename);
    }

    @Override
    public void print(Collection<Result> results) {
        try {
            if(file.exists()) {
                file.delete();
            }
            file.createNewFile();
            @Cleanup
            FileOutputStream fos = new FileOutputStream(file);
            @Cleanup
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.append("<html><head><title>Performance Report</title></head><body>");
            writer.append("<h1>Performance Report</h1>\n");
            writer.append("<p>Created at " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + "<br />\n");
            writer.append("on " + getSystemInformation() + "</p>\n");
            writer.append("<table><tr><th>Name</th><th>1</th><th>100</th><th>10000</th><th>1000000</th></tr>\n");
            Class<?> lastImplementedInterface = null;
            for (Result result : results) {
                Class<?> implementedInterface = result.getStatement().getGroup().getImplementedInterface();
                if (implementedInterface != lastImplementedInterface) {
                    writer.append("<tr><td colspan=\"5\"><b>" + implementedInterface.getCanonicalName() + "</b></td></tr>\n");
                    lastImplementedInterface = implementedInterface;
                }
                writer.append("<tr><td>");
                writer.append(result.getStatement().toString());
                writer.append("");
                long startTime = STARTTIME_NOT_SET;
                for (long time : result.getTimes()) {
                    if (startTime == STARTTIME_NOT_SET) {
                        startTime = time;
                    } else {
                        writer.append("</td><td>");
                        writer.append((time - startTime) + "");
                        writer.append("ms");
                    }
                }
                writer.append("</td></tr>\n");
            }
            writer.append("</table></body></html>");
            writer.flush();

        } catch (java.io.IOException e) {
            log.error("Cannot write to file: {}", file.getAbsolutePath(), e);
        }
    }

    private String getSystemInformation() {
        SystemInfo systemInfo = new SystemInfo();
        return systemInfo.getHardware().getProcessor().toString();
    }
}
