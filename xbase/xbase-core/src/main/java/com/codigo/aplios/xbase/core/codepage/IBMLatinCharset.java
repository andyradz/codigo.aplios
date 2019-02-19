package com.codigo.aplios.xbase.core.codepage;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public class IBMLatinCharset extends Charset {

	private static char[][] CHARSET = new char[][] {
			{
					'\u0104',
					164 }// Ą
			,
			{
					'\u0105',
					165 }// ą
			,
			{
					'\u0106',
					143 }// Ć
			,
			{
					'\u0107',
					134 }// ć
			,
			{
					'\u0118',
					168 }// Ę
			,
			{
					'\u0119',
					169 }// ę
			,
			{
					'\u0141',
					157 }// Ł
			,
			{
					'\u0142',
					136 }// ł
			,
			{
					'\u0143',
					227 }// Ń
			,
			{
					'\u0144',
					228 }// ń
			,
			{
					'\u00D3',
					224 }// Ó
			,
			{
					'\u00F3',
					162 }// ó
			,
			{
					'\u015A',
					151 }// Ś
			,
			{
					'\u015B',
					152 }// ś
			,
			{
					'\u0179',
					141 }// Ź
			,
			{
					'\u017A',
					171 }// ź
			,
			{
					'\u017B',
					189 }// Ż
			,
			{
					'\u017C',
					190 }// ż
	};

	/**
	 * Podstawowy konstruktor obiektu klasy <code>IBMLatinCharset</code>
	 *
	 * @param canonicalName
	 * @param aliases
	 */
	public IBMLatinCharset(final String canonicalName, final String[] aliases) {

		super(canonicalName, aliases);
	}

	@Override
	public boolean contains(final Charset cs) {

		return cs.equals(this);
	}

	@Override
	public CharsetDecoder newDecoder() {

		return new PrivCharsetDecoder(this, 1, 1);
	}

	@Override
	public CharsetEncoder newEncoder() {

		return new PrivCharsetEncoder(this, 1, 1);
	}

	public class PrivCharsetEncoder extends CharsetEncoder {

		public PrivCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar,
				final byte[] replacement) {

			super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
		}

		public PrivCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar) {

			super(cs, averageBytesPerChar, maxBytesPerChar);
		}

		@Override
		protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

			while (in.hasRemaining()) {
				char inputChar = in.get();

				for (final char[] convertChar : IBMLatinCharset.CHARSET)
					if (inputChar == convertChar[0])
						inputChar = convertChar[1];

				out.put((byte) (inputChar & 0xFF));
			}
			return CoderResult.UNDERFLOW;
		}

	}

	/**
	 * @author andrzej.radziszewski
	 *
	 */
	public final class PrivCharsetDecoder extends CharsetDecoder {

		/**
		 * Podstawowy konstruktor obiektu klasy <code>PrivCharsetDecoder</code>
		 *
		 * @param cs
		 * @param averageCharsPerByte
		 * @param maxCharsPerByte
		 * @category constructor
		 */
		public PrivCharsetDecoder(final Charset cs, final float averageCharsPerByte, final float maxCharsPerByte) {

			super(cs, averageCharsPerByte, maxCharsPerByte);
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.nio.charset.CharsetDecoder#decodeLoop(java.nio.ByteBuffer, java.nio.CharBuffer)
		 */
		@Override
		protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

			while (in.hasRemaining()) {
				char inputChar = (char) (in.get() & 0x00FF);

				for (final char[] convertChar : IBMLatinCharset.CHARSET)
					if (inputChar == convertChar[1])
						inputChar = convertChar[0];
				out.put(inputChar);
			}

			return CoderResult.UNDERFLOW;
		}

	}
}
