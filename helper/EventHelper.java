package helper;

import java.util.ArrayList;

import controllers.*;
import models.Post;

public class EventHelper {
	
	protected Controller activeController;
	protected SubmitFormData formData;
	
	public EventHelper( Controller activeController ) {
		this.activeController = activeController;
		formData = new SubmitFormData(activeController);
	}
	
	public void openSearchEvent()
	{
		activeController.openSearch();
	}
	
	public void openRemoveEvent()
	{
		activeController.openRemover();
	}
	
	public SubmitFormData getSubmitFormData()
	{
		return formData;
	}
	
	
	public void parse(String selected )
	{
		activeController.parse(selected);
	}
	
	public void savePostsInFile()
	{
		activeController.saveInFile();
	}
	
	public void setPostsForIndexView( ArrayList<Post> posts ) {
		activeController.setPosts(posts);
	}

}
