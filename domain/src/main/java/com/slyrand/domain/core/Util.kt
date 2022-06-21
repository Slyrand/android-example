package com.slyrand.domain.core

import arrow.core.left
import arrow.core.right
import com.slyrand.domain.core.model.DataError
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

private fun md5(stringToHash: String): DataResult<String> {
    try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(stringToHash.toByteArray())
        val messageDigest = digest.digest()

        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) {
                h = "0$h"
            }
            hexString.append(h)
        }
        return hexString.toString().right()

    } catch (e: NoSuchAlgorithmException) {
        return DataError.GenericException.left()
    }
}

fun generateHash(timestamp: Long, privateKey: String, publicKey: String): DataResult<String> =
    md5(timestamp.toString() + privateKey + publicKey)