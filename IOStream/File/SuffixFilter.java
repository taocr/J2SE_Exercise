package IOStream.File;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Taocr on 2016/11/2.
 */
public class SuffixFilter implements FilenameFilter {
    private String suffix;

    public SuffixFilter(String suffix){
        super();
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}
