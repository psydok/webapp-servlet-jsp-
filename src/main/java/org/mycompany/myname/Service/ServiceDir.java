package org.mycompany.myname.Service;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class ServiceDir {
    private String currentDir;

    private ArrayList<File> directory = new ArrayList<>();
    private ArrayList<File> simpleFiles = new ArrayList<>();
    //    private Path pathStart = Paths.get(this.currentDir);


    public ArrayList<File> getSimpleFiles() {
        return simpleFiles;
    }

    public ArrayList<File> getChildDir() {
        return directory;
    }

    public String getCurrentDir() {
        return currentDir;
    }


    public boolean isChild(String path) {
        File pathDir = new File(path);
        this.currentDir = pathDir.getParent();
//        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathDir)) {
//            for (Path child : dirStream) {
//                if (Files.isRegularFile(child))
//                    simpleFiles.add(child);
//                else if (Files.isDirectory(child))
//                    directory.add(child);
//            }
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
/* то, что вышел сработало, но почему-то getName(1) выводил только адрес директории и много "макс" как ссылки и нет...
* http://localhost:8888/?path=C:\Users\макс\Desktop\HelloHTML1\images/*/
        try {
            for (File arg : pathDir.listFiles()) {
                if (arg.isDirectory())
                    directory.add(arg);
                if (arg.isFile())
                    simpleFiles.add(arg);
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
