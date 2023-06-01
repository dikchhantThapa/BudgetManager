# BudgetManager

This program displays a Budget Manager menu that consists of 4 items and functions accordingly. 
  1. **Add income** - We must track both our expenses and our income. When this item is selected, the program asks to enter the amount of income.
  2. **Add Purchase** - This item adds a purchase to the list.
  3. **Show the list of purchases** - This menu item displays a list of all expenses and incomes in the order they were made.
  4. **Balance** - Shows the balance.
  5. **Exit** - Exits the program. For convenience and ease of readability, this is item **0**, not item 5.    


## Example
Notice the greater-than symbol followed by a space (> ) is not a part of user input, it just represents the user input after that sign and space. 

```
Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 1

Enter income:
> 1000
Income was added!

Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 4

Balance: $1000.00

Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 3

The purchase list is empty

Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 2

Enter purchase name:
>Red Fuji Apple
Enter its price:
> 5.99
Purchase was added!

Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 3

Red Fuji Apple $5.99
Total sum: $5.99

Choose your action:
1) Add income
2) Add purchase
3) Show list of purchases
4) Balance
0) Exit
> 0

Bye!

```
