package helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class ComboHelper extends WidgetHelper{

	String[] items;
	public ComboHelper(Shell shell, String[] items ) 
	{
		super(shell);
		this.items = items;
	}
	
	public Combo create()
	{
		Combo combo = new Combo(shell, SWT.BORDER);
		
		for (int i = 0; i < items.length; i++)
	    {
	        combo.add(items[i]);
	    }
		
		return setGD( combo );
	}

	protected Combo setGD( Combo combo )  
	{
		GridData gd = lh.makeGD();
		combo.setLayoutData(gd);
		return combo;
	}

}
