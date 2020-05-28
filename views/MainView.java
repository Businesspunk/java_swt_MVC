package views;

import java.util.ArrayList;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import helper.*;
import models.Post;

public class MainView {

	protected Display display;
	protected Shell shell;
	protected EventHelper event;
	protected int height = 700, width = 950;

	public MainView(Display exist, EventHelper eh) {
		display = exist;
		event = eh;
		shell = new Shell();
	}

	public int[] delete() {
		int[] coords = getCoordinations();
		shell.close();
		return coords;
	}

	public Shell getShell() {
		return shell;
	}

	protected void setMainLayout() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 30;
		shell.setLayout(gridLayout);
	}

	public void render(String title) {
		shell.setSize(width, height);
		shell.setText(title);
		shell.open();

		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void render() {
		render("Main Shell");
	}

	public int[] getCoordinations() {
		return new int[] { shell.getLocation().x, shell.getLocation().y };
	}

	public void setCoordinations(int x, int y) {
		shell.setLocation(x, y);
	}

	public void setCoordinations(int[] coords) {
		setCoordinations(coords[0], coords[1]);
	}

	public void create(ArrayList<Post> posts, Paginator paginator) {

	}

	public void create(ArrayList<Post> posts, String[] sportsType, Paginator paginator) {

	}

}