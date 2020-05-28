package views;

import helper.*;
import org.eclipse.swt.widgets.*;
import models.Post;
import views.items.*;
import java.util.ArrayList;
import org.eclipse.swt.*;

public class IndexView extends MainView {

	public IndexView(Display exist, EventHelper eh) {
		super(exist, eh);
	}

	public void create(ArrayList<Post> posts, Paginator paginator) {

		setMainLayout();

		ButtonHelper bh = new ButtonHelper(shell);
		TextHelper tx = new TextHelper(shell);
		LabelHelper lh = new LabelHelper(shell);
		DateTimeHelper dt = new DateTimeHelper(shell);

		String[] titles = { "Турнир", "Дата проведения", "Вид спорта", "ФИО победителя", "Размер призовых турнира" };

		for (int i = 0; i < titles.length; i++) {
			lh.create(titles[i] + ":");
		}

		lh.createSpacer();

		Text eventName = tx.create();
		DateTime dateTime = dt.create();
		Text sportName = tx.create();
		Text fullName = tx.create();
		Text cost = tx.create();

		Button button = bh.create("Добавить");

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				SubmitFormData formData = event.getSubmitFormData();

				String[] textData = new String[] { eventName.getText(), sportName.getText(), fullName.getText(),
						cost.getText() };

				int[] intData = new int[] { dateTime.getYear(), dateTime.getMonth() + 1, dateTime.getDay() };
				formData.setData(textData, intData);
				formData.sendData();

			}
		});

		button = bh.create("Поиск");

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				event.openSearchEvent();
			}
		});

		button = bh.create("Добавить из файла");

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				String[] filterExt = { "*.xml;*.XML" };
				dlg.setFilterExtensions(filterExt);

				String selected = dlg.open();

				if (selected != null) {
					event.parse(selected);
				}
			}
		});

		button = bh.create("Сохранить в файл");

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				event.savePostsInFile();
			}
		});

		button = bh.create("Удалить");

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				event.openRemoveEvent();
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

		render("Главная");

	}

}