# Spiice
It's notes app with authorization into local DB.<br />

![spiice gen](https://github.com/user-attachments/assets/fc7d17b2-e8d6-46cf-9321-963ff87dc85a)

## Technology stack used in development:<br />
* language - Kotlin<br />
* Single activity app<br />
* UI - XML <br />
* Architecture - Clean Architecture, UI layer - MVVM<br />
* View Binding <br />
* Navigation - Fragment manager,  Bottom navigation<br />
* Dependency injection - Dagger2<br />
* Asynchrony - Kotlin Сoroutines<br />
* DataBase - Room with Migration<br />
* SharedPreferences<br />
* Recycle View, ListAdapter with 2 ViewHolders<br />
* DiffUtil<br />
* SearchView<br />
* ViewPager2<br />
* CircleIndicator<br />
* DataPicker<br />
* Span<br />

## 1. Navigation

Navigation is implemented by the Fragment Manager and has such flow:
Onboarding screen appears on first launch -> Screen with View Pager -> SignUp/LogIn screen and then:
Transition to the Home screen with Bottom Navigation and four tabs: Home, Search, Add Note, Profile.

## 2. SignUp/LogIn 

Provides with saving in the database and validation.

![Spiice2](https://github.com/user-attachments/assets/2d245f2d-ad1e-4ea2-88eb-5eeb1c3bc0a4)

## 3. Home screen 

Presented by RecycleView with DiffUtil to display the list of notes.

## 4. Search
Screen for searching notes in the database.

![Spiice5](https://github.com/user-attachments/assets/abfeb32f-8c59-49c6-9da2-1d5276f36de3)

## 5. Add Note

Screen for creating notes with the option to choose between a Scheduled note or a Simple note, for scheduled ones a DatePicker is used.

![Spiice7](https://github.com/user-attachments/assets/737a063f-061d-4e9d-8488-8ab338a8f5f5)

## 6. Profile 

Screen with the ability to delete all notes, delete profile and logout.

![Spiice6](https://github.com/user-attachments/assets/ea0ab8d9-0f1e-45bb-800c-07fa35e1311d)



