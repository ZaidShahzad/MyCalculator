package com.zsbc.mycalculator.struct;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CalculatorMemory {

    private List<String> clickedButtons;
    private double currentAnswer;

    public CalculatorMemory() {
        this.clickedButtons = new ArrayList<>();
        this.currentAnswer = 0;
    }


    public void addClickedButton(String number) {
        clickedButtons.add(number);
    }

    public void clear() {
        clickedButtons.clear();
    }

    public String getPreviousClickedButton() {
        if(clickedButtons.size() == 1) {
            return clickedButtons.get(clickedButtons.size() - 1);
        }
        return clickedButtons.get(clickedButtons.size() - 2);
    }

    public String getCurrentClickedButton() {
        return clickedButtons.get(clickedButtons.size() - 1);
    }

    public void replaceLatestClickedButton(String button) {
        clickedButtons.remove(clickedButtons.size() - 1);
        clickedButtons.remove(clickedButtons.size() - 1);
        clickedButtons.add(button);
    }
}
