package com.zsbc.mycalculator.actions;
import com.zsbc.mycalculator.struct.AnswerBar;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


@Getter
@Setter
public class NumberButtonListener implements ActionListener {

    private JButton[] numberButtons;
    private AnswerBar answerBar;

    public NumberButtonListener(JButton[] numberButtons, AnswerBar answerBar) {
        this.numberButtons = numberButtons;
        this.answerBar = answerBar;
    }

    private JButton getNumberButton(String button) {
        return Arrays.stream(getNumberButtons()).filter(b -> b.getText().equalsIgnoreCase(button)).findFirst().get();
    }

    public JButton getNumberButton(int number) {
        return getNumberButton(String.valueOf(number));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i <= 9; i++) {
            if(e.getSource().equals(getNumberButton(i))) {
                getNumberButton(i).setFocusable(false);
                getAnswerBar().updateCurrentText(getNumberButton(i).getText());
                getAnswerBar().updateButtonDisplayText();
            }
        }
    }
}
