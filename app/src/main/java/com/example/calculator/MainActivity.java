package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNum1, etNum2; // Переменные чисел
    private EditText etOperation;  // Переменная операций
    private TextView tvResultText;
    private Button buttonToCalculateResult;
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // -----------------------------------------------
        // Присвоение переменным значений из layout`а
        // -----------------------------------------------

        etNum1 = findViewById(R.id.num1_editText);
        etNum2 = findViewById(R.id.num2_editText);

        etOperation = findViewById(R.id.operation_edit);

        tvResultText = findViewById(R.id.result_textView);

        buttonToCalculateResult = findViewById(R.id.calc_result_button);

        // -----------------------------------------------
        buttonToCalculateResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        float num1, num2, result = 0;
        boolean correctOperation = true;
        String task = "";

        try {
            num1 = Float.parseFloat(etNum1.getText().toString());
            num2 = Float.parseFloat(etNum2.getText().toString());

            task = etOperation.getText().toString();

            switch (task){
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }

            //tvResultText.setText(num1+" "+task+" "+num2+" = "+result);
            // -----------------------------------------------
        } catch (ArithmeticException e){ // Ошибка 1
            int duration = Toast.LENGTH_SHORT;

            if(toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.divide_zero, duration);
            toastError.show();
            return;
            // -----------------------------------------------
        } catch (NullPointerException e){ // Ошибка 2
            int duration = Toast.LENGTH_SHORT;

            if(toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.null_data, duration);
            toastError.show();
            return;
            // -----------------------------------------------
        }catch (NumberFormatException e){ // Ошибка 3
            int duration = Toast.LENGTH_SHORT;

            if(toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_format, duration);
            toastError.show();
            return;
            // -----------------------------------------------
        }
        // Проверка оперций
        if(correctOperation){
            tvResultText.setText(num1+" "+task+" "+num2+" = "+result);
        }
        else {
            int duration = Toast.LENGTH_SHORT;

            if(toastError != null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_operation, duration);
            toastError.show();
            return;
            // -----------------------------------------------
        }

        // --------------------------------------
    }
}