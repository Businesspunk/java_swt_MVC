package helper;

import controllers.*;

public class SubmitFormData {

	private Controller controller;
	private String[] textData;
	private int[] intData;
	private boolean[] boolData;

	public SubmitFormData(Controller controllerL) {
		controller = controllerL;
	}

	public void setData(String[] textDataL, int[] intDataL) {
		textData = textDataL;
		intData = intDataL;
	}

	public void setData(String[] textDataL, int[] intDataL, boolean[] boolData) {
		setData(textDataL, intDataL);
		this.boolData = boolData;
	}

	public void sendData() {
		controller.submitForm(this);
	}

	public void searchData() {
		controller.search(this);
	}

	public String[] getTextData() {
		return textData;
	}

	public int[] getIntData() {
		return intData;
	}

	public boolean[] getBoolData() {
		return boolData;
	}

}
