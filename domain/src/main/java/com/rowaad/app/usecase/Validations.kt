package com.rowaad.app.usecase

import java.math.BigInteger

object Validations {

    fun isValidPhone(phone: String): ValidationMsg {
        val phoneRegex = "^[+]?[\\d\\-()\\s]*$".toRegex()

        return when {
            phone.isBlank() -> ValidationMsg.EMPTY
            phone.length < 8 -> ValidationMsg.MIN
            !phone.matches(phoneRegex) -> ValidationMsg.INVALID_FORMAT
            else -> ValidationMsg.NO_ERROR
        }
    }
    fun isValidName(name: String): ValidationMsg =
        if (name.length < 2) ValidationMsg.MIN else if (name.length > 15) ValidationMsg.MAX else ValidationMsg.NO_ERROR

    fun isValidNameNoLimits(name: String): ValidationMsg =
        if (name.length < 2) ValidationMsg.MIN else ValidationMsg.NO_ERROR

    fun isValidGeneral(name: String?=null): ValidationMsg =
        if (name.isNullOrBlank()) ValidationMsg.MIN else ValidationMsg.NO_ERROR

    fun isValidPostal(postal: String?=null): ValidationMsg =
        if (postal.isNullOrBlank() || (postal.length < 2)) ValidationMsg.MIN else ValidationMsg.NO_ERROR

    fun isValidAddress(address: String): ValidationMsg =
        ValidationMsg.NO_ERROR.takeUnless { address.length < 3 } ?: ValidationMsg.MIN

    /*fun isValidIban(iban: String): Boolean =
        true.takeUnless { iban.isBlank() || iban.length < 24 } ?: false*/

    fun isValidIban(ibanOriginal: String): Boolean {
        val countryCodeLengths = mapOf(
            "AL" to 28, "AD" to 24, "AT" to 20, "AZ" to 28, "BH" to 22, "BE" to 16,
            "BA" to 20, "BR" to 29, "BG" to 22, "CR" to 21, "HR" to 21, "CY" to 28,
            "CZ" to 24, "DK" to 18, "DO" to 28, "EE" to 20, "FO" to 18, "FI" to 18,
            "FR" to 27, "GE" to 22, "DE" to 22, "GI" to 23, "GR" to 27, "GL" to 18,
            "GT" to 28, "HU" to 28, "IS" to 26, "IE" to 22, "IL" to 23, "IT" to 27,
            "KZ" to 20, "KW" to 30, "LV" to 21, "LB" to 28, "LI" to 21, "LT" to 20,
            "LU" to 20, "MK" to 19, "MT" to 31, "MR" to 27, "MU" to 30, "MC" to 27,
            "MD" to 24, "ME" to 22, "NL" to 18, "NO" to 15, "PK" to 24, "PS" to 29,
            "PL" to 28, "PT" to 25, "QA" to 29, "RO" to 24, "SM" to 27, "SA" to 24,
            "RS" to 22, "SK" to 24, "SI" to 19, "ES" to 24, "SE" to 24, "CH" to 21,
            "TN" to 24, "TR" to 26, "AE" to 23, "GB" to 22, "VG" to 24
        )

        // Remove any whitespace
        val iban = ibanOriginal.replace("\\s".toRegex(), "")
        if (iban.length < 4) return false

        val countryCode = iban.substring(0, 2)
        val length = countryCodeLengths[countryCode] ?: return false

        if (iban.length != length) return false

        val reformattedIban = iban.substring(4) + iban.substring(0, 4)
        val numericIban = StringBuilder()

        for (char in reformattedIban) {
            numericIban.append(
                if (char.isDigit()) char.toString() else (char - 'A' + 10).toString()
            )
        }

        return BigInteger(numericIban.toString()).mod(BigInteger.valueOf(97)) == BigInteger.ONE
    }

    fun isValidTitle(title: String): Boolean = true.takeUnless { title.isBlank() } ?: false

    fun isValidBody(body: String): ValidationMsg =
        ValidationMsg.NO_ERROR.takeUnless { body.length < 3 } ?: ValidationMsg.MIN

    fun isValidMail(email: String): Boolean =
        true.takeIf { email.matches("[a-zA-Z0-9._\\-]+@[a-zA-Z0-9._\\-]+\\.+[a-z]+".toRegex()) }
            ?: false

    fun isValidId(id: String): Boolean = true.takeIf { id.isNotEmpty() } ?: false
    fun isValidBirthDate(birthDate: String? = null): Boolean =
        true.takeUnless { birthDate.isNullOrBlank() } ?: false

    fun isValidNational(nationalId: Int? = null): Boolean =
        true.takeUnless { nationalId == null || nationalId == 0 } ?: false

    fun isValidLocation(location: String? = null): Boolean =
        true.takeUnless { location.isNullOrBlank() } ?: false

    fun isValidPass(pass: String): Boolean =
        true.takeUnless { pass.isBlank() || pass.length < 4 /*|| pass.length > 16*/ } ?: false

    fun isValidConfirmPass(pass: String,confirmPass: String): Boolean =
        (true.takeUnless { pass.isBlank() || pass.length < 4 /*|| pass.length > 16*/ } ?: false) && confirmPass==pass

    /* fun isValidCode(code: String): Boolean =
         true.takeUnless { code.isBlank() || code.length < 4 } ?: false
 */
    fun isValidCode(code: String): Boolean {
        val pattern = Regex("^\\d{4}$")
        return pattern.matches(code)
    }

    fun isPassMatched(pass: String, confirmPass: String): Boolean =
        true.takeUnless { confirmPass.isBlank() || pass != confirmPass } ?: false

    fun isNotEmpty(string: String?):Boolean = true.takeUnless { string.isNullOrBlank() }?:false
    fun isValidCR(cr: String): ValidationMsg {
        return ValidationMsg.NO_ERROR.takeUnless { cr.length != 10 } ?: ValidationMsg.INVALID
    }
}

enum class ValidationMsg {
    MIN, MAX, EMPTY, INVALID_FORMAT, NO_ERROR,REDUNDANT,INVALID
}

object ValidationsParms {
    var min = 3
    var max = 15
}