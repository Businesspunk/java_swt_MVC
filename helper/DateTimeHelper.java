package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class DateTimeHelper extends WidgetHelper
{
	public DateTimeHelper(Shell shell) {
		super(shell);
	}
	
	public DateTime create() {
		DateTime dateTime = new DateTime(shell, SWT.CALENDAR | SWT.BORDER);
		dateTime = setGD(dateTime);
		return dateTime;
	}
	protected DateTime setGD(DateTime dt) {
		GridData gd = lh.makeGD();
		dt.setLayoutData(gd);
		return dt;
	}
}
