package vue;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

import modele.CaseModele;
import modele.Game;

import java.awt.Polygon;


public class CaseVueTriangleDown extends CaseVue {

    private Polygon shape;

    public CaseVueTriangleDown (CaseModele caseMod) {
    	super(caseMod);
        setContentAreaFilled(false);
        initialize();
    }

    protected void initialize() {
        shape = new Polygon();
        setSize(30, 30);
        shape.addPoint(15, 26);
        shape.addPoint(0, 0); 
        shape.addPoint(30, 0);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(30, 30));
    }

    // Hit detection
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    	g.drawPolygon(shape);
    }

    protected void paintBorder(Graphics g) {
    }
}