package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class TextHelper extends WidgetHelper {

	public TextHelper(Shell shell) {
		super(shell);
	}

	public Text create() {
		Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
		text = setGD(text);

		return text;
	}

	protected Text setGD(Text text) {
		GridData gd = lh.makeGD();
		text.setLayoutData(gd);
		return text;
	}

}
