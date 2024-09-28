class GroupDetailsResult {
  GroupDetailsResult(
      {required this.crmkDcre,
      required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.factoryNo,
      required this.dekalaId,
      required this.dekalaName,
      required this.colorId,
      required this.colorName,
      required this.tablow,
      required this.free1,
      required this.free2});

  factory GroupDetailsResult.fromJson(json) {
    return GroupDetailsResult(
        crmkDcre: json['crmkDcre'] as String,
        typeId: json['typeId'] as String,
        typeName: json['typeName'] as String,
        sizeId: json['sizeId'] as String,
        sizeName: json['sizeName'] as String,
        factoryNo: json['factoryNo'] as String,
        dekalaId: json['dekalaId'] as String,
        dekalaName: json['dekalaName'] as String,
        colorId: json['colorId'] as String,
        colorName: json['colorName'] as String,
        tablow: json['tablow'] as String,
        free1: json['free1'] as String,
        free2: json['free2'] as String);
  }

  String crmkDcre;
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String factoryNo;
  String dekalaId;
  String dekalaName;
  String colorId;
  String colorName;
  String tablow;
  String free1;
  String free2;

  @override
  String toString() {
    return '{"crmkDcre": "$crmkDcre","typeId": "$typeId", "typeName": "$typeName","sizeId": "$sizeId","sizeName": "$sizeName","factoryNo": "$factoryNo","dekalaId": "$dekalaId","dekalaName": "$dekalaName","colorId": "$colorId","colorName": "$colorName","tablow": "$tablow","free1": "$free1","free2": "$free2"}';
  }
}
