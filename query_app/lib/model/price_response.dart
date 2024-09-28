import 'dart:convert';

import 'package:query_app/model/price.dart';
import 'package:query_app/model/status.dart';

class PriceResponse {
  PriceResponse({required this.status, required this.payload});

  factory PriceResponse.fromJson(Map<String, dynamic> json) => PriceResponse(
      status: Status.fromJson(json['status']),
      payload: Price.fromJson(json['payload']));
  Status status;
  Object payload;

  static PriceResponse apiResponseFromJson(String str) =>
      PriceResponse.fromJson(json.decode(str));
}
