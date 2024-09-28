import 'dart:convert';

import 'crmk_govern.dart';
import 'status.dart';

class CrmkDetailApiResponse {

  CrmkDetailApiResponse({ required this.status, required this.payload});

  factory CrmkDetailApiResponse.fromJson(Map<String, dynamic> json) =>
      CrmkDetailApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkGovern>.from(
              json['payload'].map((x) => CrmkGovern.fromJson(x))));
  Status status;
  List<Object> payload;

  static CrmkDetailApiResponse apiResponseFromJson(String str) =>
      CrmkDetailApiResponse.fromJson(json.decode(str));
}
