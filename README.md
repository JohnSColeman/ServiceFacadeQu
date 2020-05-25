# An interview code test about data consistency
This question is presented in the form of some C# like code something like this:

```java
public class Payment {
    
    public void Charge(Double amount, String description) throws ... {
        
        success = Gateway(amount, description...
        
        if (!success) {
            throw new Exception...
        }
        
        Database(amount, description)...
    }

    private void Gateway(Double amount, String description) {

    }
    
    private void Database(Double amount, String description) {
        
    }
}
```
There are a few obvious issues;
1. that there's no need to raise an expesnive exception if call to gateway isn't success == true
2. separation of concerns - the gateway client code and database access code are mixed in the same class
3. we can't unit test, so some dependency injection is required

The tougher issue is the question of what we should do if the database call fails?

Here's the answer.
