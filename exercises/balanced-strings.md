# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `null`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. We coded generators responsible for creating valide strings, one generator generates a sequence of ASCII characters and grouping symbols to match the requirements, another generator only generates grouping symbols. We have one last generator responsible for generating false strings, which don't match the requirements.

2. We added the JaCoCo plugin to create a coverage report and we reach a 100% coverage with our tests following the report's figures.
    ```xml
    <!-- Ajout de JaCoCo -->
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.6</version>
        <executions>
            <execution>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <!--  attached to Maven test phase  -->
            <execution>
                <id>report</id>
                <phase>prepare-package</phase>
                <goals>
                    <goal>report</goal>
                </goals>
                <configuration>
                    <outputDirectory>target/jacoco-report</outputDirectory>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```

3. We ran over all the code and created simple test cases for every single condition we met in the source code. We observed that without the implementation of those simple tests, way simpler than the generated random valide and false strings, we would have missed an error in our implementation of the solution.

4.
    ```java
    ================================================================================
    - Mutators
    ================================================================================
    > org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator
    >> Generated 3 Killed 3 (100%)
    > KILLED 3 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator
    >> Generated 2 Killed 2 (100%)
    > KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator
    >> Generated 2 Killed 2 (100%)
    > KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
    >> Generated 1 Killed 1 (100%)
    > KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.MathMutator
    >> Generated 2 Killed 2 (100%)
    > KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator
    >> Generated 4 Killed 4 (100%)
    > KILLED 4 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
    >> Generated 8 Killed 8 (100%)
    > KILLED 8 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    > org.pitest.mutationtest.engine.gregor.mutators.PrimitiveReturnsMutator
    >> Generated 2 Killed 2 (100%)
    > KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
    > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
    > NO_COVERAGE 0 
    --------------------------------------------------------------------------------
    ================================================================================
    - Timings
    ================================================================================
    > scan classpath : < 1 second
    > coverage and dependency analysis : 1 seconds
    > build mutation tests : < 1 second
    > run mutation analysis : 1 seconds
    --------------------------------------------------------------------------------
    > Total  : 2 seconds
    --------------------------------------------------------------------------------
    ================================================================================
    - Statistics
    ================================================================================
    >> Generated 24 mutations Killed 24 (100%)
    >> Ran 34 tests (1.42 tests per mutation)
    ```
    We observe that every single mutant was killed, thus the tests handle the mutations generated by PIT, which can reassure us about the strength and coverage of our tests. However it doesn't mean that our test panel is complete nor wide enough, thus it doesn't imply that our solution is flawless.
