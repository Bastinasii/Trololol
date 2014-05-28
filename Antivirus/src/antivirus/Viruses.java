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
public class Viruses {
    private String name;
       private String categorie;
       private String semnatura;

       public Viruses()
       {
       
       }
   // constructor
   public Viruses(String name, String categorie, String semnatura) {
      this.name = name;
      this.categorie = categorie;
      this.semnatura = semnatura;
   }

       // getter
       public String getName() { return name; }
       public String getCategorie() { return categorie; }
       public String getSemnatura() {return semnatura;}
       
       // setter

       public void setName(String name) { this.name = name; }
       public void setCategorie(String categorie) { this.categorie = categorie; }
       public void setSemnatura(String semnatura) { this.semnatura = semnatura; }
}
