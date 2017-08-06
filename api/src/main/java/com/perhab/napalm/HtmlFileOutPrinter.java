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

    private static final String BOOTSTRAP =
            "<!-- Compiled and minified CSS -->\n" +
            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n" +
            "<!-- Compiled and minified JavaScript -->\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>\n";

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
            writer.append("<html lang=\"en\">\n<head>\n<title>Performance Report</title>\n" + BOOTSTRAP + "</head>\n<body>\n");
            writer.append("<div class=\"container\">\n");
            writer.append("<h1>Performance Report</h1>\n");
            writer.append("<div class=\"jumbotron\">Created at " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + "<br />\n");
            writer.append("on " + getSystemInformation() + "</div>\n");
            Class<?> lastImplementedInterface = null;
            for (Result result : results) {
                Class<?> implementedInterface = result.getStatement().getGroup().getImplementedInterface();
                if (implementedInterface != lastImplementedInterface) {
                    if (lastImplementedInterface != null) {
                        writer.append("</tbody></table></div>");
                    }
                    writer.append("<div class=\"row\"><h2>" + implementedInterface.getCanonicalName() + "</h2>\n");
                    if (result.getStatement().getGroup().getDescription() != null) {
                        writer.append("<p>" + result.getStatement().getGroup().getDescription() + "</p>");
                    }
                    lastImplementedInterface = implementedInterface;
                    writer.append("<table class=\"table\"><thead><tr>" +
                            "<th>Name</th>" +
                            "<th class=\"text-right\"><span title=\"time needed for one invocation\">1</span></th>" +
                            "<th class=\"text-right\"><span title=\"time needed for one hundred invocations\">100</span></th>" +
                            "<th class=\"text-right\"><span title=\"time needed for ten thousand invocations\">10000</span></th>" +
                            "<th class=\"text-right\"><span title=\"time needed tor one million invocations\">1000000</span></th>" +
                            "</tr></thead><tbody>\n");
                }
                writer.append("<tr><td><span title=\"" + escapeHtmlAttribute(result.getStatement().getSourceCode()) + "\">");
                writer.append(result.getStatement().toString());
                writer.append("</span>");
                long startTime = STARTTIME_NOT_SET;
                for (long time : result.getTimes()) {
                    if (startTime == STARTTIME_NOT_SET) {
                        startTime = time;
                    } else {
                        writer.append("</td><td class=\"text-right\">");
                        writer.append((time - startTime) + "");
                        writer.append("ms");
                    }
                }
                writer.append("</td></tr>\n");
            }
            writer.append("</tbody></table></div></div></body></html>");
            writer.flush();

        } catch (java.io.IOException e) {
            log.error("Cannot write to file: {}", file.getAbsolutePath(), e);
        }
    }

    private static String escapeHtmlAttribute(String value) {
        return value.replaceAll("\"", "&quot;");
    }

    private String getSystemInformation() {
        SystemInfo systemInfo = new SystemInfo();
        return systemInfo.getHardware().getProcessor().toString();
    }
}
