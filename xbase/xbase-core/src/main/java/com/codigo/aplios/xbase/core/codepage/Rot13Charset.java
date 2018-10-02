package com.codigo.aplios.xbase.core.codepage;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Klasa implementuje mechanizm kodowania/dekodowania znaków w standardzie ROT13. Przykładowe
 * użycie:
 *
 * ROT13 – prosty szyfr przesuwający, którego działanie polega na zamianie każdego znaku alfabetu
 * łacińskiego na znak występujący 13 pozycji po nim, przy czym wielkość liter nie ma przy
 * przekształcaniu znaczenia. ROT13 jest przykładem szyfru Cezara, opracowanego w Starożytnym
 * Rzymie.
 *
 * <code>Charset.forName("ROT13")</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public final class Rot13Charset extends Charset {

	/**
	 * Domyślna nazwa standardu kodowania/dekodowania znaków
	 */
	private static final String BASE_CHARSET_NAME = "UTF-8";

	/**
	 * Atrybut reprezentuje podstawowy rodzaj kodowania/dekodowania znaków
	 */
	private final Charset baseCharset;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Rot13Charset</code>
	 *
	 * @param canonical
	 *        Potoczna nazwa standardu kodowania/dekodowania znaków
	 * @param aliases
	 *        Aliasy nazw dla standaru kodowania/dekodowania znaków
	 */
	public Rot13Charset(final String canonical, final String[] aliases) {
		super(canonical, aliases);
		this.baseCharset = Charset.forName(Rot13Charset.BASE_CHARSET_NAME);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.Charset#contains(java.nio.charset.Charset)
	 */
	@Override
	public boolean contains(final Charset cs) {
		return cs.equals(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.Charset#newDecoder()
	 */
	@Override
	public CharsetDecoder newDecoder() {
		return new Rot13CharsetDecoder(
			this, this.baseCharset.newDecoder());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.Charset#newEncoder()
	 */
	@Override
	public CharsetEncoder newEncoder() {
		return new Rot13CharsetEncoder(
			this, this.baseCharset.newEncoder());
	}

	/**
	 * Metoda implementuje mechanizm kodowanie/dekodowania znaków dla standardu ROT13
	 *
	 * @param cb
	 *        Bufor wejściowy prztetwarznych znaków
	 */
	private void rot13(final CharBuffer cb) {

		for (int pos = cb.position(); pos < cb.limit(); pos++) {
			char basechar = cb.get(pos);
			char cezarchar = '\u0000';

			// Is it lower case alpha?
			if ((basechar >= 'a') && (basechar <= 'z'))
				cezarchar = 'a';

			// Is it upper case alpha?
			if ((basechar >= 'A') && (basechar <= 'Z'))
				cezarchar = 'A';

			// If either, roll it by 13
			if (cezarchar != '\u0000') {
				basechar = (char) ((((basechar - cezarchar) + 13) % 26) + cezarchar);
				cb.put(pos, basechar);
			}
		}
	}

	/**
	 * Klasa implementuje mechanizm dekodowania znaków standardu ROT13
	 *
	 * @author andrzej.radziszewski
	 * @version 1.0.0.0
	 * @since 2017
	 * @category charset
	 */
	private final class Rot13CharsetDecoder extends CharsetDecoder {

		/**
		 * Atrybut reprezentuje podstawowy dekoder standardu R0T13
		 */
		private final CharsetDecoder baseDecoder;

		/**
		 * Podstawowy konstruktor obiektu klasy <code>Rot13CharsetDecoder</code>
		 *
		 * @param cs
		 *        Rodzaj kodowania/dekodowania znaków
		 * @param baseDecoder
		 *        Dekoder znaków standardu ROT13
		 */
		public Rot13CharsetDecoder(final Charset cs, final CharsetDecoder baseDecoder) {
			super(cs, baseDecoder.averageCharsPerByte(), baseDecoder.maxCharsPerByte());
			this.baseDecoder = baseDecoder;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.nio.charset.CharsetDecoder#decodeLoop(java.nio.ByteBuffer, java.nio.CharBuffer)
		 */
		@Override
		protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {
			this.baseDecoder.reset();
			final CoderResult result = this.baseDecoder.decode(in, out, true);
			rot13(out);

			return result;
		}

	}

	/**
	 * Klasa implementuje mechanizm kodowania standardu ROT13
	 *
	 * @author andrzej.radziszewski
	 * @version 1.0.0.0
	 * @since 2017
	 * @category charset
	 */
	private final class Rot13CharsetEncoder extends CharsetEncoder {

		/**
		 * Atrybut reprezentuje podstawowy koder standardu R0T13
		 */
		private final CharsetEncoder baseEncoder;

		/**
		 * Podstawowy konstruktor obiektu klasy <code>Rot13CharsetEncoder</code>
		 *
		 * @param cs
		 *        Rodzaj kodowania/dekodowania znaków
		 * @param baseEncoder
		 *        Podstawowy koder standardu ROT13
		 */
		public Rot13CharsetEncoder(final Charset cs, final CharsetEncoder baseEncoder) {
			super(cs, baseEncoder.averageBytesPerChar(), baseEncoder.maxBytesPerChar());
			this.baseEncoder = baseEncoder;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.nio.charset.CharsetEncoder#encodeLoop(java.nio.CharBuffer, java.nio.ByteBuffer)
		 */
		@Override
		protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

			final CharBuffer buffer = CharBuffer.allocate(in.remaining());
			while (in.hasRemaining())
				buffer.put(in.get());

			buffer.rewind();
			rot13(buffer);
			this.baseEncoder.reset();
			final CoderResult result = this.baseEncoder.encode(buffer, out, true);
			in.position(in.position() - buffer.remaining());
			return result;
		}

	}
}
