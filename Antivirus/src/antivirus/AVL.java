/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

import java.util.ArrayList;
import antivirus.nodAVL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Tanase
 */
public class AVL {
    public nodAVL root; 
 public void adaugNod(int k, String s) {
  
  nodAVL n = new nodAVL(k,s);
  
  adaugareInAVL(this.root,n);
 }
 public void adaugareInAVL(nodAVL p, nodAVL q) {
  
  if(p==null) {
   this.root=q;
  } else {
   
   
   if(q.compareTo(p)<0) {
    if(p.stanga==null) {
     p.stanga = q;
     q.parinte = p;
     recursiveBalance(p);
    } else {
     adaugareInAVL(p.stanga,q);
    }
    
   } else if(q.compareTo(p)>0) {
    if(p.dreapta==null) {
     p.dreapta = q;
     q.parinte = p;
     recursiveBalance(p);
    } else {
     adaugareInAVL(p.dreapta,q);
    }
   } else {
    // do nothing: This node already exists
   }
  }
 }
 public void recursiveBalance(nodAVL cur) {
  
  // we do not use the inaltime in this class, but the store it anyway
  echilibreazaArbore(cur);
  int inaltime = cur.inaltime;
  
  // check the inaltime
  if(inaltime==-2) {
   
   if(getInaltime(cur.stanga.stanga)>=getInaltime(cur.stanga.dreapta)) {
    cur = rotLaDreapta(cur);
   } else {
    cur = rotLaStangaDreapta(cur);
   }
  } else if(inaltime==2) {
   if(getInaltime(cur.dreapta.dreapta)>=getInaltime(cur.dreapta.stanga)) {
    cur = rotLaStanga(cur);
   } else {
    cur = rotLaDreaptaStanga(cur);
   }
  }
  
  // we did not reach the root yet
  if(cur.parinte!=null) {
   recursiveBalance(cur.parinte);
  } else {
   this.root = cur;
   System.out.println("------------ Balancing finished ----------------");
  }
 }
 public nodAVL rotLaStanga(nodAVL n) {
  
  nodAVL v = n.dreapta;
  v.parinte = n.parinte;
  
  n.dreapta = v.stanga;
  
  if(n.dreapta!=null) {
   n.dreapta.parinte=n;
  }
  
  v.stanga = n;
  n.parinte = v;
  
  if(v.parinte!=null) {
   if(v.parinte.dreapta==n) {
    v.parinte.dreapta = v;
   } else if(v.parinte.stanga==n) {
    v.parinte.stanga = v;
   }
  }
  
  echilibreazaArbore(n);
  echilibreazaArbore(v);
  
  return v;
 }
 public nodAVL rotLaDreapta(nodAVL n) {
  
  nodAVL v = n.stanga;
  v.parinte = n.parinte;
  
  n.stanga = v.dreapta;
  
  if(n.stanga!=null) {
   n.stanga.parinte=n;
  }
  
  v.dreapta = n;
  n.parinte = v;
  
  
  if(v.parinte!=null) {
   if(v.parinte.dreapta==n) {
    v.parinte.dreapta = v;
   } else if(v.parinte.stanga==n) {
    v.parinte.stanga = v;
   }
  }
  
  echilibreazaArbore(n);
  echilibreazaArbore(v);
  
  return v;
 }
 public nodAVL rotLaStangaDreapta(nodAVL u) {
  u.stanga = rotLaStanga(u.stanga);
  return rotLaDreapta(u);
 }
 public nodAVL rotLaDreaptaStanga(nodAVL u) {
  u.dreapta = rotLaDreapta(u.dreapta);
  return rotLaStanga(u);
 }
 private int getInaltime(nodAVL cur) {
  if(cur==null) {
   return -1;
  }
  if(cur.stanga==null && cur.dreapta==null) {
   return 0;
  } else if(cur.stanga==null) {
   return 1+getInaltime(cur.dreapta);
  } else if(cur.dreapta==null) {
   return 1+getInaltime(cur.stanga);
  } else {
   return 1+getMaxim(getInaltime(cur.stanga),getInaltime(cur.dreapta));
  }
 } 
 private int getMaxim(int a, int b) {
  if(a>=b) {
   return a;
  } else {
   return b;
  }
 }
 public void afisareContinutArbore(nodAVL n) {
  int l = 0;
  int r = 0;
  int p = 0;
  if(n.stanga!=null) {
   l = n.stanga.stat;
  }
  if(n.dreapta!=null) {
   r = n.dreapta.stat;
  }
  if(n.parinte!=null) {
   p = n.parinte.stat;
  }
  
  System.out.println("Left: "+l+" Key: "+n+" Right: "+r+" Parent: "+p+" Balance: "+n.inaltime);
  
  if(n.stanga!=null) {
   afisareContinutArbore(n.stanga);
  }
  if(n.dreapta!=null) {
   afisareContinutArbore(n.dreapta);
  }
 }
 private void echilibreazaArbore(nodAVL cur) {
  cur.inaltime = getInaltime(cur.dreapta)-getInaltime(cur.stanga);
 }
 final protected ArrayList<nodAVL> inorder() {
  ArrayList<nodAVL> ret = new ArrayList<nodAVL>();
  inorder(root, ret);
  return ret;
 }
 final protected void inorder(nodAVL n, ArrayList<nodAVL> io) {
  if (n == null) {
   return;
  }
  inorder(n.stanga, io);
  io.add(n);
  inorder(n.dreapta, io);
 }
}

