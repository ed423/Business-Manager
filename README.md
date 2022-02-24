# E-Commerce Business Expense Tracker (Personal Project)

## Project Summary

This application is a **business expense/budget tracker**. It tracks a business':

* Account balance
* Revenue
* Profit
* Expenses

This application is aimed at small businesses hosted online or partially online, as running an online business involves
a tremendous amount of effort to succeed. As an e-commerce business owner myself, I have found that the task of managing
financial resources is a daunting at times. I would like to have the ability to keep track of the inflow and outflow of 
finances in a simple manner, without having to decrease profits by hiring an employee to do so.

## User Stories

* As a user, I would like to be able to view my last transaction
* As a user, I would like to be able to add an expense to my list of transactions
* As a user, I would like to be able to remove a transaction from my list of transactions
* As a user, I would like to be able to select a specific expense and view more details about it
* As a user, I would like to be able to save my list of transactions to a file
* As a user, I would like to be able to load my list of transactions from a file

## Examples of log messages:
Thu Nov 25 09:57:00 PST 2021

Transaction added to transaction list.

Thu Nov 25 09:57:01 PST 2021

Saved transaction list to file and removed all transactions from transaction list. 

Thu Nov 25 09:57:03 PST 2021

Transaction added to transaction list.

Thu Nov 25 09:57:03 PST 2021

Loaded transaction list from file.

Thu Nov 25 09:57:05 PST 2021

Returned more details about the transaction.

## Aspects to improve on if I had more time:
- Extract some, or most of the classes in my GUI class into separate classes in the ui package, in order to make the GUI functionality easier to understand. 
- Create a superclass, either an abstract class or an interface, to reduce duplication in my classes. An example of a 
location in which I can implement these changes is in my button listener classes. I could create a superclass, and then
override the methods in order to achieve the correct functionality for each button listener.
