
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.codigo.aplios.gui.DatePicker;
import com.codigo.aplios.gui.core.gridview.JSortTable;

public class PropertyGrid extends JTable {

	private static final long serialVersionUID = 2931801730588225089L;

	public PropertyGrid(final JFrame parent) {

		this.labelRenderer = new LabelCellRenderer();
		this.labelEditor = new LabelCellEditor();

		this.fields = new LinkedHashMap<>();
		this.curRow = 0;

		this.eventListener = null;

		this.parent = parent;
		setModel(new PGModel());
		this.setUI(new PGUI());

		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void clear() {

		removeAll();
		clearSelection();

		this.fields.clear();
		this.curRow = 0;
	}

	public void setEventListener(final EventListener listener) {

		this.eventListener = listener;
	}

	public void addCategory(final String name, final String caption) {

		if (this.fields.containsKey(name))
			return;

		final Field field = new Field();
		field.name = name;

		field.row = this.curRow++;
		field.type = "category";
		field.choices = null;
		field.value = null;

		field.label = new JLabel(
			caption);
		field.renderer = this.labelRenderer;
		field.editor = this.labelEditor;

		this.fields.put(name, field);

		field.label.setFont(field.label.getFont()
				.deriveFont(Font.BOLD));
		field.label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void addField(final String name, final String caption, final String type, final java.util.List choices,
			final Object val) {

		if (this.fields.containsKey(name)) {
			if (!val.equals(this.fields.get(name).value))
				this.fields.get(name).value = null;

			return;
		}

		final Field field = new Field();
		field.name = name;

		field.row = this.curRow++;
		field.type = type;
		field.choices = choices;
		field.value = val;

		field.label = new JLabel(
			caption);
		field.renderer = null;

		switch (type) {
		case "text":
		case "int":
			field.editor = new TextCellEditor(
				field);
			break;

		case "float":
			field.renderer = new FloatCellRenderer();
			field.editor = new FloatCellEditor(
				field);
			break;

		case "list":
			field.editor = new ListCellEditor(
				field);
			break;

		case "bool":
			field.renderer = new BoolCellRenderer();
			field.editor = new BoolCellEditor(
				field);
			break;

		case "objname":
			field.editor = new ObjectCellEditor(
				field);
			break;
		}

		if (field.renderer == null)
			field.renderer = new GeneralCellRenderer();

		this.fields.put(name, field);
	}

	public void setFieldValue(final String field, final Object value) {

		if (!this.fields.containsKey(field))
			return;

		final Field f = this.fields.get(field);
		if (f.value == null)
			return;
		f.value = value;
	}

	public void removeField(final String field) {

		if (!this.fields.containsKey(field))
			return;

		final Field f = this.fields.get(field);
		f.renderer = null;
		f.editor = null;
		this.fields.remove(field);
	}

	@Override
	public Rectangle getCellRect(final int row, final int col, final boolean includeSpacing) {

		final Rectangle rect = super.getCellRect(row, col, includeSpacing);
		try {
			final Field field = (Field) this.fields.values()
					.toArray()[row];

			if (field.type.equals("category"))
				if (col == 0)
					rect.width = this.getBounds().width;
				else
					rect.width = 0;
		}
		catch (final ArrayIndexOutOfBoundsException ex) {
		}

		return rect;
	}

	@Override
	public TableCellRenderer getCellRenderer(final int row, final int col) {

		final Field field = (Field) this.fields.values()
				.toArray()[row];

		if (col == 0)
			return this.labelRenderer;
		if ((col == 1) && (field.renderer != null))
			return field.renderer;

		return super.getCellRenderer(row, col);
	}

	@Override
	public TableCellEditor getCellEditor(final int row, final int col) {

		final Field field = (Field) this.fields.values()
				.toArray()[row];

		if (col == 0)
			return this.labelEditor;
		if (col == 1)
			return field.editor;

		return super.getCellEditor(row, col);
	}

	public class PGModel extends AbstractTableModel {
		@Override
		public int getRowCount() {

			return PropertyGrid.this.fields.size();
		}

		@Override
		public int getColumnCount() {

			return 2;
		}

		@Override
		public Object getValueAt(final int row, final int col) {

			final Field field = (Field) PropertyGrid.this.fields.values()
					.toArray()[row];

			if (col == 0)
				return field.label.getText();
			else if (!field.type.equals("category"))
				// if (field.value == null) return "<multiple>";
				return field.value;

			return null;
		}

		@Override
		public String getColumnName(final int col) {

			if (col == 0)
				return "Property";
			return "Value";
		}

		@Override
		public boolean isCellEditable(final int row, final int col) {

			if (col == 0)
				return false;
			return true;
		}
	}

	// based off
	// http://code.google.com/p/spantable/source/browse/SpanTable/src/main/java/spantable/SpanTableUI.java
	public class PGUI extends BasicTableUI {
		@Override
		public void paint(final Graphics g, final JComponent c) {

			final Rectangle r = g.getClipBounds();
			final int firstRow = this.table.rowAtPoint(new Point(
				r.x, r.y));
			int lastRow = this.table.rowAtPoint(new Point(
				r.x, r.y + r.height));
			// -1 is a flag that the ending point is outside the table:
			if (lastRow < 0)
				lastRow = this.table.getRowCount() - 1;
			for (int row = firstRow; row <= lastRow; row++)
				paintRow(row, g);
		}

		private void paintRow(final int row, final Graphics g) {

			final Rectangle clipRect = g.getClipBounds();
			for (int col = 0; col < this.table.getColumnCount(); col++) {
				final Rectangle cellRect = this.table.getCellRect(row, col, true);
				if (cellRect.width == 0)
					continue;
				if (cellRect.intersects(clipRect))
					paintCell(row, col, g, cellRect);
			}
		}

		private void paintCell(final int row, final int column, final Graphics g, final Rectangle area) {

			final int verticalMargin = this.table.getRowMargin();
			final int horizontalMargin = this.table.getColumnModel()
					.getColumnMargin();

			final Color c = g.getColor();
			g.setColor(this.table.getGridColor());
			// Acmlmboard border method
			g.drawLine((area.x + area.width) - 1, area.y, (area.x + area.width) - 1, (area.y + area.height) - 1);
			g.drawLine(area.x, (area.y + area.height) - 1, (area.x + area.width) - 1, (area.y + area.height) - 1);
			g.setColor(c);

			area.setBounds(area.x + (horizontalMargin / 2), area.y + (verticalMargin / 2),
					area.width - horizontalMargin, area.height - verticalMargin);

			if (this.table.isEditing() && (this.table.getEditingRow() == row)
					&& (this.table.getEditingColumn() == column)) {
				final Component component = this.table.getEditorComponent();
				component.setBounds(area);
				component.validate();
			}
			else {
				final TableCellRenderer renderer = this.table.getCellRenderer(row, column);
				final Component component = this.table.prepareRenderer(renderer, row, column);
				if ((renderer != null) && (component != null)) {
					if (component.getParent() == null)
						this.rendererPane.add(component);
					this.rendererPane.paintComponent(g, component, this.table, area.x, area.y, area.width, area.height,
							true);
				}
			}
		}
	}

	public class LabelCellRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
				final boolean hasFocus, final int row, final int col) {

			final Field field = (Field) PropertyGrid.this.fields.values()
					.toArray()[row];
			field.label.setBackground(Color.CYAN);
			if (col == 0)
				return field.label;
			return null;
		}
	}

	public class GeneralCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = -1629423005838078274L;

		@Override
		public Component getTableCellRendererComponent(final JTable table, Object value, final boolean isSelected,
				final boolean hasFocus, final int row, final int col) {

			if (value == null)
				value = "<multiple>";
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		}
	}

	public class FloatCellRenderer extends DefaultTableCellRenderer {

		private static final long	serialVersionUID	= 194358554945220534L;
		JLabel						label;

		public FloatCellRenderer() {

			this.label = new JLabel();
		}

		@Override
		public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
				final boolean hasFocus, final int row, final int col) {

			if (value == null) {
				this.label.setText("<multiple>");
				return this.label;
			}

			// make float rendering consistent with JSpinner's display
			final DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
			df.applyPattern("#.###");
			final String formattedval = df.format(value);
			this.label.setText(formattedval);
			// label.setHorizontalAlignment(SwingConstants.RIGHT);
			return this.label;
		}
	}

	public class BoolCellRenderer extends DefaultTableCellRenderer {
		JCheckBox cb;

		public BoolCellRenderer() {

			this.cb = new JCheckBox();
		}

		@Override
		public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
				final boolean hasFocus, final int row, final int col) {

			if (value == null) {
				this.cb.getModel()
						.setSelected(true);
				this.cb.getModel()
						.setArmed(true);
			}
			else
				this.cb.setSelected((boolean) value);
			return this.cb;
		}
	}

	public class LabelCellEditor implements TableCellEditor {
		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int column) {

			return null;
		}

		@Override
		public Object getCellEditorValue() {

			return null;
		}

		@Override
		public boolean isCellEditable(final EventObject anEvent) {

			return false;
		}

		@Override
		public boolean shouldSelectCell(final EventObject anEvent) {

			return false;
		}

		@Override
		public boolean stopCellEditing() {

			return true;
		}

		@Override
		public void cancelCellEditing() {

		}

		@Override
		public void addCellEditorListener(final CellEditorListener l) {

		}

		@Override
		public void removeCellEditorListener(final CellEditorListener l) {

		}
	}

	public class FloatCellEditor extends AbstractCellEditor implements TableCellEditor {

		private static final long	serialVersionUID	= -6563105422119971211L;
		JSpinner					spinner;
		Field						field;

		public FloatCellEditor(final Field f) {

			this.field = f;

			this.spinner = new JSpinner();
			this.spinner.setModel(new SpinnerNumberModel(
				10.00f, -Float.MAX_VALUE, Float.MAX_VALUE, 1f));
			this.spinner.addChangeListener(evt -> {

				// guarantee the value we're giving out is a Float. herp derp
				final Object val = FloatCellEditor.this.spinner.getValue();
				final float fval = (val instanceof Double) ? (float) (double) val : (float) val;
				FloatCellEditor.this.field.value = fval;
				PropertyGrid.this.eventListener.propertyChanged(FloatCellEditor.this.field.name, fval);
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.spinner.getValue();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int col) {

			this.spinner.setValue(value == null ? 0f : value);
			return this.spinner;
		}
	}

	public class TextCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long	serialVersionUID	= -7782086271048471729L;
		JTextField					textfield;
		Field						field;
		boolean						isInt;

		public TextCellEditor(final Field f) {

			this.field = f;
			this.isInt = f.type.equals("int");

			this.textfield = new JTextField(
				f.value.toString());
			this.textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(final KeyEvent evt) {

				}

				@Override
				public void keyTyped(final KeyEvent evt) {

				}

				@Override
				public void keyReleased(final KeyEvent evt) {

					Object val = TextCellEditor.this.textfield.getText();
					try {
						if (TextCellEditor.this.isInt)
							val = Integer.parseInt((String) val);
						TextCellEditor.this.textfield.setForeground(Color.getColor("text"));

						TextCellEditor.this.field.value = val;
						PropertyGrid.this.eventListener.propertyChanged(TextCellEditor.this.field.name, val);
					}
					catch (final NumberFormatException ex) {
						TextCellEditor.this.textfield.setForeground(new Color(
							0xFF4040));
					}
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, Object value, final boolean isSelected,
				final int row, final int col) {

			if (value == null)
				value = this.isInt ? "0" : "<multiple>";
			this.textfield.setText(value.toString());
			return this.textfield;
		}
	}

	public class ListCellEditor extends AbstractCellEditor implements TableCellEditor {

		private static final long	serialVersionUID	= -3263903819579902838L;
		JComboBox<?>				combo;
		Field						field;

		public ListCellEditor(final Field f) {

			this.field = f;

			this.combo = new JComboBox<>(
				f.choices.toArray());

			this.combo.addActionListener(evt -> {

				final Object val = ListCellEditor.this.combo.getSelectedItem();

				if (!ListCellEditor.this.field.value.equals(val)) {
					ListCellEditor.this.field.value = val;
					PropertyGrid.this.eventListener.propertyChanged(ListCellEditor.this.field.name, val);
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.combo.getSelectedItem();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int col) {

			if (value == null)
				this.combo.setSelectedIndex(0);
			else
				this.combo.setSelectedItem(value);
			return this.combo;
		}
	}

	public class BoolCellEditor extends AbstractCellEditor implements TableCellEditor {
		JCheckBox	checkbox;
		Field		field;

		public BoolCellEditor(final Field f) {

			this.field = f;

			this.checkbox = new JCheckBox();
			this.checkbox.addActionListener(evt -> {

				final boolean val = BoolCellEditor.this.checkbox.isSelected();
				BoolCellEditor.this.field.label.setText(Boolean.toString(val));
				BoolCellEditor.this.field.value = val;
				PropertyGrid.this.eventListener.propertyChanged(BoolCellEditor.this.field.name, val);
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.checkbox.isSelected();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int col) {

			this.checkbox.setSelected(value == null ? false : (boolean) value);
			return this.checkbox;
		}
	}

	public class ObjectCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long	serialVersionUID	= -6140303185028155547L;
		JPanel						container;
		JTextField					textfield;
		JButton						button;
		Field						field;

		public ObjectCellEditor(final Field f) {

			this.field = f;

			this.container = new JPanel();
			this.container.setLayout(new BorderLayout());

			this.textfield = new JTextField(
				f.value.toString());
			this.container.add(this.textfield, BorderLayout.CENTER);
			this.textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(final KeyEvent evt) {

				}

				@Override
				public void keyTyped(final KeyEvent evt) {

				}

				@Override
				public void keyReleased(final KeyEvent evt) {

					final String val = ObjectCellEditor.this.textfield.getText();

					// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
					// "text") : new Color(0xFF4040));

					ObjectCellEditor.this.field.value = val;
					PropertyGrid.this.eventListener.propertyChanged(ObjectCellEditor.this.field.name, val);
				}
			});

			this.button = new JButton(
				"...");
			this.container.add(this.button, BorderLayout.EAST);
			this.button.addActionListener(evt -> {
				// GalaxyEditorForm gform = (GalaxyEditorForm) parent;

				// ObjectSelectForm objsel = new ObjectSelectForm(gform, gform.zoneArcs.get(
				// gform.galaxyName).gameMask, textfield.getText());
				// objsel.setVisible(true);

				// String val = objsel.selectedObject;
				// textfield.setText(val);
				// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
				// "text") : new Color(0xFF4040));

				// field.value = val;
				// eventListener.propertyChanged(field.name, val);
			});

			final int btnheight = this.button.getPreferredSize().height;
			this.button.setPreferredSize(new Dimension(
				btnheight, btnheight));

			// textfield.setForeground(ObjectDB.objects.containsKey((String) field.value) ? Color
			// .getColor("text") : new Color(0xFF4040));
		}

		@Override
		public Object getCellEditorValue() {

			return this.textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
				final int row, final int col) {

			this.textfield.setText(value == null ? "<multiple>" : value.toString());
			return this.container;
		}
	}

	public class Field {
		String name;

		String			type;
		int				row;
		java.util.List	choices;
		Object			value;

		JLabel				label;
		TableCellRenderer	renderer;
		TableCellEditor		editor;
	}

	public interface EventListener {
		public void propertyChanged(String propname, Object value);
	}

	public LinkedHashMap<String, PropertyGrid.Field>	fields;
	private int											curRow;

	private EventListener eventListener;

	private final JFrame parent;

	private final LabelCellRenderer	labelRenderer;
	private final LabelCellEditor	labelEditor;
	private static JComboBox		WygladProgramujComboBox;
	private static JFrame			frame	= new JFrame(
		"Prognostic :: Administracja");

	public static void main(final String s[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(PropertyGrid.frame);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		// Add a window listner for close button
		PropertyGrid.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {

				JOptionPane.showMessageDialog(PropertyGrid.frame, e.toString(), "Czy chcesz zmknąc program?",
						JOptionPane.QUESTION_MESSAGE);
				System.exit(0);
			}
		});
		System.getenv("APPDATA");
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("znaczek.ico")));
		final ImageIcon imgicon = new ImageIcon(
			"D://IIM.ico");
		PropertyGrid.frame.setIconImage(imgicon.getImage());

		new DatePicker(
			Calendar.getInstance(Locale.getDefault()));

		final JLabel jlbempty = new JLabel(
			"");
		final PropertyGrid pg = new PropertyGrid(
			PropertyGrid.frame);
		pg.setRowHeight(21);
		pg.addCategory("Visibility", "Okna");
		pg.addField("F1", "Codigo", "bool", null, true);
		pg.addField("Float", "Wartość", "float", null, 1.01);
		pg.addField("Logic", "Logical", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const", "Stała", "list", Arrays.asList(2, 3, 4, 5, 6), 2);

		pg.addCategory("Visibility1", "Okna1");
		pg.addField("F11", "Codigo1", "bool", null, true);
		pg.addField("Float1", "Wartość1", "float", null, 1);
		pg.addField("Logic1", "Logical1", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const1", "Stała1", "list", Arrays.asList(2, 3, 4, 5, 6, 23, 23, 2322, 23), 2);

		jlbempty.setPreferredSize(new Dimension(
			175, 100));
		PropertyGrid.WygladProgramujComboBox = new JComboBox<>(
			new String[] { "1", "2", "3", "4" });

		PropertyGrid.WygladProgramujComboBox.addActionListener(PropertyGrid::WygladProgramu_jComboBoxActionPerformed);
		final String[] columnNames = { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian", "Percent" };

		final Object[][] data = {
				{
						"Kathy",
						"Smith",
						"Snowboarding",
						Double.valueOf(5.01),
						Boolean.valueOf(false),
						Double.valueOf(0.50) }, };

		class CustomTableCellRender extends DefaultTableCellRenderer {

			private static final long	serialVersionUID	= 3892743523344580648L;
			Border						padding				= BorderFactory.createEmptyBorder(0, 2, 0, 2);

			@Override
			public Component getTableCellRendererComponent(final JTable table, final Object value,
					final boolean isSelected, final boolean hasFocus, final int row, final int column) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setBorder(BorderFactory.createCompoundBorder(getBorder(), this.padding));

				return this;
			}
		}

		class PercentTableCellRenderer extends CustomTableCellRender {

			private static final long	serialVersionUID	= 5110057309320837936L;
			private final NumberFormat	numFormat			= NumberFormat.getPercentInstance(Locale.getDefault());

			@Override
			public final Component getTableCellRendererComponent(final JTable table, final Object value,
					final boolean isSelected, final boolean hasFocus, final int row, final int column) {

				final Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (value instanceof Number) {
					setHorizontalAlignment(SwingConstants.RIGHT);
					setText(this.numFormat.format(value));
				}
				else
					setText("");

				return result;
			}
		}

		class CurrencyTableCellRenderer extends CustomTableCellRender {

			private static final long	serialVersionUID	= 7477511819850959638L;
			private final NumberFormat	FORMAT				= NumberFormat.getCurrencyInstance();

			@Override
			public final Component getTableCellRendererComponent(final JTable table, final Object value,
					final boolean isSelected, final boolean hasFocus, final int row, final int column) {

				final Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (value instanceof Number) {
					setHorizontalAlignment(SwingConstants.RIGHT);
					setText(this.FORMAT.format(value));
				}
				else
					setText("");

				setBackground(Color.orange);
				if (10.0 >= ((Number) value).doubleValue())
					setForeground(Color.BLUE);
				else
					setForeground(Color.RED);
				setFont(getFont().deriveFont(Font.PLAIN));
				return result;
			}
		}

		class BooleanTableCellRenderer extends CustomTableCellRender {

			private static final long serialVersionUID = -5031026174757990289L;

			@Override
			public final Component getTableCellRendererComponent(final JTable table, final Object value,
					final boolean isSelected, final boolean hasFocus, final int row, final int column) {

				final Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (value instanceof Boolean) {
					setHorizontalAlignment(SwingConstants.LEFT);
					setText((Boolean) value ? "Tak" : "Nie");
				}
				else
					setText("");

				setBackground(Color.LIGHT_GRAY);
				setForeground(Color.yellow);
				setFont(getFont().deriveFont(Font.PLAIN));
				return result;
			}
		}

		// class HeaderRenderer implements UIResource, TableCellRenderer {
		//
		// private final TableCellRenderer original;
		//
		// public HeaderRenderer(final TableCellRenderer original) {
		//
		// this.original = original;
		// }
		//
		// @Override
		// public Component getTableCellRendererComponent(final JTable table, final Object value,
		// final boolean isSelected, final boolean hasFocus, final int row, final int column) {
		//
		// final Component comp = this.original.getTableCellRendererComponent(table, value, isSelected,
		// hasFocus,
		// row, column);
		// comp.setFont(comp.getFont()
		// .deriveFont(Font.BOLD));
		// return comp;
		// }
		//
		// }

		final JSortTable sorttable = new JSortTable(
			data, columnNames);

		sorttable.setRowHeight(21);

		sorttable.getColumnModel()
				.getColumn(0)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(1)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(2)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(3)
				.setCellRenderer(new CurrencyTableCellRenderer());

		sorttable.getColumnModel()
				.getColumn(4)
				.setCellRenderer(new BooleanTableCellRenderer());

		sorttable.getColumnModel()
				.getColumn(5)
				.setCellRenderer(new PercentTableCellRenderer());

		sorttable.setFont(new Font(
			sorttable.getFont()
					.getName(),
			Font.PLAIN, 11));

		PropertyGrid.frame.setTitle("DB Suite Studio Manager Free Edition v1.6.2.1");

		PropertyGrid.frame.getContentPane()
				.add(jlbempty, BorderLayout.NORTH);
		PropertyGrid.frame.getContentPane()
				.add(PropertyGrid.WygladProgramujComboBox, BorderLayout.BEFORE_LINE_BEGINS);
		PropertyGrid.frame.getContentPane()
				.add(new JScrollPane(
					sorttable), BorderLayout.CENTER);

		final JButton button = new JButton(
			"alert");
		button.setIconTextGap(10);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20));
		// try {
		// button.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread()
		// .getContextClassLoader()
		// .getResourceAsStream("ic_add_alert_black_18dp.png"))));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		PropertyGrid.frame.getContentPane()
				.add(button, BorderLayout.SOUTH);

		// frame.pack();
		PropertyGrid.frame.setSize(800, 600);
		PropertyGrid.frame.setVisible(true);
	}

	private static void WygladProgramu_jComboBoxActionPerformed(final ActionEvent evt) {

		// TODO add your handling code here:
		final JComboBox control = (JComboBox) evt.getSource();

		/* Konfiguracja wyglądu program */
		try {
			PropertyGrid.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 0) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (final Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(PropertyGrid.frame);
			}

			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 1) {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				}
				catch (final Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}

			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 2) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				}
				catch (final Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}
			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 3) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				}
				catch (final Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);

			}
		}
		finally {
			control.setCursor(Cursor.getDefaultCursor());
		}
		System.gc();

	}
}
