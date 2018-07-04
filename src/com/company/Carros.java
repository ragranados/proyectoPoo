package com.company;

import java.util.Random;

public class Carros {
    private int x,y;
    private int nColor;
    private boolean bVisible;
    private int dx;
    private boolean bObstaculo;
    private Random r;


    public Carros (boolean obstaculo, Random r){
        bObstaculo=obstaculo;
        this.r=r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return nColor;
    }

    public boolean getVisible() {
        return bVisible;
    }

    public void mostrarCarro(int color){
        bVisible=true;
        nColor=color;
        y=-20;
        x=r.nextInt(60)+20;
        dx=r.nextInt(5)-2;
    }

    public void ocultarCarro(){
        bVisible=false;
    }

    public void moverComputador(){
        if(bVisible){
            mover(dx,1);
            if(y>100){
                bVisible=false;

            }
        }
    }

    public void setXY(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void mover(int dx,int dy){
        x+=dx;
        if(x<20){
            x=20;
            this.dx=-this.dx;
        }

        if(x>70){
            x=70;
            this.dx=-this.dx;
        }

        y+=dy;
        if(!bObstaculo){
            //LIMITACION DE MOVIMIENTO DEL VEHICULO PROPIO
            if(y<10){
                y=10;
            }
            if(y>70){
                y=70;
            }
        }
    }

    public boolean hayChoque(int x2, int y2){
        if (x+1>x2+9) return false;
        if (x2+1>x+9) return false;
        if (y+1>y2+19) return false;
        if (y2+1>y+19) return false;
        return true;
    }

}
