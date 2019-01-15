package huston;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;

public class MapBuilder {
	
	MCA mca= new MCA();

	public MapBuilder(JFrame f) {
		frame = f;
	}
	
	//ist für Robotserver nötig
	public MapBuilder() {
		
	}


	public void build() {
		// Create a new JFrame.
		JFrame f = new JFrame("Map");
		MapBuilder app = new MapBuilder(f);

		// Add components to the frame.
		f.getContentPane().add(app.createComponents());

		// Display the frame.
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setSize(400, 400);
		f.setVisible(true);
	}

	// The frame.
	protected JFrame frame;

	// The status label.
	protected JLabel label = new JLabel();

	// The SVG canvas.
	protected JSVGCanvas svgCanvas = new JSVGCanvas();



	public JComponent createComponents() {
		// Create a panel and add the button, status label and the SVG canvas.
		final JPanel panel = new JPanel(new BorderLayout());

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		p.add(label);
		

		panel.add("Center", svgCanvas);
		//file:C:/Users/SickxX/git/ki_prak3.1_3.2/RoboCentral/3.2_Houses-1819.svg
		//file:/C:/Users/MaHP/git/ki_prak3.1_3.2/RoboCentral/3.2_Houses-1819.svg
		svgCanvas.setURI("file:/C:/Users/MaHP/git/ki_prak3.1_3.2/RoboCentral/3.2_Houses-1819.svg".toString());
		
		


		// Set the JSVGCanvas listeners.
		svgCanvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {
			public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
				label.setText("Document Loading...");
			}
			public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
				label.setText("Document Loaded.");
			}
		});

		svgCanvas.addGVTTreeBuilderListener(new GVTTreeBuilderAdapter() {
			public void gvtBuildStarted(GVTTreeBuilderEvent e) {
				label.setText("Build Started...");
			}
			public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
				label.setText("Build Done.");
				frame.pack();
			}
		});

		svgCanvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {
			public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
				label.setText("Rendering Started...");
			}
			public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
				label.setText("");
			}
		});

		return panel;
	}
}
