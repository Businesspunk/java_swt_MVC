package helper;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class LayoutHelper {
	protected Shell shell;
	
	public LayoutHelper( Shell shellL ) {
		shell = shellL;
	}
	
	public void createSpacer()
	{
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 30;
		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(gd);
	}
	
	public void createMargin( int margin )
	{
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = margin*5;
		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(gd);
	}
	
	public GridData makeGD()
	{
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 5;
		return gd;
	}
}
