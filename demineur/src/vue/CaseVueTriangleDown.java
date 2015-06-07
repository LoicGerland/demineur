package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import modele.CaseModele;


public class CaseVueTriangleDown extends CaseVue {

	private static final long serialVersionUID = -5293370268452789379L;
	
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