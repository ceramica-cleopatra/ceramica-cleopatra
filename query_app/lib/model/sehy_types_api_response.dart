import 'dart:convert';

import 'package:query_app/model/sehy_types.dart';

import 'status.dart';

class SehyTypesApiResponse {
  SehyTypesApiResponse({required this.status, required this.payload});

  factory SehyTypesApiResponse.fromJson(Map<String, dynamic> json) =>
      SehyTypesApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<SehyTypes>.from(
              json['payload'].map((x) => SehyTypes.fromJson(x))));
  Status status;
  List<Object> payload;

  static SehyTypesApiResponse apiResponseFromJson(String str) =>
      SehyTypesApiResponse.fromJson(json.decode(str));
}
