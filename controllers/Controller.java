package controllers;

import java.util.ArrayList;
import org.eclipse.swt.widgets.*;
import helper.*;
import parsers.*;
import views.*;
import models.*;

public abstract class Controller {

	protected Display display = Display.getDefault();
	protected ArrayList<Post> posts = new ArrayList<Post>();
	protected Paginator paginator;
	protected String[] sportsType = new String[0];
	protected MainView activeView;
	protected SearchEngine search;
	protected XMLWriter xmlWriter;
	protected XMLParser xmlParser;

	protected EventHelper getEvents() {
		return new EventHelper(this);
	}

	public Controller() {
		paginator = new Paginator(6, posts, this);
		search = new SearchEngine();
	}

	public void index() {
		ArrayList<Post> postsOnPage = paginator.getByPage(0);
		activeView.create(postsOnPage, paginator);
	}

	public void goToPage(int page) {
		render(page);
	}

	public abstract void render(int page);

	public abstract void openSearch();

	public abstract void search(SubmitFormData data);

	public abstract void saveInFile();

	public abstract void parse(String selected);

	public abstract void submitForm(SubmitFormData sfd);

	public abstract void openRemover();

	public abstract void setPosts(ArrayList<Post> posts2);

}
