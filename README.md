# Llinguistic-summarization

## Description
Program to perform linguistic aggregation of the database content and then generate linguistic summaries of the database. 

[Earthquake Data 2010-2023 Latest](https://www.kaggle.com/datasets/rushikesh6548/earthquake-data-2010-2023-latest)

The dataset utilized is the "Earthquake Data 2010-2023", which describes earthquakes detected from 2010 to 2023, limited to the years 2022-2023 and records containing complete information about two types of earthquakes - local earthquakes and those of short duration. Each record contains 22 attributes, from which 11 numerical attributes were selected, and linguistic variables were created based on them.

### Attributes
- time: The time at which the earthquake occurred, in ISO 8601 format (yyyy-mm-ddThh:mm:ss.mmmZ).
- latitude: The latitude of the earthquake epicenter in decimal degrees.
- longitude: The longitude of the earthquake epicenter in decimal degrees.
- depth: The depth of the earthquake hypocenter in kilometers.
- mag: The magnitude of the earthquake on the Richter scale.
- gap: The largest azimuthal gap between azimuthally adjacent stations (in degrees).
- dmin: The closest distance to the earthquake epicenter on the earth's surface (in degrees).
- rms: The root-mean-square of the residuals of the earthquake location solution (in seconds).
- horizontalError: The horizontal error ellipse (semi-major axis) of the earthquake location solution (in kilometers).
- depthError: The depth error ellipse (semi-major axis) of the earthquake location solution (in kilometers).
- magError: The standard error of the earthquake magnitude.

### Measures of Linguistic Summaries Quality.
- T1 – degree of truth
- T2 – degree of imprecision
- T3 – degree of coverage
- T4 – degree of accuracy
- T5 – length of the summary
- T6 – degree of quantifier imprecision
- T7 – degree of relative quantifier cardinality
- T8 – degree of relative summarizer cardinality
- T9 – degree of qualifier imprecision
- T10 – degree of relative qualifier cardinality
- T11 – length of the qualifier

### Example of linguistic summary
- Above 6000 earthquakes have group
of continents: Asia.

### [Documentation - PL](./Documentation.pdf)