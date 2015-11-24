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

import android.app.Application;
import com.codemotion2015.workshop.events.EventsPort;
import com.codemotion2015.workshop.events.OttoEventsAdapter;
import com.codemotion2015.workshop.modules.vertical.notes.NotesModule;
import com.codemotion2015.workshop.storage.InMemoryStorageAdapter;
import com.codemotion2015.workshop.storage.StoragePort;
import com.squareup.otto.Bus;

public class BaseApp extends Application {

  private EventsPort eventsPort;

  @Override public void onCreate() {
    super.onCreate();

    eventsPort = new OttoEventsAdapter(new Bus());
    StoragePort storagePort = new InMemoryStorageAdapter(eventsPort);
    NotesModule notesModule = new NotesModule(storagePort, eventsPort);

    notesModule.run();
  }

  public EventsPort obtainEventsPort() {
    return eventsPort;
  }
}
