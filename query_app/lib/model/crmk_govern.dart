import 'crmk_item.dart';
import 'store_list.dart';

class CrmkGovern {
  CrmkGovern(
      {required this.govName,
      required this.totalFree,
      required this.totalReal,
      required this.totalRsrv,
      required this.price,
      required this.priceType,
      required this.productName,
      required this.packageSize,
      required this.storeList,
      required this.distinctItems,
      required this.pList,
      required this.dList});

  factory CrmkGovern.fromJson(json) {
    final Iterable l = json['storeList'];
    List<StoreList> storeList =
        List<StoreList>.from(l.map((model) => StoreList.fromJson(model)));

    final Iterable k = json['distinctItems'];
    List<CrmkItem> distinctItemList =
        List<CrmkItem>.from(k.map((model) => CrmkItem.fromJson(model)));
    print('distinctItemList ' + distinctItemList.toString());
    return CrmkGovern(
      govName: json['govName'] as String,
      totalFree: json['totalFree'] as String,
      totalReal: json['totalReal'] as String,
      totalRsrv: json['totalRsrv'] as String,
      price: json['price'] as String,
      priceType: json['priceType'] as String,
      productName: json['productName'] as String,
      packageSize: json['packageSize'] as String,
      storeList: storeList,
      distinctItems: distinctItemList,
      pList: json['pList'] as String,
      dList: json['dList'] as String,
    );
  }
  String govName;
  String totalFree;
  String totalReal;
  String totalRsrv;
  String price;
  String priceType;
  String productName;
  String packageSize;
  List<StoreList> storeList;
  List<CrmkItem> distinctItems;
  String pList;
  String dList;

  @override
  String toString() {
    print(
        '{"govName": "$govName", "totalFree": "$totalFree","totalReal": "$totalReal","totalRsrv": "$totalRsrv","price": "$price","priceType": "$priceType","productName": "$productName","packageSize": "$packageSize","storeList": $storeList,"distinctItems": $distinctItems}');
    return '{"govName": "$govName", "totalFree": "$totalFree","totalReal": "$totalReal","totalRsrv": "$totalRsrv","price": "$price","priceType": "$priceType","productName": "$productName","packageSize": "$packageSize","storeList": $storeList,"distinctItems": $distinctItems,"pList": "$pList","dList": "$dList"}';
  }
}
