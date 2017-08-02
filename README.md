# Omni-Search for Vehicle Searches

The intention of this project is to see if we can enable people to search for vehicles using their own natural language.
It would need to be able to handle misspellings, alternative spellings and typos as well as possible.
"diesel" is easy to misspell, "grey" can be spelt "gray" and "5-door" is the same as "5 door".

This project experiments with using phonetic normalisation to reduce similar-sounding 
and mis-spelt words to the same normalised token.

## Phonetic normalisation
The phonetic normalisation in this project uses an algorithm based on the 
[Soundex](https://en.wikipedia.org/wiki/Soundex) algorithm for phonetic normalisation, 
but keeping vowels. 
* Divide phrase into lower case tokens and trim off unwanted characters.
* Keep the first character of each token.
* Map "similar" letters to the same character.
* If the mapped character occurs more than once, only take the first one.

## Taking it further
Matching phonetically normalised tokens works pretty well but does lead to 
a few unexpected results such as: 
* make and model "ford ka" being identified as make "Kia"
* "prius+" being identified as "Prius"
* alphanumeric model identifiers, for example "XJS" and "IS 200t" which otherwise get confused with "XJ" and "IS 200d".

Could do a pass matching natural / un-normalised tokens first. If people do enter words with correct 
spellings, it seems perverse to normalise them and risk mis-matching them. I would expect people 
entering specific alphanumeric identifiers to do so correctly. A natural match would help with
* "ford ka" being identified as "Kia"
* alphanumeric identifiers

If we find we are ending up with models that do not match with the identified make, we could 
consider looking up models intelligently, only matching possible models for the make. A possible 
problem with this would be if we had mis-matched the make.

Consider treating "hwy" separately from vowels in the phonetic normaliser i.e. put them in a letter group if their own.
This would handle the make duplicates "Daihatsu" / "Dodge", "Honda" / "Hyundai" and "Toyota" / "Tata".

Match commonly used synonyms, for example:
* soft top, rag top, hard top, roadster => Convertible
* sport back, shooting back, tourer, sport break => Estate
* people carrier => MPV
* 4x4 => SUV
* satnav, sat nav => satellite navigation
* beamer, bimma, beama => bmw
* merc, mercedes => mercedes-benz
* roller, rolls => rolls-royce
* a/c => air conditioning
