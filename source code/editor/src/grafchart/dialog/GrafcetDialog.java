package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Utils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.soda.dpws.DPWSException;
import org.soda.dpws.DPWSNetworks;
import org.soda.dpws.LocalIPAddress;

/**
 * Application properties
 * @author dimon
 */
public class GrafcetDialog
  extends MyJDialog
{
  private MyJTextField tApplicationName = new MyJTextField();
  private MyJTextField tScanCycleTime = new MyJTextField();
  private JCheckBox cbSimulation = new JCheckBox();
  private JCheckBox cbDimming = new JCheckBox();
  private MyJTextField tDimRate = new MyJTextField();
  private JCheckBox modifyBox = new JCheckBox();
  private JCheckBox cbIsSocketServer = new JCheckBox();
  private MyJTextField hostField = new MyJTextField();
  private MyJTextField portField = new MyJTextField();
  private JComboBox cbDPWSInterface = new JComboBox();
  private MyJTextField tDPWSPort = new MyJTextField();
  private GCDocument myObject;
  private GCView myView;
  
  public GrafcetDialog(Frame paramFrame, GCDocument paramGCDocument, GCView paramGCView)
  {
    super(paramFrame, "Application Properties");
    this.myObject = paramGCDocument;
    this.myView = paramGCView;
    init();
    pack();
    setLocationRelativeTo(paramFrame);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 620));
    localJPanel.setPreferredSize(new Dimension(294, 620));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 580, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 580, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Application Name");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tApplicationName.setBounds(new Rectangle(50, 40, 200, 24));
    this.tApplicationName.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tApplicationName);
    JLabel localJLabel2 = new JLabel("Scan Cycle Time (ms)");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 80, 200, 24));
    localJPanel.add(localJLabel2);
    this.tScanCycleTime.setBounds(new Rectangle(50, 110, 200, 24));
    this.tScanCycleTime.setEnabled(true);
    localJPanel.add(this.tScanCycleTime);
    this.cbSimulation.setText("Simulator Mode");
    this.cbSimulation.setBounds(new Rectangle(50, 150, 220, 14));
    localJPanel.add(this.cbSimulation);
    this.cbDimming.setText("Token luminance");
    this.cbDimming.setBounds(new Rectangle(50, 180, 220, 14));
    this.cbDimming.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        GrafcetDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.cbDimming);
    JLabel localJLabel3 = new JLabel("Luminance scan cycles");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(50, 210, 220, 24));
    localJPanel.add(localJLabel3);
    this.tDimRate.setBounds(new Rectangle(50, 240, 200, 24));
    this.tDimRate.setEnabled(true);
    localJPanel.add(this.tDimRate);
    JLabel localJLabel4 = new JLabel("Socket server");
    localJLabel4.setBounds(new Rectangle(50, 270, 220, 24));
    localJLabel4.setHorizontalAlignment(2);
    localJPanel.add(localJLabel4);
    this.hostField.setBounds(new Rectangle(50, 300, 200, 24));
    this.hostField.setEnabled(true);
    localJPanel.add(this.hostField);
    this.cbIsSocketServer.setText("Be socket server");
    this.cbIsSocketServer.setBounds(new Rectangle(50, 335, 220, 14));
    this.cbIsSocketServer.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        GrafcetDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.cbIsSocketServer);
    JLabel localJLabel5 = new JLabel("Socket port");
    localJLabel5.setBounds(new Rectangle(50, 360, 220, 24));
    localJLabel5.setHorizontalAlignment(2);
    localJPanel.add(localJLabel5);
    this.portField.setBounds(new Rectangle(50, 390, 200, 24));
    this.portField.setEnabled(true);
    localJPanel.add(this.portField);
    JLabel localJLabel6 = new JLabel("DPWS Event Interface");
    localJLabel6.setBounds(new Rectangle(50, 420, 200, 24));
    localJPanel.add(localJLabel6);
    this.cbDPWSInterface.removeAllItems();
    this.cbDPWSInterface.addItem("");
    try
    {
      Enumeration localEnumeration = NetworkInterface.getNetworkInterfaces();
      while (localEnumeration.hasMoreElements())
      {
        NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration.nextElement();
        this.cbDPWSInterface.addItem(new MyNetworkInterface(localNetworkInterface));
      }
    }
    catch (SocketException localSocketException)
    {
      Utils.writeError("Unable to list network interfaces.");
    }
    this.cbDPWSInterface.setBounds(new Rectangle(50, 450, 220, 24));
    localJPanel.add(this.cbDPWSInterface);
    JLabel localJLabel7 = new JLabel("DPWS Event Port");
    localJLabel7.setBounds(new Rectangle(50, 480, 200, 24));
    localJPanel.add(localJLabel7);
    this.tDPWSPort.setBounds(new Rectangle(50, 510, 200, 24));
    localJPanel.add(this.tDPWSPort);
    this.modifyBox.setText("Locked");
    this.modifyBox.setBounds(new Rectangle(50, 540, 200, 24));
    localJPanel.add(this.modifyBox);
  }
  
  void updateDialog()
  {
    this.tApplicationName.setText(this.myObject.getName());
    this.tScanCycleTime.setText(new Integer(this.myObject.getSpeed()).toString());
    this.cbSimulation.setSelected(this.myObject.simulation);
    this.cbDimming.setSelected(this.myObject.dimming);
    this.tDimRate.setText(new Integer(this.myObject.dimTicks).toString());
    this.hostField.setText(this.myObject.host);
    this.cbIsSocketServer.setSelected(this.myObject.isSocketServer());
    this.portField.setText(new Integer(this.myObject.port).toString());
    String str = this.myObject.getDPWSInterface();
    if (!str.equals(""))
    {
      this.cbDPWSInterface.setSelectedIndex(0);
      for (int i = 1; i < this.cbDPWSInterface.getItemCount(); i++)
      {
        MyNetworkInterface localMyNetworkInterface = (MyNetworkInterface)this.cbDPWSInterface.getItemAt(i);
        if (localMyNetworkInterface.getName().equals(str))
        {
          this.cbDPWSInterface.setSelectedIndex(i);
          break;
        }
      }
      if (this.cbDPWSInterface.getSelectedIndex() == 0) {
        Utils.writeWarning("Stored DPWS Subscription Interface could not be found. (" + str + ")");
      }
    }
    this.tDPWSPort.setText(new Integer(this.myObject.getDPWSPort()).toString());
    this.modifyBox.setSelected(!this.myObject.isModifiable());
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.tDimRate.setEnabled(this.cbDimming.isSelected());
    this.hostField.setEnabled(!this.cbIsSocketServer.isSelected());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    int i = parseInt(this.tScanCycleTime, 1);
    int j = parseInt(this.tDimRate, 1);
    int k = parseInt(this.portField);
    int m = parseInt(this.tDPWSPort);
    this.myObject.setName(this.tApplicationName.getText());
    this.myView.updateTitle();
    this.myObject.setModificationProperty(!this.modifyBox.isSelected());
    this.myObject.setSpeed(i);
    this.myObject.simulation = this.cbSimulation.isSelected();
    this.myObject.propagateDimmingInfo(this.cbDimming.isSelected(), j, i);
    this.myObject.host = this.hostField.getText();
    this.myObject.port = k;
    this.myObject.setSocketServer(this.cbIsSocketServer.isSelected());
    if (this.cbDPWSInterface.getSelectedIndex() != 0) {
      this.myObject.setDPWSInterface(((MyNetworkInterface)this.cbDPWSInterface.getSelectedItem()).getName());
    } else {
      this.myObject.setDPWSInterface("");
    }
    this.myObject.setDPWSPort(m);
  }
  
  private class MyNetworkInterface
  {
    private NetworkInterface networkInterface;
    
    public MyNetworkInterface(NetworkInterface paramNetworkInterface)
    {
      this.networkInterface = paramNetworkInterface;
    }
    
    public String toString()
    {
      String str = "N/A";
      Enumeration localEnumeration = this.networkInterface.getInetAddresses();
      LocalIPAddress localLocalIPAddress = null;
      while ((localEnumeration.hasMoreElements()) && (localLocalIPAddress == null))
      {
        InetAddress localInetAddress = (InetAddress)localEnumeration.nextElement();
        if ((localInetAddress instanceof Inet4Address)) {
          try
          {
            localLocalIPAddress = DPWSNetworks.getLocalIPAddress(localInetAddress);
          }
          catch (DPWSException localDPWSException)
          {
            Utils.writeException(localDPWSException, "getLocalIPAddress() failed.");
            str = "ERROR";
          }
        }
      }
      if (localLocalIPAddress != null) {
        str = localLocalIPAddress.getAddress().getHostAddress();
      }
      return this.networkInterface.getName() + " : " + str;
    }
    
    public String getName()
    {
      return this.networkInterface.getName();
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/GrafcetDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */