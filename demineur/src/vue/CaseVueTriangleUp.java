package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import modele.CaseModele;

public class CaseVueTriangleUp extends CaseVue {

	private static final long serialVersionUID = 7417758830643828885L;

	private Polygon shape;

	public CaseVueTriangleUp(CaseModele caseMod) {
		super(caseMod);
		setContentAreaFilled(false);
		initialize();
	}

	protected void initialize() {
		shape = new Polygon();
		setSize(30, 30);
		shape.addPoint(15, 0);
		shape.addPoint(0, 26);
		shape.addPoint(30, 26);
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