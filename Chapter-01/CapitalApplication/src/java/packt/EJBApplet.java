package packt;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.naming.InitialContext;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextField;

public class EJBApplet extends JApplet {
	private JButton invokeButton = new JButton("Invoke EJB");
	private JTextField messageTextField = new JTextField("Waiting for results");

	public void init() {
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					initComponents();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initComponents() {
		Container container = this.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(invokeButton);
		container.add(messageTextField);

		invokeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				invokeEJB();
			}
		});
	}

	public void invokeEJB() {
		try {
			InitialContext context =new InitialContext();
			CapitalBeanRemote bean = (CapitalBeanRemote)
	context.lookup("java:global/CapitalApplication/CapitalBean");
			messageTextField.setText(bean.getCapital("Japan"));
          context.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
