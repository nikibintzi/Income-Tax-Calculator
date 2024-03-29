package incometaxcalculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class GraphicalInterface extends JFrame {

  private JPanel contentPane;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  private JTextField txtTaxRegistrationNumber;
  private JTextField filename = new JTextField();
  private JTextField dir = new JTextField();
  private JFrame f;
  private JList<String> b;
  private String selectedTRN;
  private String[] taxpayersTRN;
  
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalInterface frame = new GraphicalInterface();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public GraphicalInterface() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 500);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(204, 204, 204));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e2) {
      e2.printStackTrace();
    }

    JTextPane textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setBackground(new Color(153, 204, 204));
    textPane.setBounds(0, 21, 433, 441);

    JPanel fileLoaderPanel = new JPanel(new BorderLayout());
    JPanel boxPanel = new JPanel(new BorderLayout());
    JPanel taxRegistrationNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    JLabel TRN = new JLabel("Give the tax registration number:");
    JTextField taxRegistrationNumberField = new JTextField(20);
    taxRegistrationNumberPanel.add(TRN);
    taxRegistrationNumberPanel.add(taxRegistrationNumberField);
    JPanel loadPanel = new JPanel(new GridLayout(1, 2));
    loadPanel.add(taxRegistrationNumberPanel);
    fileLoaderPanel.add(boxPanel, BorderLayout.NORTH);
    fileLoaderPanel.add(loadPanel, BorderLayout.CENTER);
    JCheckBox txtBox = new JCheckBox("Txt file");
    JCheckBox xmlBox = new JCheckBox("Xml file");
    JLabel l;

    txtBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        xmlBox.setSelected(false);
      }
    });

    xmlBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txtBox.setSelected(false);
      }
    });
    txtBox.doClick();
    boxPanel.add(txtBox, BorderLayout.WEST);
    boxPanel.add(xmlBox, BorderLayout.EAST);

    DefaultListModel<String> taxRegisterNumberModel = new DefaultListModel<String>();

    JList<String> taxRegisterNumberList = new JList<String>(taxRegisterNumberModel);
    taxRegisterNumberList.setBackground(new Color(153, 204, 204));
    taxRegisterNumberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    taxRegisterNumberList.setSelectedIndex(0);
    taxRegisterNumberList.setVisibleRowCount(3);

    JScrollPane taxRegisterNumberListScrollPane = new JScrollPane(taxRegisterNumberList);
    taxRegisterNumberListScrollPane.setSize(300, 300);
    taxRegisterNumberListScrollPane.setLocation(70, 100);
    contentPane.add(taxRegisterNumberListScrollPane);

    JButton btnLoadTaxpayer = new JButton("Load Taxpayer");
    
    l = new JLabel("no file selected");
    btnLoadTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String taxRegistrationNumberAndFile="";
        String taxRegistrationNumber="";
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
        chooser.setDialogTitle("Select a file to load taxpayer from.");
        chooser.setAcceptAllFileFilterUsed(false); 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt and xml files", "txt", "xml");
        chooser.addChoosableFileFilter(filter);
        int answer = chooser.showOpenDialog(null);//GraphicalInterface.this
        if (answer == JFileChooser.APPROVE_OPTION) {
          File file =chooser.getSelectedFile();
          filename.setText(file.getName());
          dir.setText(chooser.getCurrentDirectory().toString());
          taxRegistrationNumberAndFile=filename.getText(); 
          int firstIndex = taxRegistrationNumberAndFile.indexOf("_");
          taxRegistrationNumber= (filename.getText().toString()).substring(0,firstIndex);
          while (!taxRegistrationNumberAndFile.contains("INFO") && answer == 0) {
            JOptionPane.showMessageDialog(null,
                "The file must have the following format to be loaded:\n" + "[tax resistration number]_INFO.[txt or xml]. Try again.");
            answer = chooser.showOpenDialog(null);
            file =chooser.getSelectedFile();
            filename.setText(file.getName());
            dir.setText(chooser.getCurrentDirectory().toString());
            taxRegistrationNumberAndFile=filename.getText(); 
            firstIndex = taxRegistrationNumberAndFile.indexOf("_");
            taxRegistrationNumber= (filename.getText().toString()).substring(0,firstIndex);
          }
          l.setText(file.getAbsolutePath());
        }
        if (answer == JFileChooser.CANCEL_OPTION) {
          System.out.printf("IN ELSE answer: %d\n",answer);
          JOptionPane.showMessageDialog(null, "You canceled the operation and the taxpayer wasn\'t loaded.");
        }
        if (answer == 0) {
          int trn = 0;
          try {
            trn = Integer.parseInt(taxRegistrationNumber);
            if (taxpayerManager.containsTaxpayer(trn)) {
              JOptionPane.showMessageDialog(null, "This taxpayer is already loaded.");
            } else {
              taxpayerManager.loadTaxpayer(taxRegistrationNumberAndFile);
              taxRegisterNumberModel.addElement(taxRegistrationNumber);
              
            }
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                "The tax registration number must have only digits.");
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "The file doesn't exists.");
          } catch (WrongFileFormatException e1) {
            JOptionPane.showMessageDialog(null, "Please check your file format and try again.");
          } catch (WrongFileEndingException e1) {
            JOptionPane.showMessageDialog(null, "Please check your file ending and try again.");
          } catch (WrongTaxpayerStatusException e1) {
            JOptionPane.showMessageDialog(null, "Please check taxpayer's status and try again.");
          } catch (WrongReceiptKindException e1) {
            JOptionPane.showMessageDialog(null, "Please check receipts kind and try again.");
          } catch (WrongReceiptDateException e1) {
            JOptionPane.showMessageDialog(null,
                "Please make sure your date is " + "DD/MM/YYYY and try again.");
          }
        }
      }
    });
    btnLoadTaxpayer.setBounds(0, 0, 146, 23);
    contentPane.add(btnLoadTaxpayer);

    JButton btnSelectTaxpayer = new JButton("Select Taxpayer");
    btnSelectTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (taxpayerManager.containsTaxpayer()) {
          String trn = JOptionPane.showInputDialog(null,
              "Give the tax registration number " + "that you want to be displayed : ");
          if (trn != null) {
            int taxRegistrationNumber;
            try {
              taxRegistrationNumber = Integer.parseInt(trn);
              if (taxpayerManager.containsTaxpayer(taxRegistrationNumber)) {
                TaxpayerData taxpayerData = new TaxpayerData(taxRegistrationNumber,
                    taxpayerManager);
                taxpayerData.setVisible(true);
              } else {
                JOptionPane.showMessageDialog(null, "This tax registration number isn't loaded.");
              }
            } catch (NumberFormatException e1) {
              JOptionPane.showMessageDialog(null, "You must give a tax registation number.");
            } catch (Exception e1) {
              e1.printStackTrace();
            }
          }
        } else {
          JOptionPane.showMessageDialog(null,
              "There isn't any taxpayer loaded. Please load one first.");
        }
      }
    });
    btnSelectTaxpayer.setBounds(147, 0, 139, 23);
    contentPane.add(btnSelectTaxpayer);

    JButton btnDeleteTaxpayer = new JButton("Delete Taxpayer");
    btnDeleteTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (taxpayerManager.containsTaxpayer()) {
          String trn = JOptionPane.showInputDialog(null,
              "Give the tax registration number that you want to delete: ");
          int taxRegistrationNumber;
          try {
            taxRegistrationNumber = Integer.parseInt(trn);
            if (taxpayerManager.containsTaxpayer(taxRegistrationNumber)) {
              taxpayerManager.removeTaxpayer(taxRegistrationNumber);
              taxRegisterNumberModel.removeElement(trn);
            }
          } catch (NumberFormatException e) {

          }
        } else {
          JOptionPane.showMessageDialog(null,
              "There isn't any taxpayer loaded. Please load one first.");
        }
      }
    });
    btnDeleteTaxpayer.setBounds(287, 0, 146, 23);
    contentPane.add(btnDeleteTaxpayer);

    txtTaxRegistrationNumber = new JTextField();
    txtTaxRegistrationNumber.setEditable(false);
    txtTaxRegistrationNumber.setBackground(new Color(153, 204, 204));
    txtTaxRegistrationNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
    txtTaxRegistrationNumber.setText("Tax Registration Number:");
    txtTaxRegistrationNumber.setBounds(70, 75, 300, 25);
    contentPane.add(txtTaxRegistrationNumber);
    txtTaxRegistrationNumber.setColumns(10);

  }

}
