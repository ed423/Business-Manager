package ui;

import model.Transaction;
import model.TransactionList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//adapted from components-ListDemoProject, an example on docs.oracle.com
public class BusinessManagerGUI extends JPanel implements ListSelectionListener {

    private JList list;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final String addString = "Add Transaction";
    private static final String removeString = "Delete Transaction";
    private static final String infoString = "Details";
    private JButton addButton;
    private JButton removeButton;
    private JButton infoButton;
    private JTextField transactionNumber;
    private JTextField transactionType;
    private JTextField transactionSender;
    private JTextField transactionReceiver;
    private JTextField transactionDescription;
    private JTextField transactionAmount;
    private DefaultListModel listModel;
    private JLabel numberLabel;
    private JLabel typeLabel;
    private JLabel senderLabel;
    private JLabel receiverLabel;
    private JLabel descriptionLabel;
    private JLabel amountLabel;
    private JLabel infoLabel;
    protected JPanel detailsPanel;

    protected Transaction transaction;
    protected TransactionList transactionList = new TransactionList();

    //adapted from components-ListDemoProject, an example on docs.oracle.com
    class TransactionInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Method can only be called if there are transactions remaining in the list
            //Shows more info about selected transaction from the list
            int index = list.getSelectedIndex();
            infoLabel = new JLabel(transactionList.viewTransaction(index));
            detailsPanel.add(infoLabel);

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    public BusinessManagerGUI() {
        super(new BorderLayout());
        listModel = new DefaultListModel();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        numberLabel = new JLabel("Number ");
        typeLabel = new JLabel("Type ");
        senderLabel = new JLabel("Sender ");
        receiverLabel = new JLabel("Receiver ");
        descriptionLabel = new JLabel("Description ");
        amountLabel = new JLabel("Amount ");

        addButton = new JButton(addString);
        TransactionAddListener transactionAddListener = new TransactionAddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(transactionAddListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new TransactionRemoveListener());

        infoButton = new JButton(infoString);
        infoButton.setActionCommand(infoString);
        infoButton.addActionListener(new TransactionInfoListener());

        transactionNumber = new JTextField(10);
        transactionNumber.addActionListener(transactionAddListener);
        transactionNumber.getDocument().addDocumentListener(transactionAddListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();

        transactionType = new JTextField(10);
        transactionType.addActionListener(transactionAddListener);
        transactionType.getDocument().addDocumentListener(transactionAddListener);

        transactionSender = new JTextField(10);
        transactionSender.addActionListener(transactionAddListener);
        transactionSender.getDocument().addDocumentListener(transactionAddListener);

        transactionReceiver = new JTextField(10);
        transactionReceiver.addActionListener(transactionAddListener);
        transactionReceiver.getDocument().addDocumentListener(transactionAddListener);

        transactionDescription = new JTextField(10);
        transactionDescription.addActionListener(transactionAddListener);
        transactionDescription.getDocument().addDocumentListener(transactionAddListener);

        transactionAmount = new JTextField(10);
        transactionAmount.addActionListener(transactionAddListener);
        transactionAmount.getDocument().addDocumentListener(transactionAddListener);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(numberLabel);
        buttonPane.add(transactionNumber);
        buttonPane.add(typeLabel);
        buttonPane.add(transactionType);
        buttonPane.add(senderLabel);
        buttonPane.add(transactionSender);
        buttonPane.add(receiverLabel);
        buttonPane.add(transactionReceiver);
        buttonPane.add(descriptionLabel);
        buttonPane.add(transactionDescription);
        buttonPane.add(amountLabel);
        buttonPane.add(transactionAmount);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //Create a panel that shows information about a specific transaction
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel,
                BoxLayout.LINE_AXIS));
        detailsPanel.add(infoButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(detailsPanel, BorderLayout.PAGE_START);
    }

    //Adapted from components-ListDemoProject, an example on docs.oracle.com,
    //this listener is shared by the text field and the hire button.
    class TransactionAddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public TransactionAddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String number;
            String type;
            String sender;
            String receiver;
            String description;
            String amount;

            number = transactionNumber.getText();
            type = transactionType.getText();
            sender = transactionSender.getText();
            receiver = transactionReceiver.getText();
            description = transactionDescription.getText();
            amount = transactionAmount.getText();

            if (!number.isEmpty() && number.matches("[0-9]+") && !type.isEmpty() && !sender.isEmpty()
                    && !receiver.isEmpty() && !description.isEmpty() && !amount.isEmpty()
                    && amount.matches("[0-9]+")) {
                transaction = new Transaction(Integer.parseInt(String.valueOf(transactionNumber.getText())),
                        transactionType.getText(), transactionSender.getText(), transactionReceiver.getText(),
                        transactionDescription.getText(),
                        Integer.parseInt(String.valueOf(transactionAmount.getText())));
                transactionList.addTransaction(transaction);
            }

            //User didn't type in a unique name...
            if (number.equals("") || alreadyInList(number) || !number.matches("[0-9]+")
                    || !number.matches("[0-9]+") || !amount.matches("[0-9]+") || type.isEmpty()
                    || sender.isEmpty() || receiver.isEmpty() || description.isEmpty()) {
                Toolkit.getDefaultToolkit().beep();
                transactionNumber.requestFocusInWindow();
                transactionNumber.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(transactionNumber.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            transactionNumber.requestFocusInWindow();
            transactionNumber.setText("");
            transactionType.requestFocusInWindow();
            transactionType.setText("");
            transactionSender.requestFocusInWindow();
            transactionSender.setText("");
            transactionReceiver.requestFocusInWindow();
            transactionReceiver.setText("");
            transactionDescription.requestFocusInWindow();
            transactionDescription.setText("");
            transactionAmount.requestFocusInWindow();
            transactionAmount.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

    //adapted from components-ListDemoProject, an example on docs.oracle.com
    class TransactionRemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Method can only be called if there are transactions remaining in the list
            //Removes selected transaction from the list
            int index = list.getSelectedIndex();
            listModel.remove(index);
            transactionList.removeTransaction(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable transaction removal.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removes item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Business Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new BusinessManagerGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

