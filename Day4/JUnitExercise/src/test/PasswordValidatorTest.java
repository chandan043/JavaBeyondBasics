package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.gl.exception.GlAcademyException;
import application.PasswordValidator;
public class PasswordValidatorTest {
	@Test
	public void validatePasswordValidPassword() throws GlAcademyException {
		PasswordValidator pv = new PasswordValidator();
		Assertions.assertTrue(pv.validatePassword("Asdf1234"));
	}
	@Test
	public void validatePasswordInvalidPassword() throws GlAcademyException {
		PasswordValidator pv = new PasswordValidator();
		Assertions.assertFalse(pv.validatePassword("Asdf"));
		
	}
	@Test
	public void validatePasswordInvalidPasswordException() throws GlAcademyException {
		PasswordValidator pv = new PasswordValidator();
		Exception ex = Assertions.assertThrows(Exception.class,()->pv.validatePassword(null));
		Assertions.assertEquals("Invalid Password",ex.getMessage());
	}
	@ParameterizedTest
	@CsvSource(value ={"Qwerty,false","Qwerty1234,true","QwerAsdfZxcvVcxzFdsaRewq,false","Zxcvb54321,true"})
	public void validatePasswordParameterizedPassword(String password , boolean expected) throws GlAcademyException {
		PasswordValidator pv = new PasswordValidator();
		boolean actual  =pv.validatePassword(password);
		Assertions.assertEquals(expected,actual);
	}
}