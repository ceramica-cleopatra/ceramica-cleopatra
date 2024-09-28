import 'package:query_app/model/sehy_store_list.dart';
import 'package:query_app/model/seyh_item.dart';

class SehyGovern {
  SehyGovern(
      {required this.productName,
      required this.storeList,
      required this.distinctItems});

  factory SehyGovern.fromJson(json) {
    final Iterable l = json['storeList'];
    List<SehyStoreList> storeList = List<SehyStoreList>.from(
        l.map((model) => SehyStoreList.fromJson(model)));
    final Iterable k = json['distinctItems'];
    print(storeList);
    List<SehyItem> distinctItemList =
        List<SehyItem>.from(k.map((model) => SehyItem.fromJson(model)));
    String productName = json['productName'] as String;
    print(distinctItemList);
    return SehyGovern(
        productName: productName,
        storeList: storeList,
        distinctItems: distinctItemList);
  }
  String productName;
  List<SehyStoreList> storeList;
  List<SehyItem> distinctItems;

  @override
  String toString() {
    return '{"productName":"$productName","storeList": $storeList,"distinctItems": $distinctItems}';
  }
}
