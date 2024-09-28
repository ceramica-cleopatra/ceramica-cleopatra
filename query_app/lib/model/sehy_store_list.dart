import 'package:query_app/model/seyh_item.dart';

class SehyStoreList {
  SehyStoreList(
      {required this.storeId,
      required this.storeName,
      required this.governName,
      required this.totalFree,
      required this.itemDteailList});

  factory SehyStoreList.fromJson(json) {
    final Iterable l = json['itemDteailList'];
    List<SehyItem> itemList =
        List<SehyItem>.from(l.map((model) => SehyItem.fromJson(model)));
    return SehyStoreList(
        storeId: json['storeId'] as String,
        storeName: json['storeName'] as String,
        governName: json['governName'] as String,
        totalFree: json['totalFree'] as String,
        itemDteailList: itemList);
  }

  String storeId;
  String storeName;
  String governName;
  String totalFree;
  List<SehyItem> itemDteailList;

  @override
  String toString() {
    return '{"storeId": "$storeId","storeName": "$storeName","governName": "$governName","totalFree": "$totalFree","itemDteailList": $itemDteailList}';
  }
}
