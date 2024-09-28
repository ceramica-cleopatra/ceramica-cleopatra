import 'dart:convert';

import 'package:query_app/model/crmk_plist.dart';

import 'status.dart';

class CrmkPlistApiResponse {
  CrmkPlistApiResponse({required this.status, required this.payload});

  factory CrmkPlistApiResponse.fromJson(Map<String, dynamic> json) =>
      CrmkPlistApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkPlist>.from(
              json['payload'].map((x) => CrmkPlist.fromJson(x))));
  Status status;
  List<Object> payload;

  static CrmkPlistApiResponse apiResponseFromJson(String str) =>
      CrmkPlistApiResponse.fromJson(json.decode(str));
}
