# airmilesshops-automation

## Technical Considerations

I am aware of some decisions I've made in the code that may not be optimal, so I want to clarify my thought process:

- **Test Runner Class**  
  I have limited knowledge regarding best practices, and it seems odd to execute the test cases in this manner. However, since this is an exercise and it works, I will leave it for a later "iteration."

- **Utils Class for Properties File**  
  I've created a `Utils` class to read a properties file with only one property. I anticipate that this will grow larger. I left it as is with the intention of making it as configurable as possible.

- **Cached Exceptions**  
  Ideally, I should use a logger instead of printing the error trace to the console. I'm not sure if it's worth implementing for an exercise.

- **Cucumber and Page Object Model**  
  I've never used Cucumber and Selenium in the same project before. I attempted to implement the Page Object Pattern, but it seemed unnecessary as Cucumber makes it easy to describe business logic. I created the `Pages` classes to separate some specific selectors and functions useful in the page context.

- **Including the Report**  
  In a real project, this would not be good practice, but I included it as proof that it worked on my local machine.

## Business Assumptions

- **Scenario Compatibility**  
  I assumed both scenarios could be compatible within the same feature. They seem to be part of the same navigation feature, but this could be due to my lack of business understanding.

- **Adherence to Step Definitions**  
  I tried to adhere as closely as possible to the step definitions as they were written. I had to change some wording to make sense in the Given-When-Then context. Additionally, I changed some "clicks on" actions to "hovers on" because, in a manual test, it made no sense to do that. Therefore, I've changed it to make it more from a "user point of view."