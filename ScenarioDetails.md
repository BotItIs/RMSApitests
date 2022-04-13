Introduction
The task is broken down into two parts
· Part 1 – Automation
· Part 2 – Functional Manual Testing

Part 1 Automation
-----------------

The API
-------
https://testapi.io/api/ottplatform/media
The above endpoint shows a list of music tracks and metadata. There are 14 such tracks present in the response.
Each music track contains various data elements like type, id, title_list etc.

Automate the following test scenarios for the given endpoint

Scenario 1:
1. Make a GET request to https://testapi.io/api/ottplatform/media
2. Verify that the HTTP status code of the response is 200
3. Verify that the response time of the request is below 1000 milliseconds

Scenario 2:
1. Make a GET request to https://testapi.io/api/ottplatform/media
2. Verify if the “id” field is never null or empty(“”) for all 14 items present in the data array
3. Verify that the “segment_type” field for every track is always “music”

Scenario 3:
1. Make a GET request to https://testapi.io/api/ottplatform/media
2. Verify that the “primary” field in “title_list” is never null or empty(“”) for any track

Scenario 4:
1. Make a GET request to https://testapi.io/api/ottplatform/media
2. Verify that only one track in the list has “now_playing” field in “offset” as true

Scenario 5:
1. Make a GET request to https://testapi.io/api/ottplatform/media
2. In the response headers, verify the “Date” value

[Note
- APi response time is always more than 1000 miliseconds. Hence the first scenario fails]


Part 2 – Functional Manual Testing
----------------------------------

Please write three test cases (non-automated) for the endpoint above using Gherkin.
Take a good look at the data in the API and come up with three test cases.
You can write them in Gherkin format if you wish.

[Note: Scenarios are as follows ]
### Scenario: Each track length should be more than zero seconds
     Given I make a GET request to RMS Api
     When I recieve an API response
     Then "offset" end should be more than "offset" start

### Scenario: Music track uris should match the label
      Given I make a GET request to RMS Api
      When I recieve an API response
      Then uris should include spotify urls when label is "Spotify"
      Then uris should include apple music urls when label is "Apple Music",

### Scenario: Type should be music track
      Given I make a GET request to RMS Api  
      When I recieve a api response
      Then all data type should be "music_track"