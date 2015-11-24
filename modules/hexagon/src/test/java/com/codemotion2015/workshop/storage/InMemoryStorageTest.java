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

package com.codemotion2015.workshop.storage;

import com.codemotion2015.workshop.events.EventsPort;
import com.codemotion2015.workshop.events.InMemoryEventsAdapter;
import com.codemotion2015.workshop.modules.horizontal.commons.Callback;
import com.codemotion2015.workshop.modules.vertical.notes.CreateNoteCommand;
import com.codemotion2015.workshop.modules.vertical.notes.CreateNoteResponse;
import com.codemotion2015.workshop.modules.vertical.notes.NoteDTO;
import com.codemotion2015.workshop.modules.vertical.notes.NotesModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryStorageTest {

  @Test public void storageOneNote() throws Exception {
    EventsPort eventsPort = new InMemoryEventsAdapter();
    StoragePort storagePort = new InMemoryStorageAdapter(eventsPort);
    NotesModule notesModule = new NotesModule(storagePort, eventsPort);
    NoteDTO note = new NoteDTO(4321, "What's up codemotion?");

    eventsPort.on(CreateNoteResponse.class, new Callback<CreateNoteResponse>() {
      @Override public void call(CreateNoteResponse event) {
        assertEquals(4321, event.noteResponse.getTaskId());
        assertEquals("What's up codemotion?", event.noteResponse.getContent());
      }
    });

    notesModule.run();
    eventsPort.broadcast(new CreateNoteCommand(note));
  }

  @Test public void createNoteEventHasBeenFired() throws Exception {
    InMemoryEventsAdapter eventsPort = new InMemoryEventsAdapter();
    StoragePort storagePort = new InMemoryStorageAdapter(eventsPort);
    NotesModule notesModule = new NotesModule(storagePort, eventsPort);
    NoteDTO dummyNote = new NoteDTO(9999, "I'm a dummy note");

    notesModule.run();
    eventsPort.broadcast(new CreateNoteCommand(dummyNote));

    assertTrue(eventsPort.hasBeenFired(CreateNoteResponse.class));
  }
}