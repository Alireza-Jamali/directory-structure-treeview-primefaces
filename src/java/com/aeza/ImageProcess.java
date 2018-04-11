package com.aeza;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.NodeSelectEvent;

@ManagedBean(name = "imageProcess")
@SessionScoped
public class ImageProcess implements Serializable {
    
    int i=0;
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(NodeSelectEvent img) {
        
        if (!img.getTreeNode().getType().equals("default")) {
            this.img = img.getTreeNode().toString();
            
        }
    }
}
