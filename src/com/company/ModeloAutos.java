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

    //REPRESENTACIONES EN STRING DE e.getKeyCode()

    public static final byte RIGHT=0;
    public static final byte LEFT=1;
    public static final byte DOWN=2;
    public static final byte UP=3;
    public static final byte ENTER=4;

    private boolean teclas[]=new boolean[4];

    private  int nPuntos=0;

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
        if (Jugando){
            if (teclas[RIGHT]) coches[0].mover(2,0);
            if (teclas[LEFT]) coches[0].mover(-2,0);
            if (teclas[DOWN]) coches[0].mover(0,2);
            if (teclas[UP]) coches[0].mover(0,-2);
            for (int n=1;n<coches.length;n++)
                coches[n].moverComputador();
            avanzarLineaSeparadora();
            mostrarNuevoCarro();
            detectarChoque();
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

    public void eventoTecla(int nTecla, boolean bEstado){
        teclas[nTecla]=bEstado;
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
            nPuntos=0;
            System.gc();
        }

    }

    private void detectarChoque(){
        int x=coches[0].getX();
        int y=coches[0].getY();
        for (int n=1;n<coches.length;n++)
            if (coches[n].getVisible() && coches[n].hayChoque(x,y))
                Jugando=false;
    }

    public int getPuntos(){
        return nPuntos;
    }

}
