# JavaWuzzy
[![Build Status](https://travis-ci.org/xdrop/fuzzywuzzy.svg?branch=master)](https://travis-ci.org/xdrop/fuzzywuzzy)  [ ![Download](https://api.bintray.com/packages/xdrop/FuzzyWuzzy-Java/fuzzywuzzy/images/download.svg?version=2.0.0) ](https://github.com/xdrop/fuzzywuzzy/releases)

## FuzzyWuzzy Java Implementation
Fuzzy string matching for java based on the [FuzzyWuzzy](https://github.com/seatgeek/fuzzywuzzy) Python algorithm. The algorithm uses [Levenshtein distance](https://en.wikipedia.org/wiki/Levenshtein_distance) to calculate similarity between strings.

I've personally needed to use this but all of the other Java implementations out there either had a crazy amount of
dependencies, or simply did not output the correct results as the python one, so I've decided to properly re-implement
this in Java. Enjoy!


* No dependencies!
* Includes implementation of the super-fast [python-Levenshtein](https://github.com/ztane/python-Levenshtein/) in Java!
* Simple to use!
* Lightweight!
* Credits to the great folks at seatgeek for coming up with the algorithm ([More here](http://chairnerd.seatgeek.com/fuzzywuzzy-fuzzy-string-matching-in-python/))


## Installation
### Maven Central
```xml
<dependency>
    <groupId>me.xdrop</groupId>
    <artifactId>fuzzywuzzy</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Gradle
```gradle
compile 'me.xdrop:fuzzywuzzy:2.0.0'
```

### Jar release
Download the latest release [here](https://github.com/xdrop/fuzzywuzzy/releases) and add to your classpath

## Usage

#### Calculating ratios using Levenshtein methods
```java
// Import all ScoringMethods from Levenshtein
import static me.xdrop.matchr.levenshtein.Levenshtein.Methods.*;

SIMPLE_RATIO.apply("mysmilarstring", "myawfullysimilarstirng");
// Result: 72
SIMPLE_RATIO.apply("mysmilarstring", "mysimilarstring");
// Result: 97

PARTIAL_RATIO.apply("similar", "somewhresimlrbetweenthisstring");
// Result: 71

TOKEN_SORT_SIMPLE.apply("order words out of", "  words out of order");
// Result: 100
TOKEN_SORT_PARTIAL.apply("order words out of", "  words out of order");
// Result: 100

TOKEN_SET_SIMPLE.apply("fuzzy was a bear", "fuzzy fuzzy fuzzy bear");
// Result: 100
TOKEN_SET_PARTIAL.apply("fuzzy was a bear", "fuzzy fuzzy fuzzy bear");
// Result: 100

WEIGHTED_RATIO.apply("The quick brown fox jimps ofver the small lazy dog", "the quick brown fox jumps over the small lazy dog");
// Result: 97
```

#### Extract strings using Levenshtein
```java
// Create a Levenshtein engine
FuzzyWuzzy<Levenshtein> fuzzy = FuzzyWuzzy.algorithm(Levenshtein.FACTORY);

fuzzy.extractBest("cowboys", Arrays.asList("Atlanta Falcons", "New York Jets", "New York Giants", "Dallas Cowboys"));
// Result:
// - Result[string=Dallas Cowboys;score=90;originIndex=3]#713338599

fuzzy.extractLimited("goolge", Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"), 3);
// Results:
// - [Result[string=google;score=83;originIndex=0]#468121027, Result[string=googleplus;score=75;originIndex=5]#1804094807, Result[string=plexoogl;score=43;originIndex=7]#951007336]

fuzzy.extractAll("goolge", Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"));
// With score threshold:
fuzzy.extractAll("goolge", Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"), 40);
// First results:
// - [Result[string=google;score=83;originIndex=0]#2001049719, Result[string=bing;score=23;originIndex=1]#1528902577, Result[string=facebook;score=29;originIndex=2]#1927950199, Result[string=linkedin;score=29;originIndex=3]#868693306, Result[string=twitter;score=15;originIndex=4]#1746572565, Result[string=googleplus;score=75;originIndex=5]#989110044, Result[string=bingnews;score=29;originIndex=6]#424058530, Result[string=plexoogl;score=43;originIndex=7]#321001045]
// Second results:
// - [Result[string=google;score=83;originIndex=0]#791452441, Result[string=googleplus;score=75;originIndex=5]#834600351, Result[string=plexoogl;score=43;originIndex=7]#471910020]

fuzzy.extractAllSorted("goolge", Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"));
// With score threshold:
fuzzy.extractAllSorted("goolge", Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"), 40);
// First results:
// - [Result[string=google;score=83;originIndex=0]#531885035, Result[string=googleplus;score=75;originIndex=5]#1418481495, Result[string=plexoogl;score=43;originIndex=7]#303563356, Result[string=bingnews;score=29;originIndex=6]#135721597, Result[string=linkedin;score=29;originIndex=3]#142257191, Result[string=facebook;score=29;originIndex=2]#1044036744, Result[string=bing;score=23;originIndex=1]#1826771953, Result[string=twitter;score=15;originIndex=4]#1406718218]
// Second results:
// - [Result[string=google;score=83;originIndex=0]#245257410, Result[string=googleplus;score=75;originIndex=5]#1705736037, Result[string=plexoogl;score=43;originIndex=7]#455659002]
```

#### Extract using any object
Any `extractXYZ` method can accept any type of object. Then, you need to define a string-function:
```java
public class Main {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("xdrop"));
        list.add(new User("Kaleidox"));
        list.add(new User("Goliath"));
        
        String search = "kaljidox";
        FuzzyWuzzy<Levenshtein> fuzzy = FuzzyWuzzy.algorithm(Levenshtein.FACTORY);
        
        fuzzy.extractBest(search, list, User::getName);
        // Result#toString:
        matchr
    }
}

publime.xdrop.matchrprivate final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```


## Credits

- Tobias Burdow (burdoto)
- seatgeek
- Adam Cohen
- David Necas (python-Levenshtein)
- Mikko Ohtamaa (python-Levenshtein)
- Antti Haapala (python-Levenshtein)
