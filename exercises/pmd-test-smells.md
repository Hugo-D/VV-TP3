# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

The test smells described in the documentation folder are for testability and readability of the code.

We used the first rule to detect a locally-used or never used test. [DetachedTestCase](../pmd-documentation/DetachedTestCase.md) applied on the _ detected several test smells.
```shell
run.sh pmd -d commons-math/ -f text -R category/java/errorprone.xml/DetachedTestCase | less
```
The first test smell detected is `commons-math/src/test/java/org/apache/commons/math4/linear/SingularValueDecompositionTest.java:172:   DetachedTestCase:       Probable detached JUnit test case.` refering to the [SingularValueDecompositionTest - testMatricesValues1](https://github.com/apache/commons-math/blob/0300f97d74fa0207570b5ea3ecadbf9896d3ecc4/src/test/java/org/apache/commons/math4/linear/SingularValueDecompositionTest.java#L172) test from **Apache Commons Math**.
As the comments above the actual function induce, this test is "useless" and not needed. So a possible fix to that would be to replace the head of the function signature from 
```java
public void testMatricesValues1() {
  // . . .
}
```
to
```java
@Ignore
@Test
public void testMatricesValues1() {
  // . . .
}
```
to ignore the test case.
