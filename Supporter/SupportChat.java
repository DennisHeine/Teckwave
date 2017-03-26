import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * Created by JFormDesigner on Mon Jun 25 03:05:10 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class SupportChat extends JFrame {

	private String mUsername, mPasswort;

	public void Append(String Text)
	{
		tbChatlog.append(Text);
	}
	
	public void Clear()
	{
		tbChatlog.setText("");
	}

	public SupportChat(String Username, String Passwort) 
	{
		mUsername=Username;
		mPasswort=Passwort;
		initComponents();	
		update=new ChatUpdateThrd(this,mUsername, mPasswort);
		update.start();	
	}

	public void tbChatTextKeyListener(KeyEvent e)
	{
		if(e.getKeyCode()==e.VK_ENTER)
		{
			Protokoll mProtokoll=new Protokoll(mUsername, mPasswort);
			mProtokoll.EnterText(tbChatText.getText());
			tbChatText.setText("");
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		ResourceBundle bundle = ResourceBundle.getBundle("Support.SupportChat");
		scrollPane1 = new JScrollPane();
		tbChatlog = new JTextArea();
		tbChatText = new JTextField();
		label1 = new JLabel();
		lblUsername = new JLabel();
		bnBenutzerdaten = new JButton();

		//======== this ========
		setTitle(bundle.getString("this.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(tbChatlog);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(10, 35, 460, 300);
		contentPane.add(tbChatText);
		tbChatText.setBounds(10, 340, 460, tbChatText.getPreferredSize().height);
		tbChatText.addKeyListener(new KeyListener() {
					public void keyReleased(KeyEvent e){
						tbChatTextKeyListener(e);
					}
					public void keyPressed(KeyEvent e){
						tbChatTextKeyListener(e);
					}
					public void keyTyped(KeyEvent e){
						tbChatTextKeyListener(e);
					}
				});

	

		//---- label1 ----
		label1.setText(bundle.getString("label1.text"));
		contentPane.add(label1);
		label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));

		//---- lblUsername ----
		lblUsername.setText(bundle.getString("lblUsername.text"));
		lblUsername.setForeground(Color.blue);
		contentPane.add(lblUsername);
		lblUsername.setBounds(60, 10, 220, lblUsername.getPreferredSize().height);

		//---- bnBenutzerdaten ----
		bnBenutzerdaten.setText(bundle.getString("bnBenutzerdaten.text"));
		contentPane.add(bnBenutzerdaten);
		bnBenutzerdaten.setBounds(new Rectangle(new Point(315, 5), bnBenutzerdaten.getPreferredSize()));

		((JComponent)contentPane).setPreferredSize(new Dimension(495, 405));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private JScrollPane scrollPane1;
	private JTextArea tbChatlog;
	private JTextField tbChatText;
	private JLabel label1;
	private JLabel lblUsername;
	private JButton bnBenutzerdaten;
	private ChatUpdateThrd update;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
