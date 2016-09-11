# JavaWuzzy
[![Build Status](https://travis-ci.org/xdrop/fuzzywuzzy.svg?branch=master)](https://travis-ci.org/xdrop/fuzzywuzzy)
## FuzzyWuzzy Java Implementation
Fuzzy string matching for java based on the [FuzzyWuzzy](https://github.com/seatgeek/fuzzywuzzy) Python algorithm.

I've personally needed to use this but all of the other Java implementations out there either had a crazy amount of
dependencies, or simply did not output the correct results as the python one, so I've decided to properly re-implement
this in Java. Enjoy!


* No dependencies!
* Includes implementation of the super-fast [python-Levenshtein](https://github.com/ztane/python-Levenshtein/) in Java!
* Simple to use!
* Credits to the great folks at seatgeek for coming up with the algorithm ([More here](http://chairnerd.seatgeek.com/fuzzywuzzy-fuzzy-string-matching-in-python/))


## Installation
### Maven
```
<dependency>
coming soon!
</dependency>
```
### Jar release
Download the latest release [here](https://github.com/xdrop/fuzzywuzzy/releases/tag/1.0.1-alpha) and add to your classpath

## Usage

#### Simple Ratio
```java
FuzzySearch.ratio("mysmilarstring","myawfullysimilarstirng")
72
FuzzySearch.ratio("mysmilarstring","mysimilarstring")
97

```

### Partial Ratio
```java
FuzzySearch.partialRatio("similar", "somewhresimlrbetweenthisstring")
71

```

### Token Sort Ratio
```java
FuzzySearch.tokenSortPartialRatio("order words out of","  words out of order")
100
```

### Token Set Ratio
```java
FuzzySearch.tokenSetRatio("fuzzy was a bear", "fuzzy fuzzy fuzzy bear")
100
```

