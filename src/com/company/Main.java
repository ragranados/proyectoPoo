package com.company;

public class Main {

    private static Autopista autopista=new Autopista();
    private static Thread hilo = new Thread( autopista);

    public static void main(String[] args) {

        new Autopista().setVisible(true);
        hilo.start();
    }
}
