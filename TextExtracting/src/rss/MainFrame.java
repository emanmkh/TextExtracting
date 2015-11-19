package rss;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;

import java.util.*;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBox;



import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Window.Type;

import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Label;
import java.sql.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.syndication.io.FeedException;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import java.awt.Choice;
import javax.swing.JComboBox;
import java.awt.List;
import javax.swing.DefaultComboBoxModel;
import javax.xml.stream.XMLStreamException;

import java.awt.Button;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.BoxLayout;






@SuppressWarnings({ "rawtypes", "unchecked"  , "unused"})
public class MainFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private static JTabbedPane tabbedPane ;
	private JPanel addPanel = new JPanel();
	private JPanel extractPanel = new JPanel();
    private	JList list = new JList();;
    private JFileChooser fileChooser, fileChooser2;
    private File[] classRoot, trainSelectedClasses = null, testSelectedClasses = null;
    private Vector[] trainClassFiles=null, testClassFiles=null, termsOfClass = null;
    private DefaultListModel<String> listModel1, listModel2, listModel3;
    private String path="";
    private File file=null, file2=null;
    private JTextField textFieldLink;
    private JTextField textFieldDesc;
    private JTextField textFieldTag;
    private JTextField textFieldAttr;
    private JTextField textFieldAttrVal;
    private JComboBox comboBoxDesc = new JComboBox();
    JComboBox comboBoxCon = new JComboBox();
   // private static Connection conc = null;
    private JFileChooser dir = new JFileChooser();
    private RssFeed rs;
    private TextExtracting te;
	//private Statement stmt = null;
	//private ResultSet rs;
	//private PreparedStatement ps;
	private JTextField textFieldDir;
	private JComboBox comboBoxMed = new JComboBox();
	private JTextField textFieldAuth;
	

    
	public MainFrame() {
		  try {
			 frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			  frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        this.addWindowListener(new WindowAdapter() {
//		                    @Override
//							public void windowOpened(WindowEvent e) {
//		                        this_windowOpened(e);
//		                    }
//		                });
			 
	            jbInit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	

	
	private void jbInit() {
     
		frame.getContentPane().setLayout(null);
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.setBounds(0, 36, 444, 346);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		extractPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		
		tabbedPane.addTab("استخلاص النصوص", null, extractPanel, null);
		
	
		
		JButton extractButton = new JButton("\u0627\u0633\u062A\u062E\u0644\u0627\u0635 \u0627\u0644\u0646\u0635\u0648\u0635");
		extractButton.setFont(new Font("Dialog", Font.BOLD, 13));
		extractButton.setBounds(10, 280, 142, 23);
		extractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					extractButton_actionPerformed(e);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
			extractPanel.setLayout(null);
			extractPanel.add(extractButton);
			
			
			textFieldDir = new JTextField();
			textFieldDir.setFont(new Font("Dialog", Font.PLAIN, 13));
			textFieldDir.setBounds(76, 53, 260, 20);
			extractPanel.add(textFieldDir);
			textFieldDir.setColumns(10);
			
			
			
			JLabel label_5 = new JLabel("\u062D\u0641\u0638 \u0627\u0644\u0645\u0644\u0641\u0627\u062A \u0641\u064A");
			label_5.setHorizontalAlignment(SwingConstants.RIGHT);
			label_5.setFont(new Font("Dialog", Font.BOLD, 13));
			label_5.setBounds(336, 53, 89, 22);
			extractPanel.add(label_5);
			comboBoxDesc.setFont(new Font("Dialog", Font.PLAIN, 12));
			comboBoxDesc.setModel(new DefaultComboBoxModel(new String[] {"\u0627\u062E\u062A\u0631"}));
			comboBoxDesc.setBounds(217, 31, 119, 20);
			comboBoxDesc.setMaximumRowCount(10);
			comboBoxDesc.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			extractPanel.add(comboBoxDesc);
			
			JLabel lblNewLabel = new JLabel("\u0627\u0644\u062E\u0644\u0627\u0635\u0629");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setBounds(377, 33, 48, 14);
			extractPanel.add(lblNewLabel);
			
			JButton browseButton = new JButton("استعراض");
			browseButton.setFont(new Font("Dialog", Font.BOLD, 11));
			browseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					browseButton_actionPerformed(arg0) ;
				}
				
			});
			browseButton.setBounds(0, 52, 72, 23);
			extractPanel.add(browseButton);
		addPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0645\u0639\u0644\u0648\u0645\u0627\u062A \u0627\u0644\u062E\u0644\u0627\u0635\u0629", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		tabbedPane.addTab("إضافة الخلاصات", null, addPanel, null);
		
		textFieldTag = new JTextField();
		textFieldTag.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldTag.setBounds(206, 88, 126, 20);
		textFieldTag.setColumns(15);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldDesc.setBounds(166, 62, 166, 20);
		textFieldDesc.setColumns(20);
		textFieldDesc.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		Label label_3 = new Label("HTML attribute");
		label_3.setBounds(338, 111, 87, 22);
		label_3.setFont(new Font("Dialog", Font.BOLD, 11));
		
		textFieldLink = new JTextField();
		textFieldLink.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldLink.setBounds(44, 36, 288, 20);
		textFieldLink.setColumns(30);
		
		Label label_2 = new Label("HTML tag");
		label_2.setBounds(368, 88, 57, 22);
		label_2.setFont(new Font("Dialog", Font.BOLD, 11));
		
		textFieldAttr = new JTextField();
		textFieldAttr.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldAttr.setBounds(206, 111, 126, 20);
		textFieldAttr.setColumns(15);
		
		
		Label label_4 = new Label("Attribute value");
		label_4.setBounds(338, 136, 87, 22);
		label_4.setFont(new Font("Dialog", Font.BOLD, 11));
		
		textFieldAttrVal = new JTextField();
		textFieldAttrVal.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldAttrVal.setBounds(206, 136, 126, 20);
		textFieldAttrVal.setColumns(15);
		addPanel.setLayout(null);
		addPanel.add(label_3);
		addPanel.add(textFieldDesc);
		addPanel.add(textFieldTag);
		addPanel.add(textFieldAttrVal);
		addPanel.add(label_4);
		addPanel.add(textFieldAttr);
		addPanel.add(label_2);
		addPanel.add(textFieldLink);
		
		JButton addButton = new JButton("\u062A\u0646\u0641\u064A\u0630");
		addButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		addButton.setBounds(10, 280, 142, 23);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addButton_actionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addPanel.add(addButton);
		fillComboBox();
		frame.setSize(new Dimension(464, 428));
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//  listModel2 = new DefaultListModel();
		 listModel1 = new DefaultListModel();
       
		fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fileChooser.setMultiSelectionEnabled(false);
	    fileChooser.setSize(new Dimension(400, 300));
		
	    fileChooser2 = new JFileChooser();
	    fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fileChooser2.setMultiSelectionEnabled(false);
	    fileChooser2.setSize(new Dimension(400, 300));
		
		JLabel lblNewLabel_7 = new JLabel("\u0627\u0644\u0645\u0635\u062F\u0631");
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(377, 164, 46, 14);
		addPanel.add(lblNewLabel_7);
		
		textFieldAuth = new JTextField();
		textFieldAuth.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldAuth.setFont(new Font("Dialog", Font.PLAIN, 13));
		textFieldAuth.setBounds(166, 161, 166, 20);
		addPanel.add(textFieldAuth);
		textFieldAuth.setColumns(10);
		textFieldAuth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JLabel label_10 = new JLabel("\u0627\u0644\u0648\u0639\u0627\u0621");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Dialog", Font.BOLD, 13));
		label_10.setBounds(377, 189, 46, 14);
		addPanel.add(label_10);
		
		JLabel label_11 = new JLabel("\u0627\u0644\u0628\u0644\u062F");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("Dialog", Font.BOLD, 13));
		label_11.setBounds(377, 215, 46, 14);
		addPanel.add(label_11);
		
		comboBoxCon.setModel(new DefaultComboBoxModel(new String[] {"\u0627\u062E\u062A\u0631"}));
		comboBoxCon.setBounds(193, 213, 139, 20);
		addPanel.add(comboBoxCon);
		fillComboBoxCon();
		comboBoxCon.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		comboBoxMed.setModel(new DefaultComboBoxModel(new String[] {"\u0627\u062E\u062A\u0631"}));
		
		
		comboBoxMed.setBounds(193, 187, 139, 20);
		addPanel.add(comboBoxMed);
		comboBoxMed.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		fillComboBoxMed();
		JLabel label_6 = new JLabel("الرابط");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.BOLD, 13));
		label_6.setVerticalAlignment(SwingConstants.TOP);
		label_6.setBounds(379, 37, 46, 19);
		addPanel.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("الوصف");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(379, 65, 46, 14);
		addPanel.add(lblNewLabel_1);
	
		
		
//		Timer timer = new Timer( 1000, new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent ae) {
//	        	try {
//					extractButton_actionPerformed(ae);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	        }
//	    });
		
		
		
		
	}


	private void extractButton_actionPerformed(ActionEvent e) throws Exception {
		String output;
	
 	try{
		 te = new TextExtracting(comboBoxDesc.getSelectedItem().toString(), textFieldDir.getText());
		
		 output = te.createRssFeedFiles();
		 System.out.println(output);
	 
		} catch (Exception e1) {
		
			System.out.println(e1.getMessage());
		 
		}
	}
	 
	private void browseButton_actionPerformed(ActionEvent e) {
		fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.CANCEL_OPTION)
            return;
            
       
        file =fileChooser.getSelectedFile();
        textFieldDir.setText(file.getAbsolutePath());
   //     for (int i=0; i<classRoot.length; i++) {
   //    listModel1.addElement(classRoot[i].getPath());
  //      }
    }
	
	private void addButton_actionPerformed(ActionEvent e) throws IOException {
     
		String msg;
		
	try{
		
	
		//	progressLbl.setVisible(true);
			rs = new RssFeed(textFieldLink.getText(), textFieldDesc.getText(), textFieldTag.getText(), textFieldAttr.getText(),
					textFieldAttrVal.getText(), textFieldAuth.getText(), comboBoxMed.getSelectedItem().toString(),comboBoxCon.getSelectedItem().toString());
			
			msg = rs.insert();
		
			
	
			comboBoxDesc.removeAllItems();
			comboBoxDesc.addItem("اختر");
			fillComboBox();
			System.out.println(msg);
		
   
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
		}
     
	 }
	    
 public void fillComboBox(){
	 ResultSet rs_fill = null;
	 Connection con_fill = null;
	 PreparedStatement ps_fill = null;
	 int id;
	 String element;
	 
	 try { 
		 
		 con_fill =  SqliteConnection.getConn();
		
		 String sql = "SELECT id_rss_feed_metadata, rss_description FROM rss_feed_metadata";
		 
		 ps_fill= con_fill.prepareStatement(sql);
		 rs_fill= ps_fill.executeQuery();
		 while(rs_fill.next()){	 
			// comboBoxDesc.insertItemAt(rs.getString(2),rs.getInt(1));
			 comboBoxDesc.addItem(rs_fill.getString(2));
			 
		 }
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
 }
 public void fillComboBoxCon(){
	 ResultSet rs_con = null;
	 Connection conn_fill = null;
	 PreparedStatement ps_con = null;
	 int id;
	 String element;
	 
	 try { 
		 
		 conn_fill =  SqliteConnection.getConn();
		
		 String sql = "SELECT * FROM region_country";
		 
		 ps_con= conn_fill.prepareStatement(sql);
		 rs_con= ps_con.executeQuery();
		 while(rs_con.next()){	 
			 comboBoxCon.addItem(rs_con.getString(2));
			
		 }
		 rs_con.close(); 
		ps_con.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//append(e.getMessage(),Color.RED);
	}
 }
 public void fillComboBoxMed(){
	 ResultSet rs_med = null;
	 Connection conn_fill = null;
	 PreparedStatement ps_med = null;
	 int id;
	 String element;
	 
	 try { 
		 
		 conn_fill =  SqliteConnection.getConn();
		
		 String sql = "SELECT * FROM rss_feed_medium";
		 
		 ps_med= conn_fill.prepareStatement(sql);
		 rs_med= ps_med.executeQuery();
		 while(rs_med.next()){	 
			 comboBoxMed.addItem(rs_med.getString(2));
			
			 
			
		 }
		 rs_med.close(); 
		 ps_med.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//append(e.getMessage(),Color.RED);
	}
 }
 

		public static void main(String[] args) {
			
			 EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame window = new MainFrame();
						window.frame.setVisible(true);
						 
					} catch (Exception e) {
						
					
				
						System.out.println(e.getMessage());
					
					}
				}
			});
		}
}

	 

