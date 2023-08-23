
# Health Risk Analytics

### What are you going to learn?
* Implementing a class with the help of unit tests
* Working with dates
* Working with <span style="color:red">Random</span>

## Story

A healthcare company has contacted you to help them implement a health risk analytics software,
with specific focus on the risks related to being overweight.

They have a database populated with data about their customers. They record the date of birth,
gender, height and the weight of their patients. The weights are updated once every year to keep track of the changes.

They prepared a list of calculations to be performed on the dataset. Your task is to provide the
implementation of these features in order to generate useful insights about their patients.

There are unit tests provided for the <span style="color:red">AnalyticsService</span> class. Be sure to make
good use of them, as they can make your life easier. When you think you're done with implementing a method,
run the tests for it to see if your implementation is correct!

## Tasks

**Age calculation**
The company wants the software to calculate the age of every customer. For this, you need to implement
the calculateAge method of the <span style="color:red">AnalyticsService</span> class. The
<span style="color:red">Person</span> class holds all the information about a patient. The birth date
is recorded as a <span style="color:red">String</span> field in the <span style="color:red">Person</span>
class. You need to come up with a way to calculate the person's current age using the birth date.

1. The <span style="color:red">calculateAge</span> method in the <span style="color:red">AnalyticsService</span>
	class is implemented and it correctly returns a person's current age in years.

**Body-Mass Index (BMI) calculation**

The next step is to create a function that calculates the Body-Mass Index (BMI) for each patient. The
<span style="color:red">Person</span> class contains an <span style="color:red">int</span> array that
holds the patient's reported weights for each year, with the latest year being the first value in the
array. The BMI needs to be calculated for each reported year. The formula is the following:

BMI = kg/m2,

where kg is a person's weight in kilograms and m2 is their height in metres squared.

1. The <span style="color:red">calculateBmiSeries</span> method in the <span style="color:red">AnalyticsService</span>
	class is implemented and it correctly calculates the BMI values for each related weight in the
	<span style="color:red">weights</span> array of the <span style="color:red">Person</span> class.
	It returns an array of <span style="color:red">double</span> where each element corresponds to the
	value with the same index in the <span style="color:red">weights</span> array of the
	<span style="color:red">Person</span> class.
	
**Overweight risk calculation**

Next, we need method that determines if a person currently has the risk of being overweight. A person can
be considered at the risk of being overweight if their Body-Mass Index (BMI) was greater than **25** for the
last **3** years. That means that the first 3 elements of the BMI series calculated for that person are all
greater than 25.

If a person doesn't have the weight data for the last 3 years, the result should be <span style="color:red">Unknown</span>.

1. The <span style="color:red">determineWeightCondition</span> method in the <span style="color:red">AnalyticsService</span>
	class is implemented and it the returns the correct <span style="color:red">WeightCondition</span> enum
	corresponding to the person having an overweight risk or being healthy.
	
**Statistics**

Finally, we need some statistics. The company wants to know what percentage of their customers can be counted as
having an overweight risk. For this we need a method that receives an array of <span style="color:red">Person objects</span>,
determines the overweight risk for each and then compares the number of persons with overweight risk to the total
number of customers, otherwise known as the **Overweight Risk Ratio (ORR)**.

1. The <span style="color:red">calculateOrr</span> method in the <span style="color:red">AnalyticsService</span>
	class is implemented and it returns the correct Overweight Risk Ratio for the dataset
	
**Extract the AnalyticsService interface**

When you're done with developing the <span style="color:red">AnalyticsService</span> class, create an interface
for it, and use it through the interface in the other parts of the application. You can use an IDE feature for this.

1. The <span style="color:red">AnalyticsService</span> interface has been created.

**User interface**

Now that the all the analytics features are implemented, we need a basic UI to show the calculation results.
Create a <span style="color:red">HealthRiskUi</span> class that receives an instance of
<span style="color:red">AnalyticsService</span> in the constructor, and has public methods to print the
results of the analysis to the console.

1. The <span style="color:red">HealthRiskUi</span> class is created and placed in a ui package.

2. A method has been created that invokes the relevant functions in the <span style="color:red">AnalyticsService</span>
	for each person in the database and diplays their current age, gender, the BMI series, and the overweight risk.

3. A method has been created that invokes the statistics functions of the <span style="color:red">AnalyticsService</span>
	and displays the results on the console.

**Application**

All the compnents are ready! Now we just need to do some scaffolding. Complete the <span style="color:red">main</span>
method in the <span style="color:red">Application</span> class that creates a <span style="color:red">Person</span>
array, an instance of <span style="color:red">AnalyticsService</span>, and a UI object. It then it calls the
public methods of the <span style="color:red">HealthRiskUi</span> class to show the analysis results.

1. The <span style="color:red">main</span> method of the <span style="color:red">Application</span> class is
	implemented and all the required calls are made to show the calculation results on the console.

**OPTIONAL TASK: More statistics**

The company has requested to create a bit more statistics for them. Complete the tasks below for some extra challenge!

1. A method is created that calculates the Overweight Risk Ratio (ORR) per gender. The method has two inputs: a
	<span style="color:red">Person</span> array and a <span style="color:red">Gender</span> enum. From these, it calculates the ratio of persons with overweight risk in the dataset for the specified gender.

2. A method is created that calculates the Overweight Risk Ratio (ORR) per age group. The method has two inputs: a
	<span style="color:red">Person</span> array and something that specifies the age group. From these, it
	calculates the ratio of persons with overweight risk in the dataset for the specified age group. The company
	is interested in the following age groups:

Young adults: 18-25

Adults: 26-44

Middle aged: 45-59

Old: 60+


3. The new statistical methods are invoked from the UI and the results are displayed on the console.

## Hints

* The <span style="color:red">PersonProvider</span> class has a method that randomly generetes an array of Person
	objects with the specified length. Feel free to change the length to suit your development and debugging needs.
	For example, in the early phase it may be easier to work with a smaller dataset. However, when it comes to the
	statistics, you probably want more Person objects in order to get some sensible statistics.
* The <span style="color:red">PersonProvider</span> also contains the date format it uses. This can help you
	creating LocalDate objects from the strings.

## Background materials

[Java 8 Date/Time API](https://www.baeldung.com/java-8-date-time-intro) 

[Unit testing with JUnit](https://www.baeldung.com/junit) 

[Random number generator in Java](https://www.baeldung.com/java-generating-random-numbers) 

[Convert strings to dates in Java](https://www.baeldung.com/java-string-to-date) 