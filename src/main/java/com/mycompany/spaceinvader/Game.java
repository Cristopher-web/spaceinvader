/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvader;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Game {
private Terminal terminal;
    private Screen screen;
    private boolean exit_key;
    private static final int COLUMNS=80;
    static final int ROWS=24;
    private Bullet bala;
    private Ship ship;
    private Wall walls[];
    public Game(){
        this.exit_key = false;
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            screen.setCursorPosition(null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        bala = new Bullet(40,12);
        ship = new Ship(38,20);
        
    }
    public void loop(){
        try {
            screen.startScreen();
            screen.clear();
            while(!this.exit_key){
                //Se procesa la entrada
                process_input();
                update();
                paint();
                try {
                    Thread.sleep((1/60)*1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            screen.close();
            terminal.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void update(){
        this.ship.moveBullet();
        this.collission();
    }
     private void collission(){
        Bullet[] ship_bullets = this.ship.getBullets();
        for(int i=0;i<this.walls.length;i++){
            for(int j=0; j<ship_bullets.length;j++){
                if(this.walls != null && ship_bullets[j] != null){
                    if(this.walls[i].collission(ship_bullets[j])){
                        ship_bullets[j] = null;
                    }
                }
            }
        }
    }
    private void createWalls(){
        this.walls = new Wall[4];
        for(int i=0; i<this.walls.length; i++){
            this.walls[i] = new Wall(new Point2D(((i+1)*20),15));
        }
    }
    private void paintcreateWall(Screen s){
        for(int i=0;i<this.walls.length;i++){
            this.walls[i].paint(s);
        }
    }
  
    private void paint( ) throws IOException {
        TerminalSize terminalSize = this.screen.getTerminalSize();
        for (int column = 0; column < terminalSize.getColumns(); column++){
            for (int row = 0; row < terminalSize.getRows(); row++) {
                this.screen.setCharacter(column, row, new TextCharacter(' ',TextColor.ANSI.DEFAULT ,TextColor.ANSI.BLACK));
            }
        }
        this.ship.paint(screen);
        this.bala.paint(screen);
        this.paintcreateWall(screen);
        screen.refresh();
    }
    private void process_input() {
        KeyStroke keyStroke=null;
        try {
           keyStroke = screen.pollInput();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mira si toca alguna tecla
        if (keyStroke != null){
            //mira si pulsa escape
            if (keyStroke.getKeyType() == KeyType.Escape) {
                this.exit_key = true;
            }
            if(keyStroke.getKeyType() == KeyType.ArrowUp){
                this.bala.moveVertical(-1, 0, Game.ROWS);
            }
            if(keyStroke.getKeyType()== KeyType.ArrowDown){
                this.bala.moveVertical(1, 0, Game.ROWS);
            }
             if(keyStroke.getKeyType() == KeyType.ArrowLeft){
                this.ship.moveHorizontal(-1, 0, Game.COLUMNS);
            }
            if(keyStroke.getKeyType()== KeyType.ArrowRight){
                this.ship.moveHorizontal(1, 0, Game.COLUMNS);
        }
            if(keyStroke.getKeyType()== KeyType.Enter){
                this.ship.shot();
            }
    }
  }
}