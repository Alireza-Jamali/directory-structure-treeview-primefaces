package com.aeza.service;

import com.aeza.Document;
import com.aeza.Scout;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "documentService")
@SessionScoped
public class DocumentService implements Serializable {

    private boolean sw = false;
    private String address;
    
    @ManagedProperty("#{scout}")
    Scout scout;

    public Scout getScout() {
        return scout;
    }

    public void setScout(Scout scout) {
        this.scout = scout;
    }

    public String checkAddress() {

        if (sw) {
            sw = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Entered address is: '" + address + "'"));
            address = null;
            return null;
        } else {
            return "imageTree.xhtml";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        File file;

        try {

            file = new File(address);

            if (!file.exists()) {
                System.out.println("Error: this path '" + file + "' doesn't exist apparantly!");
                sw = true;
                return;
            }

            if (file.getParent() == null) {
                System.out.println("Error: parent Directory can't be Null, choose another folder");
                sw = true;
                return;
            }

            if (!file.isDirectory() && !file.isFile()) {
                System.out.println("system can't recoginze ur file as directory nor a file");
                sw = true;
                return;
            }
        } catch (Exception e) {
            sw = true;
            return;
        }

        this.address = address;
    }

    public TreeNode createDocuments() {

        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);

        try {

            File file = new File(address);

            scout.init(file, root);

            address = null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }
   
    
}
