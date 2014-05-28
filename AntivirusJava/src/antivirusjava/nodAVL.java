/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirusjava;

/**
 *
 * @author Tanase
 */
public class nodAVL {
    public nodAVL stanga;
 public nodAVL dreapta;
 public nodAVL parinte;
 public int p;
 public String cale;
 public int inaltime;

 public nodAVL(int p, String cale) {
  stanga = null;
  dreapta = null;
  parinte = null;
  inaltime = 0;
  this.p = p;
  this.cale=cale;
 }
    @Override
 public String toString() {
  return "" + p;
 }
}
