class DcreSearchResult {
  DcreSearchResult(
      {required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.factoryNo,
      required this.dekalaId,
      required this.dekalaName,
      required this.frz,
      required this.colorId,
      required this.colorName,
      required this.tablow});

  factory DcreSearchResult.fromJson(json) {
    return DcreSearchResult(
        typeId: json['typeId'] as String,
        typeName: json['typeName'] as String,
        sizeId: json['sizeId'] as String,
        sizeName: json['sizeName'] as String,
        factoryNo: json['factoryNo'] as String,
        dekalaId: json['dekalaId'] as String,
        dekalaName: json['dekalaName'] as String,
        frz: json['frz'] as String,
        colorId: json['colorId'] as String,
        colorName: json['colorName'] as String,
        tablow: json['tablow'] as String);
  }
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String factoryNo;
  String dekalaId;
  String dekalaName;
  String frz;
  String colorId;
  String colorName;
  String tablow;

  @override
  String toString() {
    return '{"typeId": "$typeId", "typeName": "$typeName","sizeId": "$sizeId","sizeName": "$sizeName","factoryNo": "$factoryNo","dekalaId": "$dekalaId","dekalaName": "$dekalaName","frz": "$frz","colorId": "$colorId","colorName": "$colorName","tablow": "$tablow"}';
  }
}
