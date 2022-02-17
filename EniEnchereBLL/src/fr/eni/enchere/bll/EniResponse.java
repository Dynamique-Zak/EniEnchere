package fr.eni.enchere.bll;

public class EniResponse {

	private int codeResponse;
	private String messageResponse;
	
	/**
	 * @param codeResponse
	 * @param messageResponse
	 */
	public EniResponse(int codeResponse, String messageResponse) {
		super();
		this.codeResponse = codeResponse;
		this.messageResponse = messageResponse;
	}

	public void setResponse(int code, String message) {
		setCodeResponse(code);
		setMessageResponse(message);
	}
	/**
	 * @return the codeResponse
	 */
	public int getCodeResponse() {
		return codeResponse;
	}

	/**
	 * @param codeResponse the codeResponse to set
	 */
	public void setCodeResponse(int codeResponse) {
		this.codeResponse = codeResponse;
	}

	/**
	 * @return the messageResponse
	 */
	public String getMessageResponse() {
		return messageResponse;
	}

	/**
	 * @param messageResponse the messageResponse to set
	 */
	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	
	
	
}
