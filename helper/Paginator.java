package helper;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import controllers.Controller;
import models.Post;

public class Paginator {
	
	protected ArrayList<Post> posts;
	protected int pagination;
	protected int currentPage = 0;
	protected Controller activeController;


	public Paginator( int paginationL, ArrayList<Post> postsL, Controller activeControllerL )
	{
		setPosts( postsL );
		pagination = paginationL;
		activeController = activeControllerL;
	}
	
	public void setPosts( ArrayList<Post> postsL )
	{
		posts = postsL;
	}
	
	public void updatePosts( ArrayList<Post> postsL ) {
		posts = postsL;
	}
	
	public boolean isAvaible()
	{
		return getNumOfPages() >= 2;
	}
	
	protected Listener getListener( int page ) {
		Paginator active = this;
		
		Listener listener = new Listener() {
			public void handleEvent(Event e) {
				active.goToPageLayer( page ); 		  		
			}
		};
			
		return listener;
	}
	
	public void create( Shell shell  )
	{
		int numOfPages = getNumOfPages();
		ButtonHelper bh = new ButtonHelper(shell);
		LabelHelper lh = new LabelHelper(shell);
		
		bh.createSpacer();
		
		Button button = bh.create( "Назад" );
		if( currentPage == 0 ) {
			button.setEnabled(false);
		}else {
			button.addListener(SWT.Selection, getListener(currentPage-1));
		}
		
		button = bh.create( "Вперед" );
		if( currentPage == numOfPages - 1 ) {
			button.setEnabled(false);
		}else {
			button.addListener(SWT.Selection, getListener(currentPage+1));
		}
		
		bh.createSpacer();
		
		for( int i = 0; i < numOfPages; i++ ) {
			
		    button = bh.create(i+1);
			
			if( currentPage == i ) {
				button.setEnabled(false);
			}else {
				button.addListener(SWT.Selection, getListener(i));
			}
		}
		
		printPostsCount(shell);
		bh.createSpacer();
		lh.create( "На странице: " + (currentPage + 1) + " из " + getNumOfPages());
	}
	
	public void printPostsCount( Shell shell )
	{
		ButtonHelper bh = new ButtonHelper(shell);
		TextHelper tx = new TextHelper(shell);
		LabelHelper lh = new LabelHelper(shell);
		
		lh.createSpacer();
		lh.create( "Записей на странице" );
		
		lh.createSpacer();
		Text pages = tx.create();
		pages.setText( Integer.toString(pagination) );
		Button button = bh.create("Обновить");
		
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int newPages = Integer.parseInt( pages.getText() );
				pagination = newPages;
				activeController.render(0);
			}
		});
		
		lh.createSpacer();
		lh.create( "Записей всего: " + posts.size() );
	}
	
	public int getCurrentPostsOnPage()
	{
		if( pagination > posts.size() ) {
			return posts.size();
		}else {
			return pagination;
		}
	}
	
	
	public ArrayList<Post> getByPage( int page )
	{	
		currentPage = page;
		
		int endLength, startLength;
		
		if( posts.size() < (page + 1)*pagination )
		{
			endLength = posts.size();
		}else {
			endLength = (page + 1)*pagination;
		}
		
		startLength = page* pagination;
		
		if(startLength + 1 > posts.size()) {
			return new ArrayList<Post>();
		}
		
		ArrayList<Post> onPage = new ArrayList<Post>(endLength - startLength);
		
		for( int i = startLength, j = 0; i < endLength; i++, j++ ) {
			onPage.add( posts.get(i) );
		}
		
		return onPage;
	}
	
	
	public int getNumOfPages()
	{
		double ceil = (double) posts.size()/pagination;
		int all = (int) ceil;
		
		if(ceil - all == 0) {
			return all;
		}else {
			return all + 1;
		}
	}
	
	public void goToPageLayer(int page)
	{
		currentPage = page;
		activeController.goToPage(page);
	}
	
	public int getCurrentPage()
	{
		return currentPage;
	}
	
}
