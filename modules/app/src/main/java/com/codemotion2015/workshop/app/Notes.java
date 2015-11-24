package com.codemotion2015.workshop.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.codemotion2015.workshop.events.EventsPort;
import com.codemotion2015.workshop.modules.horizontal.commons.Callback;
import com.codemotion2015.workshop.modules.vertical.notes.CreateNoteResponse;

public class Notes extends AppCompatActivity {

  private EventsPort eventsPort;
  private TextView title;
  private TextView description;
  private Callback callback;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.notes);

    initToolbar();
    initNote();
    initFAB();

    initEventsPort();

    callback = new Callback<CreateNoteResponse>() {
      @Override public void call(CreateNoteResponse event) {
        refreshNoteData(event);
      }
    };
  }

  @Override protected void onResume() {
    super.onResume();
    eventsPort.on(CreateNoteResponse.class, callback);
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_notes, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  private void initToolbar() {
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void initNote() {
    title = (TextView) findViewById(R.id.title);
    description = (TextView) findViewById(R.id.description);
  }

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(Notes.this, NoteForm.class);
        startActivity(intent);
      }
    });
  }

  private void initEventsPort() {
    BaseApp app = (BaseApp) getApplication();
    eventsPort = app.obtainEventsPort();
  }

  private void refreshNoteData(CreateNoteResponse event) {
    title.setText(Integer.toString(event.noteResponse.getTaskId()));
    description.setText(event.noteResponse.getContent());
  }
}
