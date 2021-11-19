package ui;

import model.Transaction;
import model.TransactionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

//adapted from components-ListDemoProject, an example on docs.oracle.com. Linked below:
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
public class BusinessManagerGUI extends JPanel implements ListSelectionListener {

    private final JList list;
    private static final String addString = "Add Transaction";
    private static final String removeString = "Delete Transaction";
    private static final String infoString = "Details";
    private static final String saveString = "Save Transaction List";
    private static final String loadString = "Load Transaction List";
    private JButton addButton;
    private JButton removeButton;
    private JButton infoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField transactionNumber;
    private JTextField transactionType;
    private JTextField transactionSender;
    private JTextField transactionReceiver;
    private JTextField transactionDescription;
    private JTextField transactionAmount;
    private final DefaultListModel listModel;
    private JLabel numberLabel;
    private JLabel typeLabel;
    private JLabel senderLabel;
    private JLabel receiverLabel;
    private JLabel descriptionLabel;
    private JLabel amountLabel;
    protected JLabel infoLabel = new JLabel("No transaction details to show");
    protected JPanel detailsPanel;
    protected JLabel saveLoadLabel = new JLabel("");

    protected Transaction transaction;
    protected TransactionList transactionList = new TransactionList();

    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    private static final String JSON_STORE = "./data/transactionlist.json";

    public BusinessManagerGUI() {
        super(new BorderLayout());
        listModel = new DefaultListModel();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        createLabelsForTextFields();

        TransactionAddListener transactionAddListener = createButtons();

        createJTextFields(transactionAddListener);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = createButtonPaneWithTextFields();

        //Create a panel that shows information about a specific transaction
        setUpDetailsPanel();

        setUpGUI(listScrollPane, buttonPane);
    }

    // EFFECTS: adds the scroll pane, and two JPanels with buttons and labels
    private void setUpGUI(JScrollPane listScrollPane, JPanel buttonPane) {
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(detailsPanel, BorderLayout.PAGE_START);
    }

    // EFFECTS: creates all the JTextFields and adds action and document listeners to each one respectively
    private void createJTextFields(TransactionAddListener transactionAddListener) {
        transactionNumber = new JTextField(10);
        transactionNumber.addActionListener(transactionAddListener);
        transactionNumber.getDocument().addDocumentListener(transactionAddListener);

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
    }

    // EFFECTS: creates the add transaction, remove transaction, details, save and load buttons and adds action
    // listeners to each one
    private TransactionAddListener createButtons() {
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

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new TransactionSaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new TransactionLoadListener());
        return transactionAddListener;
    }

    // EFFECTS: creates labels next to the text fields
    private void createLabelsForTextFields() {
        numberLabel = new JLabel("Number ");
        typeLabel = new JLabel("Type ");
        senderLabel = new JLabel("Sender ");
        receiverLabel = new JLabel("Receiver ");
        descriptionLabel = new JLabel("Description ");
        amountLabel = new JLabel("Amount ");
    }

    // EFFECTS: sets up the JPanel that displays more details about a transaction, as well as contains the save and
    // load buttons
    private void setUpDetailsPanel() {
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel,
                BoxLayout.LINE_AXIS));
        detailsPanel.add(infoButton);
        detailsPanel.add(infoLabel);
        detailsPanel.add(saveButton);
        detailsPanel.add(loadButton);
        detailsPanel.add(saveLoadLabel);
    }

    // EFFECTS: creates JPanel at the bottom of the page with add transaction/remove transaction buttons, as well as
    // text fields and labels
    private JPanel createButtonPaneWithTextFields() {
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
        return buttonPane;
    }

    //Adapted from components-ListDemoProject, an example on docs.oracle.com,
    //this listener is shared by the text field and the hire button.
    class TransactionAddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // EFFECTS: button to add transaction to list
        public TransactionAddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: listModel, this
        // EFFECTS: acts as a listener for the add transaction button, only adds transaction if the types in the text
        // fields are correct
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

            //User didn't type in a unique name...
            if (number.equals("") || alreadyInList(number) || !number.matches("[0-9]+")
                    || !amount.matches("[0-9]+") || type.isEmpty() || sender.isEmpty() || receiver.isEmpty()
                    || description.isEmpty()) {
                Toolkit.getDefaultToolkit().beep();
                transactionNumber.requestFocusInWindow();
                transactionNumber.selectAll();
                return;
            }

            addTransactionToListIfPossible(number, type, sender, receiver, description, amount);

            int index = insertIntoList();

            insertTransactionAndMakeVisibleInList(index);
        }

        // EFFECTS: returns true if listModel already contains tbe given transaction number in the text field
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // EFFECTS: enables button
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: handles an empty text field
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: if text field is not empty, enable button
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: if button is not already enabled, enable button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: returns true if text field is 0, and disables button
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: increments index by one
    private int insertIntoList() {
        int index = list.getSelectedIndex(); //get selected index
        if (index == -1) { //no selection, so insert at beginning
            index = 0;
        } else {           //add after the selected item
            index++;
        }
        return index;
    }

    // EFFECTS: adds transaction to transaction list if all the types are correct in the text fields and if there does
    // not already exist a transaction with a number that is in the scroll pane list already
    private void addTransactionToListIfPossible(String number, String type, String sender, String receiver,
                                                String description, String amount) {
        if (!number.isEmpty() && number.matches("[0-9]+") || !type.isEmpty() && !sender.isEmpty()
                && !receiver.isEmpty() && !description.isEmpty() && !amount.isEmpty()
                && amount.matches("[0-9]+")) {
            transaction = new Transaction(Integer.parseInt(String.valueOf(transactionNumber.getText())),
                    transactionType.getText(), transactionSender.getText(), transactionReceiver.getText(),
                    transactionDescription.getText(),
                    Integer.parseInt(String.valueOf(transactionAmount.getText())));
            transactionList.addTransaction(transaction);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds transaction number to list and resets text fields
    private void insertTransactionAndMakeVisibleInList(int index) {
        listModel.insertElementAt(transactionNumber.getText(), index);

        resetTextFields();

        //Select the new item and make it visible.
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }

    // MODIFIES: this
    // EFFECTS: resets all the text fields
    private void resetTextFields() {
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
    }

    // MODIFIES: this
    // EFFECTS: disable remove transaction button if no item in the list is selected in the scroll pane, and enable it
    // otherwise
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            //No selection, disable remove transaction button.
            //Selection, enable the remove transaction button.
            removeButton.setEnabled(list.getSelectedIndex() != -1);
        }
    }

    //clicking the "details" button shows more info about a selected transaction
    class TransactionInfoListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: displays information about the transaction with the selected transaction number in the scroll pane
        @Override
        public void actionPerformed(ActionEvent e) {
            //Method can only be called if there are transactions remaining in the list
            //Shows more info about selected transaction from the list
            if (list.isSelectionEmpty()) {
                infoLabel.setText("No transaction details to show");
                return;
            }

            String selected = list.getSelectedValue().toString();
            int selectedID = Integer.parseInt(selected);
            infoLabel.setText(transactionList.viewTransaction(selectedID));
        }
    }

    //clicking the "details" button shows more info about a selected transaction
    class TransactionSaveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: saves transaction list to file as JSON data
        @Override
        public void actionPerformed(ActionEvent e) {
            //Saves transaction list to file
            try {
                jsonWriter = new JsonWriter(JSON_STORE);
                jsonWriter.open();
                jsonWriter.write(transactionList);
                jsonWriter.close();
                saveLoadLabel.setText("Saved transaction list" + " to " + JSON_STORE);
                listModel.removeAllElements();
            } catch (FileNotFoundException f) {
                saveLoadLabel.setText("Unable to write to file: " + JSON_STORE);
            }
        }
    }


    class TransactionLoadListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: acts as a listener for a button that loads a transaction list from JSON data to the transaction
        // list, and adds each transaction number from each transaction to the list in the scroll pane
        @Override
        public void actionPerformed(ActionEvent e) {
            //Loads transaction list from file
            try {
                listModel.removeAllElements();
                jsonReader = new JsonReader(JSON_STORE);
                transactionList = jsonReader.read();
                for (int i = 0; i < transactionList.size(); i++) {
                    listModel.addElement(transactionList.getTransaction(i).getNumber());
                }
                saveLoadLabel.setText("Loaded transaction list" + " from " + JSON_STORE);
                JFrame successFrame = new JFrame();
                successFrame.setTitle("yay");
                successFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                JPanel successPanel = new JPanel();
                successPanel.add(new JLabel(new
                        ImageIcon(Objects.requireNonNull(getClass().getResource("frog.png")))));
                successFrame.add(successPanel);

                successFrame.pack();
                successFrame.setVisible(true);

            } catch (IOException f) {
                saveLoadLabel.setText("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    class TransactionRemoveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: acts as a listener for a button that removes a transaction from transaction list, and removes
        // identifier from the list in the scroll pane
        @Override
        public void actionPerformed(ActionEvent e) {
            //Method can only be called if there are transactions remaining in the list
            //Removes selected transaction from the list
            int index = list.getSelectedIndex();
            String selected = list.getSelectedValue().toString();
            listModel.remove(index);

            int selectedID = Integer.parseInt(selected);
            transactionList.removeTransaction(selectedID);

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

    // EFFECTS: creates and shows the gui application
    private static void createAndShowGUI() {
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
        createAndShowGUI();
    }

}

