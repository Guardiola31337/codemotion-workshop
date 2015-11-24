Feature: Create note feature

  Scenario: As a user I can create a note into my app
    Then I press view with id "fab"
    Then I wait for the "NoteForm" screen to appear
    Then I enter text "1234" into field with id "input_title"
    Then I enter text "Hey codemotion! What's up?" into field with id "input_description"
    Then I press view with id "btn_save_note"
    Then I wait
    Then I see the text "1234" in field with id "title"
    Then I see the text "Hey codemotion! What's up?" in field with id "description"

