# Endpoint documentation

## [Scraper] Refresh talks
### Description
Simulates a call to a remote server to fetch, process and store data on TedTalks.
Use this endpoint to populate the database. It reads and stores a random number of TedTalks (between 0 and 10, inclusive).

### Request
|                    |                                |
|--------------------|--------------------------------|
| **Method**         | `GET`                          |
| **URL**            | `localhost:8080/talks/refresh` |
| **Request params** | `None`                         |
| **Authorization**  | `None`                         |
| **Headers**        | `None`                         |
| **Request body**   | `None`                         |

### Example response:

Status code: `200 OK`<br/>
Response body:<br/>
```JSON
{
    "newTalksFound": 8
}
```
---



## [Talks] Get all
### Description
Retrieves all talks. Note that there is not limit on the maximum number of items returned.
The response also includes a `total` that represents the total number of talks in the database.


### Request
|                    |                               |
|--------------------|-------------------------------|
| **Method**         | `GET`                         |
| **URL**            | `localhost:8080/talks/getAll` |
| **Request params** | `None`                        |
| **Authorization**  | `None`                        |
| **Headers**        | `None`                        |
| **Request body**   | `None`                        |

### Example response:

Status code: `200 OK`<br/>
Response body:<br/>
```JSON
{
  "total": 2,
  "talks": [
    {
      "id": 1,
      "author": {
        "name": "Ozawa Bineshi Albert"
      },
      "title": "Climate action needs new frontline leadership",
      "views": 404000,
      "likes": 12000,
      "url": "https://ted.com/talks/ozawa_bineshi_albert_climate_action_needs_new_frontline_leadership",
      "influence_factor": 208000,
      "year": 2021
    },
    {
      "id": 2,
      "author": {
        "name": "Sydney Iaukea"
      },
      "title": "The dark history of the overthrow of Hawaii",
      "views": 214000,
      "likes": 6400,
      "url": "https://ted.com/talks/sydney_iaukea_the_dark_history_of_the_overthrow_of_hawaii",
      "influence_factor": 110200,
      "year": 2022
    }
  ]
}
```
---



## [Talks] Get by id
### Description
Returns a single talk by ID.

### Request
|                    |                                   |
|--------------------|-----------------------------------|
| **Method**         | `GET`                             |
| **URL**            | `localhost:8080/talks/get?id=123` |
| **Request params** | (Required integer) `id`           |
| **Authorization**  | `None`                            |
| **Headers**        | `None`                            |
| **Request body**   | `None`                            |

### Example response:

Status code: `200 OK`<br/>
Response body:<br/>
```JSON
{
  "id": 1,
  "author": {
    "name": "Ozawa Bineshi Albert"
  },
  "title": "Climate action needs new frontline leadership",
  "views": 404000,
  "likes": 12000,
  "url": "https://ted.com/talks/ozawa_bineshi_albert_climate_action_needs_new_frontline_leadership",
  "influence_factor": 208000,
  "year": 2021
}
```
---



## [Talks] Get most influential talks / year (optional endpoint)
### Description
Retrieves the most influential TedTalks per year. Note that if 2 or more TedTalks have the same MAXIMUM influence for the same 
year, both are included in the response.

### Request
|                    |                                               |
|--------------------|-----------------------------------------------|
| **Method**         | `GET`                                         |
| **URL**            | `localhost:8080/talks/mostInfluentialPerYear` |
| **Request params** | `None`                                        |
| **Authorization**  | `None`                                        |
| **Headers**        | `None`                                        |
| **Request body**   | `None`                                        |

### Example response:

Status code: `200 OK`<br/>
Response body:<br/>
```JSON
{
  "talks": [
    {
      "id": 13,
      "author": {
        "name": "Daniel H. Pink"
      },
      "title": "What regret can teach you about living a good life",
      "views": 622000,
      "likes": 18000,
      "url": "https://ted.com/talks/daniel_h_pink_what_regret_can_teach_you_about_living_a_good_life",
      "influence_factor": 320000,
      "year": 2022
    },
    {
      "id": 8,
      "author": {
        "name": "Gay Gordon-Byrne"
      },
      "title": "You deserve the right to repair your stuff",
      "views": 455000,
      "likes": 13000,
      "url": "https://ted.com/talks/gay_gordon_byrne_you_deserve_the_right_to_repair_your_stuff",
      "influence_factor": 234000,
      "year": 2021
    },
    {
      "id": 10,
      "author": {
        "name": "Dwan Reece"
      },
      "title": "The origins of blackface and Black stereotypes",
      "views": 584000,
      "likes": 17000,
      "url": "https://ted.com/talks/dwan_reece_the_origins_of_blackface_and_black_stereotypes",
      "influence_factor": 300500,
      "year": 2019
    }
  ]
}
```
---



## [Talks] Delete a talk
### Description
Deletes a TedTalk by ID

### Request
|                    |                                      |
|--------------------|--------------------------------------|
| **Method**         | `DELETE`                             |
| **URL**            | `localhost:8080/talks/remove?id=123` |
| **Request params** | (Required integer) `id`              |
| **Authorization**  | `None`                               |
| **Headers**        | `None`                               |
| **Request body**   | `None`                               |

### Example response:

Status code: `200 OK`<br/>
Response body: `None`
---



## [Talks] Update talk details
### Description
Updates the talk details by ID.<br/>
Notes:
- The `Author` cannot be edited via this endpoint (in the real world, we could add another endpoint for that)
- The `Influence` cannot be edited directly as it is the result of a calculation with: `views` and `likes`. Changing the `views` and 
`likes` **will** in turn update the `influence`.

### Request
|                    |                                            |
|--------------------|--------------------------------------------|
| **Method**         | `PUT`                                      |
| **URL**            | `localhost:8080/talks/update?id=123`       |
| **Request params** | (Required integer) `id`                    |
| **Authorization**  | `None`                                     |
| **Headers**        | `None`                                     |

 **Request body:**
```JSON
 {
    "author": {
        "name": "Sydney Iaukea"
    },
    "title": "Why is China?",
    "views": 123000,
    "likes": 400,
    "url": "https://ted.qqks/james_k_thornton_why_is_china_appointing_judges_to_combat_climate_change",
    "year": 2
}
```

### Example response:

Status code: `200 OK`<br/>
Response body: `None`
---



## [Authors] Get most influential authors
### Description
Gets the most influential authors (speakers) from the TedTalks we have in record.

### Request
|                    |                                                  |
|--------------------|--------------------------------------------------|
| **Method**         | `GET`                                            |
| **URL**            | `localhost:8080/authors/mostInfluential?limit=5` |
| **Request params** | (Optional integer. Default: `10`) `limit`        |
| **Authorization**  | `None`                                           |
| **Headers**        | `None`                                           |

**Request body:** `None`

### Example response:

Status code: `200 OK`<br/>
Response body: 
```JSON
{
    "authors": [
        {
            "name": "Ozawa Bineshi Albert",
            "influence": "1666667"
        },
        {
            "name": "Daniel H. Pink",
            "influence": "320000"
        },
        {
            "name": "Dwan Reece",
            "influence": "300500"
        }
    ]
}
```
---