class CrmkItem {

  CrmkItem({
    required this.itemId,
    required this.c,
    required this.tone,
    required this.freeQty,
    required this.realQty,
    required this.rsrvQty,
  });

  factory CrmkItem.fromJson(json) {
    return CrmkItem(
        itemId: json['itemId'] as int,
        c: json['c'] as String,
        tone: json['tone'] as String,
        freeQty: json['freeQty'] as String,
        realQty: json['realQty'] as String,
        rsrvQty: json['rsrvQty'] as String);
  }
  int itemId;
  String c;
  String tone;
  String freeQty;
  String realQty;
  String rsrvQty;

  @override
  String toString() {
    return '{"itemId": $itemId, "c": "$c","tone": "$tone","freeQty": "$freeQty","realQty": "$realQty","rsrvQty": "$rsrvQty"}';
  }
}
