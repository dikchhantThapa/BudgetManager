# BudgetManager

This program reads and processes every line user inputs in the format *"Item Name and $Amount"*. It reads data from the console and at the end displays the list of all purchases and its total amount as: `Total: $22.00`

To end the input, the user should type End-of-file symbol that tells your operating system that no more input will be provided(**Ctrl+D** on Linux and Mac and **Ctrl+Z** on Windows). 

## Example
Notice the greater-than symbol followed by a space (> ) is not a part of user input, it just represents the user input after that sign and space. 

```
> Almond 250gm $35.43        
> LEGO DUPLO Town Farm Animals $10.10              
> Sensodyne Pronamel Toothpaste $19.74      
> Hershey's milk chocolate bars $8.54            
> Gildan LT $8.61        

Almond 250gm $35.43          
LEGO DUPLO Town Farm Animals $10.10           
Sensodyne Pronamel Toothpaste $19.74            
Hershey's milk chocolate bars $8.54                
Gildan LT $8.61           
  
Total: $82.42

```