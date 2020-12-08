# custom-test-import

This repository contains a tool to import custom tests from [aud-custom-tests](https://github.com/stormofice/aud-custom-tests) without any effort.

---
## Configuration
* Configuration
  * BASE_FOLDER
    * Folder in which the tests should be imported into
  * IMPORT_TYPE
    * **BASE_FOLDER**: Downloads the file to the select base folder
    * **SEARCH_PUBLIC_TEST**: Searches the file tree (starting from base folder) and puts the test in the same directory as the public test
    * **SEARCH_MAIN_FILE**: Searches the file tree (starting from base folder) and puts the test in the same directory as the class file
