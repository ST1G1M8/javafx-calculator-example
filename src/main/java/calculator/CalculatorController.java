package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true, isFloat = false;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if(isFloat){
               display.setText(String.format("%s", result));
           } else {
               display.setText(String.format("%.0f", result));
           }
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void clearDisplay(ActionEvent event){
        String pressedAC = ((Button) event.getSource()).getText();
        System.out.println(pressedAC);
        startNumber = true;
        operator = "";
        display.setText("0");
        isFloat = false;
    }

    @FXML
    public void processFloat(ActionEvent event){
        String pressedDot = ((Button) event.getSource()).getText();
        System.out.println(pressedDot);
        if(isFloat) {
            return;
        }
        display.setText(display.getText() + ".");
        isFloat = true;
    }

    @FXML
    public void processNegate(ActionEvent event){
        String pressedNegate = ((Button) event.getSource()).getText();
        System.out.println(pressedNegate);
        if(display.getText().equals("0")) {
            return;
        } else if(display.getText().charAt(0) == '-') {
            display.setText(display.getText().substring(1));
        } else {
            display.setText("-" + display.getText());
        }
    }

}
