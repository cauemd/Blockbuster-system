package createMedia;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;

/*
 *@author = Caue Meireles Duarte 
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//view used to create new media in the system
public class CreateMediaView extends JFrame{

	private CreateMediaController controller;
	private JTextField title;
	private JTextField release;
	private JLabel errorLbl;
	private JComboBox<String> genre;
	private JTextField creator;					//also being used to store tv set number of seasons

	public CreateMediaView(CreateMediaController controller) {
		this.controller = controller;
		this.addWindowListener(controller);
		settings();
		createElements();
	}

	//create main elements for the view
	private void createElements() {

		//creating a grid layout manager for the frame
		this.setLayout(new GridLayout(0,1));

		//setting buttons that will generate the view for different kinds of media
		JButton movieBtn = new JButton("New Movie");
		movieBtn.addActionListener(controller);
		movieBtn.setActionCommand("movie");
		this.add(movieBtn);
		JButton concertBtn = new JButton("New Live Concert");
		concertBtn.addActionListener(controller);
		concertBtn.setActionCommand("concert");
		this.add(concertBtn);
		JButton tvBtn = new JButton("New TV Show");
		tvBtn.addActionListener(controller);
		tvBtn.setActionCommand("tv");
		this.add(tvBtn);
		JButton loginBtn = new JButton("Return to Front Page");
		loginBtn.addActionListener(controller);
		loginBtn.setActionCommand("cancel");
		this.add(loginBtn);

		this.validate();
		this.repaint();		
	}

	//clears the frame and adds the labels and TextFields to get new movie's info
	public void addMovie() {

		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 0, 0, 10);
		c.anchor = GridBagConstraints.WEST;

		JLabel titleLbl = new JLabel("Title:");
		this.add(titleLbl, c);
		JTextField titleTF = new JTextField(15);
		this.title = titleTF;
		c.gridx = 1;
		this.add(titleTF, c);
		JLabel releaseLbl = new JLabel("Year Of Release:");
		c.gridx = 0;
		c.gridy = 1;
		this.add(releaseLbl, c);
		JTextField releaseTF = new JTextField(15);
		this.release = releaseTF;
		c.gridx = 1;
		this.add(releaseTF, c);
		JLabel creatorLb = new JLabel("Director: ");
		c.gridx = 0;
		c.gridy = 2;
		this.add(creatorLb, c);
		JTextField creatorTF = new JTextField(15);
		this.creator = creatorTF;
		c.gridx = 1;
		this.add(creatorTF, c);
		JLabel genreLb = new JLabel("Genre");
		c.gridx = 0;
		c.gridy = 3;
		this.add(genreLb, c);
		JComboBox<String> genreCB = new JComboBox<String>(controller.gettingMovieGenres());
		c.gridx = 1;
		this.genre = genreCB;
		this.add(genreCB, c);

		//creating buttons
		JButton registerBtn = new JButton("Add New Movie");
		registerBtn.addActionListener(controller);
		registerBtn.setActionCommand("addMovie");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20,10,0,10);
		this.add(registerBtn, c);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(controller);
		c.gridx = 1;
		this.add(cancelBtn, c);	
		JLabel errorLbl = new JLabel("# Ultra-Vision #");
		this.errorLbl = errorLbl;
		c.gridwidth = 2;
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 5;
		this.add(errorLbl, c);	


		this.validate();
		this.repaint();

	}

	//clears the frame and adds the labels and TextFields to get new live concert info
	public void addConcert() {

		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 0, 0, 10);
		c.anchor = GridBagConstraints.WEST;

		JLabel titleLbl = new JLabel("Title:");
		this.add(titleLbl, c);
		JTextField titleTF = new JTextField(15);
		this.title = titleTF;
		c.gridx = 1;
		this.add(titleTF, c);
		JLabel releaseLbl = new JLabel("Year of Release:");
		c.gridx = 0;
		c.gridy = 1;
		this.add(releaseLbl, c);
		JTextField releaseTF = new JTextField(15);
		this.release = releaseTF;
		c.gridx = 1;
		this.add(releaseTF, c);
		JLabel creatorLb = new JLabel("Band or Artist: ");
		c.gridx = 0;
		c.gridy = 2;
		this.add(creatorLb, c);
		JTextField creatorTF = new JTextField(15);
		this.creator = creatorTF;
		c.gridx = 1;
		this.add(creatorTF, c);
		JLabel genreLb = new JLabel("Genre");
		c.gridx = 0;
		c.gridy = 3;
		this.add(genreLb, c);
		JComboBox<String> genreCB = new JComboBox<String>(controller.gettingMusicGenres());
		c.gridx = 1;
		this.genre = genreCB;
		this.add(genreCB, c);

		//creating buttons
		JButton registerBtn = new JButton("Add Live Concert");
		registerBtn.addActionListener(controller);
		registerBtn.setActionCommand("addConcert");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20,10,0,10);
		this.add(registerBtn, c);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(controller);
		c.gridx = 1;
		this.add(cancelBtn, c);	
		JLabel errorLbl = new JLabel("# Ultra-Vision #");
		this.errorLbl = errorLbl;
		c.gridwidth = 2;
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 5;
		this.add(errorLbl, c);	


		this.validate();
		this.repaint();

	}

	//clears the frame and adds the labels and TextFields to get new movie's info
	public void addTV() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 0, 0, 10);
		c.anchor = GridBagConstraints.WEST;

		JLabel titleLbl = new JLabel("Title:");
		this.add(titleLbl, c);
		JTextField titleTF = new JTextField(15);
		this.title = titleTF;
		c.gridx = 1;
		this.add(titleTF, c);
		JLabel releaseLbl = new JLabel("Year of Release:");
		c.gridx = 0;
		c.gridy = 1;
		this.add(releaseLbl, c);
		JTextField releaseTF = new JTextField(15);
		this.release = releaseTF;
		c.gridx = 1;
		this.add(releaseTF, c);
		JLabel creatorLb = new JLabel("Number of Seasons: ");
		c.gridx = 0;
		c.gridy = 2;
		this.add(creatorLb, c);
		JTextField creatorTF = new JTextField(15);
		this.creator = creatorTF;
		c.gridx = 1;
		this.add(creatorTF, c);
		JLabel genreLb = new JLabel("Genre");
		c.gridx = 0;
		c.gridy = 3;
		this.add(genreLb, c);
		JComboBox<String> genreCB = new JComboBox<String>(controller.gettingMovieGenres());
		c.gridx = 1;
		this.genre = genreCB;
		this.add(genreCB, c);

		//creating buttons
		JButton registerBtn = new JButton("Add TV Box Set");
		registerBtn.addActionListener(controller);
		registerBtn.setActionCommand("addTV");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20,10,0,10);
		this.add(registerBtn, c);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(controller);
		c.gridx = 1;
		this.add(cancelBtn, c);	
		JLabel errorLbl = new JLabel("# Ultra-Vision #");
		this.errorLbl = errorLbl;
		c.gridwidth = 2;
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 5;
		this.add(errorLbl, c);	

		this.validate();
		this.repaint();

	}


	//create main settings for the frame
	private void settings() {
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(350,300);
		this.validate();
		this.repaint();

	}

	//getters
	public String getMediaTitle() {
		return this.title.getText();
	}
	public String getYear() {
		return this.release.getText();
	}
	public String getCreator() {
		return this.creator.getText();
	}
	public String getGenre() {
		return (String) this.genre.getSelectedItem();
	}
	public JLabel getErrorLb() {
		return this.errorLbl;
	}



}
