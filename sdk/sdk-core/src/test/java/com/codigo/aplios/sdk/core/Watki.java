package com.codigo.aplios.sdk.core;

import java.time.LocalTime;

public class Watki {

    public static void main(final String[] args) {

        final Bufor c = new Bufor();
        final Producent p1 = new Producent(
                c, 1);
        final Konsument c1 = new Konsument(
                c, 1);
        p1.start();
        c1.start();
    }

}

class Bufor {

    private int contents;

    private boolean available = false;

    public synchronized int get() {

        while (!this.available)
            try {
                wait();
            } catch (final InterruptedException e) {
            }
        this.available = false;
        notifyAll();
        return this.contents;
    }

    public synchronized void put(final int value) {

        while (this.available)
            try {
                wait();
            } catch (final InterruptedException e) {
            }
        this.contents = value;
        this.available = true;
        notifyAll();
    }

}

class Producent
        extends Thread {

    private final Bufor buf;

    private final int number;

    public Producent(final Bufor c, final int number) {
        this.buf = c;
        this.number = number;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10000000; i++) {
            this.buf.put(i);
            System.out.println(LocalTime.now() + "::" + getId() + "-> Producent #" + this.number + " put: " + i);
            try {
                Thread.sleep((int)(Math.random() * 3000));
            } catch (final InterruptedException e) {
            }
        }
    }

}

class Konsument
        extends Thread {

    private final Bufor buf;

    private final int number;

    public Konsument(final Bufor c, final int number) {
        this.buf = c;
        this.number = number;
    }

    @Override
    public void run() {

        int value = 0;
        for (int i = 0; i < 10000000; i++) {
            value = this.buf.get();
            System.out.println(LocalTime.now() + "::" + getId() + "-> Konsument #" + this.number + " got: " + value);
        }
    }

}
