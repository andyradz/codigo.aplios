package com.codigo.aplios.sdk.core.compare;

import org.checkerframework.checker.nullness.qual.NonNull;

public enum CompareOperator implements com.codigo.aplios.sdk.core.compare.IComparable {

	EQUALS {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.EQUALS.result();
		}

	},
	NOTEQUALS {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			return !EQUALS.compare(leftOperand, rightOperand);
		}

	},
	GREATERTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.GREATER.result();
		}

	},
	NOTGREATERTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			return !GREATERTHEN.compare(leftOperand, rightOperand);
		}

	},
	EQUALSGREATERTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return (comparable.compareTo(rightOperand) == CompareResult.EQUALS.result())
					|| (comparable.compareTo(rightOperand) == CompareResult.GREATER.result());
		}

	},
	NOTEQUALSGREATERTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			return !EQUALSGREATERTHEN.compare(leftOperand, rightOperand);
		}

	},
	LESSTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return comparable.compareTo(rightOperand) == CompareResult.LESSER.result();
		}

	},
	NOTLESSTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			return !LESSTHEN.compare(leftOperand, rightOperand);
		}

	},
	EQUALSLESSTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			CompareOperator.checkObjectClass(leftOperand, rightOperand);

			final Comparable<Object> comparable = Comparable.class.cast(leftOperand);

			return (comparable.compareTo(rightOperand) == CompareResult.EQUALS.result())
					|| (comparable.compareTo(rightOperand) == CompareResult.LESSER.result());
		}

	},
	NOTEQUALSLESSTHEN {

		@Override
		public boolean compare(@NonNull final Comparable<?> leftOperand, @NonNull final Comparable<?> rightOperand) {

			return !EQUALSLESSTHEN.compare(leftOperand, rightOperand);
		}
	};

	/**
	 *
	 * @param leftOperand
	 * @param rightOperand
	 */
	private static void checkObjectClass(@NonNull final Comparable<?> leftOperand,
			@NonNull final Comparable<?> rightOperand) {

		if (leftOperand.getClass() != rightOperand.getClass())
			throw new UnsupportedOperationException(
				"leftOperand.getClass() != rightOperand.getClass()");
	}

}
