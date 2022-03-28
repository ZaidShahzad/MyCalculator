package com.zsbc.mycalculator.struct;

import com.zsbc.mycalculator.actions.NumberButtonListener;
import com.zsbc.mycalculator.actions.OperationButtonListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@Getter
@Setter
public class Calculator implements ActionListener {

    private JButton[] numberButtons = {new JButton("0"), new JButton("1"), new JButton("2"), new JButton("3"), new JButton("4"), new JButton("5"), new JButton("6"), new JButton("7"), new JButton("8"), new JButton("9")};
    private JButton[] operationButton = {new JButton("+"), new JButton("-"), new JButton("AC"), new JButton("="), new JButton("/"), new JButton("*")};
    private JButton dotButton;
    private JFrame frame;
    private JPanel panel;
    private Font buttonFont;
    private NumberButtonListener numberButtonListener;
    private OperationButtonListener operationButtonListener;
    private AnswerBar answerBar;

    public Calculator() {
        this.buttonFont = new Font("Serif", Font.BOLD, 30);
        this.frame = new JFrame("ZSBC MyCalculator App");
        this.panel = new JPanel();
        this.dotButton = new JButton(".");
        this.answerBar = new AnswerBar(operationButton);
        this.numberButtonListener = new NumberButtonListener(numberButtons, answerBar);
        this.operationButtonListener = new OperationButtonListener(operationButton, answerBar, answerBar.getCalculatorMemory());

        setupFrame();
    }

    private JButton getNumberButton(String button) {
        return Arrays.stream(getNumberButtons()).filter(b -> b.getText().equalsIgnoreCase(button)).findFirst().get();
    }

    public JButton getNumberButton(int number) {
        return getNumberButton(String.valueOf(number));
    }

    public JButton getOperationButton(String button) {
        return Arrays.stream(getOperationButton()).filter(b -> b.getText().equalsIgnoreCase(button)).findFirst().get();
    }

    private void setupFrame() {
        getFrame().setSize(410, 470);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setVisible(true);
        getFrame().add(getPanel());
        getFrame().setResizable(false);
        getPanel().setBackground(Color.white);

        getPanel().setLayout(null);
        addButtons();
    }

    private void addButtons() {
        for(int i = 0; i <= 9; i++) {
            getPanel().add(getNumberButton(i));
            getNumberButton(i).addActionListener(getNumberButtonListener());
        }
        for(JButton operationButton : getOperationButton()) {
            getPanel().add(operationButton);
            getOperationButton(operationButton.getText()).addActionListener(getOperationButtonListener());
        }

        getPanel().add(getDotButton());
        getDotButton().addActionListener(this);

        getAnswerBar().addButtons(getPanel());

        getNumberButton(7).setBounds(15, 70, 80, 55);
        getNumberButton(7).setFont(getButtonFont());

        getNumberButton(4).setBounds(15, 140, 80, 55);
        getNumberButton(4).setFont(getButtonFont());

        getNumberButton(1).setBounds(15, 210, 80, 55);
        getNumberButton(1).setFont(getButtonFont());

        getNumberButton(8).setBounds(110, 70, 80, 55);
        getNumberButton(8).setFont(getButtonFont());

        getNumberButton(5).setBounds(110, 140, 80, 55);
        getNumberButton(5).setFont(getButtonFont());

        getNumberButton(2).setBounds(110, 210, 80, 55);
        getNumberButton(2).setFont(getButtonFont());

        getNumberButton(9).setBounds(205, 70, 80, 55);
        getNumberButton(9).setFont(getButtonFont());

        getNumberButton(6).setBounds(205, 140, 80, 55);
        getNumberButton(6).setFont(getButtonFont());

        getNumberButton(3).setBounds(205, 210, 80, 55);
        getNumberButton(3).setFont(getButtonFont());

        getNumberButton(0).setBounds(15, 280, 175, 55);
        getNumberButton(0).setFont(getButtonFont());

        getDotButton().setBounds(205, 280, 80, 55);
        getDotButton().setFont(getButtonFont());

        getOperationButton("=").setBounds(205, 350, 175, 55);
        getOperationButton("=").setFont(getButtonFont());

        getOperationButton("AC").setBounds(15, 350, 175, 55);
        getOperationButton("AC").setFont(getButtonFont());

        getOperationButton("/").setBounds(300, 70, 80, 55);
        getOperationButton("/").setFont(getButtonFont());

        getOperationButton("*").setBounds(300, 140, 80, 55);
        getOperationButton("*").setFont(getButtonFont());

        getOperationButton("-").setBounds(300, 210, 80, 55);
        getOperationButton("-").setFont(getButtonFont());

        getOperationButton("+").setBounds(300, 280, 80, 55);
        getOperationButton("+").setFont(getButtonFont());

        getAnswerBar().setButtonDisplayTextLocation(360, 1, 160, 25);
        getAnswerBar().setCurrentTextLocation(18, 20, 400, 30);

        setButtonsColor(getNumberButtons(), Color.white);
        setButtonColor(getDotButton(), Color.white);
        setButtonColor(getOperationButton("AC"), Color.red);
        setButtonColor(getOperationButton("="), Color.green);
        setButtonColor(getOperationButton("-"), Color.orange);
        setButtonColor(getOperationButton("/"), Color.orange);
        setButtonColor(getOperationButton("*"), Color.orange);
        setButtonColor(getOperationButton("+"), Color.orange);
        setButtonColor(getOperationButton("-"), Color.orange);
    }

    private void setButtonsColor(JButton[] buttons, Color color) {
        Arrays.stream(buttons).forEach(b -> b.setBackground(color));
    }

    private void setButtonColor(JButton button, Color color) {
        button.setBackground(color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(getDotButton())) {
            getDotButton().setFocusable(false);
            getAnswerBar().updateCurrentText(getDotButton().getText());
            getAnswerBar().updateButtonDisplayText();
        }
    }
}
