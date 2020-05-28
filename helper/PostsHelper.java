package helper;

import java.util.ArrayList;

import models.Post;

public class PostsHelper {

	public static boolean isExistInArray(Post post, ArrayList<Post> posts) {

		for (int i = 0; i < posts.size(); i++) {
			if (post.equals(posts.get(i))) {
				return true;
			}
		}

		return false;
	}

	public static ArrayList<Post> postsMinus(ArrayList<Post> from, ArrayList<Post> minus) {
		ArrayList<Post> result = new ArrayList<Post>();

		for (int i = 0; i < from.size(); i++) {
			if (!isExistInArray(from.get(i), minus)) {
				result.add(from.get(i));
			}
		}

		return result;
	}

}
