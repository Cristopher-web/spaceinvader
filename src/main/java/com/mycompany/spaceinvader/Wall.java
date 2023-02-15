/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvader;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author DAWTarde
 */
public class Wall {
    private Point2D posicion;
    private int widht = 7;
    private int height = 2;
    private char[][] cartoon = {{'▤','▤','▤','▤','▤','▤','▤'},
                                {'▤','▤','▤','▤','▤','▤','▤'}};
   public Wall(){
       this.posicion = new Point2D();
   }
  public Wall(Point2D posicion){
      this.posicion = posicion;
  }
  public Wall(int x, int y){
      this.posicion = new Point2D(x,y);
  }

    public Point2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }
  public void paint(Screen s){
          char c;
    for(int i=0;i < this.height;i++){
        for(int j=0;j<this.getWidht();j++){
            c= this.cartoon[i][j];
          s.setCharacter(this.getPosicion().getX() + j, this.getPosicion().getY() + i, new TextCharacter(c,TextColor.ANSI.WHITE,TextColor.ANSI.BLACK));     
   }
}
}
  public boolean collission(Bullet b){
       boolean col= false;
       int cordenadax, cordenaday;
       if(this.posicion.getY()<= b.getPosicion().getY() && 
          this.posicion.getY() + this.height > b.getPosicion().getY() &&
          this.posicion.getX()<=b.getPosicion().getX() &&
          this.posicion.getX() + this.widht > b.getPosicion().getX()){
           cordenadax=b.getPosicion().getX()-this.posicion.getX();
           cordenaday=b.getPosicion().getY()-this.posicion.getY();
           if(this.cartoon[cordenaday][cordenadax]!=' '){
                col=true;
                this.cartoon[cordenaday][cordenadax]=' ';
           }
       }
       return col;
  }
}