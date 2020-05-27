package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class ButtonHelper extends WidgetHelper {
	
	public ButtonHelper( Shell shell ) {
		super(shell);
	}

	public Button create( String text ) {
		
		Button button = new Button(shell, SWT.NONE);	
		
	    button.setText( text );
		button = setGD(button);
		return button;
	}
	
	public Button create( int num ) {
		return create( String.valueOf(num));
	}
	
	protected Button setGD(Button button)
	{
		GridData gd = lh.makeGD();
		button.setLayoutData(gd);
		return button;
	}
	
	
}
