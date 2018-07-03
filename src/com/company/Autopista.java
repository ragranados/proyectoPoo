package com.company;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Autopista extends JFrame implements Runnable {


    ModeloAutos modelo = new ModeloAutos();
   // public static Autopista autopista=new Autopista();
    public  Thread hilo = new Thread(this);
    public Autopista(){
        super("Autopista");
        initialComponents();
        hilo.start();
    }

    public void initialComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);//Desde código,decimos DONDE  va cada botón, en qué posición va y qué tamaño ocupa en la ventana
        setResizable(true);// Sirve para habilitar o no, que se modifique el tamanio de la ventana
        Container contains ;
        setSize(500, 500);

    }

    /**
     *
     * @param x
     * @return Coordenadas de el tamanio de la pantalla para reajustarse
     */
    public int getX(int x){
        return (x*getWidth())/100;
    }

    public int getY(int y){
        return (y*getHeight())/100;
    }

    public void pintarFondo(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(getX(0),getY(0),getX(20),getY(100));
        g.fillRect(getX(80),getY(0),getX(20),getY(100));
        g.setColor(Color.BLACK);
        g.fillRect(getX(20),getY(0),getX(60),getY(100));
        g.setColor(Color.WHITE);
        g.fillRect(getX(20),getY(0),getX(1),getY(100));
        g.fillRect(getX(79),getY(0),getX(1),getY(100));
    }

    public void lineasSeparadoras(Graphics g){
        int iD=modelo.getLineaSeparadora();
        g.setColor(Color.WHITE);

        for (int n=iD;n<100;n+=40){
            g.fillRect(getX(40),getY(n),getX(2),getY(20));
            g.fillRect(getX(60),getY(n),getX(2),getY(20));
        }
    }

    /**
     *
     * @param  g
     * @return Clase padre paint, sobreescrita
     *
     */

    public void paint (Graphics g){
        //super.paint(g);
        pintarFondo(g);
        lineasSeparadoras(g);
        pintarCoche(g,40,40,0xFF0000);
        Carros coches[]=modelo.getCarros();
        for(int i =0 ; i<coches.length ;i++){
            if(coches[i].getVisible()){
                pintarCoche(g,coches[i].getX(),coches[i].getY(),coches[i].getColor());
            }
        }

    }

    private void pintarCoche(Graphics g,int x,int y,int nColorBase){
        g.setColor(Color.getColor(Integer.toString(nColorBase)));
        g.fillRoundRect(getX(x),getY(y),getX(10),getY(20),20,10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(getX(x),getY(y),getX(10),getY(20),20,10);
        g.drawRect(getX(x),getY(y+5),getX(10),getY(13));
        g.drawRect(getX(x),getY(y+8),getX(10),getY(10));
        g.setColor(Color.orange);
        g.fillRect(getX(x+1),getY(y+6),getX(8),getY(1));
        g.fillRect(getX(x),getY(y+18),getX(8),getY(1));
    }

    @Override
    public void run (){
        while (true){
            try{
                retardo();
                modelo.Mover();
                repaint();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void retardo(){
        try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Autopista().setVisible(true);
            }
        });
    }

}
