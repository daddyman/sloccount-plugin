package hudson.plugins.sloccount.model.cloc;

import hudson.plugins.sloccount.model.SloccountReportInterface;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;

/**
 * Cloc report parser and the parsed file.
 *
 * @author Michal Turek
 * @since 1.20
 */
public class ClocReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private final ClocHeader header;

    private final ClocFiles files;

    /**
     * Constructor.
     * @param header header
     * @param files files
     */
    public ClocReport(ClocHeader header, ClocFiles files) {
        this.header = header;
        this.files = files;
    }

    /**
     * This constructor is required by JAXB.
     */
    public ClocReport() {
        this(null, null);
    }

    public ClocHeader getHeader() {
        return header;
    }

    public ClocFiles getFiles() {
        return files;
    }

    /**
     * Parse one input file.
     *
     * @param file the file to be parsed
     * @return the content of the parsed file in form of a report
     * @throws javax.xml.bind.IOException if a XML related error occurs
     */
    public static ClocReport parse(File file) throws IOException {
        try {
            final XStream xstream = new XStream();
            xstream.alias("results", ClocReport.class);
            xstream.processAnnotations(ClocReport.class);

            return (ClocReport) xstream.fromXML(file);
        } catch (Exception ex) {
            throw new IOException("Failed to deserialize cloc report", ex);
        }
    }

    /**
     * Convert cloc report to SLOCCount report representation used in the plugin.
     *
     * @param report        output report
     * @param commentIsCode include comments to the measured lines
     * @throws IOException if the report has unexpected structure
     */
    public void toSloccountReport(SloccountReportInterface report, boolean commentIsCode)
            throws IOException {
        try {
            for (ClocFile file : files.getFiles()) {
                // Get rid of Microsoft's incompatibility once and forever
                String filePath = file.getName().replace('\\', '/');

                int begin = filePath.indexOf('/');
                int end = filePath.indexOf('/', begin + 1);
                String moduleName = (begin != -1 && end != -1) ? filePath.substring(begin + 1, end) : "";

                int lineCount = file.getCode();
                if (commentIsCode) {
                    lineCount += file.getComment();
                }

                report.add(filePath, file.getLanguage(), moduleName, lineCount, file.getComment());
            }
        } catch (RuntimeException e) {
            throw new IOException("Broken cloc report file: " + e, e);
        }
    }
}
