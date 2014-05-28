/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iulia
 */
public class Report {
    public static void main(ArrayList<String> args) {
        
        
        
       //TODO code application logic here
       int nr=0;
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
        
      FileWriter fw_tmp = null;
        BufferedWriter bf_tmp = null;
     FileWriter fw_rep = null;
        BufferedWriter bf_rep = null;
        FileReader fr_rep = null;
        BufferedReader bfr_rep = null;
        try{
        
        fw_tmp = new FileWriter(System.getProperty("user.dir")+"/temp.txt");
        bf_tmp = new BufferedWriter(fw_tmp);
        fw_tmp = new FileWriter(System.getProperty("user.dir")+"/report.txt");
        bf_tmp = new BufferedWriter(fw_tmp);
        fr_rep = new FileReader(System.getProperty("user.dir")+"/temp.txt");
        bfr_rep = new BufferedReader(fr_rep);
        //Carantina cr= new(); String sr;
        //while((sr=bf_vir.readLine())!=null)
        if(args.size()<2)
        {
            System.out.println("Waiting for: -n -d -c -t path|drive|file output_file. Choose at least -n!");
        }
        int okN=0, okD=0, okT=0, okC=0;
        for(int i=1;i<=args.size();i++)
            {if(args.get(i)=="-n")
			okN=1;
		else 
			if(args.get(i)=="-d")
				okD=1;
			else
				if(args.get(i)=="-c")
					okC=1;
				else 
					if(args.get(i)=="-t")
						okT=1;
		}
        
        if(okN==0)
        {
            System.out.println("alegeti cel putin -n!");
        }
        else
        {
        if(args.get(args.size()-1)!="-n"&&args.get(args.size()-1)!="-c"&&args.get(args.size()-1)!="-t"&&args.get(args.size()-1)!="d")
        {
        for(int i=0;i<listCarantina.size();i++)
        {
            if(args.get(args.size()-1).toLowerCase().contains(listCarantina.get(i).getPath().toLowerCase()))
            {
                if(okC==1)
                {
                    if(okT==1)
                    {
                        for(int j=0;j<listVirusi.size();j++)
                            if(listCarantina.get(j).getCategorie().toLowerCase().equals(listVirusi.get(j).getCategorie().toLowerCase()))
                                for(int k=0;k<listaExtensii.size();k++)
                                    if(listaExtensii.get(k).equals(listCarantina.get(k).getPath().substring(listCarantina.get(k).getPath().length()-3)))
                                    {
                                      bf_tmp.write("\n"+listCarantina.get(k).getName()+" "+listCarantina.get(k).getCategorie()+" "+listCarantina.get(k).getPath());

                                        nr++;
                                    }
                    }
                    else
                    {
                        for(int j=0;j<listVirusi.size();j++)
                            if(listCarantina.get(j).getCategorie().toLowerCase().equals(listVirusi.get(j).getCategorie().toLowerCase()))
                            {
                               bf_tmp.write("\n"+listCarantina.get(j).getName()+" "+listCarantina.get(j).getCategorie()+" "+listCarantina.get(j).getPath());

                                        nr++;
                            }
                    }
                    
                }
                else
                {
                    if(okT==1)
                    {for(int k=0;k<listaExtensii.size();k++)
                                    if(listaExtensii.get(k).equals(listCarantina.get(k).getPath().substring(listCarantina.get(k).getPath().length()-3)))
                                    {
                                        bf_tmp.write("\n"+listCarantina.get(k).getName()+" "+listCarantina.get(k).getCategorie()+" "+listCarantina.get(k).getPath());
                                        nr++;
                                    }
                    }
                    else
                    {
                        bf_tmp.write(listCarantina.get(i).getName());
                        nr++;
                    }
                        
                }
                    
            }
            
        }
        }
        else
        {
            bf_rep.write(nr);
        }
        if(okD==0)
        {
            bf_rep.write(nr);
        }
        else
        {
            Carantina tmp = new Carantina(); String s;
            bf_rep.write(nr);
            while((s=bfr_rep.readLine())!=null)
            {
                String[] v = s.split(" ");
                bf_rep.write("\n"+v[0]+" "+v[1]+" "+v[2]);
                
            }
        }
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        finally
        {
            try{
        fw_tmp.close();
        bf_tmp.close();
        fw_tmp.close();
        bf_tmp.close();
        fr_rep.close();
        bfr_rep.close();}
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
}
