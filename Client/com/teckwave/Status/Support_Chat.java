package com.teckwave.Status;

import com.teckwave.Protokoll.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Sun Jun 24 00:14:35 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class Support_Chat extends JDialog {

	String Username, Passwort, IDSession;
	UpdateThread Thrd;
	CSupport mCSupport;
	int ChatCount=0;
	private class UpdateThread extends Thread
	{
		UpdateThread(){}
		
		public void run()
		{
			do
			{
				Update();
				try{this.sleep(1000L);}catch(Exception e){}
			}while(true);
		}
	}
	
	private void Update()
	{
		String[] Chat=mCSupport.GetChat(IDSession);
		String[] mChat=new String[Chat.length-1];
		for(int i=1;i<Chat.length;i++)
			mChat[i-1]=Chat[i];
	
		for(int i=ChatCount;i<mChat.length;i++)
		{
			tbChatlog.append(mChat[i]+"\n");
		}
		ChatCount=mChat.length;
	}

	public Support_Chat(String mUsername, String mPasswort) {
		super();
		initComponents();
		Start(mUsername, mPasswort);		
	}

	public Support_Chat(Dialog owner, String mUsername, String mPasswort) {
		super(owner);
		initComponents();
		Start(mUsername, mPasswort);
	}
	
	private void Start(String mUsername, String mPasswort)
	{
		mCSupport=new CSupport(mUsername, mPasswort);
		String[] ret;
		if(mCSupport.QuerySession())
		{	
					
			ret=mCSupport.OpenSession();
			IDSession=ret[1];
			Thrd=new UpdateThread();
			Thrd.start();			
		}
		else
		{
			
		}
	}

	private void tbTextSendenKeyReleased(KeyEvent e) {
		// TODO add your code here
		if(e.getKeyCode()==e.VK_ENTER)
		{
			String Text=tbTextSenden.getText();
			tbTextSenden.setText("");
			mCSupport.SendText(Text,IDSession);
			
		}
	}

	private void bnBeendenActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ResourceBundle bundle = ResourceBundle.getBundle("lang.Deutsch.SupportChat");
		dialogPane = new JPanel();
		scrollPane1 = new JScrollPane();
		tbChatlog = new JTextArea();
		tbTextSenden = new JTextField();
		bnBeenden = new JButton();

		//======== this ========
		setTitle(bundle.getString("this.title"));
		setModal(true);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(null);

			//======== scrollPane1 ========
			{

				//---- tbChatlog ----
				tbChatlog.setEditable(false);
				scrollPane1.setViewportView(tbChatlog);
			}
			dialogPane.add(scrollPane1);
			scrollPane1.setBounds(5, 5, 360, 165);

			//---- tbTextSenden ----
			tbTextSenden.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					tbTextSendenKeyReleased(e);
				}
			});
			dialogPane.add(tbTextSenden);
			tbTextSenden.setBounds(5, 170, 360, tbTextSenden.getPreferredSize().height);

			//---- bnBeenden ----
			bnBeenden.setText(bundle.getString("bnBeenden.text"));
			bnBeenden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bnBeendenActionPerformed(e);
				}
			});
			dialogPane.add(bnBeenden);
			bnBeenden.setBounds(new Rectangle(new Point(290, 195), bnBeenden.getPreferredSize()));

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < dialogPane.getComponentCount(); i++) {
					Rectangle bounds = dialogPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = dialogPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				dialogPane.setMinimumSize(preferredSize);
				dialogPane.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(380, 255);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private JPanel dialogPane;
	private JScrollPane scrollPane1;
	private JTextArea tbChatlog;
	private JTextField tbTextSenden;
	private JButton bnBeenden;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
