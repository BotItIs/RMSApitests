Feature: RMS Api
  As a RMS api user
  I want to view a list of music tracks and metadata

  Scenario: User should get a success response from RMS API
    Given I make a GET request to "https://testapi.io/api/ottplatform/media"
    Then HTTP status code of the response should be "200"
    And  the response time of the request should be below "1000" milliseconds

  Scenario: Segment type should always be music
    Given I make a GET request to "https://testapi.io/api/ottplatform/media"
    Then "id" field should never be null
    And  "segment_type" field for every track is always "music"