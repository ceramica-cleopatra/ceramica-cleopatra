import 'package:flutter/material.dart';
import 'package:query_app/model/cart_item.dart';

import 'cart_item.dart';

class Customer {
  late final int? id;
  final String? customerName;
  final String? phoneNumber;
  final List<Cart>? crmk;
  final List<Cart>? dcre;
  final List<Cart>? sehy;

  Customer(
      {required this.id,
      required this.customerName,
      required this.phoneNumber,
      required this.crmk,
      required this.dcre,
      required this.sehy});

  Customer.fromMap(Map<dynamic, dynamic> data)
      : id = data['id'],
        customerName = data['customerName'],
        phoneNumber = data['phoneNumber'],
        crmk = data['crmk'],
        dcre = data['dcre'],
        sehy = data['sehy'];

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'customerName': customerName,
      'phoneNumber': phoneNumber,
      'crmk': crmk,
      'dcre': dcre,
      'sehy': sehy
    };
  }
}
