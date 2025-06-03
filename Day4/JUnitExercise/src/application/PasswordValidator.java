package application;

import com.gl.exception.GlAcademyException;

public class PasswordValidator {
	public boolean validatePassword(String password) throws GlAcademyException {
		if(password == null || password.isBlank())
			throw new GlAcademyException("Invalid Password");
		return password.matches("[A-Za-z0-9]{8,20}");
	}
}
