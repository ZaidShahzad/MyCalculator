package com.zsbc.mycalculator.struct;
import com.zsbc.mycalculator.Program;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class AnswerBar {


    private double answer;
    private JLabel buttonDisplayText;
    private JLabel currentText;

    private Font font;

    private CalculatorMemory calculatorMemory;
    private JButton[] operationButtons;

    public AnswerBar(JButton[] operationButtons) {
        this.buttonDisplayText = new JLabel("");
        this.currentText = new JLabel("");
        this.font = new Font("Serif", Font.BOLD, 40);
        this.calculatorMemory = new CalculatorMemory();
        this.operationButtons = operationButtons;

        answer = 0;
    }

    public void updateButtonDisplayText() {
        getButtonDisplayText().setText(getCalculatorMemory().getCurrentClickedButton());
    }

    private boolean isOperationButton(String button) {
        return Arrays.stream(getOperationButtons()).anyMatch(b -> button.equalsIgnoreCase(b.getText()));
    }

    public void updateCurrentText(String text) {
        if (getCalculatorMemory().getClickedButtons().isEmpty()) {
            getCalculatorMemory().addClickedButton(text);
        } else {
            getCalculatorMemory().addClickedButton(text);
            String previous = getCalculatorMemory().getPreviousClickedButton();
            String clicked = text;
            if(isOperationButton(previous) && isOperationButton(clicked)) {
                getCalculatorMemory().replaceLatestClickedButton(clicked);
            }
        }

        int clickedButtonSize = getCalculatorMemory().getClickedButtons().size();
        getCurrentText().setFont(new Font("Serif", Font.ITALIC, 40 - (clickedButtonSize)));

        getCurrentText().setText(toString());
    }


    public String toString() {
        String updated = "";
        for(String clickedButton : getCalculatorMemory().getClickedButtons()) {
            updated += clickedButton;
        }
        updated = updated.replaceAll("\\+", " + ").replaceAll("-", " - ").replaceAll("\\*", " * ").replaceAll("/", " / ");
        return updated;
    }

    private double onSpotCalculation(double currentAnswer, String operation, double nextNumber) {
        double answer = 0;
        switch(operation) {
            case "*": answer = (currentAnswer * nextNumber); break;
            case "/": answer = (currentAnswer / nextNumber); break;
            case "+": answer = (currentAnswer + nextNumber); break;
            case "-": answer = (currentAnswer - nextNumber); break;
        }
        return answer;
    }

    public void calculateAnswer(ArrayList<String> calculation) {
        if(calculation.isEmpty()) {
            getCalculatorMemory().setCurrentAnswer(getAnswer());
            getCurrentText().setText(String.valueOf(getAnswer()));
            return;
        }
        int index = -1;
        boolean onNumber = false;
        for(String clickedButton : calculation) {
            index++;
            if(!isOperationButton(clickedButton)) {
                onNumber = true;
                setAnswer(Double.valueOf(clickedButton));
                doSecondStep(calculation, onNumber, index);
                return;
            }
            else {
                String operationSymbol = clickedButton;
                String nextNumber = calculation.get(index + 1);
                double newAnswer = onSpotCalculation(getAnswer(), operationSymbol, Double.valueOf(nextNumber));
                setAnswer(newAnswer);
                doSecondStep(calculation, onNumber, index);
                return;
            }
        }
    }

    private void doSecondStep(ArrayList<String> calculation, boolean onNumber, int index) {
        ArrayList<String> newCalc = new ArrayList<>();
        calculation.stream().forEach(c -> {
            newCalc.add(c);
        });

        if(onNumber) {
            newCalc.remove(index);
        }
        else {
            newCalc.remove(index);
            newCalc.remove(index);
        }
        calculateAnswer(newCalc);
    }

    public void clearCalculatorMemory() {
        getCalculatorMemory().clear();
        getButtonDisplayText().setText("");
        getCurrentText().setText("");
    }

    public void setButtonDisplayTextLocation(int x, int y, int width, int height) {
        getButtonDisplayText().setBounds(x,y,width,height);
    }

    public void setCurrentTextLocation(int x, int y, int width, int height) {
        getCurrentText().setBounds(x,y,width,height);
    }
    public void addButtons(JPanel jPanel) {
        jPanel.add(getButtonDisplayText());
        jPanel.add(getCurrentText());
        getButtonDisplayText().setFont(new Font("Serif", Font.ITALIC, 20));
        getCurrentText().setFont(getFont());
    }
}
