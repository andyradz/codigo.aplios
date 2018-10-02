/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.checksum.core.crc;

/**
 *
 * @author andrzej.radziszewski
 */
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryptionStandard {

	public static void main(final String[] args) throws Exception {
		final byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
		final byte[] plainText = "Hello world!".getBytes(StandardCharsets.UTF_8);
		final AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(
			encryptionKey);
		final byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
		final byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);

		System.out.println(
				new String(
					plainText));
		System.out.println(
				new String(
					cipherText));
		System.out.println(
				new String(
					decryptedCipherText));
	}

	private final byte[] key;

	private static final String ALGORITHM = "AES";

	public AdvancedEncryptionStandard(final byte[] key) {
		this.key = key;
	}

	/**
	 * Encrypts the given plain text
	 *
	 * @param plainText
	 *        The plain text to encrypt
	 * @return
	 */
	public byte[] encrypt(final byte[] plainText) throws Exception {
		final SecretKeySpec secretKey = new SecretKeySpec(
			this.key, AdvancedEncryptionStandard.ALGORITHM);
		final Cipher cipher = Cipher.getInstance(AdvancedEncryptionStandard.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		return cipher.doFinal(plainText);
	}

	/**
	 * Decrypts the given byte array
	 *
	 * @param cipherText
	 *        The data to decrypt
	 * @return
	 */
	public byte[] decrypt(final byte[] cipherText) throws Exception {
		final SecretKeySpec secretKey = new SecretKeySpec(
			this.key, AdvancedEncryptionStandard.ALGORITHM);
		final Cipher cipher = Cipher.getInstance(AdvancedEncryptionStandard.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		return cipher.doFinal(cipherText);
	}

}
