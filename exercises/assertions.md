# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

The problem comes from the fact that 0.4 is not easily represented binarily, indeed it is an infinite periodical binary sequence. Thus coded on 64 bits (as a double), multiplied by 3, it is approximated to a close value of 0.4. But it can be fixed when we limit the encoding as a float (32-bits). Then the result is as the expected 1.2 result.
```java
assertTrue( (float)(3 * .4) == 1.2)
```
The 32-bits encoding is less precise than the double, 64-bits encoding, thus it limits the low digits errors seen with 0.4 and which exist with 0.1 as well for example.


2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

`assertEquals` compares the values of primitive variables, or if it's a complex variable, it invokes the `equals()` method of the object to be used as a mean to compare the 2 variables.
On the other hand, `assertSame` compares the references of the objects, equivalent to an `assertTrue( _ == _)`.
Scenarios:
* Identical behavior
  ```java
  int a = 1;
  int b = 1;

  assertEquals(a, b); // PASS
  assertSame(a, b); // PASS
  ```
* Different behavior
  ```java
  Integer a = new Integer(1);
  Integer b = new Integer(1);

  assertEquals(a, b); // PASS
  assertSame(a, b); // FAIL
  ```
3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?
