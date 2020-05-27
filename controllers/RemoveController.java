package controllers;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import helper.*;
import models.Post;
import views.RemoveView;

public class RemoveController extends Controller {
	
	EventHelper eventHelperIndex;
	
	public RemoveController( ArrayList<Post> posts, EventHelper eh, String[] sportsTypeL )
	{	
		super();
		this.posts = posts;
		eventHelperIndex = eh;
		sportsType = sportsTypeL;
		
	}
	
	public void index() 
	{		
		activeView = new RemoveView(display, getEvents());
		activeView.create( new ArrayList<Post>(0), sportsType, paginator);	
	}
	
	public void search( SubmitFormData data )
	{
		ArrayList<Post> result = search.search(posts, data);	
		minus ( posts, result );
	}
	
	public void minus( ArrayList<Post> old, ArrayList<Post> find )
	{
		ArrayList<Post> posts = PostsHelper.postsMinus(old, find);
		
		MessageBox dialog = new MessageBox(activeView.getShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
		dialog.setText("Удаление записей");
		dialog.setMessage("Удалиться " + (this.posts.size() - posts.size() ) + " запией");
		int returnCode = dialog.open();
		
		activeView.delete();
		if( returnCode == 32 ) {
			eventHelperIndex.setPostsForIndexView(posts);
		}
				
	}

	public void render(int page) {}

	public void openSearch() {}

	public void saveInFile() {}

	public void parse(String selected) {}

	public void submitForm(SubmitFormData sfd) {}

	public void openRemover() {}

	public void setPosts(ArrayList<Post> posts2) {}
	
}
