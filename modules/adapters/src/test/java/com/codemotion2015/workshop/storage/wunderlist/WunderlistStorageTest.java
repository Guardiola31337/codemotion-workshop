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

package com.codemotion2015.workshop.storage.wunderlist;

import com.codemotion2015.workshop.events.EventsPort;
import com.codemotion2015.workshop.events.InMemoryEventsAdapter;
import com.codemotion2015.workshop.modules.horizontal.commons.Callback;
import com.codemotion2015.workshop.modules.vertical.notes.CreateNoteCommand;
import com.codemotion2015.workshop.modules.vertical.notes.CreateNoteResponse;
import com.codemotion2015.workshop.modules.vertical.notes.NoteDTO;
import com.codemotion2015.workshop.modules.vertical.notes.NotesModule;
import com.codemotion2015.workshop.storage.StoragePort;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import java.util.concurrent.Executor;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static org.junit.Assert.assertEquals;

public class WunderlistStorageTest {

  @Test public void mockWebServerWorking() throws Exception {
    MockWebServer server = new MockWebServer();
    Gson gson = new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();

    // Schedule some responses.
    server.enqueue(new MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
        .setBody("{\n"
            + "  \"id\": 1,\n"
            + "  \"task_id\": 1234,\n"
            + "  \"content\": \"Hey there\",\n"
            + "  \"created_at\": \"2013-08-30T08:36:13.273Z\",\n"
            + "  \"updated_at\": \"2013-08-30T08:36:13.273Z\",\n"
            + "  \"revision\": 999\n"
            + "}"));

    // Start the server.
    server.start();

    RestAdapter restAdapter = new RestAdapter.Builder().setExecutors(new Executor() {
      @Override public void execute(Runnable command) {
        command.run();
      }
    }, null)
        .setEndpoint(server.getUrl("/").toString())
        .setConverter(new GsonConverter(gson))
        .build();
    WunderlistAPI apiService = restAdapter.create(WunderlistAPI.class);

    EventsPort eventsPort = new InMemoryEventsAdapter();
    StoragePort storagePort = new WunderlistStorageAdapter(eventsPort, apiService);
    NotesModule notesModule = new NotesModule(storagePort, eventsPort);
    NoteDTO note = new NoteDTO(1234, "Hey there");

    eventsPort.on(CreateNoteResponse.class, new Callback<CreateNoteResponse>() {
      @Override public void call(CreateNoteResponse event) {
        assertEquals(1234, event.noteResponse.getTaskId());
        assertEquals("Hey there", event.noteResponse.getContent());
      }
    });

    notesModule.run();
    eventsPort.broadcast(new CreateNoteCommand(note));

    // Shut down the server. Instances cannot be reused.
    server.shutdown();
  }
}