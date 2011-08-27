function myFunction() {
  var spreadSheet = SpreadsheetApp.getActiveSpreadsheet();
  var object = Utilities.jsonParse(UrlFetchApp.fetch("gdd-2011-quiz-japan.appspot.com/apps_script/sample").getContentText());
  for (var i in object) {
    var city = object[i];
    var city_name = city.city_name;
    if (spreadSheet.getSheetByName(city_name) == null) {
      spreadSheet.insertSheet(city_name);
    }
    var sheet = spreadSheet.getSheetByName(city_name);
    sheet.clear();
    for (var j in city.data) {
      var record = city.data[j];
      sheet.appendRow([record.capacity, record.usage]);
    }
  }
}