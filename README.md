| API                             | Method              | Post Request                |
|---------------------------------|---------------------|-----------------------------|
| /login                          | POST                | To login                    |
| /logout                         | POST                | To logout                   |
| /register                       | POST                | To register an account      |
| /profile/{userID}               | POST/GET            | To update profile           |
| /course                         | GET                 | N/A                         |
| /course/{courseID}/{userID}     | POST/GET            | To update user's properties |
| /course/{courseID}/participants | GET                 | N/A                         |
| /course/{courseID}              | POST/GET/PUT/DELETE | To update course content    |
| /lesson                         | GET                 | N/A                         |
| /lesson/{lessonID}              | POST/GET/PUT/DELETE | To update lesson            |
| /exam                           | GET                 | N/A                         |
| /exam/{examID}                  | POST/GET/PUT/DELETE | To update exam              |
| /exam/{examID}/submit           | POST                | To submit exam              |
| /user/{userID}/courses          | GET                 | N/A                         |

