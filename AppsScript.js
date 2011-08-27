function myFunction() {
  var spreadSheet = SpreadsheetApp.getActiveSpreadsheet();
  if (spreadSheet.getSheetByName("てすと") == null) {
    spreadSheet.insertSheet("てすと");
  }
  var sheet = spreadSheet.getSheetByName("てすと");
  sheet.clear();
  sheet.appendRow([1, 2, 3]);
}