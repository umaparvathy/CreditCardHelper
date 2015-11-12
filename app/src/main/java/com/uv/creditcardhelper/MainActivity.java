package com.uv.creditcardhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText cardBalance, yearlyInterest, minPayment, finalCardBalance, monthsRemaining, paidInterest;
    Button compute, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        registerOnClickListeners();

    }

    private void registerOnClickListeners() {
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = validateInputValues();

                if (valid) {
                    String cardBalanceString = cardBalance.getText().toString();
                    double principal = Double.valueOf(cardBalanceString);

                    String yearlyInterestString = yearlyInterest.getText().toString();
                    double yearlyInterestValue = Double.valueOf(yearlyInterestString);

                    String minPaymentString = minPayment.getText().toString();
                    double minPaymentValue = Double.valueOf(minPaymentString);

                    double monthlyFloatInterestPaid = Math.round((principal * (yearlyInterestValue / (100 * 12))));
                    double monthlyPrincipal = minPaymentValue - monthlyFloatInterestPaid;
                    double monthlyBalance = principal - monthlyPrincipal;

                    int numberOfMonthsRemaining = 0;
                    principal = monthlyBalance;
                    while (principal > 0.0) {
                        numberOfMonthsRemaining++;
                        double monthlyFloatInterestPaid1 = Math.round((principal * (yearlyInterestValue / (100 * 12))));
                        double monthlyPrincipal1 = minPaymentValue - monthlyFloatInterestPaid1;
                        double monthlyBalance1 = principal - monthlyPrincipal1;
                        principal = monthlyBalance1;
                    }

                    finalCardBalance.setText(String.valueOf(monthlyBalance));
                    monthsRemaining.setText(String.valueOf(numberOfMonthsRemaining));
                    paidInterest.setText(String.valueOf(monthlyFloatInterestPaid));

                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardBalance.setText("");
                yearlyInterest.setText("");
                minPayment.setText("");
                finalCardBalance.setText("");
                monthsRemaining.setText("");
                paidInterest.setText("");
            }
        });
    }

    private boolean validateInputValues() {

        boolean valid = false;

        try {
            String cardBalanceString = cardBalance.getText().toString();
            double principal = Double.valueOf(cardBalanceString);

            String yearlyInterestString = yearlyInterest.getText().toString();
            double yearlyInterestValue = Double.valueOf(yearlyInterestString);

            String minPaymentString = minPayment.getText().toString();
            double minPaymentValue = Double.valueOf(minPaymentString);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(),"Enter valid values", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    private void initializeViews() {

        cardBalance = (EditText) findViewById(R.id.cardBalanceText);
        yearlyInterest = (EditText) findViewById(R.id.yearlyInterestText);
        minPayment = (EditText) findViewById(R.id.minPaymentText);
        finalCardBalance = (EditText) findViewById(R.id.finalCardBalanceText);
        monthsRemaining = (EditText) findViewById(R.id.monthsRemainingText);
        paidInterest = (EditText) findViewById(R.id.paidInterestText);

        compute = (Button) findViewById(R.id.compute);
        clear = (Button) findViewById(R.id.clear);
    }


}
