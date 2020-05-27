package helper;

import org.eclipse.swt.widgets.*;

public class WidgetHelper {
	protected Shell shell;
	protected LayoutHelper lh;
	
	public WidgetHelper( Shell shell ) {
		this.shell = shell;
		lh = new LayoutHelper(shell);
	}
	
	public void createSpacer() {
		lh.createSpacer();
	}
	
	public void createMargin( int margin )
	{
		lh.createMargin(margin);
	}
}
