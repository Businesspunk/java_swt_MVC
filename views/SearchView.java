package views;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import helper.*;
import models.*;
import views.items.PostView;

public class SearchView extends MainView {

	private boolean isModified = false;

	public SearchView(Display exist, EventHelper eh) {
		super(exist, eh);
	}

	public void create(ArrayList<Post> posts, String[] sportsType, Paginator paginator) {
		setMainLayout();

		ButtonHelper bh = new ButtonHelper(shell);
		TextHelper tx = new TextHelper(shell);
		LabelHelper lh = new LabelHelper(shell);
		DateTimeHelper dt = new DateTimeHelper(shell);
		ComboHelper ch = new ComboHelper(shell, sportsType);

		String[] titles = { "Турнир", "Дата проведения", "Вид спорта", "ФИО победителя", "Размер призовых турнира",
				"Заработок победителя" };

		for (int i = 0; i < titles.length; i++) {
			lh.create(titles[i] + ":");
		}

		lh.createSpacer();

		Text eventName = tx.create();
		DateTime dateTime = dt.create();
		Combo sportName = ch.create();
		Text fullName = tx.create();
		Text costMax = tx.create();
		Text costMaxRev = tx.create();

		lh.createMargin(4);
		Text costMin = tx.create();
		Text costMinRev = tx.create();

		lh.createSpacer();

		Button button = bh.create("Поиск");

		SelectionListener selListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isModified = true;
			}
		};

		dateTime.addSelectionListener(selListener);

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				SubmitFormData formData = event.getSubmitFormData();

				String[] textData = new String[] { eventName.getText(), sportName.getText(), fullName.getText(),
						costMin.getText(), costMax.getText(), costMinRev.getText(), costMaxRev.getText() };

				int[] intData = new int[] { dateTime.getYear(), dateTime.getMonth() + 1, dateTime.getDay() };

				formData.setData(textData, intData, new boolean[] { isModified });
				formData.searchData();

			}
		});

		if (posts.size() > 0) {
			bh.createSpacer();

			for (int i = 0; i < posts.size(); i++) {

				PostView postView = new PostView(posts.get(i), shell);
				postView.create();
			}
		}

		if (paginator.isAvaible()) {
			paginator.create(shell);
		} else {
			paginator.printPostsCount(shell);
		}

		render("Поиск");
	}

}