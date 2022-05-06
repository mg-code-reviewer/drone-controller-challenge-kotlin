# Drone Controller Challenge

## Description

Our merchant has a drone flying in the field and spraying fertilizer to protect their crops.
Their field is divided in sectors of 10 x 10. Our merchant can control this Drone via REST by commands:

1. Turn left or right

example request
```json
{
  "rotation": "L"
}
```

example response:
```json
{
  "data": {
    "direction": {
      "name": "EATS"
    },
    "position": {
      "x": 9,
      "y": 4
    },
    "north": {
      "name": "NORTH"
    },
    "east": {
      "name": "EATS"
    },
    "south": {
      "name": "SOUTH"
    },
    "west": {
      "name": "WEST"
    }
  }
}
```
2. Go forward, example response:
```json
{
  "data": {
    "direction": {
      "name": "SOUTH"
    },
    "position": {
      "x": 9,
      "y": 4
    },
    "north": {
      "name": "NORTH"
    },
    "east": {
      "name": "EATS"
    },
    "south": {
      "name": "SOUTH"
    },
    "west": {
      "name": "WEST"
    }
  },
  "errors": null
}
```
3. Where are you? Example response:
```json
{
  "data": {
    "x": 9,
    "y": 5
  }
}
```

## Task

We ask you to identify and fix as many issues as possible in the code we provided.
You should also pay attention to make notes with all the recommendations for the person who developed this solution.

### Constraints

* The drone can look in 4 directions (North, South, West, East)
* The drone can only turn to neighbouring directions (e.g., if the drone is facing North it
  can turn only to West or East)

## Delivery

Please create your own private repository from this template repository. We ask for you to return this task to us no
more than 7 days after the day that you receive it. We recommend not spending more than 4 hours in total. Once you are
happy with your output, please share the GitHub repository of the code with the following account: `mg-code-reviewer`
Good luck and thank you for your time and consideration in being our next SumUpper!

# How run the project

The project could be run by the following command:

```bash
./gradlew bootRun
```

# Recommendations

## What types of violations were fixed?

## What ideas for future developement do you have for this project? (functionality, code)
