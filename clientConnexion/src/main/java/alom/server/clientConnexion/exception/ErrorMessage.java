package alom.server.clientConnexion.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ErrorMessage implements Serializable{
	
	
	private int code ;
	private String message;
	private String reason;
	
	
	public ErrorMessage() {
		super();
	}


	public ErrorMessage(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}



	public ErrorMessage( int code, String message, String reason) {
		super();
		this.code = code;
		this.message = message;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		if (code != other.code)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	
}
