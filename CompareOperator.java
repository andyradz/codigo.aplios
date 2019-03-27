package com.codigo.aplios.group.timeline.common.helper;

import java.util.Comparator;

public enum CompareOperator implements IComparable {

	/**
	 * Operator porównania, sprawdzający zależność ===
	 */
	EQUALS {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.EQUALS.get();
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność ===
	 */
	NOTEQUALS {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			return !EQUALS.compare(leftOperand, rightOperand);
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność >
	 */
	GREATERTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.GREATER.get();
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność not >
	 */
	NOTGREATERTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			return !GREATERTHEN.compare(leftOperand, rightOperand);
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność >=
	 */
	EQUALSGREATERTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return (comparable.compareTo(rightOperand) == CompareResult.EQUALS.get())
					|| (comparable.compareTo(rightOperand) == CompareResult.GREATER.get());
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność not >=
	 */
	NOTEQUALSGREATERTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			return !EQUALSGREATERTHEN.compare(leftOperand, rightOperand);
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność <
	 */
	LESSTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.LESSER.get();
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność not <
	 */
	NOTLESSTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			return !LESSTHEN.compare(leftOperand, rightOperand);
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność <=
	 */
	EQUALSLESSTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return (comparable.compareTo(rightOperand) == CompareResult.EQUALS.get())
					|| (comparable.compareTo(rightOperand) == CompareResult.LESSER.get());
		}

	},

	/**
	 * Operator porównania, sprawdzający zależność not <=
	 */
	NOTEQUALSLESSTHEN {

		@Override
		public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

			return !EQUALSLESSTHEN.compare(leftOperand, rightOperand);
		}
	};

	/**
	 * @param comparator
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> boolean compare(final Comparator<T> comparator, final T first,
			final T second) {

		final int result = comparator.compare(first, second);

		switch (result) {
		case -1:
			return result == CompareResult.LESSER.get();

		case 1:
			return result == CompareResult.GREATER.get();

		case 0:
			return result == CompareResult.EQUALS.get();

		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 *
	 * @param leftOperand
	 * @param rightOperand
	 */
	private static void checkObjectClass(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

		if (leftOperand.getClass() != rightOperand.getClass())
			throw new UnsupportedOperationException("leftOperand.getClass() != rightOperand.getClass()");
	}

}
