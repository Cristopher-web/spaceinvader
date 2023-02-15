/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvader;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.Symbols;

/**
 *
 * @author DAWTarde
 */
public class Bullet {
    private Point2D posicion;
    private TextColor color;
    private TextColor backgroundcolor;
    private TextCharacter bulletsimbol;
    private static int COUNTER_MAX=24;
    private int counter=COUNTER_MAX;
    
    public Bullet() {
        this.posicion = new Point2D();
    }
    public Bullet(Point2D posicion){
        this.posicion = posicion;
    }
    public Bullet(int x, int y){
       this.posicion = new Point2D(x,y);
    }

    public Point2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    private void init(){
        this.color = TextColor.ANSI.GREEN;
        this.backgroundcolor = TextColor.ANSI.GREEN;
        this.bulletsimbol = TextCharacter.fromCharacter(Symbols.ARROW_UP)[0].withBackgroundColor(this.backgroundcolor);
    }
    public void paint(Screen s){
        s.setCharacter(this.posicion.getX(), this.posicion.getY(), this.bulletsimbol);
    }
    public void moveVertical(int incy, int miny, int maxy){
        this.counter--;
        if(this.counter <= 0){
            this.counter=COUNTER_MAX;
            if(this.posicion.getY()+incy > miny && this.posicion.getY() + incy < maxy){
            this.posicion.addy(incy);
            }
        }
    }
}
