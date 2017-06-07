package adib.mehedi.simplecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Context;
import bsh.Interpreter;

public class CalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent=new Intent();
        String calcValue=getIntent().getStringExtra("CalcValue");
        try {
            calcValue += ".0";
            Interpreter interpreter = new Interpreter();
            calcValue = interpreter.eval(calcValue).toString();
        }catch (Exception e){
            Toast.makeText(this, "Exception ",Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("value",calcValue);
        setResult(2,intent);
        finish();

    }



}
