import 'package:flutter/material.dart';

class Cart {
  late final int? id;
  final String? productId;
  final String? productName;
  final double? initialPrice;
  final double? discount;
  final ValueNotifier<double>? productPrice;
  final ValueNotifier<double>? quantity;
  final double? package;
  final int? priceList;
  final int? discountList;
  final String? crmkSehy;
  final int? customerId;

  Cart(
      {this.id,
      required this.productId,
      required this.productName,
      required this.initialPrice,
      required this.discount,
      required this.productPrice,
      required this.quantity,
      required this.package,
      required this.priceList,
      required this.discountList,
      required this.crmkSehy,
      required this.customerId});

  Cart.fromMap(Map<dynamic, dynamic> data)
      : id = data['id'],
        productId = data['productId'],
        productName = data['productName'],
        initialPrice = data['initialPrice'],
        discount = data['discount'],
        productPrice = ValueNotifier(data['productPrice']),
        quantity = ValueNotifier(data['quantity']),
        package = data['package'],
        priceList = data['priceList'],
        discountList = data['discountList'],
        crmkSehy = data['crmkSehy'],
        customerId = data['customerId'];

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'productId': productId,
      'productName': productName,
      'initialPrice': initialPrice,
      'discount': discount,
      'productPrice': productPrice?.value,
      'quantity': quantity?.value,
      'package': package,
      'priceList': priceList,
      'discountList': discountList,
      'crmkSehy': crmkSehy,
      'customerId': customerId,
    };
  }

  Map<String, dynamic> quantityMap() {
    return {
      'productId': productId,
      'quantity': quantity?.value ?? 0,
      'productPrice': productPrice?.value ?? 0
    };
  }
}
