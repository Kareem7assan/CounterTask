This project is a tweet Counter in multi module structure and clean architecture :
**App Module**: This is the core part which contains MVVM with state flow
  there is also unit test for (TweetViewModel) to validate it's flow you can find it inside class (TweetViewModelTest) in this module.

**domain**: this module including usecase which has most of business logic
  there is also unit test for TweetUseCase and i handled most of edge cases to verify the business logic inside (TweetUseCaseTest)

**di**: using dagger-hilt as dependency injection module 

**data**: Thats the base layer for app including repos,models and networking interfaces.


