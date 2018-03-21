package tian.pusen.java7.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathSample {
    public static void main(String[] args) throws IOException {  
        Path path=Paths.get("ReaderMe.txt");  
        System.out.println("root:"+path.getRoot());  
        System.out.println("path:"+path.toString());  
        System.out.println("fileName:"+path.getFileName());  
        System.out.println("nameCount:"+path.getNameCount());  
        System.out.println("name:"+path.getName(path.getNameCount()-1));  

        Path realPath=path.toRealPath(LinkOption.NOFOLLOW_LINKS);  
        System.out.println("real path root:"+realPath.getRoot());  
        System.out.println("real path:"+realPath);  
        
        //获得path方法一,e:/logs/access.log  
        Path path1 = FileSystems.getDefault().getPath("e:/logs", "access.log");  
        System.out.println(path1.getNameCount());  
        //获得path方法二，用File的toPath()方法获得Path对象  
        File file = new File("e:/logs/access.log");  
        Path pathOther = file.toPath();  
        //0,说明这两个path是相等的  
        System.out.println(path1.compareTo(pathOther));  
        //获得path方法三  
        Path path3 = Paths.get("e:/logs", "access.log");  
        System.out.println(path3.toString());  
    }  
}
