import 'dart:convert';

import 'crmk_dekalas.dart';
import 'status.dart';

class DekalaApiResponse {

  DekalaApiResponse({ required this.status, required this.payload});

  factory DekalaApiResponse.fromJson(Map<String, dynamic> json) =>
      DekalaApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkDekalas>.from(
              json['payload'].map((x) => CrmkDekalas.fromJson(x))));
  Status status;
  List<Object> payload;

  static DekalaApiResponse apiResponseFromJson(String str) =>
      DekalaApiResponse.fromJson(json.decode(str));
}
