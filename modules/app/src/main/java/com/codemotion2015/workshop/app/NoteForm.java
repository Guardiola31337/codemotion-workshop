/*
 * Copyright (C) 2015 Pablo Guardiola Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codemotion2015.workshop.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteForm extends AppCompatActivity {

  private EditText title;
  private EditText description;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.note_form);

    initToolbar();
    initNoteForm();
    initSaveNoteButton();
  }

  private void initToolbar() {
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(R.string.note_form_label);
  }

  private void initNoteForm() {
    title = (EditText) findViewById(R.id.input_title);
    description = (EditText) findViewById(R.id.input_description);
  }

  private void initSaveNoteButton() {
    Button saveNote = (Button) findViewById(R.id.btn_save_note);
    saveNote.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

      }
    });
  }
}
