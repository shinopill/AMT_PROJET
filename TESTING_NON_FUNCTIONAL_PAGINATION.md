"What is the impact of using pagination between the business and the resources tier?"

In order to have this response, we tried the following test.

We made sets of data 10 / 1'000 / 100'000 user in our DB and we try to logged in as an admin to see how much time we use to load the page that has to make a query to the DB.

For each of this dataset, we made tests with 10 an 100 users doing each 100 request of logging.

The results are all in the .csv in the Jmeter repository in the Test repository.

Analazing the results, we can conculte that with "huge" amount of data, the query to select the first 10 of the DB was very slow and the user experience would be bad if we had no pagination.

Everytime, would have to get all the users in the database and this query would take so much time and memory that we woudn't be able to run our service.
