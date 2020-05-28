package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class LabelHelper extends WidgetHelper {
	public LabelHelper(Shell shell) {
		super(shell);
	}

	public Label create(String text) {
		Label label = new Label(shell, SWT.NULL);
		label.setText(text);
		label = setGD(label);

		return label;
	}

	protected Label setGD(Label label) {
		GridData gd = lh.makeGD();
		label.setLayoutData(gd);
		return label;
	}
}
