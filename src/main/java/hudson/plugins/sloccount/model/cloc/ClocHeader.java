package hudson.plugins.sloccount.model.cloc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

/**
 * Cloc XML, header element.
 *
 * @author Michal Turek
 * @since 1.20
 */
public class ClocHeader implements Serializable {
    private static final long serialVersionUID = 1;

    @XStreamAlias("cloc_url")
    private String clocUrl;

    @XStreamAlias("cloc_version")
    private String clocVersion;

    @XStreamAlias("elapsed_seconds")
    private String elapsedSeconds;

    @XStreamAlias("n_files")
    private int filesCount;

    @XStreamAlias("n_lines")
    private int linesCount;

    @XStreamAlias("files_per_second")
    private String filesPerSecond;

    @XStreamAlias("lines_per_second")
    private String linesPerSecond;

    @XStreamAlias("report_file")
    private String reportFile;

    /**
     * This constructor is required by JAXB.
     */
    public ClocHeader() {
    }

    public String getClocUrl() {
        return clocUrl;
    }

    public String getClocVersion() {
        return clocVersion;
    }

    public String getElapsedSeconds() {
        return elapsedSeconds;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public String getFilesPerSecond() {
        return filesPerSecond;
    }

    public String getLinesPerSecond() {
        return linesPerSecond;
    }

    public String getReportFile() {
        return reportFile;
    }
}
