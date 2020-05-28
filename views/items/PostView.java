package views.items;

import org.eclipse.swt.widgets.*;

import helper.LabelHelper;
import models.Post;

public class PostView {

	protected Post post;
	protected Shell shell;

	public PostView(Post postL, Shell shellL) {
		post = postL;
		shell = shellL;
	}

	public void create() {
		LabelHelper lh = new LabelHelper(shell);
		String[] arrayData = post.getArrayStringPublicData();

		for (int i = 0; i < arrayData.length; i++) {
			lh.create(arrayData[i]);
		}
	}
}