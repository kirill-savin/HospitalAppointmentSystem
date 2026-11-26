<h2>Hospital Appointment System</h2>

This project is the backend for a system that manages appointments, and keeps record of the doctors and patients for one hospital building.

The .json file visible on the main page contains Postman requests you could use to test (note: the Postman tests included within are not working as intended, it is better to run the requests manually)

Running deployment URL: https://hospitalappointmentsystem-1-kg8h.onrender.com/

<h1>ENDPOINTS TABLE</h1>

<h2>PATIENT:</h2>

| Endpoint                                     | What it's for                                                 |
|:---------------------------------------------|:--------------------------------------------------------------|
| GET                                          |
| `/api/patients`                              | Returns All patients                                          |
| `/api/patients/{id}`                         | Returns The patient with that id                              |
| POST                                         |
| `/api/patients`                              | Creates a new patient using a json block                      |
| PUT                                          |
| `/api/patients/{id} `                        | Updates The patient with that id using a json block           |
| DELETE                                       |
| `/api/patients/{id} `                        | Deletes The patient with that id                              |
<h2>DOCTOR:</h2>

| Endpoint                                     | What it's for                                                 |
|:---------------------------------------------|:--------------------------------------------------------------|
| GET                                          |
| `/api/doctors`                               | Returns All doctors                                           |
| `/api/doctors/{id}`                          | Returns The doctor with that id                               |
| POST                                         |
| `/api/doctors`                               | Creates a new doctor using a json block                       |
| PUT                                          |
| `/api/doctors/{id} `                         | Updates The doctor with that id using a json block            |
| DELETE                                       |
| `/api/doctors/{id} `                         | Deletes The doctor with that id                               |
<h2>APPOINTMENT:</h2>

| Endpoint                                     | What it's for                                                 |
|:---------------------------------------------|:--------------------------------------------------------------|
| GET                                          |
| `/api/appointments`                          | Returns All appointments                                      |
| `/api/appointments/{id}`                     | Returns The appointment with that id (not of the UUID type)   |
| POST                                         |
| `/api/appointments`                          | Creates a new appointment using a json block                  |
| PUT                                          |
| `/api/appointments/{id} `                    | Updates The appointment with that id using a json block       |
| DELETE                                       |
| `/api/appointments/{id} `                    | Deletes The appointment with that id                          |





