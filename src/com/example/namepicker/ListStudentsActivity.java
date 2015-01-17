package com.example.namepicker;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListStudentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_students);
		
		StudentListManager.initManager(this.getApplicationContext());
		// Display students in listview
		ListView listView = (ListView) findViewById(R.id.studentListView);
		Collection<Student> students = StudentListController.getStudentList().getStudents();
		final ArrayList<Student> list = new ArrayList<Student>(students);
		final ArrayAdapter<Student> studentAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(studentAdapter);
		
		// added a change observer
		StudentListController.getStudentList().addListener(new Listener() {
			public void update() {
				list.clear();
				Collection<Student> students = StudentListController.getStudentList().getStudents();
				list.addAll(students);
				studentAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AlertDialog.Builder adb = new AlertDialog.Builder(ListStudentsActivity.this);
				adb.setMessage("Delete" + list.get(position).toString() + "?");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Student student = list.get(finalPosition);
						StudentListController.getStudentList().removeStudent(student);
						
					}
					
				});
				adb.setNegativeButton("Cancel", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
					
				});
				adb.show();
				return false;
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_students, menu);
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
	
	public void addStudentAction(View v) {
		Toast.makeText(this, "Adding A Student", Toast.LENGTH_SHORT).show();
		StudentListController st = new StudentListController();
		EditText textView = (EditText) findViewById(R.id.addStudentNameText);
		st.addStudent(new Student(textView.getText().toString()));
	}
	
	
}
