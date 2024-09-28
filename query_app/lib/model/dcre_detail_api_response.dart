import 'dart:convert';

import 'crmk_govern.dart';
import 'status.dart';

class DcreDetailApiResponse {
  DcreDetailApiResponse({required this.status, required this.payload});

  factory DcreDetailApiResponse.fromJson(Map<String, dynamic> json) =>
      DcreDetailApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkGovern>.from(
              json['payload'].map((x) => CrmkGovern.fromJson(x))));
  Status status;
  List<Object> payload;

  static DcreDetailApiResponse apiResponseFromJson(String str) =>
      DcreDetailApiResponse.fromJson(json.decode(str));
}
