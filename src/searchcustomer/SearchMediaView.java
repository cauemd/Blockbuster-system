
/*
 *  @Author = Caue Meireles Duarte
 */
package searchcustomer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import medias.Media;

public class SearchMediaView extends JFrame{

	private SearchMediaController controller;
	public JTable table;

	public SearchMediaView(SearchMediaController controller) {
		this.controller = controller;
		settings();
		createElements();
	}

	//creates buttons, layout manager, JTable and adds them to the frame
	private void createElements() {

		//creating Layout Manager
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//creating RadioButtons to choose the type of media displayed
		JRadioButton movieRB = new JRadioButton("Display Movies");
		movieRB.setActionCommand("movies");
		movieRB.addActionListener(controller);
		movieRB.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		this.add(movieRB, c);
		JRadioButton concertRB = new JRadioButton("Display Live Concerts");
		concertRB.setActionCommand("concerts");
		concertRB.addActionListener(controller);
		concertRB.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 1;
		this.add(concertRB, c);
		JRadioButton tvRB = new JRadioButton("Display TV Shows");
		tvRB.setHorizontalAlignment(SwingConstants.CENTER);
		tvRB.setActionCommand("tv");
		tvRB.addActionListener(controller);
		c.gridx = 2;
		this.add(tvRB, c);
		ButtonGroup gBtn = new ButtonGroup();
		gBtn.add(tvRB);
		gBtn.add(concertRB);
		gBtn.add(movieRB);

		//Adding table that will show titles and availability
		
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
    	JScrollPane scrollPane = new JScrollPane(table);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 5;
		c.weightx = 1;
		c.weighty = 1;
		this.add(scrollPane,c);
		
		//adding return button
		JButton returnBt = new JButton("Return to Front Page");
		returnBt.setActionCommand("return");
		returnBt.addActionListener(controller);
		c.gridy = 2;
		c.weighty = 0;
		this.add(returnBt, c);
		
		this.validate();
		this.repaint();

	}

	//create main settings for the frame
	private void settings() {
		this.setVisible(true);
		this.addWindowListener(controller);
		this.setSize(800,500);
		this.validate();
		this.repaint();

	}


}
