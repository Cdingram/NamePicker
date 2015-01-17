package com.example.namepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentListManager.initManager(this.getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void editStudents(MenuItem menu) {
    	Toast.makeText(this, "Edit Students", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ListStudentsActivity.class);
    	startActivity(intent);
    }
    
    public void bulkImport(MenuItem menu) {
    	Toast.makeText(this, "Bulk Import", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, BulkImportActivity.class);
    	startActivity(intent);
    }
    
    public void chooseAStudent(View v){
    	Toast.makeText(this, "Choose A Student", Toast.LENGTH_SHORT).show();
    	StudentListController st = new StudentListController();
    	
    	try {
    		Student s = st.chooseStudent();
    		TextView view = (TextView) findViewById(R.id.chosenStudentTextView);
        	view.setText(s.getName());
    	} catch (EmptyStudentListException e) {
    		Toast.makeText(this, "There are no students", Toast.LENGTH_SHORT).show();
    	}
    }
}
