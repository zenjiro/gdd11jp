function myFunction() {
  var spreadSheet = SpreadsheetApp.getActiveSpreadsheet();
  if (spreadSheet.getSheetByName("てすと") == null) {
    spreadSheet.insertSheet("てすと");
  }
  var sheet = spreadSheet.getSheetByName("てすと");
  sheet.clear();
 
  var object = Utilities.jsonParse(UrlFetchApp.fetch("gdd-2011-quiz-japan.appspot.com/apps_script/sample").getContentText());
  for (var i in object) {
    var city = object[i];
    sheet.appendRow([city]);
  }
}