/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicking;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Point;

/**
 *
 * @author jszumega
 */
public class Clicking {
    
    static final int step = 20;
    PointerInfo info;
    Point pos;
    Robot mouse;
    
    
    void mouseUp() {
        info = MouseInfo.getPointerInfo();
        pos = info.getLocation();
        mouse.mouseMove((int)pos.getX(),        (int)pos.getY()-step);
    }
    
    void mouseDown() {
        info = MouseInfo.getPointerInfo();
        pos = info.getLocation();
        mouse.mouseMove((int)pos.getX(),        (int)pos.getY()+step);
    }
    
    void mouseLeft() {
        info = MouseInfo.getPointerInfo();
        pos = info.getLocation();
        mouse.mouseMove((int)pos.getX()-step,   (int)pos.getY());
    }
    
    void mouseRight() {
        info = MouseInfo.getPointerInfo();
        pos = info.getLocation();
        mouse.mouseMove((int)pos.getX()+step,   (int)pos.getY());
    }
    
    void setup() {
        
        try {
            mouse = new Robot();
        } catch(Exception e) {
            e.printStackTrace();
            mouse = null;
        }
    }
    
    
    public static void main(String[] args) {
        
        Clicking clicker = new Clicking();
        clicker.setup();
       
        clicker.mouseUp();
        clicker.mouseDown();
        clicker.mouseLeft();
        clicker.mouseRight();
    }
    
}
