package controllers;

import java.nio.file.*;
import java.util.ArrayList;
import helper.*;
import models.Post;
import parsers.*;
import views.IndexView;

public class IndexController extends Controller {

	public IndexController() {

		super();

		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();

		xmlWriter = new XMLWriter(currentPath + "/files/output.xml");
		xmlParser = new XMLParser();

		activeView = new IndexView(display, getEvents());
	}

	public void render(int page) {
		int[] coords = activeView.delete();
		activeView = new IndexView(display, getEvents());
		activeView.setCoordinations(coords);

		ArrayList<Post> postsOnPage = paginator.getByPage(page);
		activeView.create(postsOnPage, paginator);
	}

	public void openSearch() {
		Controller sc = new SearchController(sportsType, posts);
		sc.index();
	}

	public void openRemover() {
		Controller rc = new RemoveController(posts, getEvents(), sportsType);
		rc.index();
	}

	public void setPosts(ArrayList<Post> postsL) {
		posts = postsL;
		paginator.updatePosts(posts);
		render(0);
	}

	public void updateSporstTypes(String sport) {
		StringHelper sh = new StringHelper(sportsType);
		sportsType = sh.push(sport);
	}

	public void parse(String path) {
		saveParseResult(xmlParser.parse(path));
	}

	public void saveInFile() {
		xmlWriter.create(posts);
	}

	public void saveParseResult(ArrayList<Post> posts) {
		for (int i = 0; i < posts.size(); i++) {
			savePost(posts.get(i));
		}

		render(0);
	}

	public void savePost(Post post) {
		posts.add(post);
		paginator.updatePosts(posts);
		updateSporstTypes(post.getSportType());
	}

	public void submitForm(SubmitFormData data) {
		String[] textData = data.getTextData();
		int[] intData = data.getIntData();

		Post post = new Post(textData);
		post.setDate(intData);

		savePost(post);
		render(0);
	}

	public void search(SubmitFormData data) {
	}

}
