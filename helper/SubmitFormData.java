package helper;

import controllers.*;

public class SubmitFormData {
	
	protected Controller controller;
	protected String[] textData;
	protected int[] intData;
	protected boolean[] boolData;
	
	public SubmitFormData( Controller controllerL )
	{
		controller = controllerL;
	}
	
	public void setData( String[] textDataL, int[] intDataL )
	{
		textData = textDataL;
		intData = intDataL;
	}
	
	public void setData(String[] textDataL, int[] intDataL, boolean[] boolData ) 
	{
		setData( textDataL, intDataL );
		this.boolData = boolData;
	}
	
	public void sendData() 
	{
		controller.submitForm( this );
	}
	
	public void searchData()
	{
		controller.search(this);
	}
	
	public String[] getTextData()
	{
		return textData;
	}
	
	public int[] getIntData()
	{
		return intData;
	}
	
	public boolean[] getBoolData()
	{
		return boolData;
	}

}
