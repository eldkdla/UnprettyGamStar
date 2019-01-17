package com.gamstar.filecontroller;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class NewspeedMediaReNamePolicy implements FileRenamePolicy{

    public File rename(File f) {
        String name = f.getName();
        String body = null;
        String ext = null;
 
        int dot = name.lastIndexOf(".");
        
        if (dot != -1) {
            ext = name.substring(dot); // includes "."
        } else {
            ext = ".png";
        }
        body = Long.toString( System.currentTimeMillis() );
 
        File renameFile = new File( f.getParent(), body + ext );
         
        if( renameFile.exists() ){
            int count = 0;
            do {
                count++;
                String newName = body + count + ext;
                renameFile = new File(f.getParent(), newName);
            }while( renameFile.exists() && count < 9999 );
             
        }
        f.renameTo( renameFile );
        return renameFile;
         
    }

}
