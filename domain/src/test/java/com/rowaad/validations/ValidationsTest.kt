package com.rowaad.validations

import com.rowaad.app.usecase.ValidationMsg
import com.rowaad.app.usecase.Validations
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationsTest {


    @Test
    fun `isValidPhone general cases`() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidPhone("123456789"))
        assertEquals(ValidationMsg.MIN, Validations.isValidPhone("12345678"))
        assertEquals(ValidationMsg.INVALID_FORMAT, Validations.isValidPhone("123.45678"))
        assertEquals(ValidationMsg.EMPTY, Validations.isValidPhone(""))


    }
    @Test
    fun `isValidPhone with special characters`() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidPhone("(123) 456-7890"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidPhone("+123-456(7890)"))
        assertEquals(ValidationMsg.INVALID_FORMAT, Validations.isValidPhone("123.456.7890"))
    }

    @Test
    fun `isValidName general`() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("John Doe"))
        assertEquals(ValidationMsg.MIN, Validations.isValidName("Jo"))
        assertEquals(ValidationMsg.MAX, Validations.isValidName("abcdefghijklmnop"))
    }

    @Test
    fun `isValidName with special characters and digits`() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("John Doe"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("Ángel Gabriel"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("María-José"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("O'Connell"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("Smith-Jones"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidName("Anne Marie"))
    }

    @Test
    fun isValidNameNoLimitsTest() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidNameNoLimits("John Doe"))
        assertEquals(ValidationMsg.MIN, Validations.isValidNameNoLimits("Jo"))
    }


    @Test
    fun isValidAddressTest() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidAddress("123 Main St"))
        assertEquals(ValidationMsg.MIN, Validations.isValidAddress("12"))
    }

    @Test
    fun `isValidAddress with special characters`() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidAddress("123 Main St."))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidAddress("123 Main St, Apt #4B"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidAddress("P.O. Box 123"))
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidAddress("Flat 3A, 1 The Avenue"))
    }



    @Test
    fun isValidIbanTest() {
        assertTrue(Validations.isValidIban("FR14 2004 1010 0505 0001 3M02 606"))
        assertTrue(Validations.isValidIban("FR14 2004 1010 0505 0001 3M02 606"))
        assertTrue(Validations.isValidIban("GB29 NWBK 6016 1331 9268 19"))
        assertTrue(Validations.isValidIban("SA03 8000 0000 6080 1016 7519"))
        assertFalse(Validations.isValidIban("SA12313130313113131"))
        assertFalse(Validations.isValidIban("SA1.313130313113131"))
        assertFalse(Validations.isValidIban("S313130313113131"))
        assertFalse(Validations.isValidIban("123456789012345678901234"))
        assertFalse(Validations.isValidIban("     "))
        assertFalse(Validations.isValidIban(""))
    }





    @Test
    fun isValidTitleTest() {
        assertTrue(Validations.isValidTitle("A valid title"))
        assertFalse(Validations.isValidTitle(""))
    }

    @Test
    fun isValidBodyTest() {
        assertEquals(ValidationMsg.NO_ERROR, Validations.isValidBody("A valid body"))
        assertEquals(ValidationMsg.MIN, Validations.isValidBody("12"))
    }

    @Test
    fun isValidMailTest() {
        assertTrue(Validations.isValidMail("john@example.com"))
        assertFalse(Validations.isValidMail("john@example"))
        assertFalse(Validations.isValidMail("john"))
    }

    @Test
    fun `isValidMail with special characters`() {
        assertEquals(true, Validations.isValidMail("john_doe@example.co.uk"))
        assertEquals(false, Validations.isValidMail("test.user+alias@example.com"))
        assertEquals(false, Validations.isValidMail("john@example"))
        assertEquals(false, Validations.isValidMail("john@.com"))
    }

    @Test
    fun isValidIdTest() {
        assertTrue(Validations.isValidId("12345"))
        assertFalse(Validations.isValidId(""))
    }

    @Test
    fun isValidBirthDateTest() {
        assertTrue(Validations.isValidBirthDate("2000-01-01"))
        assertFalse(Validations.isValidBirthDate(""))
        assertFalse(Validations.isValidBirthDate(null))
    }

    @Test
    fun isValidNationalTest() {
        assertTrue(Validations.isValidNational(1))
        assertFalse(Validations.isValidNational(null))
        assertFalse(Validations.isValidNational(0))
    }

    @Test
    fun isValidLocationTest() {
        assertTrue(Validations.isValidLocation("New York"))
        assertFalse(Validations.isValidLocation(""))
        assertFalse(Validations.isValidLocation(null))
    }

    @Test
    fun isValidPassTest() {
        assertTrue(Validations.isValidPass("password123"))
        assertFalse(Validations.isValidPass("pass"))
        assertFalse(Validations.isValidPass("passpasspasspass1"))
    }
    @Test
    fun `isValidPass with different character types`() {
        assertEquals(true, Validations.isValidPass("12345678"))
        assertEquals(true, Validations.isValidPass("!@#$%^&*"))
        assertEquals(false, Validations.isValidPass("        "))
        assertEquals(false, Validations.isValidPass("Aa1!"))
    }

    @Test
    fun isValidCodeTest() {
        assertTrue(Validations.isValidCode("1234"))
        assertFalse(Validations.isValidCode("123"))
        assertFalse(Validations.isValidCode(""))
    }

    @Test
    fun `isValidCode with special characters and whitespace`() {
        assertEquals(false, Validations.isValidCode("12#4"))
        assertEquals(false, Validations.isValidCode("  12"))
    }

    @Test
    fun isPassMatchedTest() {
        assertTrue(Validations.isPassMatched("password123", "password123"))
        assertFalse(Validations.isPassMatched("password123", "password12"))
        assertFalse(Validations.isPassMatched("password123", ""))
    }

    @Test
    fun isNotEmptyTest() {
        assertTrue(Validations.isNotEmpty(" NotEmpty"))
        assertFalse(Validations.isNotEmpty(""))
        assertFalse(Validations.isNotEmpty(null))
    }


}