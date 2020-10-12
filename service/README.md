# FROCKG


## Installation

use Dockerfile

```bash
docker build --tag dicegroup/frockg:1.0 .
docker run --publish 8081:8081 dicegroup/frockg:1.0
```

## Usage

send Get request to this endpoint (you can change the subject, object, predicate with valid URI
also if you want to use a virtual type set the parameter, for path length you can set 2 or 3.
```
localhost:8081/api/v1/checkFact?subject=http://dbpedia.org/resource/Bill_Gates&object=http://dbpedia.org/resource/United_States&predicate=http://dbpedia.org/ontology/nationality&isVirtualType=False&pathlength=2
```

OR

use the postman file, import the collection to your postman and send the requests to endpoints
```
FROCKG.postman_collection.json
```
