function myFunction() {
  var spreadSheet = SpreadsheetApp.getActiveSpreadsheet();
  if (spreadSheet.getSheetByName("てすと") == null) {
    spreadSheet.insertSheet("てすと");
  }
  var sheet = spreadSheet.getSheetByName("てすと");
  sheet.clear();
 
  var response = UrlFetchApp.fetch("gdd-2011-quiz-japan.appspot.com/apps_script/sample").getContentText();
  var object = Utilities.jsonParse(response);
  sheet.appendRow(object);
}