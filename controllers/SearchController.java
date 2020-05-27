package controllers;

import java.util.ArrayList;

import helper.*;
import models.Post;
import views.*;

public class SearchController extends Controller {
	
	protected ArrayList<Post> searchResult;
	
	public SearchController( String[] sportsType, ArrayList<Post> posts )
	{
		super();
		this.sportsType = sportsType; 
		this.posts = posts;
	}
	
	public void index() 
	{		
		activeView = new SearchView(display, getEvents());
		activeView.create( new ArrayList<Post>(0), sportsType, paginator);	
	}
	
	public void render(int page)
	{	
		int[] coords = activeView.delete();
		activeView = new SearchView(display, getEvents() );
		activeView.setCoordinations( coords );
		
		paginator.setPosts(searchResult);
		ArrayList<Post> postsOnPage = paginator.getByPage(page);
		activeView.create( postsOnPage,  sportsType, paginator );
	}
	
	
	public void renderSearch(ArrayList<Post> posts)
	{
		render( 0);
	}
	
	public void search( SubmitFormData data )
	{
		searchResult = search.search(posts, data);	
		renderSearch(searchResult);
	}

	public void openSearch() {}

	public void saveInFile() {}

	public void parse(String selected) {}

	public void submitForm(SubmitFormData sfd) {}

	public void openRemover() {}

	public void setPosts(ArrayList<Post> posts2) {}
	
}
