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

package com.codemotion2015.workshop.modules.vertical.notes;

public class NoteDTO {
  private final int taskId;
  private final String content;

  public NoteDTO(int taskId, String content) {
    this.taskId = taskId;
    this.content = content;
  }

  public int getTaskId() {
    return taskId;
  }

  public String getContent() {
    return content;
  }
}
