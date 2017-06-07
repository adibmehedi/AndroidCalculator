package adib.mehedi.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHandler dbHandler;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tv = (TextView) findViewById(R.id.historyTextView);
        //String str = this.getIntent().getStringExtra(MainActivity.KEY);
        dbHandler = new DatabaseHandler(this);
        String dbString = dbHandler.displayHistory();
        tv.setText(dbString);
    }
}
