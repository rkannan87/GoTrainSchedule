# GoTrainSchedule
 
GO Train Schedule API Service
Your job is to build an API that provides a (simplified) train timetable with weekday train
times leaving Union Station.
Requirements:
Your service must be a Spring Boot service.
The times must be stored in an in-memory database. Spring's default (H2) is fine.
The timetable must be loaded into the application DB during startup. It's up to you to
choose a way to do this. For convenience, there's a CSV version of the timetable in
the timetable.csv file, and one in JSON format in the timetable.json file.
The service must serve the timetable under the following endpoints:
GET /schedule
Returns the entire timetable as a JSON array. Example:
[
{
"id": 1,
"line": "Lakeshore",
"departure": 800,
"arrival": 900
},
{
"id": 2,
"line": "Lakeshore",
"departure": 1000,
"arrival": 1100
},
{"..." : "..."}
]
GET /schedule/{line}
Returns a JSON array with the timetable for that specific line.
If the line does not exist, the request should return HTTP 404 (Not Found).
GET /schedule/{line}?departure={time}
Returns a one-element JSON array with the record for the specified train line and
departure time, e.g. GET /schedule/Kitchener?departure=1215 should return this:
[
{
"id": 15,
"line": "Kitchener",

"departure": 1215,
"arrival": 1300
}
]
If a line does not have any trains departing at the specified time, the request should still
be successful, but should return an empty array.
If the line does not exist, the request should still return HTTP 404 (Not Found).
The departure time should accept times in two different formats:
• 24-hour "military" format: a single, three or four-digit number, with the hours and
minutes in sequence, e.g. 700 (7 am), 1215 (12:15 pm), 1755 (5:55 pm).
• 12-hour format: hours and minutes separated by a colon and ending with
either am or pm, like 12:00am, 3:40pm, 7:55am. For simplicity, in this format the colon
and the "am/pm" bits are mandatory, so times like 6am or 11:00 are considered
invalid.
If departure is not a valid 12 or 24-hour format time, the service should respond with
HTTP 400 (Bad Request).
