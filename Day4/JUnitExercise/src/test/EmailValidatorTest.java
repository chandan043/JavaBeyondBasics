package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.gl.exception.GlAcademyException;

import application.EmailValidator;

public class EmailValidatorTest {
	@Test
	public void validateEmailIdValidEmailId() throws GlAcademyException {
		EmailValidator ev = new EmailValidator();
		Assertions.assertTrue(ev.validateEmailId("chandan@gmail.com"));
	}
	@Test
	public void validateEmailIdInvalidEmailId() {
		EmailValidator ev = new EmailValidator();
		Exception ex = Assertions.assertThrows(Exception.class,() -> ev.validateEmailId(""));
		Assertions.assertEquals("Invalid Email ID",ex.getMessage());
	}
	@Test
	public void validateEmailIdInvalidEmailIdException() throws GlAcademyException {
		EmailValidator ev = new EmailValidator();
		Exception ex = Assertions.assertThrows(Exception.class,() -> ev.validateEmailId(null));
		Assertions.assertEquals("Invalid Email ID",ex.getMessage());
	}
	@ParameterizedTest
	@CsvSource(value= {"Sirius_Black,false","Lily_Evans@Hoggy.in,true","Remus_Lups,false","NymphieTonks@magic.com,true"})
	public void validateEmailIdParamterizedEmailId(String email,boolean expected) throws GlAcademyException {
		EmailValidator ev = new EmailValidator();
		Assertions.assertEquals(expected,ev.validateEmailId(email));
	}
}
