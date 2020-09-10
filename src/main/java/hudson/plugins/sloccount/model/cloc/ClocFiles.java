package hudson.plugins.sloccount.model.cloc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.io.Serializable;
import java.util.List;

/**
 * Cloc XML, files element.
 *
 * @author Michal Turek
 * @since 1.20
 */
public class ClocFiles implements Serializable {
    private static final long serialVersionUID = 1;

    @XStreamImplicit(itemFieldName="file")
    private final List<ClocFile> files;

    @XStreamAlias("total")
    private final ClocTotal total;

    /**
     * Constructor.
     * @param files files
     * @param total total
     */
    public ClocFiles(List<ClocFile> files, ClocTotal total) {
        this.files = files;
        this.total = total;
    }

    /**
     * This constructor is required by JAXB.
     */
    public ClocFiles() {
        this(null, null);
    }

    public List<ClocFile> getFiles() {
        return files;
    }

    public ClocTotal getTotal() {
        return total;
    }
}
