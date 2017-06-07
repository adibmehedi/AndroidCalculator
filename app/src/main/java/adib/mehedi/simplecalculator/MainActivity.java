package adib.mehedi.simplecalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bsh.Interpreter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnPlus,btnMinus,btnDiv,btnMul,btnEqual, btnPoint,btnMplus,btnMminus,btnMrc,btnAc;
    public Button btnshowHistory, btnMemory, btnMemoryClear;

    public TextView display;
    public String valueString="";
    public String memoryValue="";
    public String calculatedResult="";
    Intent intent;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializing();
    }


    public void addHistory(String _history){
        String str = _history;
        CalcHistory history = new CalcHistory(str);
        dbHandler = new DatabaseHandler(this, null, null, 1);
        dbHandler.addHistory(history);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("value", memoryValue);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        memoryValue = savedInstanceState.getString("value");
        valueString=memoryValue;
        updateDisplay();

    }

    @Override
    public void onClick(View v)
    {

        int pressedButtonId=v.getId();
        if(pressedButtonId==R.id.button_equals) { //Equals
            calculateTotal();
        }
        else
        if(pressedButtonId==R.id.button_ac){
            valueString="";
            memoryValue="";
            updateDisplay();
        }
        else
        if(pressedButtonId==R.id.button_memory_plus){
            SharedPreferences sharedPref =getSharedPreferences("SavedMemory", Context.MODE_PRIVATE);
            memoryValue = sharedPref.getString("data","");
            valueString+="+"+memoryValue;
            display.setText(valueString);
        }
        else
        if(pressedButtonId==R.id.button_mrc)
        {
            SharedPreferences sharedPref =getSharedPreferences("SavedMemory", Context.MODE_PRIVATE);
            memoryValue = sharedPref.getString("data","");
            valueString+=memoryValue;
            display.setText(valueString);
        }
        else
            if(pressedButtonId==R.id.button_memory_minus){
                SharedPreferences sharedPref =getSharedPreferences("SavedMemory", Context.MODE_PRIVATE);
                memoryValue = sharedPref.getString("data","");
                valueString+="-"+memoryValue;
                display.setText(valueString);
            }
            else
                if(pressedButtonId==R.id.button_database){
                    Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                    startActivity(intent);
                }
                else
                    if(pressedButtonId==R.id.button_memory){
                        SharedPreferences sharedPref =getSharedPreferences("SavedMemory", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sharedPref.edit();
                        editor.putString("data",memoryValue);
                        editor.apply();
                        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
                    }
                    else
                        if(pressedButtonId==R.id.button_memory_delete){
                            SharedPreferences sharedPref =getSharedPreferences("SavedMemory", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor =sharedPref.edit();
                            editor.putString("data","");
                            editor.apply();
                            Toast.makeText(this,"Data Deleted",Toast.LENGTH_LONG).show();
                        }
        else{
              //  int flag=1;
                //if(valueString.length()>=1){
                  //  String temp=""+valueString.charAt(valueString.length()-1);
                   // if((temp.equals("+") || temp.equals("-") || temp.equals("/")||temp.equals("*")))
                   // {
                   //    flag=0;
                    //}

              //  }
              //  if(flag==1){
                    valueString += ((Button) v).getHint().toString();
                    memoryValue = valueString;
                    updateDisplay();
              //  }
              //  flag=1;

            }

    }

    public void updateDisplay()
    {
        display.setText(valueString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            calculatedResult=data.getStringExtra("value");
            updateDisplay();

        }
    }

    public void calculateTotal()
    {
        addHistory(valueString);
        if(!valueString.contains(".")){
            valueString += ".0";
        }
        try {
            Interpreter interpreter = new Interpreter();
            valueString = interpreter.eval(valueString).toString();
        }catch (Exception e){
            Toast.makeText(this, "Exception ",Toast.LENGTH_SHORT).show();
        }
            memoryValue=valueString;
            updateDisplay();
            valueString="";
    }



    public void initializing()
    {
        display=(TextView)findViewById(R.id.edit_text_mainscreen);
        btn1=(Button)findViewById(R.id.button_one);
        btn1.setOnClickListener(this); // calling onClick() method
        btn2=(Button)findViewById(R.id.button_two);
        btn2.setOnClickListener(this);
        btn3=(Button)findViewById(R.id.button_three);
        btn3.setOnClickListener(this);
        btn4=(Button)findViewById(R.id.button_four);
        btn4.setOnClickListener(this);
        btn5=(Button)findViewById(R.id.button_five);
        btn5.setOnClickListener(this);
        btn6=(Button)findViewById(R.id.button_six);
        btn6.setOnClickListener(this);
        btn7=(Button)findViewById(R.id.button_seven);
        btn7.setOnClickListener(this);
        btn8=(Button)findViewById(R.id.button_eight);
        btn8.setOnClickListener(this);
        btn9=(Button)findViewById(R.id.button_nine);
        btn9.setOnClickListener(this);
        btn0=(Button)findViewById(R.id.button_zero);
        btn0.setOnClickListener(this);
        btnPlus=(Button)findViewById(R.id.button_plus);
        btnPlus.setOnClickListener(this);
        btnMinus=(Button)findViewById(R.id.button_minus);
        btnMinus.setOnClickListener(this);
        btnDiv=(Button)findViewById(R.id.button_divide);
        btnDiv.setOnClickListener(this);
        btnMul=(Button)findViewById(R.id.button_multiple);
        btnMul.setOnClickListener(this);
        btnEqual=(Button)findViewById(R.id.button_equals);
        btnEqual.setOnClickListener(this);
        btnPoint=(Button)findViewById(R.id.button_dot);
        btnPoint.setOnClickListener(this);
        btnMplus=(Button)findViewById(R.id.button_memory_plus);
        btnMplus.setOnClickListener(this);
        btnMminus=(Button)findViewById(R.id.button_memory_minus);
        btnMminus.setOnClickListener(this);
        btnMrc=(Button)findViewById(R.id.button_mrc);
        btnMrc.setOnClickListener(this);
        btnAc=(Button)findViewById(R.id.button_ac);
        btnAc.setOnClickListener(this);
        btnshowHistory=(Button)findViewById(R.id.button_database);
        btnshowHistory.setOnClickListener(this);

        btnMemory=(Button)findViewById(R.id.button_memory);
        btnMemory.setOnClickListener(this);
        btnMemoryClear=(Button)findViewById(R.id.button_memory_delete);
        btnMemoryClear.setOnClickListener(this);


    }


}
