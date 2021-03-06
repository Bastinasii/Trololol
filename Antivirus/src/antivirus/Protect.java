/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tanase
 */
public class Protect {
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
        
        
        List<String> listExt=getExtensii();
        
        String ext="";
        if (null != listOfFiles[i].getName() && listOfFiles[i].getName().length() > 0 )
        {
            int endIndex = listOfFiles[i].getName().lastIndexOf(".");
            if (endIndex != -1)  
            {
                ext = listOfFiles[i].getName().substring(endIndex, listOfFiles[i].getName().length()); // not forgot to put check if(endIndex != -1)
            }
        }  
        
        
            
        for(String str: listExt) 
        {
            if(str.trim().contains(ext))
            {
                copac.adaugNod(1,listOfFiles[i].getPath());
                //System.out.println(ext);
            }
        }
        
 
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
        getList(listOfFiles[i].getPath());
      }
    }
    }
    public static List<String> getExtensii()
    {
        BufferedReader in = null;
        FileReader fr = null;
        List<String> listaExtensii = new ArrayList<String>();

        try {
            //fr = new FileReader("C:/Users/Iulia/Documents/NetBeansProjects/BojaProj/src/bojaproj/ext.txt");
            fr = new FileReader(System.getProperty("user.dir")+"/ext.txt");
            in = new BufferedReader(fr);
            String str;
            while ((str = in.readLine()) != null) {
                listaExtensii.add(str);
                
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally
        {
            try{
            in.close();
            fr.close();}
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return listaExtensii;
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
        try{
        if(DBConnect.fileExists(n.cale))
        {
            System.out.println("A intrat in if");
           if(DBConnect.fileIsModified(n.cale, Long.toBinaryString(crc.crcInput(nou.getPath())))) 
           {
               System.out.println("Exista si a fost modificat");
        DBConnect.updateFile(nou.getName(),ext, "nothing yet", Long.toString(nou.lastModified()), nou.getPath(), Long.toString(nou.getTotalSpace()),Long.toBinaryString(crc.crcInput(nou.getPath())) );
           }
           else{
               System.out.println("Exista si nu a fost modificat");
           }
        }else
        {
            DBConnect.insertFile(nou.getName(),ext, "nothing yet", Long.toString(nou.lastModified()), nou.getPath(), Long.toString(nou.getTotalSpace()),Long.toBinaryString(crc.crcInput(nou.getPath())) );
        }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            DBConnect.close();
        }
        
        
        if(n.stanga!=null) {
        uploadItems(n.stanga);
        }
        if(n.dreapta!=null) {
        uploadItems(n.dreapta);
        }
        
    }
    public static void startProtect(String  caleaCeaDreapta){
    getList(caleaCeaDreapta);
    copac.afisareContinutArbore(copac.root);
    uploadItems(copac.root);
    List<String> list = getExtensii();
    for(int i=0;i<list.size();i++){
    System.out.println(list.get(i));
    } 
}
}
