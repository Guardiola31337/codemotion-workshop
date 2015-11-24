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

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(MyRunner.class) public class NotesAppTest {

  @Rule public ActivityTestRule<Notes> mActivityRule = new ActivityTestRule<>(Notes.class);

  @Test public void titleViewShouldExist() {
    onView(withId(R.id.title)).check(matches(notNullValue()));
  }

  @Test public void descriptionViewShouldExist() {
    onView(withId(R.id.description)).check(matches(notNullValue()));
  }

  @Test public void fabShouldExist() {
    onView(withId(R.id.fab)).check(matches(notNullValue()));
  }

  @Test public void whenPressingFabNoteFormActivityShouldStart() {
    onView(withId(R.id.fab)).check(matches(notNullValue()));
    onView(withId(R.id.fab)).perform(click());

    onView(withId(R.id.input_title)).check(matches(notNullValue()));
    onView(withId(R.id.input_description)).check(matches(notNullValue()));
    onView(withId(R.id.btn_save_note)).check(matches(notNullValue()));
    onView(withId(R.id.btn_save_note)).check(matches(withText("Save note")));
  }
}