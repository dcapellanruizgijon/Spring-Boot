package com.example.examen;

import java.util.ArrayList;

public class gestor {
    private ArrayList<pc> listaPcs;
    private ArrayList<laptop> listaLaptops;

    public gestor(){
        this.listaPcs=new ArrayList<pc>();
        this.listaLaptops=new ArrayList<laptop>();
        

        laptop l1=new laptop("L001", 800, "CPU", "examen\\src\\main\\resources\\templates\\portatil.jpg");
        laptop l2=new laptop("L002", 950, "RAM", "examen\\src\\main\\resources\\templates\\portatil.jpg");
        this.listaLaptops.add(l1);
        this.listaLaptops.add(l2);

        pc p1=new pc("P002", 743, "RAM", "pc.jpg");
        pc p2=new pc("P002", 427, "Disco", "pc.jpg");
        this.listaPcs.add(p1);
        this.listaPcs.add(p2);
    }

    public void anadirPc(pc pc){
        this.listaPcs.add(pc);
    }
    public ArrayList<pc> getListaPcs() {
        return listaPcs;
    }

    public ArrayList<laptop> getListaLaptops() {
        return listaLaptops;
    }

    public void anadirLaptop(laptop l){
        this.listaLaptops.add(l);
    }

    public int calculaTotal(){
        int totalPcs=0;
        for(pc pc: this.listaPcs){
            totalPcs=totalPcs+pc.getPrecio();
        }
        int totalLaptops=0;
        for(laptop lp: this.listaLaptops){
            totalLaptops=totalLaptops+lp.getPrecio();
        }

        return totalPcs+totalLaptops;
    }

    public int calculaCantidad(){
        int cantidad=this.listaLaptops.size();
        int cantidad2=this.listaPcs.size();
        return cantidad+cantidad2;
    }
}
