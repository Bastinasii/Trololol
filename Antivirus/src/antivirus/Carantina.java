/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

/**
 *
 * @author Iulia
 */
public class Carantina {
    public String path; 
    public String nume_vir;
    public String categorie;
       

       public Carantina()
       {
       
       }
   // constructor
   public Carantina(String nume_vir, String categorie, String path) {
      this.nume_vir = nume_vir;
      this.categorie = categorie;
      this.path = path;
   }

       // getter
       public String getName() { return nume_vir; }
       public String getCategorie() { return categorie; }
       public String getPath() {return path;}
       
       // setter

       public void setName(String name) { this.nume_vir = name; }
       public void setCategorie(String categorie) { this.categorie = categorie; }
       public void setPath(String path) { this.path = path; }
       
       
    
}
