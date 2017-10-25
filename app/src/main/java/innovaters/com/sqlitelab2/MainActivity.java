package innovaters.com.sqlitelab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "inserting..");
        db.addContact(new Contact("Ravi", "900345343"));
        db.addContact(new Contact("Svas", "54334535"));
        db.addContact(new Contact("Tommy", "9003232"));
        db.addContact(new Contact("Kark", "90345345"));

        Log.d("Reading: ", "reading all contacts..");
        List<Contact> contacts = db.getAllContact();

        StringBuilder str = new StringBuilder();

        for (Contact contact : contacts) {
            Log.e("er", contact.getName());
            String log = String.format("Id : %s, Name %s, Phone: %s ", contact.getId(), contact.getName(), contact.getPhone_number() + "\n");
            str.append(log);
            Log.d("Name", log);
        }

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(str);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
