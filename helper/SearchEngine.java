package helper;

import java.util.*;

import models.Post;

public class SearchEngine {

	private String eventName, sportName, fullName;

	private Integer minCost, maxCost, minRev, maxRev;
	private Date date;

	protected void setAllAtNull() {
		eventName = null;
		sportName = null;
		fullName = null;
		minCost = null;
		maxCost = null;
		minRev = null;
		maxRev = null;
		date = null;
	}

	public void setRulesForSearch(SubmitFormData data) {
		setAllAtNull();

		if (!data.getTextData()[0].isEmpty()) {
			eventName = data.getTextData()[0];
		}

		if (!data.getTextData()[1].isEmpty()) {
			sportName = data.getTextData()[1];
		}

		if (!data.getTextData()[2].isEmpty()) {
			fullName = data.getTextData()[2];
		}

		// IntData Below

		if (!data.getTextData()[3].isEmpty()) {
			minCost = Integer.parseInt(data.getTextData()[3]);
		}

		if (!data.getTextData()[4].isEmpty()) {
			maxCost = Integer.parseInt(data.getTextData()[4]);
		}

		if (!data.getTextData()[5].isEmpty()) {
			minRev = Integer.parseInt(data.getTextData()[5]);
		}

		if (!data.getTextData()[6].isEmpty()) {
			maxRev = Integer.parseInt(data.getTextData()[6]);
		}

		// DateData Below

		if (data.getBoolData()[0]) {
			date = DateEngine.create(data.getIntData());
		}

	}

	protected boolean checkAll(Post item) {
		return checkEvent(item) && checkSportName(item) && checkFullName(item) && checkAllCost(item)
				&& checkEarnCost(item) && checkDate(item);
	}

	protected boolean checkEvent(Post item) {
		if (eventName == null) {
			return true;
		} else {
			return item.isThisEvent(eventName);
		}
	}

	protected boolean checkSportName(Post item) {
		if (sportName == null) {
			return true;
		} else {
			return item.isThisSport(sportName);
		}
	}

	protected boolean checkFullName(Post item) {
		if (fullName == null) {
			return true;
		} else {
			return item.isThisName(fullName);
		}
	}

	protected boolean checkAllCost(Post item) {
		return compare2Integer(item, minCost, maxCost);
	}

	protected boolean checkEarnCost(Post item) {
		return compare2Integer(item, minRev, maxRev);
	}

	protected boolean checkDate(Post item) {
		if (date == null) {
			return true;
		} else {
			return item.isThisDate(date);
		}
	}

	protected boolean compare2Integer(Post item, Integer min, Integer max) {
		boolean result;

		if (min == null) {
			result = true;
		} else {
			result = min < item.getIntAllCost();
		}

		if (result) {
			if (max == null) {
				result = true;
			} else {
				result = max > item.getIntAllCost();
			}
		}

		return result;
	}

	public ArrayList<Post> search(ArrayList<Post> posts, SubmitFormData data) {
		setRulesForSearch(data);
		ArrayList<Post> result = new ArrayList<Post>();

		for (int i = 0; i < posts.size(); i++) {
			if (checkAll(posts.get(i))) {
				result.add(posts.get(i));
			}
		}

		return result;
	}

	public void debug() {
		System.out.println("Название турнира: " + eventName);
		System.out.println("Дата проведения: " + date);
		System.out.println("Вид спорта: " + sportName);
		System.out.println("ФИО: " + fullName);
		System.out.println("Размер призовых min: " + minCost);
		System.out.println("Размер призовых max: " + maxCost);
		System.out.println("Заработал min: " + minRev);
		System.out.println("Заработал max: " + maxRev);
	}
}
