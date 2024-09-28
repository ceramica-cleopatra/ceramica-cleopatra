class SehySearchResult {
  SehySearchResult(
      {required this.typeId,
      required this.typeName,
      required this.nameId,
      required this.name,
      required this.dekalaId,
      required this.dekalaName,
      required this.colorId,
      required this.colorName,
      required this.frz,
      required this.takmId});

  factory SehySearchResult.fromJson(json) {
    return SehySearchResult(
        typeId: json['typeId'] as String,
        typeName: json['typeName'] as String,
        nameId: json['nameId'] as String,
        name: json['name'] as String,
        dekalaId: json['dekalaId'] as String,
        dekalaName: json['dekalaName'] as String,
        colorId: json['colorId'] as String,
        colorName: json['colorName'] as String,
        frz: json['frz'] as String,
        takmId: json['takmId'] as String);
  }
  String typeId;
  String typeName;
  String nameId;
  String name;
  String dekalaId;
  String dekalaName;
  String colorId;
  String colorName;
  String frz;
  String takmId;

  @override
  String toString() {
    return '{"typeId": "$typeId", "typeName": "$typeName","nameId": "$nameId","name": "$name","dekalaId": "$dekalaId","dekalaName": "$dekalaName","colorId": "$colorId","colorName": "$colorName","frz": "$frz","takmId": "$takmId"}';
  }
}
