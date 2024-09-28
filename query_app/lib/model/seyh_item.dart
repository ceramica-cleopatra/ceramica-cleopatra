import 'dart:convert';

class SehyItem {
  SehyItem(
      {required this.itemId,
      required this.itemName,
      required this.freeQty,
      required this.realQty,
      required this.rsrvQty,
      required this.price});

  factory SehyItem.fromJson(json) {
    SehyItem sehyItem = SehyItem(
        itemId: json['itemId'] as int,
        itemName: json['itemName'] as String,
        freeQty: json['freeQty'] as String,
        realQty: json['realQty'] as String,
        rsrvQty: json['rsrvQty'] as String,
        price: json['price'] as String);
    return sehyItem;
  }

  int itemId;
  String itemName;
  String freeQty;
  String realQty;
  String rsrvQty;
  String price;

  @override
  String toString() {
    return '{"itemId": $itemId, "itemName": "$itemName","freeQty": "$freeQty","realQty": "$realQty","rsrvQty": "$rsrvQty","price": "$price"}';
  }
}
