import 'dart:convert';

import 'package:query_app/model/sehy_govern.dart';

import 'status.dart';

class SehyTakmDetailApiResponse {
  SehyTakmDetailApiResponse({required this.status, required this.payload});

  factory SehyTakmDetailApiResponse.fromJson(Map<String, dynamic> json) =>
      SehyTakmDetailApiResponse(
          status: Status.fromJson(json['status']),
          payload: SehyGovern.fromJson(json['payload']));

  Status status;
  SehyGovern payload;

  static SehyTakmDetailApiResponse apiResponseFromJson(String str) =>
      SehyTakmDetailApiResponse.fromJson(json.decode(str));
}
