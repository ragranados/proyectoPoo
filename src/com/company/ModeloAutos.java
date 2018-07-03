package com.company;
import java.util.Random;

/**
 * Clase que maneja la logica del juego y del computador
 */
public class ModeloAutos {

    private int lineasSeparadoras=0;
    private Random r= new Random();
    private int mostrarCoche=0;
    private int nCoche=1;
    private boolean Jugando;
    private Carros coches []= new Carros[5];

    public ModeloAutos(){
        crearCarros();
        empezarPartida();
    }

    public int getLineaSeparadora(){
        return lineasSeparadoras;
    }

    private void avanzarLineaSeparadora(){
        lineasSeparadoras+=4;
        if(lineasSeparadoras>0)
            lineasSeparadoras-=40;
    }

    /**
     * Metodo encargado del movimiento de las lineas separadoras
     */
    public void Mover(){
        if(Jugando){
            for (int i=1;i<coches.length;i++){
                coches[i].moverComputador();
            }
            avanzarLineaSeparadora();
            mostrarNuevoCarro();
        }
    }

    private void mostrarNuevoCarro(){
        mostrarCoche++;
        if(mostrarCoche==30){
            mostrarCoche=0;
            int red=r.nextInt(255);
            int green=r.nextInt(255);
            int blue=r.nextInt(255);
            int nColor = red+green*0x100+blue*0x10000;
            coches[nCoche].mostrarCarro(nColor);
            nCoche++;
            if(nCoche==coches.length){
                nCoche=1;
            }
        }
    }

    private void crearCarros(){
        for(int i=0 ;i < coches.length;i++){
            if(i==0){
                coches[i]= new Carros(false,r);
            }
            else{
                coches[i]= new Carros(true,r);
            }
        }
    }

    public Carros [] getCarros(){
        return coches;
    }

    public void empezarPartida(){
        coches[0].mostrarCarro(0xFF0000);
        coches[0].setXY(45,70);
        for(int i =1;i<coches.length;i++){
            coches[i].ocultarCarro();
            Jugando=true;
            System.gc();
        }

    }

}
