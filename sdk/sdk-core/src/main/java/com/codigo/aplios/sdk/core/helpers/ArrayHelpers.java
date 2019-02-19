package com.codigo.aplios.sdk.core.helpers;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

class MyInt {
	private final Integer val;

	public MyInt(final Integer val) {

		this.val = val;
	}

	public Integer value() {

		return this.val;
	}
}

public class ArrayHelpers {
	enum Stany {
		A("1"), B("1"), C("1"), D("1"), E("1"), F("1"), G("1"), H("1");

		private final String symbol;

		Stany(final String symbol) {

			this.symbol = symbol;
		}
	}

	static class CustomRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 6703872492730589499L;

		@Override
		public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
				final boolean hasFocus, final int row, final int column) {

			final Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);

			if (row == 0)
				cellComponent.setBackground(Color.YELLOW);
			else if (row == 1)
				cellComponent.setBackground(Color.GRAY);
			else
				cellComponent.setBackground(Color.CYAN);
			return cellComponent;
		}
	}

	public static class FiveCharacterEditor extends DefaultCellEditor {
		FiveCharacterEditor() {

			super(new JTextField());
		}

		@Override
		public boolean stopCellEditing() {

			try {
				final String editingValue = (String) getCellEditorValue();

				if (editingValue.length() != 5) {
					final JTextField textField = (JTextField) getComponent();
					textField.setBorder(new LineBorder(Color.red));
					textField.selectAll();
					textField.requestFocusInWindow();

					JOptionPane.showMessageDialog(null, "Please enter string with 5 letters.", "Alert!",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (final ClassCastException exception) {
				return false;
			}

			return super.stopCellEditing();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int column) {

			final Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
			((JComponent) c).setBorder(new LineBorder(Color.black));

			return c;
		}

	}

	public static void createGui() {

		final JFrame frmMain = new JFrame("Konfigurator");
		final String className = ArrayHelpers.getLookAndFeelClassName("Windows");
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final JTable frmGrid = new JTable(20, 10);

		// Ustawnienie właściwości grida
		// ..............................................................................................................
		// frmGrid.setRowMargin(1);
		frmGrid.getColumnModel().setColumnMargin(1);
		frmGrid.setIntercellSpacing(new Dimension(1, 1));
		final TableCellEditor fce = new FiveCharacterEditor();
		frmGrid.setDefaultEditor(Object.class, fce);
		frmGrid.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
		frmGrid.getModel()
				.addTableModelListener(e -> EventQueue.invokeLater(() -> frmGrid.setRowHeight(e.getFirstRow(), 30)));

		// frmGrid.setRowHeight(10, 40);
		final JButton btnClear = new JButton("Zakończ");
		final JButton btnRestore = new JButton("Przywróć");

		frmMain.setLayout(new FlowLayout());
		frmMain.add(new JButton("Koniec"));
		frmMain.add(new JButton("Początek"));
		frmMain.add(new JButton("Rozpocznij"));
		frmMain.add(btnClear);
		frmMain.add(btnRestore);
		frmMain.add(new JScrollPane(frmGrid));
		btnClear.addActionListener(e -> {

			EveryNth.makeWindowScreenshot(frmMain);
			try {
				EveryNth.makeWindowsScreenshot(frmMain);
			} catch (AWTException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// display/center the jdialog when the button is pressed
			// JDialog d = new JDialog(frmMain, "Hello", true);
			// d.setLocationRelativeTo(frmMain);
			// d.setVisible(true);
			final DefaultTableModel model = (DefaultTableModel) frmGrid.getModel();
			model.setNumRows(1);
			model.fireTableDataChanged();
		});

		btnRestore.addActionListener(e -> {

			// display/center the jdialog when the button is pressed
			// JDialog d = new JDialog(frmMain, "Hello", true);
			// d.setLocationRelativeTo(frmMain);
			// d.setVisible(true);
			final DefaultTableModel model = (DefaultTableModel) frmGrid.getModel();
			// model.setRowCount(100);
			model.setNumRows(100);
			model.fireTableDataChanged();
		});
		frmMain.add(frmGrid);

		frmMain.setSize(800, 600);
		frmMain.setLocationRelativeTo(null);
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmMain.setVisible(true);

	}

	public static String getLookAndFeelClassName(final String nameSnippet) {

		final LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
		for (final LookAndFeelInfo info : plafs)
			if (info.getName().contains(nameSnippet))
				return info.getClassName();
		return null;
	}

	public static void main(final String[] args)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {

		Arrays.stream(args).forEach(System.out::println);

		SwingUtilities.invokeLater(ArrayHelpers::createGui);

		final Integer[] znaki = { 1, 2, 3, 4, 5, 6 };

		Arrays.stream(znaki)
				// .map(String::valueOf)
				.map(MyInt::new).toArray(MyInt[]::new);

		Arrays.stream(znaki).map(String::valueOf).toArray(String[]::new);

		final Integer[][] dane = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 11, 12, 13, 14, 15, 16, 17, 18, 19 } };

		Arrays.stream(dane).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
		// Stream<Integer> stringStream = temp.flatMap(x -> Arrays.stream(x));

		// stringStream.collect(Collectors.toList())
		// .forEach(System.out::println);

		new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
				add(4);
			}
		};

		final Integer[] arr = Stream.of(1, 2, 23, 12, 211, 232, 3).toArray(Integer[]::new);

		final Integer[] arr1 = Stream.of(1000, 2000, 3000, 4000).toArray(Integer[]::new);
		System.out.println("");
		ArrayHelpers.join(arr, arr1).forEachRemaining(System.out::println);

		Number val = ArrayHelpers.min(arr);
		System.out.println("Wartość minimalna:" + val.doubleValue());
		val = ArrayHelpers.max(arr);
		System.out.println("Wartość maxsymalna:" + val.doubleValue());
		val = ArrayHelpers.count(arr);
		System.out.println("Ilość elementów:" + val.longValue());

		final Integer[] numbers = { 0, 1, 2, 1, 3, 4, 4, 4, 4, 0, 23, 23, 11, 12, 22, -1, 0, 0, 0, 0, 33, 100, 293,
				-12 };

		System.out.println("Sortowanie elemntów tablicy");
		ArrayHelpers.sort(numbers, (x, y) -> x.compareTo(y)).forEachRemaining(System.out::println);

		System.out.println("Zduplikowane wartości");
		ArrayHelpers.duplicate(numbers).iterator().forEachRemaining(System.out::println);

		val = ArrayHelpers.avg(numbers);
		System.out.println("Wartość średnia:" + StrictMath.ceil(val.doubleValue()));

		System.out.println("Podzbiór elemntów tablicy");
		ArrayHelpers.subarr(numbers, 0L, 5L).forEachRemaining(System.out::println);

	}

	/**
	 * Metoda realizuje wyznaczenie maksymalnej wartości elemntu z elementów tablicy
	 * przekazanej jako parametr <code>array</code>.
	 *
	 * @param array
	 * @return
	 */
	private static final Number max(final Number[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).map(Number::doubleValue).max(Double::compare).get();
	}

	/**
	 * Metoda realizuje wyznaczenie minimalnej wartości elemntu z elementów tablicy
	 * przekazanej jako parametr <code>array</code>.
	 *
	 * @param array
	 * @return
	 */
	private static final Number min(final Number[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).map(Number::doubleValue).min(Double::compare).get();
	}

	/**
	 * Metoda realizuje zliczenie ilości elementów tablicy przekazanej jako parametr
	 * <code>array</code>.
	 *
	 * @param array
	 * @return
	 */
	private static final <T> Number count(final T[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).count();
	}

	private static final <T> Iterator<T> subarr(final T[] array, final long skip, final long limit) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).skip(skip).limit(limit).iterator();
	}

	/**
	 * Metoda realizuje wyznaczenie zduplikowanych wartości z elementów tablicy
	 * przekazanych jako parametr <code>array</code>.
	 *
	 * @param array
	 * @return
	 */
	private static final <T> Stream<T> duplicate(final T[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).filter(n -> Arrays.stream(array).filter(x -> x == n).count() > 1).distinct();
	}

	/**
	 * Metoda realizuje wyznaczenie średniej wartości z elementów przekaznaych jako
	 * tablica elementów numerycznych.
	 *
	 * @param array
	 * @return
	 */
	private static final <T extends Number> double avg(final T[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array).map(Number::doubleValue).mapToDouble(Double::doubleValue).average().getAsDouble();
	}

	private static final <T> Iterator<T> sort(final T[] array, final Comparator<T> comparatotr) {

		return Arrays.stream(array).sorted(comparatotr).iterator();
	}

	private static final <T> Iterator<T> join(final T[] array1, final T[] array2) {

		if (Objects.isNull(array1) || Objects.isNull(array1))
			throw new NullPointerException();

		return Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).iterator();
	}

	private static final Function<Field, Long> functor = (item) -> {
		Enum<?> val = null;
		try {
			val = (Enum<?>) item.get(val);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return (long) val.ordinal();
	};

	public static long getOrdinalSum(final Class<? extends Enum<?>> instance)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {

		return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(ArrayHelpers.functor)
				.collect(Collectors.summingLong(item -> item));
	}

	public static double getOrdinalAvg(final Class<? extends Enum<?>> instance) {

		return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(ArrayHelpers.functor)
				.collect(Collectors.averagingLong(item -> item));
	}

	public static long getOrdinalMul(final Class<? extends Enum<?>> instance) {

		return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(ArrayHelpers.functor)
				.reduce((sum, p) -> sum = (sum == 0L ? 1L : p.longValue() * sum)).get();
	}

	public static long getLastOrdinal(final Class<? extends Enum<?>> instance) {

		return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(ArrayHelpers.functor)
				.reduce((a, b) -> b).get();
	}

	public static String getLastElement(final Class<? extends Enum<?>> instance) {

		return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(item -> item.getName())
				.reduce((a, b) -> b).get();
	}
}

class EveryNth<C> {

	private final int nth;
	private final List<List<C>> lists = new ArrayList<>();
	private int next = 0;

	private EveryNth(final int nth) {

		this.nth = nth;
		IntStream.range(0, nth).forEach(i -> this.lists.add(new ArrayList<>()));
	}

	private void accept(final C item) {

		this.lists.get(this.next++ % this.nth).add(item);
	}

	private EveryNth<C> combine(final EveryNth<C> other) {

		other.lists.forEach(l -> this.lists.get(this.next++ % this.nth).addAll(l));
		this.next += other.next;
		return this;
	}

	private List<C> getResult() {

		return this.lists.get(0);
	}

	public static <T> Collector<Integer, ?, List<Integer>> collector(final int nth) {

		return Collector.of(() -> new EveryNth(nth), EveryNth::accept, EveryNth::combine, EveryNth::getResult);
	}

	/**
	 * Procedura wykonuje zrzut zawartości ekranu i zapisuje go do pliku;
	 *
	 * @param winForm Okno ekranu systemu operacyjnego.
	 */
	public static final void makeWindowScreenshot(final JFrame winForm) {

		// obszar roboczy okna ekranu
		// ..............................................................................................................
		final Rectangle winBound = winForm.getBounds();

		// buffor operacji graficznych
		// ..............................................................................................................
		final BufferedImage bufferedImage = new BufferedImage(winBound.width, winBound.height,
				BufferedImage.TYPE_INT_ARGB);

		winForm.paint(bufferedImage.getGraphics());

		try {
			// utworzenie pliku
			final File screenFile = File.createTempFile(winForm.getName(), ".png");

			// Use the ImageIO API to write the bufferedImage to a temporary file
			ImageIO.write(bufferedImage, "png", screenFile);
			screenFile.deleteOnExit();
		} catch (final IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static final void makeWindowsScreenshot(final JFrame winForm) throws AWTException, IOException {

		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice[] screens = ge.getScreenDevices();
		final Rectangle allScreenBounds = new Rectangle();
		for (final GraphicsDevice screen : screens) {
			final Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
			allScreenBounds.width += screenBounds.width;
			allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
			allScreenBounds.x = Math.min(allScreenBounds.x, screenBounds.x);
			allScreenBounds.y = Math.min(allScreenBounds.y, screenBounds.y);
		}
		final Robot robot = new Robot();
		final BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
		final File file = new File("d:\\scr.png");
		if (!file.exists())
			file.createNewFile();
		final FileOutputStream fos = new FileOutputStream(file);
		ImageIO.write(bufferedImage, "png", fos);
	}
}