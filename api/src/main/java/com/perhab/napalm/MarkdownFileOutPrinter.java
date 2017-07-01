package com.perhab.napalm;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

/**
 * Created by bigbear3001 on 01.07.17.
 */
@Slf4j
public class MarkdownFileOutPrinter implements Printer {

    private static final long STARTTIME_NOT_SET = -1L;

    private final File file;

    public MarkdownFileOutPrinter(String filename) {
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
            writer.append("|Name|  1|100|10000|1000000|\n");
            writer.append("|:---|--:|--:|----:|------:|\n");
            for (Result result : results) {
                writer.append("|");
                writer.append(result.getStatement().toString());
                long startTime = STARTTIME_NOT_SET;
                for (long time : result.getTimes()) {
                    if (startTime == STARTTIME_NOT_SET) {
                        startTime = time;
                    } else {
                        writer.append('|');
                        writer.append((time - startTime) + "");
                        writer.append("ms");
                    }
                }
                writer.append("|\n");
                writer.flush();
            }
        } catch (java.io.IOException e) {
            log.error("Cannot write to file: {}", file.getAbsolutePath(), e);
        }
    }
}
