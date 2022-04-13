Feature: RMS Api
  As a RMS user
  I want to build an api
  So that I can view a list of all music tracks and metadata

  Scenario: Verify that user gets a success response from RMS API within a second
    When I make a GET request to RMSApi
    Then HTTP status code of the response should be 200
    And Response should match the expected response schema
    And  the response time of the request should be below 1000 milliseconds

  Scenario: Verify that segment type is always music
    When I make a GET request to RMSApi
    Then "id" field should never be null for all 14 items present in the data array
    And  "segment_type" field for every track is always "music"

  Scenario: Verify that Primary title list for all the tracks is never null or empty  
    When I make a GET request to RMSApi
    Then  "primary" field in "title_list" is never null or empty("") for any track

  Scenario: Verify that only one track in the list has “now_playing” field in “offset” as true
    When I make a GET request to RMSApi
    Then only one track in the list has "now_playing" field in "offset" as true

  Scenario: Verify Date value in response header
    When I make a GET request to RMSApi
    Then the response header "Date" has a specific date  