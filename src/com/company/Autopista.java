package com.company;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Autopista extends JFrame implements Runnable {

    private int lineasSeparadoras=0;
    ModeloAutos modeloAutos=new ModeloAutos();
    private String codTecla;
    public  Thread hilo = new Thread(this);

    public String getCodTecla() {
        return codTecla;
    }

    public void setCodTecla(String codTecla) {
        this.codTecla = codTecla;
    }

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
        Carros coches[]=modeloAutos.getCarros();
        for (int n=0;n<coches.length;n++)
           if (coches[n].getVisible())
                pintarCoche(g,coches[n].getX(),
                        coches[n].getY(),
                        coches[n].getColor());
        pintarPuntos(g);
    }

    private void pintarCoche(Graphics g, int x, int y, int nColorBase){
        g.setColor(Color.getColor(Integer.toString(nColorBase)));
        g.fillRoundRect(getX(x), getY(y), getX(10), getY(20), 20, 10);
        g.setColor(Color.black);
        g.drawRoundRect(getX(x), getY(y), getX(10), getY(20), 20, 10);
        g.drawRect(getX(x), getY(y+5), getX(10), getY(13));
        g.drawRect(getX(x), getY(y+8), getX(10), getY(10));
        g.setColor(Color.blue);
        g.fillRect(getX(x+1), getY(y+6), getX(8), getY(1));
        g.fillRect(getX(x+1), getY(y+18), getX(8), getY(1));
    }


    private void pintarPuntos(Graphics g){

        g.setColor(Color.BLACK);
        g.drawString("Score:" + modeloAutos.getPuntos(), getX(50), getY(5));
    }

    private void eventoTecla(int tecla, boolean estado){
        System.out.println("Estoy en el metodo eventoTecla de la clase Autopista");
        switch(getCodTecla()){
            case "RIGHT":
                System.out.println("Estoy en el primer case Right");
                modeloAutos.eventoTecla(ModeloAutos.RIGHT,estado);
                break;
            case "LEFT":
                modeloAutos.eventoTecla(ModeloAutos.LEFT,estado);
                break;
            case "DOWN":
                modeloAutos.eventoTecla(ModeloAutos.DOWN,estado);
                break;
            case "UP":
                modeloAutos.eventoTecla(ModeloAutos.UP,estado);
                break;
        }
    }

    protected void keyPressed(int tecla){

        switch(getCodTecla()){
            case "RIGHT":
            case "LEFT":
            case "UP":
            case "DOWN":
                eventoTecla(tecla, true);
                break;
            case "ENTER":
                modeloAutos.empezarPartida();
                break;
        }
    }

    protected void keyReleased(int tecla){

        switch(getCodTecla()){
            case "RIGHT":
            case "LEFT":
            case "UP":
            case "DOWN":
                eventoTecla(tecla, false);
                break;
        }
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

    /**
     * CLASE QUE SIRVE PARA OBTENER LOS CODIGOS NUMERICOS DE LAS TECLAS PRESIONADAS
     */
    class EventoTeclado implements KeyListener{
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            setCodTecla(e.getKeyText(e.getKeyCode()).toUpperCase());
            System.out.println(getCodTecla());

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

}
