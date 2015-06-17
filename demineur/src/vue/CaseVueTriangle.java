package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import modele.CaseModele;

public class CaseVueTriangle extends CaseVue {

	private static final long serialVersionUID = -5293370268452789379L;

	private Polygon shape;

	public CaseVueTriangle(CaseModele caseMod, boolean up) {
		super(caseMod);
		setContentAreaFilled(false);
		initialize(up);
	}

	protected void initialize(boolean up) {
		shape = new Polygon();
		setSize(30, 30);
		if (up) {
			shape.addPoint(15, 0);
			shape.addPoint(0, 26);
			shape.addPoint(30, 26);
		} else {
			shape.addPoint(15, 26);
			shape.addPoint(0, 0);
			shape.addPoint(30, 0);
		}
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
		if (this.getCaseMod().isClicked()) {
			g.setColor(Color.BLUE);
		} else
			g.setColor(Color.black);
		super.paintComponent(g);
		g.drawPolygon(shape);
	}

	protected void paintBorder(Graphics g) {
	}
}