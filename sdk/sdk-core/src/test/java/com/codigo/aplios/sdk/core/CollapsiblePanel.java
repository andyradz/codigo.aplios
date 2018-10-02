package com.codigo.aplios.sdk.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * * Copyright (c) 2004 Memorial Sloan-Kettering Cancer Center * * Code written by: Gary Bader * Authors: Gary Bader,
 * Ethan Cerami, Chris Sander * * This library is free software; you can redistribute it and/or modify it * under the
 * terms of the GNU Lesser General Public License as published * by the Free Software Foundation; either version 2.1 of
 * the License, or * any later version. * * This library is distributed in the hope that it will be useful, but *
 * WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. The
 * software and * documentation provided hereunder is on an "as is" basis, and * Memorial Sloan-Kettering Cancer Center
 * * has no obligations to provide maintenance, support, * updates, enhancements or modifications. In no event shall
 * the
 * * Memorial Sloan-Kettering Cancer Center * be liable to any party for direct, indirect, special, * incidental or
 * consequential damages, including lost profits, arising * out of the use of this software and its documentation, even
 * if * Memorial Sloan-Kettering Cancer Center * has been advised of the possibility of such damage. See * the GNU
 * Lesser General Public License for more details. * * You should have received a copy of the GNU Lesser General Public
 * License * along with this library; if not, write to the Free Software Foundation, * Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA.
 *
 * User: Vuk Pavlovic Date: Nov 29, 2006 Time: 5:34:46 PM Description: The user-triggered collapsable panel containing
 * the component (trigger) in the titled border
 */
/**
 * The user-triggered collapsable panel containing the component (trigger) in the titled border
 */
public class CollapsiblePanel
        extends JPanel {

    private static final long serialVersionUID = 1270484262007416569L;

    // Border
    CollapsableTitledBorder border;																// includes upper left
    // component and line
    // type

    Border collapsedBorderLine = BorderFactory.createEmptyBorder(2, 2, 2, 2);	// no border

    Border expandedBorderLine = null;											// because this is null,
    // default is used,
    // etched lowered border
    // on MAC

    // Title
    AbstractButton titleComponent; // displayed in the titled border

    // Expand/Collapse button
    final static int COLLAPSED = 0, EXPANDED = 1;						// image States

    ImageIcon[] iconArrow = this.createExpandAndCollapseIcon();

    JButton arrow = this.createArrowButton();

    // Content Pane
    JPanel panel;

    // Container State
    boolean collapsed; // stores curent state of the collapsable panel

    /**
     * Constructor for an option button controlled collapsable panel. This is useful when a group of options each have
     * unique sub contents. The radio buttons should be created, grouped, and then used to construct their own
     * collapsable panels. This way choosing a different option in the same option group will collapse all unselected
     * options. Expanded panels draw a border around the contents and through the radio button in the fashion of a
     * titled border.
     *
     * @param component
     *                  Radio button that expands and collapses the panel based on if it is selected or not
     */
    public CollapsiblePanel(final JRadioButton component) {

        component.addItemListener(new CollapsiblePanel.ExpandAndCollapseAction());
        this.titleComponent = component;
        this.collapsed = !component.isSelected();
        this.commonConstructor();
    }

    /**
     * Constructor for a label/button controlled collapsable panel. Displays a clickable title that resembles a native
     * titled border except for an arrow on the right side indicating an expandable panel. The actual border only
     * appears when the panel is expanded.
     *
     * @param text
     *             Title of the collapsable panel in string format, used to create a button with text and an arrow icon
     */
    public CollapsiblePanel(final String text) {

        this.arrow.setText(text);
        this.titleComponent = this.arrow;
        this.collapsed = true;
        this.commonConstructor();
    }

    /**
     * Sets layout, creates the content panel and adds it and the title component to the container, all constructors
     * have this procedure in common.
     */
    private void commonConstructor() {

        this.setLayout(new BorderLayout());

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());

        this.add(this.titleComponent, BorderLayout.CENTER);
        this.add(this.panel, BorderLayout.CENTER);
        this.setCollapsed(this.collapsed);

        this.placeTitleComponent();
    }

    /**
     * Sets the bounds of the border title component so that it is properly positioned.
     */
    private void placeTitleComponent() {

        final Insets insets = this.getInsets();
        final Rectangle containerRectangle = this.getBounds();
        final Rectangle componentRectangle = this.border.getComponentRect(containerRectangle, insets);
        this.titleComponent.setBounds(componentRectangle);
    }

    public void setTitleComponentText(final String text) {

        if (this.titleComponent instanceof JButton)
            this.titleComponent.setText(text);
        this.placeTitleComponent();
    }

    /**
     * This class requires that all content be placed within a designated panel, this method returns that panel.
     *
     * @return panel The content panel
     */
    public JPanel getContentPane() {

        return this.panel;
    }

    /**
     * Collapses or expands the panel. This is done by adding or removing the content pane, alternating between a frame
     * and empty border, and changing the title arrow. Also, the current state is stored in the collapsed boolean.
     *
     * @param collapse
     *                 When set to true, the panel is collapsed, else it is expanded
     */
    public void setCollapsed(final boolean collapse) {

        if (collapse) {
            // collapse the panel, remove content and set border to empty border
            this.remove(this.panel);
            this.arrow.setIcon(this.iconArrow[CollapsiblePanel.COLLAPSED]);
            this.border = new CollapsableTitledBorder(this.collapsedBorderLine, this.titleComponent);
        } else {
            // expand the panel, add content and set border to titled border
            this.add(this.panel, BorderLayout.NORTH);
            this.arrow.setIcon(this.iconArrow[CollapsiblePanel.EXPANDED]);
            this.border = new CollapsableTitledBorder(this.expandedBorderLine, this.titleComponent);
        }

        this.setBorder(this.border);
        this.collapsed = collapse;
        this.updateUI();
    }

    /**
     * Returns the current state of the panel, collapsed (true) or expanded (false).
     *
     * @return collapsed Returns true if the panel is collapsed and false if it is expanded
     */
    public boolean isCollapsed() {

        return this.collapsed;
    }

    /**
     * Returns an ImageIcon array with arrow images used for the different states of the panel.
     *
     * @return iconArrow An ImageIcon array holding the collapse and expanded versions of the right hand side arrow
     */
    private ImageIcon[] createExpandAndCollapseIcon() {

        final ImageIcon[] iconArrow = new ImageIcon[2];
        final URL iconURL = null;

        // iconURL = Resources.getUrl(ImageName.ARROW_COLLAPSED);
        if (iconURL != null)
            iconArrow[CollapsiblePanel.COLLAPSED] = new ImageIcon(iconURL);

        // iconURL = Resources.getUrl(ImageName.ARROW_EXPANDED);
        if (iconURL != null)
            iconArrow[CollapsiblePanel.EXPANDED] = new ImageIcon(iconURL);

        return iconArrow;
    }

    /**
     * Returns a button with an arrow icon and a collapse/expand action listener.
     *
     * @return button Button which is used in the titled border component
     */
    private JButton createArrowButton() {

        final JButton button = new JButton("arrow", this.iconArrow[CollapsiblePanel.COLLAPSED]);
        button.setBorder(BorderFactory.createEmptyBorder(0, 1, 5, 1));
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 0, 3, 0));

        // We want to use the same font as those in the titled border font
        final Font font = BorderFactory.createTitledBorder("Sample")
                .getTitleFont();
        final Color color = BorderFactory.createTitledBorder("Sample")
                .getTitleColor();
        button.setFont(font);
        button.setForeground(color);
        button.setFocusable(false);
        button.setContentAreaFilled(false);

        button.addActionListener(new CollapsiblePanel.ExpandAndCollapseAction());

        return button;
    }

    /**
     * Handles expanding and collapsing of extra content on the user's click of the titledBorder component.
     */
    private class ExpandAndCollapseAction
            extends AbstractAction
            implements ActionListener,
            ItemListener {

        @Override
        public void actionPerformed(final ActionEvent e) {

            CollapsiblePanel.this.setCollapsed(!CollapsiblePanel.this.isCollapsed());
        }

        @Override
        public void itemStateChanged(final ItemEvent e) {

            CollapsiblePanel.this.setCollapsed(!CollapsiblePanel.this.isCollapsed());
        }

    }

    /**
     * Special titled border that includes a component in the title area
     */
    private class CollapsableTitledBorder
            extends TitledBorder {

        JComponent component;

        public CollapsableTitledBorder(final Border border, final JComponent component) {

            this(border, component, TitledBorder.LEFT, TitledBorder.TOP);
        }

        public CollapsableTitledBorder(final Border border, final JComponent component, final int titleJustification,
                final int titlePosition) {

            // TitledBorder needs border, title, justification, position, font, and color
            super(border, null, titleJustification, titlePosition, null, null);
            this.component = component;
            if (border == null)
                this.border = super.getBorder();
        }

        @Override
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width,
                final int height) {

            final Rectangle borderR = new Rectangle(
                    x + TitledBorder.EDGE_SPACING, y + TitledBorder.EDGE_SPACING,
                    width - (TitledBorder.EDGE_SPACING * 2), height - (TitledBorder.EDGE_SPACING * 2));
            Insets borderInsets;
            if (this.border != null)
                borderInsets = this.border.getBorderInsets(c);
            else
                borderInsets = new Insets(0, 0, 0, 0);

            final Rectangle rect = new Rectangle(x, y, width, height);
            final Insets insets = this.getBorderInsets(c);
            final Rectangle compR = this.getComponentRect(rect, insets);
            int diff;
            switch (this.titlePosition) {
                case ABOVE_TOP:
                    diff = compR.height + TitledBorder.TEXT_SPACING;
                    borderR.y += diff;
                    borderR.height -= diff;
                    break;
                case TOP:
                case DEFAULT_POSITION:
                    diff = (insets.top / 2) - borderInsets.top - TitledBorder.EDGE_SPACING;
                    borderR.y += diff;
                    borderR.height -= diff;
                    break;
                case BELOW_TOP:
                case ABOVE_BOTTOM:
                    break;
                case BOTTOM:
                    diff = (insets.bottom / 2) - borderInsets.bottom - TitledBorder.EDGE_SPACING;
                    borderR.height -= diff;
                    break;
                case BELOW_BOTTOM:
                    diff = compR.height + TitledBorder.TEXT_SPACING;
                    borderR.height -= diff;
                    break;
            }
            this.border.paintBorder(c, g, borderR.x, borderR.y, borderR.width, borderR.height);
            final Color col = g.getColor();
            g.setColor(c.getBackground());
            g.fillRect(compR.x, compR.y, compR.width, compR.height);
            g.setColor(col);
        }

        @Override
        public Insets getBorderInsets(final Component c, final Insets insets) {

            Insets borderInsets;
            if (this.border != null)
                borderInsets = this.border.getBorderInsets(c);
            else
                borderInsets = new Insets(0, 0, 0, 0);
            insets.top = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.top;
            insets.right = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.right;
            insets.bottom = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.bottom;
            insets.left = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.left;

            if ((c == null) || (this.component == null))
                return insets;

            final int compHeight = this.component.getPreferredSize().height;

            switch (this.titlePosition) {
                case ABOVE_TOP:
                    insets.top += compHeight + TitledBorder.TEXT_SPACING;
                    break;
                case TOP:
                case DEFAULT_POSITION:
                    insets.top += Math.max(compHeight, borderInsets.top) - borderInsets.top;
                    break;
                case BELOW_TOP:
                    insets.top += compHeight + TitledBorder.TEXT_SPACING;
                    break;
                case ABOVE_BOTTOM:
                    insets.bottom += compHeight + TitledBorder.TEXT_SPACING;
                    break;
                case BOTTOM:
                    insets.bottom += Math.max(compHeight, borderInsets.bottom) - borderInsets.bottom;
                    break;
                case BELOW_BOTTOM:
                    insets.bottom += compHeight + TitledBorder.TEXT_SPACING;
                    break;
            }
            return insets;
        }

        public Rectangle getComponentRect(final Rectangle rect, final Insets borderInsets) {

            final Dimension compD = this.component.getPreferredSize();
            final Rectangle compR = new Rectangle(0, 0, compD.width, compD.height);
            switch (this.titlePosition) {
                case ABOVE_TOP:
                    compR.y = TitledBorder.EDGE_SPACING;
                    break;
                case TOP:
                case DEFAULT_POSITION:
                    if (CollapsiblePanel.this.titleComponent instanceof JButton)
                        compR.y = TitledBorder.EDGE_SPACING +
                                 ((borderInsets.top -
                                 TitledBorder.EDGE_SPACING - TitledBorder.TEXT_SPACING - compD.height) / 2);
                    else if (CollapsiblePanel.this.titleComponent instanceof JRadioButton)
                        compR.y = (borderInsets.top -
                                 TitledBorder.EDGE_SPACING - TitledBorder.TEXT_SPACING - compD.height) / 2;
                    break;
                case BELOW_TOP:
                    compR.y = borderInsets.top - compD.height - TitledBorder.TEXT_SPACING;
                    break;
                case ABOVE_BOTTOM:
                    compR.y = (rect.height - borderInsets.bottom) + TitledBorder.TEXT_SPACING;
                    break;
                case BOTTOM:
                    compR.y = (rect.height - borderInsets.bottom) +
                             TitledBorder.TEXT_SPACING + ((borderInsets.bottom -
                             TitledBorder.EDGE_SPACING - TitledBorder.TEXT_SPACING - compD.height) / 2);
                    break;
                case BELOW_BOTTOM:
                    compR.y = rect.height - compD.height - TitledBorder.EDGE_SPACING;
                    break;
            }
            switch (this.titleJustification) {
                case LEFT:
                case DEFAULT_JUSTIFICATION:
                    // compR.x = TEXT_INSET_H + borderInsets.left;
                    compR.x = (TitledBorder.TEXT_INSET_H + borderInsets.left) - TitledBorder.EDGE_SPACING;
                    break;
                case RIGHT:
                    compR.x = rect.width - borderInsets.right - TitledBorder.TEXT_INSET_H - compR.width;
                    break;
                case CENTER:
                    compR.x = (rect.width - compR.width) / 2;
                    break;
            }
            return compR;
        }

    }
}
