
/*
 *  @Author = Caue Meireles Duarte
 */
package customerpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import medias.Media;

public class CustomerPageView extends JFrame {

	private CustomerPageController controller;
	private BorderLayout manager;
	private JTable table; 
	private JLabel pointsLbl;

	public CustomerPageView(CustomerPageController controller) {
		this.controller = controller;
		this.addWindowListener(controller);
		settings();
		createElements();
	}

	/**
	 *Sets the frame's layout to a border layout and create panel with the three main functionalities buttons
	 *on the left of the frame. Also creates a panel in the center of the border layout that will be used
	 *to display data according to the button pressed.
	 *
	 * @see CustomerPageController
	 */
	private void createElements() {

		BorderLayout manager = new BorderLayout();
		this.setLayout(manager);
		this.manager = manager;
		JLabel title = new JLabel("Welcome!"); //change to display customer name
		title.setFont(new Font(title.getName(), Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title, BorderLayout.PAGE_START);

		//left Option panel
		JPanel optPanel = new JPanel();
		optPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		optPanel.setPreferredSize(new Dimension (200,500));
		optPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton newApt = new JButton("Check Rented Media");
		newApt.addActionListener(controller);
		newApt.setActionCommand("rented");
		JButton viewApt = new JButton("Rent Media");
		viewApt.addActionListener(controller);
		viewApt.setActionCommand("newRent");
		JButton close = new JButton("Exit Program");
		close.addActionListener(controller);
		close.setActionCommand("close");
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.insets = new Insets(20,0,0,0);
		c.gridy = 0;
		optPanel.add(newApt, c);
		c.gridy = 1;
		optPanel.add(viewApt, c);
		c.gridy = 2;
		optPanel.add(close, c);
		this.add(optPanel, BorderLayout.LINE_START);

		JPanel placeHolder = new JPanel();
		this.add(placeHolder, BorderLayout.CENTER);

		JLabel pointsLbl = new JLabel ("The customer has " + controller.getCustomerPoints() +
				" loyalty points.");
		this.pointsLbl = pointsLbl;
		pointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(pointsLbl, BorderLayout.PAGE_END);
		this.validate();
		this.repaint();

	}


	/**
	 *	Sets main visual aspects for the frame.
	 */
	private void settings() {

		this.addWindowListener(controller);
		this.setVisible(true);
		this.setSize(1000, 660);
		this.validate();
		this.repaint();

	}

	/**
	 *Clears the view's mid-panel and displays rented media information. 
	 *If the user hasn't rented any medias, display message.
	 *
	 * @see Customer, CustomerPageController
	 */
	public void rentedPanel() {

		//clearing center position on the frame
		this.remove(manager.getLayoutComponent(BorderLayout.CENTER));

		//Creating ArrayList with item being currently rented by the user
		ArrayList<Media> rentalList = this.controller.getCustomerRentedList();

		//Creating panel
		JPanel rentalsPnl = new JPanel();
		rentalsPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(rentalsPnl, BorderLayout.CENTER);

		//Verifying if the customer has any media rented
		if(rentalList.size() == 0) {
			JLabel noRentsLbl = new JLabel("This customer has no rented items at the moment.");
			noRentsLbl.setFont(new Font(noRentsLbl.getName(), Font.BOLD, 30));
			noRentsLbl.setHorizontalAlignment(SwingConstants.CENTER);
			rentalsPnl.add(noRentsLbl);

			//Displaying rented media information	
		} else {
			int counter = 0;
			int btnCounter = 0;

			for(Media rentals:rentalList) {
				JLabel title = new JLabel("Title: " + rentals.getTitle());
				c.gridy = counter;
				c.gridx = 0;
				rentalsPnl.add(title, c);

				JButton btn = new JButton("Return Item");
				btn.setActionCommand(""+ btnCounter++);
				btn.addActionListener(controller);
				c.gridx = 1;
				c.ipady = 40;  
				c.gridheight = 2;
				counter++;
				rentalsPnl.add(btn, c);

				JLabel rented = new JLabel("Rented On: " + rentals.getDate());
				c.ipady = 0;  
				c.gridx = 0;
				c.gridy = counter;
				c.gridheight = 1;
				rentalsPnl.add(rented, c);
				counter++;
			}
		}

		this.validate();
		this.repaint();

	}

	/**
	 *Clears the view's mid-panel and displays table with available media. Media is categorized using radio
	 *buttons on top of the panel, which are enabled according to the customer's membership type.
	 *
	 * @see Customer, CustomerPageController
	 */
	public void availableMediaPanel(int membershipType) {

		//clearing center position on the frame
		this.remove(manager.getLayoutComponent(BorderLayout.CENTER));

		//Creating panel
		JPanel mediaPnl = new JPanel();
		mediaPnl.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(mediaPnl, BorderLayout.CENTER);

		/*creating RadioButtons to choose the type of media displayed, disabling the ones related to
		 *the types of media the customer can't rent
		 */
		JRadioButton movieRB = new JRadioButton("Display Movies");
		movieRB.setActionCommand("movies");
		movieRB.addActionListener(controller);
		movieRB.setHorizontalAlignment(SwingConstants.CENTER);
		if (membershipType !=1 && membershipType !=4 ) {
			movieRB.setEnabled(false);
		}
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		mediaPnl.add(movieRB, c);
		
		JRadioButton concertRB = new JRadioButton("Display Live Concerts");
		concertRB.setActionCommand("concerts");
		concertRB.addActionListener(controller);
		concertRB.setHorizontalAlignment(SwingConstants.CENTER);
		if (membershipType != 2 && membershipType != 4 ) {
			concertRB.setEnabled(false);
		}
		c.gridx = 1;
		mediaPnl.add(concertRB, c);
		
		JRadioButton tvRB = new JRadioButton("Display TV Shows");
		tvRB.setActionCommand("tv");
		tvRB.addActionListener(controller);
		tvRB.setHorizontalAlignment(SwingConstants.CENTER);
		if (membershipType !=3 && membershipType !=4 ) {
			tvRB.setEnabled(false);
		}
		c.gridx = 2;
		mediaPnl.add(tvRB, c);
		
		
		ButtonGroup gBtn = new ButtonGroup();
		gBtn.add(tvRB);
		gBtn.add(concertRB);
		gBtn.add(movieRB);

		//Adding table that will show titles and availability
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table = table;
		JScrollPane scrollPane = new JScrollPane(table);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 5;
		c.weightx = 1;
		c.weighty = 1;
		mediaPnl.add(scrollPane,c);

		//adding return button
		JButton rentBt = new JButton("Rent Item");
		rentBt.setActionCommand("rentItem");
		rentBt.addActionListener(controller);
		c.gridy = 2;
		c.weighty = 0;
		mediaPnl.add(rentBt, c);

		this.validate();
		this.repaint();
	}
	
	public JLabel getPointsLbl() {
		return this.pointsLbl;
	}

	public JTable getTable() {
		return this.table;
	}
	
}
