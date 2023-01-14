# Electronic Store Application
## Built using Java, JavaFX, OOP and MVC Programming Paradigms

In order for this project to run, ensure you have the JavaFX library installed, and include the path in your build.

This can be done using the following:

### Installing JavaFX:
1. You must have Java installed first 
2. Download the JavaFX SDK version 17.0.2 (the one that says SDK, not mods) for your operating system from https://gluonhq.com/products/javafx/. If you aren't sure what architecture to select, x64 is the most likely one.
3. The downloaded file should be a .zip file. Extract its contents to a location on your hard-drive. Remember where you put it. A good suggestion to make the later steps easier it to place the JavaFX folder somewhere near the root of your hard drive (e.g., C:\javafx-sdk-17.0.2 on Windows or /Users/yourname/javafx-sdk-17.0.2 on Mac). Do not include any spaces in the folder names.


### Setting up an IntelliJ project to use JavaFX:
1. Create a new Java project within IntelliJ, as you normally would do (i.e., don't select JavaFX project from the project type list).
2. Go to File -> Project Structure -> Libraries. Click the + sign to add a new library and select Java. You will be able to select a folder from the menu that appears. Select the 'lib' folder within the JavaFX folder (i.e., the folder you extracted in the steps above. Click OK to confirm the addition of the library and then click Apply. Close the Project Structure menu.
3. Run the BlankWindowGUI class. It will crash with the error "Error: JavaFX runtime components are missing, and are required to run this application".
4. Go to Run -> Edit Configurations. Select the 'Modify options' dropdown and then select 'Add VM options'. A text box to enter VM options will then appear.
5. Within the VM options text box, enter the following (remember to update the /path/to/ part): --module-path /path/to/javafx-sdk-17.0.2/lib --add-modules javafx.controls,javafx.fxml
6. Click Apply and close the Run Configuration menu.

From there, you should open the code in the source folder in whatever IDE you choose (IntelliJ) and open the `ElectronicStoreApp.java` file to see the application in action.

