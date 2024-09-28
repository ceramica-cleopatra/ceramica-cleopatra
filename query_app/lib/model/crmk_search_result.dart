class CrmkSearchResult {
  CrmkSearchResult(
      {required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.factoryNo,
      required this.dekalaId,
      required this.dekalaName,
      required this.frz});

  factory CrmkSearchResult.fromJson(json) {
    return CrmkSearchResult(
        typeId: json['typeId'] as String,
        typeName: json['typeName'] as String,
        sizeId: json['sizeId'] as String,
        sizeName: json['sizeName'] as String,
        factoryNo: json['factoryNo'] as String,
        dekalaId: json['dekalaId'] as String,
        dekalaName: json['dekalaName'] as String,
        frz: json['frz'] as String);
  }
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String factoryNo;
  String dekalaId;
  String dekalaName;
  String frz;

  @override
  String toString() {
    return '{"typeId": "$typeId", "typeName": "$typeName","sizeId": "$sizeId","sizeName": "$sizeName","factoryNo": "$factoryNo","dekalaId": "$dekalaId","dekalaName": "$dekalaName","frz": "$frz"}';
  }
}
