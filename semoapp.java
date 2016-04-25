package semoapp;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class semoapp
{
	public static String datee;
	public static String year;
	
  JTextField t1 = new JTextField("email");
  JTextField t2 = new JPasswordField("password");
  JTextField t3 = new JTextField("C://Sem-o_Archive/XML/");
  JLabel l1 = new JLabel("File Type: ");
  JLabel l2 = new JLabel("Username: ");
  JLabel l3 = new JLabel("Password: ");
  JLabel l4 = new JLabel("Start Date: ");
  JLabel l5 = new JLabel("End Date: ");
  JLabel l6 = new JLabel("YYYY/MM/DD");
  JLabel l7 = new JLabel("Download Directory: ");
  String[] comboBoxOptions = { "Demand Offer", "Market Schedule Quantities", "Actual Availability", "Daily Actual Load", "Dispatch Quantity", "Market Schedule Quantity", "Generator Offers", "Interconnector Offer", "Market Prices and Load Results", "Merket SMP Results", "Capacity Information", "Capacity Payments", "Metered Generation by Unit", "Metered Generation by Jurisdiction", "Daily Dispatch Instructions", "Settlements Calendar" };
  String convertedComboResult = "";
  String p1Option = "";
  String name;
  JComboBox c1 = new JComboBox(this.comboBoxOptions);
  DateFormat format = new SimpleDateFormat("yyyy_MM_dd");
  JTextField dateTextField2 = new JTextField(datee);
  JTextField dateTextField1 = new JTextField(year + "/01/01");
  JButton b1 = new JButton("Download");
  
  public semoapp()
  {
    frame();
  }
  
  protected MaskFormatter createFormatter(String s)
  {
    MaskFormatter formatter = null;
    try
    {
      formatter = new MaskFormatter(s);
      formatter.setPlaceholderCharacter('_');
    }
    catch (ParseException e) {}
    return formatter;
  }
  
  public void frame()
  {
    JFrame f = new JFrame();
    f.setVisible(true);
    f.setSize(700, 200);
    f.setDefaultCloseOperation(3);
    
    JPanel p1 = new JPanel(new FlowLayout());
    p1.add(this.l2);
    p1.add(this.t1);
    p1.add(this.l3);
    p1.add(this.t2);
    
    JPanel p2 = new JPanel(new FlowLayout());
    p2.add(this.l1);
    p2.add(this.c1);
    p2.add(this.l4);
    p2.add(this.dateTextField1);
    p2.add(this.l5);
    p2.add(this.dateTextField2);
    p2.add(this.l6);
    
    JPanel p3 = new JPanel(new FlowLayout());
    this.b1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String username = semoapp.this.t1.getText();
        String password = semoapp.this.t2.getText();
        String comboBox = (String)semoapp.this.c1.getSelectedItem();
        if (comboBox.equals("Demand Offer Data"))
        {
          semoapp.this.convertedComboResult = "DEMAND_OFFER";
        }
        else if (comboBox.equals("Market Schedule Quantities"))
        {
          semoapp.this.convertedComboResult = "EA_MSQ";
          semoapp.this.p1Option = "EP2";
          name = "Market Schedule Quantities";
        }
        else if (comboBox.equals("Actual Availability"))
        {
          semoapp.this.convertedComboResult = "EP2_AA";
          semoapp.this.p1Option = "EP2";
          name = "Actual Availability_";
        }
        else if (comboBox.equals("Daily Actual Load"))
        {
          semoapp.this.convertedComboResult = "EP2_DAL";
        }
        else if (comboBox.equals("Dispatch Quantity"))
        {
          semoapp.this.convertedComboResult = "EP2_DQ";
          semoapp.this.p1Option = "EP2";
          name = "Dispatch Quantity_";
        }
        else if (comboBox.equals("Market Schedule Quantity"))
        {
          semoapp.this.convertedComboResult = "EP2_MSQ";
        }
        else if (comboBox.equals("Generator Offers"))
        {
          semoapp.this.convertedComboResult = "GENERATOR_OFFER";
        }
        else if (comboBox.equals("Interconnector Offer"))
        {
          semoapp.this.convertedComboResult = "INTERCONNECTOR_OFFER";
        }
        else if (comboBox.equals("Market Prices and Load Results"))
        {
          semoapp.this.convertedComboResult = "EP2_RESULTS";
        }
        else if (comboBox.equals("Market SMP Results"))
        {
          semoapp.this.convertedComboResult = "EP2_SMP";
        }
        else if (comboBox.equals("Capacity Information"))
        {
          semoapp.this.convertedComboResult = "CD_INFORMATION";
        }
        else if (comboBox.equals("Capacity Payments"))
        {
          semoapp.this.convertedComboResult = "CD_PAYMENTS";
        }
        else if (comboBox.equals("Metered Generation by Unit"))
        {
          semoapp.this.convertedComboResult = "MG_UNIT";
          semoapp.this.p1Option = "EP2";
          name = "Metered Generation by Unit_";
        }
        else if (comboBox.equals("Metered Generation by Jurisdiction"))
        {
          semoapp.this.convertedComboResult = "MG_JURST";
        }
        else if (comboBox.equals("Daily Dispatch Instructions"))
        {
          semoapp.this.convertedComboResult = "DI_D3";
        }
        else if (comboBox.equals("Settlements Calendar"))
        {
          semoapp.this.convertedComboResult = "SET_CAL";
        }
        else
        {
          semoapp.this.convertedComboResult = "EP2_DAL";
        }
        String sDate = semoapp.this.dateTextField1.getText();
        String sDateNew = sDate.replace("/", "");
        
        String eDate = semoapp.this.dateTextField2.getText();
        String eDateNew = eDate.replace("/", "");
        String dDir = semoapp.this.t3.getText();
        
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
        DateTime dtStart = fmt.parseDateTime(sDateNew);
        DateTime dtEnd = fmt.parseDateTime(eDateNew);
        dtEnd = dtEnd.plusDays(1);
        
        DateTime endCurrent = new DateTime(dtStart);
     
        String ss1;
        do
        {
        	String finalURL = "http://semorep.sem-o.com/DataCollection/Datasets.asmx/queryDatasetXML?DatasetName=" + semoapp.this.convertedComboResult + "&User=" + username + "&Password=" + password + "&FromDate=" + dtStart.toString("yyyyMMdd") + "&ToDate=" + endCurrent.toString("yyyyMMdd") + "&P1=" + semoapp.this.p1Option + "&P2=&P3=&P4=&p5=";
        	try
        	{
        		File fname = new File(dDir + "/" + name + dtStart.toString("dd_MM_yy") + ".xml");
        		if (!fname.exists()){
        			semoapp.download(finalURL, dDir, dtStart.toString("dd_MM_yy"), name);
        		}
        	} //semoapp.this.convertedComboResult
        	catch (IOException e1)
        	{
        		e1.printStackTrace();
         	}
        	dtStart = dtStart.plusDays(1);
        	String ss = fmt.print(dtStart);
        	endCurrent = endCurrent.plusDays(1);
        	ss1 = fmt.print(endCurrent);
        } while (endCurrent.isBefore(dtEnd));
        	System.exit(0);
      	}
    	});
    p3.add(this.l7);
    p3.add(this.t3);
    p3.add(this.b1);
    
    JPanel mainPanel = new JPanel(new GridLayout(3, 1));
    mainPanel.add(p1);
    mainPanel.add(p2);
    mainPanel.add(p3);
    
    f.setLayout(new FlowLayout(3));
    f.add(mainPanel);
  }
  
  public static void download(String url, String fileVar, String fileVar1, String fileVar2)
    throws IOException
  {
	CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet(url);
    CloseableHttpResponse response = httpclient.execute(httpget);
    try
    {
      HttpEntity entity = response.getEntity();
      if (entity != null)
      {
        InputStream instream = new BufferedInputStream(entity.getContent());
        try
        {
	        	File ffname = new File(fileVar + "/" + fileVar2 + fileVar1 + ".xml");
	        	FileOutputStream fos = new FileOutputStream(ffname);
	        	ByteArrayOutputStream outstream = new ByteArrayOutputStream();
	        	entity.writeTo(outstream);
	        	outstream.writeTo(fos);
	        	fos.close();
	        	outstream.close();
        	
        }
        finally
        {
          instream.close();
        }
      }
    }
    finally
    {
      response.close();
    }
  }
  
  
  public static void main(String[] args)
    throws IOException
  {
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		  //get current date time with Date()
		  Date date1 = new Date();
		  datee = dateFormat1.format(date1);
		  DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
		  //get current date time with Date()
		  Date date2 = new Date();
		  year = dateFormat2.format(date2);
		  new semoapp();
		  
  }
}

