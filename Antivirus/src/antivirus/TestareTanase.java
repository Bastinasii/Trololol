/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.io.*;
import java.lang.StringBuilder;

/**
 *
 * @author Tanase
 */
public class TestareTanase {
    public static AVL copac = new AVL();
    public static void getList(String path)
    {
    
    //List<String> list = new ArrayList<String>();
    
    File dir = new File(path);
    File[] listOfFiles = dir.listFiles();
    for(int i=0;i<listOfFiles.length;i++)
    {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
        copac.adaugNod(1,listOfFiles[i].getPath());
        //list.add(listOfFiles[i].getPath());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
        getList(listOfFiles[i].getPath());
      }
    }
    }
    public static void uploadItems(nodAVL n)
    {
        DBConnect.open();
        GenCrc crc = new GenCrc();
        File nou = new File(n.cale);
        String ext="";
        if (null != n.cale && n.cale.length() > 0 )
        {
            int endIndex = n.cale.lastIndexOf(".");
            if (endIndex != -1)  
            {
                ext = n.cale.substring(endIndex, n.cale.length()); // not forgot to put check if(endIndex != -1)
            }
        }  
        
        DBConnect.insertFile(nou.getName(),ext, "nothing yet", Long.toString(nou.lastModified()), nou.getPath(), Long.toString(nou.getTotalSpace()),Long.toBinaryString(crc.crcInput(nou.getPath())) );
        
        DBConnect.close();
        
        if(n.stanga!=null) {
        uploadItems(n.stanga);
        }
        if(n.dreapta!=null) {
        uploadItems(n.dreapta);
        }
    }
    public static void main(String [] args){
    getList("C:\\Users\\Tanase\\Pictures");
    copac.afisareContinutArbore(copac.root);
    uploadItems(copac.root);
    }
}
