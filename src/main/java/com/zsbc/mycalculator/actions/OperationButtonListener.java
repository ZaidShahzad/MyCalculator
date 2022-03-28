package com.zsbc.mycalculator.actions;

import com.zsbc.mycalculator.struct.AnswerBar;
import com.zsbc.mycalculator.struct.CalculatorMemory;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class OperationButtonListener implements ActionListener {

    private CalculatorMemory calculatorMemory;
    private JButton[] operationButtons;
    private AnswerBar answerBar;

    public OperationButtonListener(JButton[] operationButtons, AnswerBar answerBar, CalculatorMemory calculatorMemory) {
        this.operationButtons = operationButtons;
        this.answerBar = answerBar;
        this.calculatorMemory = calculatorMemory;
    }

    public JButton getOperationButton(String button) {
        return Arrays.stream(getOperationButtons()).filter(b -> b.getText().equalsIgnoreCase(button)).findFirst().get();
    }

    private ArrayList<String> calculation(String currentText) {
        ArrayList<String> calculation = new ArrayList<>();
        for(String string : currentText.split(" ")) {
            calculation.add(string);
        }
        return calculation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(getOperationButton("="))) {
            getOperationButton("=").setFocusable(false);
            getAnswerBar().calculateAnswer(calculation(getAnswerBar().getCurrentText().getText()));
            return;
        }
        if(e.getSource().equals(getOperationButton("AC"))) {
            getOperationButton("AC").setFocusable(false);
            getAnswerBar().clearCalculatorMemory();
            return;
        }
        for(JButton button : getOperationButtons()) {
            if(e.getSource().equals(button)) {
                button.setFocusable(false);
                getAnswerBar().updateCurrentText(button.getText());
                getAnswerBar().updateButtonDisplayText();
            }
        }
    }
}
