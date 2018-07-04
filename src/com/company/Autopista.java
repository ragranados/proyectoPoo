package com.company;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Autopista extends JFrame implements Runnable {

    private int lineasSeparadoras=0;
   // public static Autopista autopista=new Autopista();
    public  Thread hilo = new Thread(this);
    public Autopista(){
        super("Autopista");
        hilo.start();
        initialComponents();

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
        g.setColor(Color.GRAY);
        g.fillRect(getX(20),getY(0),getX(60),getY(100));
        g.setColor(Color.WHITE);
        g.fillRect(getX(20),getY(0),getX(1),getY(100));
        g.fillRect(getX(79),getY(0),getX(1),getY(100));
    }

    public void lineasSeparadoras(Graphics g){
        int iD=lineasSeparadoras;
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
    }


    @Override
    public void run (){
        while (true){
            try{
                retardo();
                lineasSeparadoras+=4;
                if(lineasSeparadoras>0)
                    lineasSeparadoras-=40;
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
