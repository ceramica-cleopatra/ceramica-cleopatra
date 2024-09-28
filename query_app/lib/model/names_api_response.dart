import 'dart:convert';

import 'package:query_app/model/sehy_names.dart';

import 'status.dart';

class NamesApiResponse {
  NamesApiResponse({required this.status, required this.payload});

  factory NamesApiResponse.fromJson(Map<String, dynamic> json) =>
      NamesApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<SehyNames>.from(
              json['payload'].map((x) => SehyNames.fromJson(x))));
  Status status;
  List<Object> payload;

  static NamesApiResponse apiResponseFromJson(String str) =>
      NamesApiResponse.fromJson(json.decode(str));
}
