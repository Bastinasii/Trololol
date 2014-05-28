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
 * @author Iulia
 */
public class ListeSiCitireFisiere {
    
     public static void main(String[] args) {
        // TODO code application logic here
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
        
        FileReader fr_vir = null;
        BufferedReader bf_vir = null;
        List<Viruses> listVirusi = new ArrayList<Viruses>();
        try{
        //fr_vir = new FileReader("C:/Users/Iulia/Documents/NetBeansProjects/BojaProj/src/bojaproj/viruses.dat");
        fr_vir = new FileReader(System.getProperty("user.dir")+"/viruses.txt");
        bf_vir = new BufferedReader(fr_vir);
        Viruses vr= new Viruses(); String sr;
        while((sr=bf_vir.readLine())!=null)
        {
            System.out.println(sr);
            String[] vec = sr.split(" ");
            vr.setName(vec[0]);
            vr.setCategorie(vec[1]);
            vr.setSemnatura(vec[2]);
            listVirusi.add(vr);
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        finally
        {
            try{
        bf_vir.close();
        fr_vir.close();}
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        List<Carantina> listCarantina = new ArrayList<Carantina>();
        FileReader fr_car=null;
        BufferedReader bf_car = null;
        try
        {
        fr_car = new FileReader(System.getProperty("user.dir")+"/carantina.txt");
        bf_car = new BufferedReader(fr_car);
        Carantina car= new Carantina(); String sr;
        while((sr=bf_car.readLine())!=null)
        {
            System.out.println(sr);
            String[] vec = sr.split(" ");
            car.setName(vec[0]);
            car.setCategorie(vec[1]);
            car.setPath(vec[2]);
            listCarantina.add(car);
        }
        
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
        bf_car.close();
        fr_car.close();}
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        for (String d : listaExtensii) System.out.println(d);
        for (Viruses v : listVirusi) System.out.println(v);
        
        //List<String> results = new ArrayList<String>();
//linkul a fost de proba, folositi ce link vreti dar sa fie final, gen sa fie un folder in care nu mai sunt foldere
        File dir = new File("C:\\Users\\Iulia\\Desktop\\ATP\\ATP\\Algoritmi si Tehnici de Programare\\Explicatii Probleme ATP - Iulia Ghindeanu");
        File[] listOfFiles = dir.listFiles();

      for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
        //for(String s : results)
        //{
        //    System.out.println(s);
        }
    }
}
