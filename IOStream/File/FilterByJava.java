package IOStream.File;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 过滤器，只有文件名末尾为.java的文件返回true
 * Created by Taocr on 2016/11/2.
 */
public class FilterByJava implements FilenameFilter{

    @Override
    public boolean accept(File dir, String name) {

        return name.endsWith(".java");
    }
}
