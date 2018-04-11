package com.aeza;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name="scout")
@SessionScoped
public class Scout implements Serializable {

    boolean setExpanded = true;
    private int filesNumber;
    private int foldersNumber;

    public int getFilesNumber() {
        return filesNumber;
    }
    public int getFoldersNumber() {
        return foldersNumber;
    }
    
    public void init(File file, TreeNode node) throws IOException {
        
        TreeNode dir;

        if (file.isDirectory()) {

            dir = new DefaultTreeNode(new Document(file.getName(), "-", "Folder"), node);
            foldersNumber++;
            
            
            if (setExpanded) {
                dir.setExpanded(true);
                setExpanded = false;
                System.out.println("set ex is: "+setExpanded);
            }
            
            for (File files: file.listFiles()){
                init(files, dir);
            }
        }
        
        if (file.isFile()) {
            new DefaultTreeNode("picture", new Document(file.getPath(), "0 KB", "JPEG Image"), node);
            filesNumber++;
        }
        
    }
}
