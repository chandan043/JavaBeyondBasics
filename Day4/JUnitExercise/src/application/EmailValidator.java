package application;

import com.gl.exception.GlAcademyException;

public class EmailValidator {
	public boolean validateEmailId(String emailId) throws GlAcademyException {
		
		if(emailId == null || emailId.isBlank())
			throw new GlAcademyException("Invalid Email ID");
		return emailId.matches("\\w+@\\w+\\.(com|in)");
	}
	
}
