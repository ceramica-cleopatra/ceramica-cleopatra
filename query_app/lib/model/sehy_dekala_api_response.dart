import 'dart:convert';

import 'package:query_app/model/sehy_dekalas.dart';

import 'status.dart';

class SehyDekalaApiResponse {
  SehyDekalaApiResponse({required this.status, required this.payload});

  factory SehyDekalaApiResponse.fromJson(Map<String, dynamic> json) =>
      SehyDekalaApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<SehyDekalas>.from(
              json['payload'].map((x) => SehyDekalas.fromJson(x))));
  Status status;
  List<Object> payload;

  static SehyDekalaApiResponse apiResponseFromJson(String str) =>
      SehyDekalaApiResponse.fromJson(json.decode(str));
}
