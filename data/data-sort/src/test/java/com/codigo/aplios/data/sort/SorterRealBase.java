package com.codigo.aplios.data.sort;

import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;

public abstract class SorterRealBase extends SorterBase {

	protected static final double MIN_ELEMENT;

	protected static final double MAX_ELEMENT;

	protected Double[] data;

	protected SorterFactory<Double> sortable;

	static {
		MIN_ELEMENT = -912.2D;
		MAX_ELEMENT = 1_000_000.03D;
	}

	@Before
	public void fillArray() {

		this.data = new Double[] { 1_000_000.03D, 1_000_000.02D, 3D, 231.1D, 332D, 5.1D, .4D, .1D, .022D, 10D, -2D,
				-912D, -0D, +0D, 11D, 23D, 23D, -12D, -1D, -6D, -5D, 221D, 10D, 12D, 209D, 20D, 0D, 3D, 2D, 23D, 12D,
				23D, -912.1, 34D, 45D, 3D, 42D, 1D, 7D, 2D, 12D, 9D, 4D, 8.2D, 1D, 22D, 50D, 34D, 1D, 2D, 3D, 4D, 5D,
				32D, -99D, 12D, -9D, -0D, 12D, 82D, 12D, 22.3D, 22.1D, 22D, 55D, 0D, 9D, 3D, 1D, 2D, 13D, 3D, 1D, 32D,
				89D, 1D, 12D, 1D, 21D, 3D, 99D, 12D, 12D, 11D, 222D, 0D, 88D, 997D, 1_000D, 43D, 1D, 0D, 8D, 2D, 1D, 2D,
				1D, 34D, 23D, 12D, 1_000D, 100D, -912.2, 99D, 98D, 11D, 1D, 1D, 111D, 232D, 12D, 23_123D, 12_121D,
				999_999D, 4_444D, 21_111D, 21_212D, 212_121D, 8723D, 121D, -0D, -98D, 1_000_000.01D, 998D };
	}

	@After
	public void printEmptyLine() {

		if (SorterBase.SHOW_LOG) {
			Stream.of(this.data)
					.forEach(e -> System.out.printf("|%4f", e));
			System.out.println();
		}
	}

}
