package com.codigo.aplios.xbase.core.codepage;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Klasa implementuje
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public class Rot5Charset extends Charset {

	private static final String BASE_CHARSET_NAME = "UTF-8";

	private final Charset baseCharset;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>Rot5Charset</code>
	 *
	 * @param canonical
	 *        Potoczna nazwa standardu kodowania/dekodowania znaków
	 * @param aliases
	 */
	public Rot5Charset(final String canonical, final String[] aliases) {

		super(canonical, aliases);
		this.baseCharset = Charset.forName(Rot5Charset.BASE_CHARSET_NAME);
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

		return new Rot5CharsetDecoder(
			this, this.baseCharset.newDecoder());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.Charset#newEncoder()
	 */
	@Override
	public CharsetEncoder newEncoder() {

		return new Rot5CharsetEncoder(
			this, this.baseCharset.newEncoder());
	}

	private void rot5(final CharBuffer cb) {

		for (int position = cb.position(); position < cb.limit(); position++) {
			final char character = cb.get(position);

			if ((character >= 48) && (character <= 57))
				if (character <= 52)
					cb.put(position, (char) (character + 5));
				else
					cb.put(position, (char) (character - 5));
			else
				cb.put(position, character);
		}
	}

	private final class Rot5CharsetDecoder extends CharsetDecoder {

		private final CharsetDecoder baseDecoder;

		public Rot5CharsetDecoder(final Charset cs, final CharsetDecoder baseDecoder) {

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
			rot5(out);

			return result;
		}

	}

	/**
	 * @author andrzej.radziszewski
	 *
	 */
	private final class Rot5CharsetEncoder extends CharsetEncoder {

		private final CharsetEncoder baseEncoder;

		public Rot5CharsetEncoder(final Charset cs, final CharsetEncoder baseEncoder) {

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
			rot5(buffer);
			this.baseEncoder.reset();
			final CoderResult result = this.baseEncoder.encode(buffer, out, true);
			in.position(in.position() - buffer.remaining());

			return result;
		}

	}
}
