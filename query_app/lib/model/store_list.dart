import 'crmk_item.dart';

class StoreList {

  StoreList({required this.storeName, required this.totalFree, required this.itemDteailList});

  factory StoreList.fromJson(json) {
    final Iterable l = json['itemDteailList'];
    List<CrmkItem> itemList =
        List<CrmkItem>.from(l.map((model) => CrmkItem.fromJson(model)));
    return StoreList(
        storeName: json['storeName'] as String,
        totalFree: json['totalFree'] as String,
        itemDteailList: itemList);
  }
  String storeName;
  String totalFree;
  List<CrmkItem> itemDteailList;

  @override
  String toString() {
    return '{"storeName": "$storeName", "totalFree": "$totalFree","itemDteailList": $itemDteailList}';
  }
}
