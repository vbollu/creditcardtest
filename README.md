# creditcardtest

This is a Java based back-end code

Required installations:
 Java 8
 Maven

Steps to run:

1. Clone the project from https://github.com/vbollu/creditcardtest
2. Run below maven command in the root directory

  mvn clean install
  
3. Above command generates target folder and "CreditCardTest-0.0.1-SNAPSHOT.jar" file inside it. 
4. execute jar through command line in below format
   
   java -jar CreditCardTest-0.0.1-SNAPSHOT.jar
   This will run application on 8081 port (port can be changed by updating application.properties under resources folder) 
5. Now api is ready to serve /creditcard/validate/{card number}
      ex: http://localhost:8081/creditcard/validate/341111111111111

Implementation: 
 Data class : setting up initial data  (startid digits and lenghts for credit cards)
            - Used 2 maps, one to find card type based on starting digits and another to find valid lengths of card
 CreditCartValidateController class: This is the rest api serves the actual requests
   

Further changes to be considered:
- We can use Trie Data structure to store startind digits to find card types
     class Trie{
        Map<char, Trie> map =  new HashMap<>();
        String cardType = "";
     }
     This will improve search card type based on starting digits.
- Adding api security
- Adding more unit tests
- Handling exceptions  (Add error codes for possible exceptions)
