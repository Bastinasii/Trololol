/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

/**
 *
 * @author Tanase
 */
public class nodAVL implements Comparable<nodAVL>{
    
    public nodAVL stanga;
 public nodAVL dreapta;
 public nodAVL parinte;
 public int stat;
 public String cale;
 public int inaltime;

 @Override
    public int compareTo(nodAVL other) {
        int difCale = this.cale.compareTo(other.cale);
        return difCale;
    }
 
 public nodAVL(int p, String cale) {
  stanga = null;
  dreapta = null;
  parinte = null;
  inaltime = 0;
  this.stat = p;
  this.cale=cale;
 }
    @Override
 public String toString() {
  return "" + stat + " " + cale;
 }

}
